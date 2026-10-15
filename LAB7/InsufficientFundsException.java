public class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(double balance, double requested) {
        super(String.format("Insufficient funds: balance %.2f, requested %.2f", balance, requested));
        this.shortfall = requested - balance;
    }

    public double getShortfall() { return shortfall; }
}
