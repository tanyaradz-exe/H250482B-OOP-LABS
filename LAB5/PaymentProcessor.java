// DESIGN 1: the processor inspects the type with instanceof pattern matching.
public class PaymentProcessor {
    public static void pay(Payment p) {
        if (p instanceof CardPayment c) {
            System.out.printf("[instanceof] Charging %.2f to card %s%n", c.getAmount(), c.maskedCard());
        } else if (p instanceof MobileMoneyPayment m) {
            System.out.printf("[instanceof] Requesting %.2f from wallet %s%n", m.getAmount(), m.getPhone());
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + p.getClass().getSimpleName());
        }
    }
}
