public class Dvd extends LibraryItem {
    private final String director;
    private final int minutes;

    public Dvd(String id, String title, String director, int minutes) {
        super(id, title);
        this.director = director;
        this.minutes = minutes;
    }

    @Override public String getType()    { return "DVD"; }
    @Override public String getCreator() { return director; }
    @Override protected String getExtra() { return String.valueOf(minutes); }
    @Override public int getLoanDays()   { return 7; }      // DVDs are due back sooner
}
