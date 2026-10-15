public class Book extends LibraryItem {
    private final String author;
    private final String isbn;

    public Book(String id, String title, String author, String isbn) {
        super(id, title);
        this.author = author;
        this.isbn = isbn;
    }

    @Override public String getType()    { return "BOOK"; }
    @Override public String getCreator() { return author; }
    @Override protected String getExtra() { return isbn; }
    @Override public int getLoanDays()   { return 14; }
}
