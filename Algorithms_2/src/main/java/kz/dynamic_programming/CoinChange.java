package kz.dynamic_programming;

/**
 * Created by mmyrzaku on 22/02/2016.
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println("Coin change.");
        int[] coins = {1, 2, 3};
        int sum = 4;
        int coinIndex = coins.length;

        System.out.println(count(coins, coinIndex, sum));
    }

    public static int count(int[] coins, int currentIndex, int sum) {
        if (sum == 0) {
            return 1;
        }

        if (sum < 0) {
            return 0;
        }

        if (currentIndex <= 0 && sum >= 1) {
            return 0;
        }

        return count(coins, currentIndex, sum - coins[currentIndex - 1])
                + count(coins, currentIndex - 1, sum);
    }
}
