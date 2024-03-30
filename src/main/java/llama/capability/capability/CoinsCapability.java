package llama.capability.capability;

public class CoinsCapability {
    private static int clientCoins = 0;

    public static double getCoins() {
        return clientCoins;
    }

    public static void setCoins(int coins) {
        clientCoins = coins;
    }
}
