public abstract class Payment {
    protected final double amount;
    protected Payment(double amount) { this.amount = amount; }
    public double getAmount() { return amount; }

    // Used only by the polymorphic design (Design 2)
    public abstract void process();
}
