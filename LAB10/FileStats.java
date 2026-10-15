import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileStats {
    public static void main(String[] args) throws IOException {
        Path file;
        if (args.length > 0) {
            file = Path.of(args[0]);
        } else {                                        // no file given: build a small sample
            file = Path.of("sample.txt");
            Files.writeString(file, "Object oriented programming in Java\n"
                    + "classes and objects\n\n"
                    + "inheritance, polymorphism and abstraction\n");
        }

        List<String> lines = Files.readAllLines(file);
        long words = lines.stream()
                .flatMap(l -> java.util.Arrays.stream(l.trim().split("\\s+")))
                .filter(w -> !w.isEmpty())
                .count();
        long chars = Files.readString(file).length();   // includes newline characters

        System.out.println("File      : " + file);
        System.out.println("Lines     : " + lines.size());
        System.out.println("Words     : " + words);
        System.out.println("Characters: " + chars);
    }
}
