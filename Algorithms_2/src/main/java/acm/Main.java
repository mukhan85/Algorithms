package acm;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream("input"));
      Scanner input = new Scanner(System.in);
        while (input.hasNextInt()) {
            int size = input.nextInt();
            int[] prices = new int[size];
            for (int i = 0; i < size; i++) {
                prices[i] = input.nextInt();
            }
            int total = input.nextInt();
            solve(total, prices);
        }
        input.close();
    }

    private static void solve(int total, int[] prices) {
        int first = 0;
        int second = Integer.MAX_VALUE;
        int solFirst = 0, solSecond = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            first = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                second = prices[j];
                if (first + second == total && Math.abs(first - second) < Math.abs(solFirst - solSecond)) {
                    solFirst = first;
                    solSecond = second;
                }
            }
        }

        if (solFirst < solSecond) {
            System.out.printf("Peter should buy books whose prices are %d and %d.\n", solFirst, solSecond);
        } else {
            System.out.printf("Peter should buy books whose prices are %d and %d.\n", solSecond, solFirst);
        }
        System.out.println();
    }
}