// Contract for anything that can be lent out (interface = capability, not identity)
public interface Borrowable {
    boolean isAvailable();
    void borrow() throws ItemNotAvailableException;
    void giveBack();
    int getLoanDays();
}
