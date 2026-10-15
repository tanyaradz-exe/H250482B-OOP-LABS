import java.util.EnumMap;
import java.util.Map;

public enum Coin {
    // Declared from smallest to largest; the algorithm walks them in reverse
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int cents;

    Coin(int cents) { this.cents = cents; }

    public int getCents() { return cents; }

    // Greedy: always take as many of the biggest coin as possible.
    // Optimal for this coin set (each value divides nicely into the next).
    public static Map<Coin, Integer> fewestCoins(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);
        Coin[] all = values();
        for (int i = all.length - 1; i >= 0; i--) {
            int count = amount / all[i].cents;
            if (count > 0) {
                result.put(all[i], count);
                amount -= count * all[i].cents;
            }
        }
        return result;
    }
}
