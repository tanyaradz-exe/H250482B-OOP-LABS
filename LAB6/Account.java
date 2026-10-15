public abstract class Account {
    private final String id;
    protected double balance;

    protected Account(String id, double openingBalance) {
        this.id = id;
        this.balance = openingBalance;
    }

    // Concrete: identical for every account type
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    // Abstract: each account type decides its own fee
    public abstract double monthlyFee();

    public void applyMonthlyFee() { balance -= monthlyFee(); }

    public double getBalance() { return balance; }
    public String getId()      { return id; }

    @Override
    public String toString() {
        return String.format("%-15s %s balance=%9.2f fee=%.2f",
                getClass().getSimpleName(), id, balance, monthlyFee());
    }
}
