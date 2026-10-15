/** Requirement 2: abstract parent of everything the library lends. */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank())       throw new IllegalArgumentException("Item id must not be blank");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be blank");
        this.id = id.trim();
        this.title = title.trim();
    }

    /** Each subclass decides its own loan period (polymorphism). */
    public abstract int loanPeriodDays();

    /** Short label such as "Book" or "DVD", used in menus and CSV files. */
    public abstract String type();

    /** The one subclass-specific value (author or duration) saved to CSV. */
    public abstract String extra();

    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException(id);
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }
    void setAvailable(boolean available) { this.available = available; } // package-private: used when reloading

    @Override
    public String toString() {
        return String.format("%-4s %-6s %-30s %s", type(), id, title, available ? "available" : "ON LOAN");
    }
}
