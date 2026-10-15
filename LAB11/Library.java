import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    // LinkedHashMap keeps insertion order, which makes listings predictable
    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final Map<String, Loan> loans = new LinkedHashMap<>();   // key = itemId (one loan per item)

    private int clockOffsetDays = 0;      // lets us simulate time passing to test overdue items

    // ---------- clock ----------
    public LocalDate today() { return LocalDate.now().plusDays(clockOffsetDays); }
    public void advanceClock(int days) { clockOffsetDays += days; }

    // ---------- items ----------
    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item id already exists: " + item.getId());
        }
        items.put(item.getId(), item);
    }

    public String nextItemId(String prefix) {
        int max = 0;
        for (String id : items.keySet()) {
            if (id.startsWith(prefix)) {
                try { max = Math.max(max, Integer.parseInt(id.substring(prefix.length()))); }
                catch (NumberFormatException ignored) { }
            }
        }
        return String.format("%s%03d", prefix, max + 1);
    }

    public LibraryItem findItem(String id) throws NotFoundException {
        LibraryItem item = items.get(id);
        if (item == null) throw new NotFoundException("Item", id);
        return item;
    }

    public Collection<LibraryItem> allItems() { return Collections.unmodifiableCollection(items.values()); }

    // ---------- members ----------
    public void registerMember(Member m) {
        if (members.containsKey(m.getId())) {
            throw new IllegalArgumentException("Member id already exists: " + m.getId());
        }
        members.put(m.getId(), m);
    }

    public String nextMemberId() {
        int max = 0;
        for (String id : members.keySet()) {
            try { max = Math.max(max, Integer.parseInt(id.substring(1))); }
            catch (NumberFormatException | StringIndexOutOfBoundsException ignored) { }
        }
        return String.format("M%03d", max + 1);
    }

    public Member findMember(String id) throws NotFoundException {
        Member m = members.get(id);
        if (m == null) throw new NotFoundException("Member", id);
        return m;
    }

    public Collection<Member> allMembers() { return Collections.unmodifiableCollection(members.values()); }

    // ---------- loans ----------
    public Loan borrow(String memberId, String itemId) throws LibraryException {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        // Order matters: validate everything first, then change state, so a failure leaves nothing half-done
        if (!item.isAvailable()) throw new ItemNotAvailableException(item.getTitle());
        member.loanAdded();                       // may throw LoanLimitExceededException
        try {
            item.borrow();
        } catch (ItemNotAvailableException e) {   // should not happen, but undo if it does
            member.loanRemoved();
            throw e;
        }
        Loan loan = new Loan(itemId, memberId, today(), today().plusDays(item.getLoanDays()));
        loans.put(itemId, loan);
        return loan;
    }

    // Returns the loan that was closed (so the caller can report lateness)
    public Loan returnItem(String itemId) throws LibraryException {
        LibraryItem item = findItem(itemId);
        Loan loan = loans.remove(itemId);
        if (loan == null) throw new LibraryException("'" + item.getTitle() + "' is not currently on loan.");
        item.giveBack();
        findMember(loan.memberId()).loanRemoved();
        return loan;
    }

    public Collection<Loan> allLoans() { return Collections.unmodifiableCollection(loans.values()); }

    public List<Loan> overdueLoans() {
        return loans.values().stream()
                .filter(l -> l.isOverdue(today()))
                .sorted(Comparator.comparing(Loan::dueOn))
                .collect(Collectors.toList());
    }

    // ---------- search (streams) ----------
    public List<LibraryItem> searchByTitle(String text) {
        String needle = text.toLowerCase().trim();
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(needle))
                .sorted(Comparator.comparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // Used by LibraryStorage when restoring a saved loan (does not re-check limits or dates)
    void restoreLoan(Loan loan) throws LibraryException {
        LibraryItem item = findItem(loan.itemId());
        Member member = findMember(loan.memberId());
        item.borrow();
        member.loanAdded();
        loans.put(loan.itemId(), loan);
    }
}
