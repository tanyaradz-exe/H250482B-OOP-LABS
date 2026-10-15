import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Requirement 7: console menu. Usage: java Main [dataDir]   (default: ./data) */
public class Main {
    public static void main(String[] args) throws IOException {
        Path dir = Path.of(args.length > 0 ? args[0] : "data");
        Library library = new Library();
        library.load(dir);                                   // reload on start
        System.out.println("Loaded " + library.allItems().size() + " items, "
                + library.allMembers().size() + " members, " + library.activeLoans().size() + " loans.");

        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== Library Menu ===\n1 Add item\n2 Register member\n3 Borrow\n4 Return\n"
                    + "5 List overdue\n6 Search by title\n7 List all items\n0 Save and exit");
            System.out.print("Choice: ");
            if (!in.hasNextLine()) break;                    // end of piped input: treat as exit
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> addItem(library, in);
                    case "2" -> {
                        String id = ask(in, "Member id: ");
                        library.registerMember(new Member(id, ask(in, "Name: ")));
                        System.out.println("Registered.");
                    }
                    case "3" -> {
                        Loan l = library.borrow(ask(in, "Member id: "), ask(in, "Item id: "), LocalDate.now());
                        System.out.println("Borrowed: " + l);
                    }
                    case "4" -> System.out.println("Returned: " + library.giveBack(ask(in, "Item id: ")));
                    case "5" -> {
                        List<Loan> overdue = library.listOverdue(LocalDate.now());
                        if (overdue.isEmpty()) System.out.println("No overdue items.");
                        else overdue.forEach(l -> System.out.println("OVERDUE: " + l));
                    }
                    case "6" -> {
                        List<LibraryItem> found = library.searchByTitle(ask(in, "Search text: "));
                        if (found.isEmpty()) System.out.println("No matches.");
                        else found.forEach(System.out::println);
                    }
                    case "7" -> library.allItems().forEach(System.out::println);
                    case "0" -> running = false;
                    default -> System.out.println("Unknown choice.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot borrow: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        library.save(dir);                                   // persist on exit
        System.out.println("Saved to " + dir.toAbsolutePath() + ". Goodbye.");
    }

    private static void addItem(Library library, Scanner in) {
        String kind = ask(in, "Type (book/dvd): ").toLowerCase();
        String id = ask(in, "Item id: ");
        String title = ask(in, "Title: ");
        switch (kind) {
            case "book" -> library.addItem(new Book(id, title, ask(in, "Author: ")));
            case "dvd" -> {
                try { library.addItem(new Dvd(id, title, Integer.parseInt(ask(in, "Minutes: ")))); }
                catch (NumberFormatException e) { throw new IllegalArgumentException("Minutes must be a number"); }
            }
            default -> throw new IllegalArgumentException("Type must be book or dvd");
        }
        System.out.println("Added.");
    }

    private static String ask(Scanner in, String prompt) {
        System.out.print(prompt);
        return in.hasNextLine() ? in.nextLine().trim() : "";
    }
}
