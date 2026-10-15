import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private final String accountNumber;
    private String ownerName;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public BankAccount(String accountNumber, String ownerName, double openingBalance) {
        if (openingBalance < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
        this.accountNumber = accountNumber;
        setOwnerName(ownerName);
        this.balance = openingBalance;
        history.add(String.format("OPEN     %.2f", openingBalance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) throw new IllegalArgumentException("Owner name required");
        this.ownerName = ownerName.trim();
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        history.add(String.format("DEPOSIT  %.2f", amount));
    }

    // CHANGED from Lab 3: returns void and THROWS instead of returning false.
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) {
            history.add(String.format("DECLINED %.2f", amount));
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
        history.add(String.format("WITHDRAW %.2f", amount));
    }

    public List<String> getHistory() { return Collections.unmodifiableList(new ArrayList<>(history)); }

    @Override
    public String toString() { return String.format("%s [%s] balance: %.2f", accountNumber, ownerName, balance); }
}

/*
TRADE-OFFS: returning boolean vs throwing InsufficientFundsException
 boolean:   + simple, cheap, no try/catch needed for an expected outcome.
            - the caller can IGNORE the result (acc.withdraw(500); and carry on as if it worked),
              the result carries no reason, and it is easy to forget the check.
 exception: + failure cannot be silently ignored (checked exceptions force handling),
              carries details (shortfall, message), and separates the normal path from error handling.
            - more verbose for callers, and exceptions are slower than a return value.
              If "not enough money" is a routine, frequent outcome, a boolean (or a result
              object) can be the better fit; exceptions suit exceptional conditions.
*/
