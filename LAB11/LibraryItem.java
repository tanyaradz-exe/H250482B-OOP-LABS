// Abstract parent: shared state and behaviour for everything the library lends
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank())       throw new IllegalArgumentException("Item id required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
        this.id = id.trim();
        this.title = title.trim();
    }

    public String getId()    { return id; }
    public String getTitle() { return title; }

    @Override public boolean isAvailable() { return available; }

    @Override
    public void borrow() throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException(title);
        available = false;
    }

    @Override public void giveBack() { available = true; }

    // Each subclass decides these (polymorphism)
    public abstract String getType();
    public abstract String getCreator();
    protected abstract String getExtra();          // isbn / duration, used for CSV

    public String toCsv() {
        return String.join(",", getType(), id, title, getCreator(), getExtra());
    }

    @Override
    public String toString() {
        return String.format("%-5s %-6s %-30s %-18s %s",
                getType(), id, title, getCreator(), available ? "available" : "ON LOAN");
    }
}
