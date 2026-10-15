import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<String> names = List.of("Tariro", "Farai", "Rudo", "Farai", "Tariro", "Anesu", "Rudo");

        // LinkedHashSet: no duplicates AND keeps first-seen order (a HashSet would not)
        Set<String> unique = new LinkedHashSet<>(names);

        System.out.println("Original: " + names);
        System.out.println("Unique  : " + unique);
        System.out.println("Removed " + (names.size() - unique.size()) + " duplicates");
    }
}
