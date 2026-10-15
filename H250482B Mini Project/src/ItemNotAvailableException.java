/** Checked exception: the requested item is already on loan. */
public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String itemId) {
        super("Item " + itemId + " is not available");
    }
}
