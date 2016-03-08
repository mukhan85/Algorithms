package kz.dynamic_programming;

import java.util.Arrays;

/**
 * Created by mmyrzaku on 22/02/2016.
 */
public class MinCostPath {

    public static void main(String[] args) {
        System.out.println("Print out path with minumum cost");
        int data[][] = {{1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}};

        System.out.println(minCostPathRecursive(data, data.length - 1, data[0].length - 1));
        System.out.println(minCostPathDynamic(data));
    }

    private static int minCostPathRecursive(int[][] data, int rowId, int colId) {
        if (rowId < 0 || colId < 0) {
            return Integer.MAX_VALUE;
        } else if (rowId == 0 && colId == 0) {
            return data[rowId][colId];
        }

        return data[rowId][colId] + min(minCostPathRecursive(data, rowId - 1, colId - 1),
                minCostPathRecursive(data, rowId - 1, colId),
                minCostPathRecursive(data, rowId, colId - 1));
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static int minCostPathDynamic(int[][] data) {
        int[][] sol = new int[data.length][data[0].length];
        sol[0][0] = data[0][0];

        for (int i = 1; i < data.length; i++) {
            sol[0][i] = data[0][i] + sol[0][i - 1];
        }

        for (int i = 1; i < data[0].length; i++) {
            sol[i][0] = data[i][0] + sol[i - 1][0];
        }

        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[0].length; j++) {
                sol[i][j] = min(sol[i - 1][j - 1], sol[i - 1][j], sol[i][j - 1]) + data[i][j];
            }
        }

        for (int i = 0; i < data.length; i++) {
            System.out.println(Arrays.toString(data[i]));
        }
        System.out.println();
        for (int i = 0; i < sol.length; i++) {
            System.out.println(Arrays.toString(sol[i]));
        }
        System.out.println();
        return sol[data.length - 1][data[0].length - 1];
    }
}
