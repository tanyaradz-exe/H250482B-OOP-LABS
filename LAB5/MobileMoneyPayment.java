public class MobileMoneyPayment extends Payment {
    private final String phone;

    public MobileMoneyPayment(double amount, String phone) {
        super(amount);
        this.phone = phone;
    }

    public String getPhone() { return phone; }

    @Override public void process() {
        System.out.printf("Requesting %.2f from mobile wallet %s%n", amount, phone);
    }
}
