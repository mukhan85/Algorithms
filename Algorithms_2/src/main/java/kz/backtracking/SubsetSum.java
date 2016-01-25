package kz.backtracking;

import java.util.Arrays;

/**
 * Created by mmyrzaku on 25/01/2016.
 */
public class SubsetSum {
    private static int[] sol;
    private static final int NOT_INCLUDED = -1;

    public static void main(String[] args) {

        final int [] weights = {15, 22, 14, 26, 32, 9, 16, 8};
        final int target = 40;
        sol = new int[weights.length];
        Arrays.fill(sol, NOT_INCLUDED);

        if (solve(weights, target, 0)) {
            for (int i = 0; i < sol.length; i++) {
                if (sol[i] != NOT_INCLUDED) {
                    System.out.print(sol[i] + " ");
                }
            }
        } else {
            System.out.println("No solution.");
        }
    }

    private static boolean solve(int[] weights, int target, int currentIndex) {
        if (target == 0) {
            return true;
        }

        for (int i = currentIndex; i < weights.length; i++) {
            if (target - weights[i] >= 0) {
                sol[i] = weights[i];
                target -= weights[i];
                if (solve(weights, target, i + 1)) {
                    return true;
                } else {
                    target += weights[i];
                    sol[i] = NOT_INCLUDED;
                }
            }
        }
        return false;
    }
}
