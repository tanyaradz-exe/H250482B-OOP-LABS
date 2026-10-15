import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

// Saves and loads three CSV files: items.csv, members.csv, loans.csv
public class LibraryStorage {
    private final Path dir;

    public LibraryStorage(String directory) { this.dir = Path.of(directory); }

    public void save(Library lib) throws IOException {
        Files.createDirectories(dir);

        List<String> itemLines = new ArrayList<>();
        for (LibraryItem i : lib.allItems()) itemLines.add(i.toCsv());
        Files.write(dir.resolve("items.csv"), itemLines);

        List<String> memberLines = new ArrayList<>();
        for (Member m : lib.allMembers()) memberLines.add(m.toCsv());
        Files.write(dir.resolve("members.csv"), memberLines);

        List<String> loanLines = new ArrayList<>();
        for (Loan l : lib.allLoans()) loanLines.add(l.toCsv());
        Files.write(dir.resolve("loans.csv"), loanLines);
    }

    public Library load() throws IOException {
        Library lib = new Library();

        for (String line : readLines("items.csv")) {
            try { lib.addItem(ItemFactory.fromCsv(line)); }
            catch (RuntimeException e) { System.out.println("Skipping bad item line: " + line); }
        }
        for (String line : readLines("members.csv")) {
            String[] p = line.split(",", -1);
            try { lib.registerMember(new Member(p[0], p[1])); }
            catch (RuntimeException e) { System.out.println("Skipping bad member line: " + line); }
        }
        for (String line : readLines("loans.csv")) {
            try { lib.restoreLoan(Loan.fromCsv(line)); }
            catch (Exception e) { System.out.println("Skipping bad loan line: " + line + " (" + e.getMessage() + ")"); }
        }
        return lib;
    }

    private List<String> readLines(String file) throws IOException {
        Path p = dir.resolve(file);
        List<String> result = new ArrayList<>();
        if (!Files.exists(p)) return result;               // first run: nothing saved yet
        for (String line : Files.readAllLines(p)) if (!line.isBlank()) result.add(line);
        return result;
    }
}
