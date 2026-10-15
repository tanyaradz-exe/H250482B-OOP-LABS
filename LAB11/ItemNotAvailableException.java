public class ItemNotAvailableException extends LibraryException {
    public ItemNotAvailableException(String title) {
        super("'" + title + "' is already on loan.");
    }
}
