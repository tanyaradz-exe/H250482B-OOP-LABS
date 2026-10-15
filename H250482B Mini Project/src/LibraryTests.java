import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/** Dependency-free tests. Run: java LibraryTests  (exit code 1 on any failure). */
public class LibraryTests {
    static int passed, failed;
    static final LocalDate DAY = LocalDate.of(2026, 1, 1);

    static void check(String name, boolean ok) {
        if (ok) { passed++; System.out.println("PASS  " + name); }
        else    { failed++; System.out.println("FAIL  " + name); }
    }

    static boolean throwsIAE(Runnable r) {
        try { r.run(); return false; } catch (IllegalArgumentException e) { return true; }
    }

    static Library sample() {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java, The Complete Reference", "H. Schildt"));   // comma in title
        lib.addItem(new Book("B4", "Head First Design Patterns", "Freeman"));
        lib.addItem(new Dvd("D1", "The Pragmatic Programmer Talk", 90));
        lib.registerMember(new Member("M1", "Alice"));
        lib.registerMember(new Member("M2", "Bob"));
        return lib;
    }

    public static void main(String[] args) throws Exception {
        // polymorphism
        List<LibraryItem> mixed = List.of(new Book("x", "t", "a"), new Dvd("y", "t", 10));
        check("Book loan period is 21", mixed.get(0).loanPeriodDays() == 21);
        check("Dvd loan period is 7",   mixed.get(1).loanPeriodDays() == 7);

        // validation
        check("blank title rejected", throwsIAE(() -> new Book("1", " ", "a")));
        check("negative DVD minutes rejected", throwsIAE(() -> new Dvd("1", "t", -5)));
        check("blank member name rejected", throwsIAE(() -> new Member("1", "")));
        check("duplicate item id rejected", throwsIAE(() -> { Library l = sample(); l.addItem(new Book("B1", "x", "y")); }));

        // borrow / return
        Library lib = sample();
        Loan loan = lib.borrow("M1", "B1", DAY);
        check("borrow marks item unavailable", !lib.getItem("B1").isAvailable());
        check("due date = +21 days for a book", loan.getDueDate().equals(DAY.plusDays(21)));
        check("member has 1 loan", lib.getMember("M1").getLoanCount() == 1);

        boolean notAvail = false;
        try { lib.borrow("M2", "B1", DAY); } catch (ItemNotAvailableException e) { notAvail = true; }
        check("ItemNotAvailableException for borrowed item", notAvail);

        check("unknown member -> IllegalArgumentException", throwsIAE(() -> {
            try { lib.borrow("NOPE", "B2", DAY); } catch (ItemNotAvailableException | MemberLimitExceededException e) { }
        }));
        check("unknown item -> IllegalArgumentException", throwsIAE(() -> {
            try { lib.borrow("M1", "NOPE", DAY); } catch (ItemNotAvailableException | MemberLimitExceededException e) { }
        }));

        lib.borrow("M1", "B2", DAY);
        lib.borrow("M1", "B3", DAY);
        boolean limit = false;
        try { lib.borrow("M1", "B4", DAY); } catch (MemberLimitExceededException e) { limit = true; }
        check("MemberLimitExceededException on 4th loan", limit);
        check("item stays available after refused 4th loan", lib.getItem("B4").isAvailable());

        lib.giveBack("B1");
        check("return makes item available", lib.getItem("B1").isAvailable());
        check("return frees a loan slot", lib.getMember("M1").canBorrow());
        check("returning an item not on loan fails", throwsIAE(() -> lib.giveBack("B1")));

        // overdue (B2, B3 borrowed on DAY, due DAY+21)
        check("nothing overdue on due date", lib.listOverdue(DAY.plusDays(21)).isEmpty());
        check("2 overdue the day after", lib.listOverdue(DAY.plusDays(22)).size() == 2);

        // streams search
        check("search is case-insensitive", lib.searchByTitle("JAVA").size() == 2);
        check("search results sorted by title",
                lib.searchByTitle("java").get(0).getTitle().equals("Effective Java"));
        check("search with no match is empty", lib.searchByTitle("zzz").isEmpty());

        // persistence round trip
        Path dir = Files.createTempDirectory("librarytest");
        lib.save(dir);
        Library reloaded = new Library();
        reloaded.load(dir);
        check("reload keeps item count", reloaded.allItems().size() == 5);
        check("reload keeps member count", reloaded.allMembers().size() == 2);
        check("reload keeps loans", reloaded.activeLoans().size() == 2);
        check("reload keeps availability", !reloaded.getItem("B2").isAvailable() && reloaded.getItem("B1").isAvailable());
        check("reload keeps comma in title", reloaded.getItem("B3").getTitle().equals("Java, The Complete Reference"));
        check("reload keeps DVD type", reloaded.getItem("D1") instanceof Dvd d && d.getMinutes() == 90);
        check("reloaded member loan count", reloaded.getMember("M1").getLoanCount() == 2);
        check("reload keeps due date", reloaded.listOverdue(DAY.plusDays(22)).size() == 2);

        System.out.println("\n" + passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }
}
