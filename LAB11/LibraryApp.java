import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private final Library library;
    private final LibraryStorage storage;
    private final Scanner in = new Scanner(System.in);

    public LibraryApp(Library library, LibraryStorage storage) {
        this.library = library;
        this.storage = storage;
    }

    public static void main(String[] args) throws IOException {
        LibraryStorage storage = new LibraryStorage("data");
        Library library = storage.load();
        System.out.println("Library loaded: " + library.allItems().size() + " items, "
                + library.allMembers().size() + " members, " + library.allLoans().size() + " active loans.");
        new LibraryApp(library, storage).run();
    }

    private void run() throws IOException {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose: ");
            if (choice == null) break;                     // input ended (e.g. piped input)
            try {
                switch (choice.trim()) {
                    case "1" -> addItem();
                    case "2" -> registerMember();
                    case "3" -> borrow();
                    case "4" -> giveBack();
                    case "5" -> listItems();
                    case "6" -> listMembers();
                    case "7" -> listOverdue();
                    case "8" -> search();
                    case "9" -> advanceClock();
                    case "0" -> running = false;
                    default  -> System.out.println("Unknown option.");
                }
            } catch (LibraryException e) {
                System.out.println("!! " + e.getMessage());           // expected business errors
            } catch (IllegalArgumentException e) {
                System.out.println("!! Invalid input: " + e.getMessage());
            }
        }
        storage.save(library);
        System.out.println("Data saved. Goodbye!");
    }

    private void printMenu() {
        System.out.println("\n===== LIBRARY MANAGEMENT (date: " + library.today() + ") =====");
        System.out.println("1 Add item      2 Register member   3 Borrow      4 Return");
        System.out.println("5 List items    6 List members      7 Overdue     8 Search title");
        System.out.println("9 Advance clock (testing)           0 Save and exit");
    }

    private void addItem() {
        String type = readLine("Type (BOOK/DVD): ").trim().toUpperCase();
        String title = clean(readLine("Title: "));
        LibraryItem item;
        if (type.equals("BOOK")) {
            String author = clean(readLine("Author: "));
            String isbn = clean(readLine("ISBN: "));
            item = ItemFactory.create(type, library.nextItemId("B"), title, author, isbn);
        } else if (type.equals("DVD")) {
            String director = clean(readLine("Director: "));
            int mins = Integer.parseInt(readLine("Duration (minutes): ").trim());
            item = ItemFactory.create(type, library.nextItemId("D"), title, director, String.valueOf(mins));
        } else {
            throw new IllegalArgumentException("Type must be BOOK or DVD");
        }
        library.addItem(item);
        System.out.println("Added: " + item);
    }

    private void registerMember() {
        Member m = new Member(library.nextMemberId(), clean(readLine("Member name: ")));
        library.registerMember(m);
        System.out.println("Registered: " + m);
    }

    private void borrow() throws LibraryException {
        String memberId = readLine("Member id: ").trim().toUpperCase();
        String itemId = readLine("Item id: ").trim().toUpperCase();
        Loan loan = library.borrow(memberId, itemId);
        System.out.println("OK. Due back on " + loan.dueOn() + ".");
    }

    private void giveBack() throws LibraryException {
        String itemId = readLine("Item id: ").trim().toUpperCase();
        Loan loan = library.returnItem(itemId);
        if (loan.isOverdue(library.today())) {
            long late = java.time.temporal.ChronoUnit.DAYS.between(loan.dueOn(), library.today());
            System.out.println("Returned LATE by " + late + " day(s).");
        } else {
            System.out.println("Returned on time. Thank you!");
        }
    }

    private void listItems() {
        if (library.allItems().isEmpty()) { System.out.println("(no items)"); return; }
        library.allItems().forEach(System.out::println);
    }

    private void listMembers() {
        if (library.allMembers().isEmpty()) { System.out.println("(no members)"); return; }
        library.allMembers().forEach(System.out::println);
    }

    private void listOverdue() throws LibraryException {
        List<Loan> overdue = library.overdueLoans();
        if (overdue.isEmpty()) { System.out.println("No overdue items."); return; }
        for (Loan l : overdue) {
            LibraryItem item = library.findItem(l.itemId());
            Member m = library.findMember(l.memberId());
            long days = java.time.temporal.ChronoUnit.DAYS.between(l.dueOn(), library.today());
            System.out.printf("%s '%s' borrowed by %s, due %s (%d days overdue)%n",
                    item.getId(), item.getTitle(), m.getName(), l.dueOn(), days);
        }
    }

    private void search() {
        List<LibraryItem> found = library.searchByTitle(readLine("Search text: "));
        if (found.isEmpty()) System.out.println("No matches.");
        else found.forEach(System.out::println);
    }

    private void advanceClock() {
        int days = Integer.parseInt(readLine("Days to advance: ").trim());
        library.advanceClock(days);
        System.out.println("Date is now " + library.today());
    }

    // Helpers
    private String readLine(String prompt) {
        System.out.print(prompt);
        return in.hasNextLine() ? in.nextLine() : null;
    }

    private String clean(String s) {                       // commas would break our CSV format
        if (s == null) throw new IllegalArgumentException("No input");
        return s.replace(",", " ").trim();
    }
}
