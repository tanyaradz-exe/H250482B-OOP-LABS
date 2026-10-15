import java.util.HashSet;
import java.util.Set;

public class BookSetDemo {
    public static void main(String[] args) {
        Set<Book> shelf = new HashSet<>();
        System.out.println("add Java Basics   : " + shelf.add(new Book("978-1", "Java Basics")));
        System.out.println("add Data Structures: " + shelf.add(new Book("978-2", "Data Structures")));
        // Same ISBN, different object and different title text
        System.out.println("add duplicate ISBN : " + shelf.add(new Book("978-1", "Java Basics (2nd copy)")));
        System.out.println("Set size = " + shelf.size() + " -> " + shelf);
        // Without equals/hashCode the set would hold 3 books, since identity (==) would be used.
    }
}
