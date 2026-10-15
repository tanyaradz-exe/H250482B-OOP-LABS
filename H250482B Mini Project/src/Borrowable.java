/** Requirement 3: anything that can be lent out and handed back. */
public interface Borrowable {
    void borrow(Member member) throws ItemNotAvailableException;
    void giveBack();
}
