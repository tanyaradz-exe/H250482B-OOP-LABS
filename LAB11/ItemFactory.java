// FACTORY pattern: one place that knows how to build the right subclass
public class ItemFactory {
    private ItemFactory() { }

    public static LibraryItem create(String type, String id, String title, String creator, String extra) {
        return switch (type.toUpperCase()) {
            case "BOOK" -> new Book(id, title, creator, extra);
            case "DVD"  -> new Dvd(id, title, creator, Integer.parseInt(extra.trim()));
            default     -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }

    public static LibraryItem fromCsv(String line) {
        String[] p = line.split(",", -1);
        return create(p[0], p[1], p[2], p[3], p[4]);
    }
}
