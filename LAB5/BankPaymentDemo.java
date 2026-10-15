public class BankPaymentDemo {
    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(120.50, "4111111111111234"),
            new MobileMoneyPayment(15.00, "+263 77 123 4567")
        };

        System.out.println("--- Design 1: instanceof ---");
        for (Payment p : payments) PaymentProcessor.pay(p);

        System.out.println("--- Design 2: polymorphic process() ---");
        for (Payment p : payments) PolymorphicPaymentProcessor.pay(p);
    }
}

/*
COMPARISON
 Design 1 (instanceof): all payment logic sits in one place, which is easy to read at first,
   but every new payment type (say BankTransfer) forces you to EDIT the processor and
   add another branch. Forget one branch and you get a run-time failure. It breaks the
   open/closed principle and the compiler cannot warn you.
 Design 2 (polymorphism): add a new subclass that overrides process() and NOTHING else changes.
   The compiler forces every concrete subclass to implement process(). The behaviour lives
   next to the data it uses. Prefer it whenever you control the class hierarchy. instanceof
   is still appropriate when you cannot modify the classes (e.g. library types) or the
   decision is not really the object's own responsibility.
*/
