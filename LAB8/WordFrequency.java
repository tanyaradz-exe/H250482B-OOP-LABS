import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = args.length > 0 ? String.join(" ", args)
            : "Java is fun. Java is object oriented, and object oriented programming "
            + "makes Java code reusable. Is Java hard? No, Java is fun and fun is good.";

        Map<String, Integer> freq = new HashMap<>();
        for (String w : paragraph.toLowerCase().split("[^a-z]+")) {
            if (!w.isEmpty()) freq.merge(w, 1, Integer::sum);
        }

        // Sort entries by frequency DESCENDING, ties alphabetically
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()));

        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("%-10s %d%n", e.getKey(), e.getValue());
        }
    }
}
