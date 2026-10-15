public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Tendai", 500.00);
        try {
            acc.withdraw(100);
            System.out.println("Withdrew 100 -> " + acc);
            acc.withdraw(5000);                    // throws
            System.out.println("This line is never reached.");
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.printf("You are short by %.2f%n", e.getShortfall());
        }
        System.out.println(acc);
    }
}
