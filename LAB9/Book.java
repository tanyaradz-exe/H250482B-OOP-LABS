import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) { this.isbn = isbn; this.title = title; }

    public String getIsbn() { return isbn; }

    // Two books are "the same book" when the ISBN matches
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book other)) return false;
        return isbn.equals(other.isbn);
    }

    // Contract: equal objects MUST have equal hash codes, so hash the same field
    @Override
    public int hashCode() { return Objects.hash(isbn); }

    @Override
    public String toString() { return title + " [" + isbn + "]"; }
}
