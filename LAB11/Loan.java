import java.time.LocalDate;

public record Loan(String itemId, String memberId, LocalDate borrowedOn, LocalDate dueOn) {

    public boolean isOverdue(LocalDate today) { return today.isAfter(dueOn); }

    public String toCsv() { return itemId + "," + memberId + "," + borrowedOn + "," + dueOn; }

    public static Loan fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Loan(p[0], p[1], LocalDate.parse(p[2]), LocalDate.parse(p[3]));
    }
}
