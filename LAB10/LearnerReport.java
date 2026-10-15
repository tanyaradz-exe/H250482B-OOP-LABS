import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class LearnerReport {

    record Learner(String name, int roll, double gpa) {
        static Learner fromCsv(String line) {
            String[] p = line.split(",");
            return new Learner(p[0].trim(), Integer.parseInt(p[1].trim()), Double.parseDouble(p[2].trim()));
        }
    }

    // A: gpa >= 3.5, B: 2.5 <= gpa < 3.5, C: below 2.5
    static String band(Learner l) {
        if (l.gpa() >= 3.5) return "A";
        if (l.gpa() >= 2.5) return "B";
        return "C";
    }

    public static void main(String[] args) throws IOException {
        List<Learner> learners = Files.readAllLines(Path.of("learners.csv")).stream()
                .filter(line -> !line.isBlank())
                .map(Learner::fromCsv)
                .toList();

        Map<String, List<Learner>> byBand = learners.stream()
                .collect(Collectors.groupingBy(LearnerReport::band, TreeMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Learner>> e : byBand.entrySet()) {
            List<String> report = new ArrayList<>();
            report.add("Band " + e.getKey() + " report (" + e.getValue().size() + " learners)");
            report.add("-".repeat(40));
            e.getValue().stream()
                    .sorted(Comparator.comparingDouble(Learner::gpa).reversed())
                    .forEach(l -> report.add(String.format("%-10s roll %-3d GPA %.1f", l.name(), l.roll(), l.gpa())));

            Path out = Path.of("report_band_" + e.getKey() + ".txt");
            Files.write(out, report);
            System.out.println("Wrote " + out + " (" + e.getValue().size() + " learners)");
        }
        System.out.println("\n--- report_band_A.txt ---");
        Files.readAllLines(Path.of("report_band_A.txt")).forEach(System.out::println);
    }
}
