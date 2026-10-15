// DESIGN 2: each payment knows how to process itself; the processor never checks types.
public class PolymorphicPaymentProcessor {
    public static void pay(Payment p) {
        p.process();                   // dynamic dispatch picks the right behaviour
    }
}
