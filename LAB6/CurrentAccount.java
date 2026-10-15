public class CurrentAccount extends Account {
    public CurrentAccount(String id, double openingBalance) { super(id, openingBalance); }

    // Current: fixed maintenance fee of 10.00
    @Override public double monthlyFee() { return 10.0; }
}
