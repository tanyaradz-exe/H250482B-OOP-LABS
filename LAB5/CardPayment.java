public class CardPayment extends Payment {
    private final String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public String maskedCard() { return "****" + cardNumber.substring(cardNumber.length() - 4); }

    @Override public void process() {
        System.out.printf("Charging %.2f to card %s%n", amount, maskedCard());
    }
}
