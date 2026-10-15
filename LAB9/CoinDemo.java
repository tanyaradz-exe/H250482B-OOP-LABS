public class CoinDemo {
    public static void main(String[] args) {
        for (int amount : new int[]{0, 6, 41, 99, 100}) {
            System.out.println(amount + " cents -> " + Coin.fewestCoins(amount));
        }
    }
}
