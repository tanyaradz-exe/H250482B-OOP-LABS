import java.time.LocalDate;

public class Loan {
    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    /** Normal loan: due date = borrow date + the item's own loan period. */
    public Loan(LibraryItem item, Member member, LocalDate borrowDate) {
        this(item, member, borrowDate, borrowDate == null || item == null ? null : borrowDate.plusDays(item.loanPeriodDays()));
    }

    /** Used when reloading a saved loan. */
    public Loan(LibraryItem item, Member member, LocalDate borrowDate, LocalDate dueDate) {
        if (item == null || member == null || borrowDate == null || dueDate == null)
            throw new IllegalArgumentException("Loan fields must not be null");
        if (dueDate.isBefore(borrowDate)) throw new IllegalArgumentException("Due date is before borrow date");
        this.item = item;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public LibraryItem getItem()   { return item; }
    public Member getMember()      { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate()  { return dueDate; }
    public boolean isOverdue(LocalDate today) { return today.isAfter(dueDate); }

    @Override
    public String toString() {
        return item.getTitle() + " [" + item.getId() + "] -> " + member.getName()
                + " (borrowed " + borrowDate + ", due " + dueDate + ")";
    }
}
