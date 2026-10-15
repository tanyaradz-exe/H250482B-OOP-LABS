public class AccountDemo {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("SAV-1", 800),
            new SavingsAccount("SAV-2", 300),
            new CurrentAccount("CUR-1", 1000)
        };
        for (Account a : accounts) a.deposit(100);
        System.out.println("Before fees:");
        for (Account a : accounts) System.out.println("  " + a);
        for (Account a : accounts) a.applyMonthlyFee();
        System.out.println("After fees:");
        for (Account a : accounts) System.out.println("  " + a);
    }
}
