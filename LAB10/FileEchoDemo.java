import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileEchoDemo {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            lines.add(in.nextLine());
        }

        Path file = Path.of("notes.txt");
        Files.write(file, lines);                       // creates or overwrites
        System.out.println("Saved to " + file.toAbsolutePath().getFileName());

        System.out.println("--- reading back ---");
        for (String line : Files.readAllLines(file)) {
            System.out.println(line);
        }
    }
}
