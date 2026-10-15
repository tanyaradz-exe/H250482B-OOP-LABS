import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

/** Core service: owns the collections, enforces the rules, saves/loads CSV. */
public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final List<Loan> loans = new ArrayList<>();   // active loans only

    // ---------- registration ----------
    public void addItem(LibraryItem item) {
        if (items.putIfAbsent(item.getId(), item) != null)
            throw new IllegalArgumentException("Item id already exists: " + item.getId());
    }

    public void registerMember(Member member) {
        if (members.putIfAbsent(member.getId(), member) != null)
            throw new IllegalArgumentException("Member id already exists: " + member.getId());
    }

    // ---------- borrowing ----------
    public Loan borrow(String memberId, String itemId, LocalDate today)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item
        Member member = members.get(memberId);
        if (member == null) throw new IllegalArgumentException("No such member: " + memberId);
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);
        // 2. check the loan limit and availability
        if (!member.canBorrow()) throw new MemberLimitExceededException(memberId, Member.MAX_LOANS);
        item.borrow(member);                       // throws ItemNotAvailableException; polymorphic call
        // 3. create the Loan and record it
        Loan loan = new Loan(item, member, today);
        member.addLoan(loan);
        loans.add(loan);
        return loan;
    }

    public Loan giveBack(String itemId) {
        Loan loan = loans.stream().filter(l -> l.getItem().getId().equals(itemId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item is not on loan: " + itemId));
        loan.getItem().giveBack();
        loan.getMember().removeLoan(loan);
        loans.remove(loan);
        return loan;
    }

    // ---------- queries (streams) ----------
    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    public List<Loan> listOverdue(LocalDate today) {
        return loans.stream()
                .filter(l -> l.isOverdue(today))
                .sorted(Comparator.comparing(Loan::getDueDate))
                .toList();
    }

    public List<LibraryItem> allItems() {
        return items.values().stream().sorted(Comparator.comparing(LibraryItem::getId)).toList();
    }
    public List<Member> allMembers() {
        return members.values().stream().sorted(Comparator.comparing(Member::getId)).toList();
    }
    public List<Loan> activeLoans() { return List.copyOf(loans); }
    public LibraryItem getItem(String id)   { return items.get(id); }
    public Member getMember(String id)      { return members.get(id); }

    // ---------- persistence (Lab 10 techniques) ----------
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);
        Files.write(dir.resolve("items.csv"), allItems().stream()
                .map(i -> Csv.line(i.type(), i.getId(), i.getTitle(), i.extra(), String.valueOf(i.isAvailable()))).toList());
        Files.write(dir.resolve("members.csv"), allMembers().stream()
                .map(m -> Csv.line(m.getId(), m.getName())).toList());
        Files.write(dir.resolve("loans.csv"), loans.stream()
                .map(l -> Csv.line(l.getMember().getId(), l.getItem().getId(),
                        l.getBorrowDate().toString(), l.getDueDate().toString())).toList());
    }

    /** Loads whatever files exist; a missing file just means a first run. Bad lines are skipped with a warning. */
    public void load(Path dir) throws IOException {
        items.clear(); members.clear(); loans.clear();
        for (String line : readLines(dir.resolve("items.csv"))) {
            try {
                List<String> f = Csv.parse(line);
                LibraryItem item = f.get(0).equals("DVD")
                        ? new Dvd(f.get(1), f.get(2), Integer.parseInt(f.get(3)))
                        : new Book(f.get(1), f.get(2), f.get(3));
                item.setAvailable(true);   // availability is rebuilt from loans.csv below
                addItem(item);
            } catch (RuntimeException e) { System.err.println("Skipping bad item line: " + line + " (" + e.getMessage() + ")"); }
        }
        for (String line : readLines(dir.resolve("members.csv"))) {
            try { List<String> f = Csv.parse(line); registerMember(new Member(f.get(0), f.get(1))); }
            catch (RuntimeException e) { System.err.println("Skipping bad member line: " + line + " (" + e.getMessage() + ")"); }
        }
        for (String line : readLines(dir.resolve("loans.csv"))) {
            try {
                List<String> f = Csv.parse(line);
                Member m = members.get(f.get(0));
                LibraryItem i = items.get(f.get(1));
                if (m == null || i == null) throw new IllegalArgumentException("unknown member or item");
                Loan loan = new Loan(i, m, LocalDate.parse(f.get(2)), LocalDate.parse(f.get(3)));
                i.setAvailable(false);
                m.addLoan(loan);
                loans.add(loan);
            } catch (RuntimeException e) { System.err.println("Skipping bad loan line: " + line + " (" + e.getMessage() + ")"); }
        }
    }

    private static List<String> readLines(Path p) throws IOException {
        return Files.exists(p) ? Files.readAllLines(p).stream().filter(s -> !s.isBlank()).toList() : List.of();
    }
}
