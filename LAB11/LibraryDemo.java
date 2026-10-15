// Non-interactive walkthrough: shows every feature and every custom exception.
public class LibraryDemo {
    public static void main(String[] args) throws Exception {
        Library lib = new Library();

        lib.addItem(new Book("B001", "Clean Code", "Robert Martin", "978-0132350884"));
        lib.addItem(new Book("B002", "Effective Java", "Joshua Bloch", "978-0134685991"));
        lib.addItem(new Book("B003", "Head First Java", "Kathy Sierra", "978-0596009205"));
        lib.addItem(new Book("B004", "Java Concurrency in Practice", "Brian Goetz", "978-0321349606"));
        lib.addItem(new Dvd("D001", "The Social Network", "David Fincher", 120));
        lib.registerMember(new Member("M001", "Tariro"));
        lib.registerMember(new Member("M002", "Farai"));

        System.out.println("-- Borrow 3 items as Tariro --");
        for (String id : new String[]{"B001", "B002", "D001"}) {
            System.out.println(id + " due " + lib.borrow("M001", id).dueOn());
        }

        System.out.println("\n-- Error cases --");
        try { lib.borrow("M001", "B003"); }  catch (LibraryException e) { System.out.println("4th loan  : " + e.getMessage()); }
        try { lib.borrow("M002", "B001"); }  catch (LibraryException e) { System.out.println("on loan   : " + e.getMessage()); }
        try { lib.borrow("M999", "B003"); }  catch (LibraryException e) { System.out.println("no member : " + e.getMessage()); }
        try { lib.borrow("M002", "B999"); }  catch (LibraryException e) { System.out.println("no item   : " + e.getMessage()); }
        try { lib.returnItem("B003"); }      catch (LibraryException e) { System.out.println("not loaned: " + e.getMessage()); }

        System.out.println("\n-- Stream search for 'java' --");
        lib.searchByTitle("java").forEach(System.out::println);

        System.out.println("\n-- 10 days later (books due after 14, DVD after 7) --");
        lib.advanceClock(10);
        lib.overdueLoans().forEach(l -> System.out.println("OVERDUE: " + l.itemId() + " due " + l.dueOn()));

        System.out.println("\n-- Return the DVD, then save and reload --");
        lib.returnItem("D001");
        LibraryStorage storage = new LibraryStorage("demo-data");
        storage.save(lib);
        Library reloaded = storage.load();
        System.out.println("Reloaded " + reloaded.allItems().size() + " items, "
                + reloaded.allMembers().size() + " members, " + reloaded.allLoans().size() + " loans");
        reloaded.allItems().forEach(System.out::println);
    }
}
