import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Tendai", 500.00);
        acc.deposit(250.00);
        System.out.println("Withdraw 100: " + acc.withdraw(100.00));
        System.out.println("Withdraw 5000: " + acc.withdraw(5000.00));
        System.out.println(acc);

        List<String> snapshot = acc.getHistory();
        System.out.println("--- history ---");
        snapshot.forEach(System.out::println);

        try {
            snapshot.add("HACK     1000000");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify history from outside: UnsupportedOperationException");
        }

        acc.deposit(10);
        System.out.println("Old snapshot size: " + snapshot.size()
                + ", fresh history size: " + acc.getHistory().size());
    }
}
