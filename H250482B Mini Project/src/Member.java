import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    public static final int MAX_LOANS = 3;

    private final String id;
    private final String name;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank())     throw new IllegalArgumentException("Member id must not be blank");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Member name must not be blank");
        this.id = id.trim();
        this.name = name.trim();
    }

    public String getId()   { return id; }
    public String getName() { return name; }
    public int getLoanCount() { return loans.size(); }
    public boolean canBorrow() { return loans.size() < MAX_LOANS; }
    /** Read-only view so outsiders cannot bypass the loan limit. */
    public List<Loan> getLoans() { return Collections.unmodifiableList(loans); }

    void addLoan(Loan loan)    { loans.add(loan); }
    void removeLoan(Loan loan) { loans.remove(loan); }

    @Override
    public String toString() { return "Member[" + id + ", " + name + ", loans=" + loans.size() + "/" + MAX_LOANS + "]"; }
}
