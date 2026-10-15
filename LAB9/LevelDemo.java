public class LevelDemo {

    static void advise(Level level) {
        switch (level) {
            case LOW    -> System.out.println("LOW: relax, nothing to do.");
            case MEDIUM -> System.out.println("MEDIUM: keep an eye on it.");
            case HIGH   -> System.out.println("HIGH: act now!");
        }
    }

    public static void main(String[] args) {
        for (Level l : Level.values()) advise(l);
        Level chosen = Level.valueOf("MEDIUM");
        System.out.println("Chosen: " + chosen + ", ordinal " + chosen.ordinal());
    }
}
