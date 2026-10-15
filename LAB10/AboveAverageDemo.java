import java.util.*;
import java.util.stream.*;

record Pupil(String name, int score) { }

public class AboveAverageDemo {
    public static void main(String[] args) {
        List<Pupil> class1 = List.of(
            new Pupil("Tariro", 82), new Pupil("Farai", 55), new Pupil("Rudo", 91),
            new Pupil("Anesu", 67), new Pupil("Chipo", 74), new Pupil("Kuda", 48));

        double avg = class1.stream().mapToInt(Pupil::score).average().orElse(0);
        System.out.printf("Class average: %.2f%n", avg);

        List<String> above = class1.stream()
                .filter(p -> p.score() > avg)
                .map(Pupil::name)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Above average (A-Z): " + above);
    }
}
