package kz.dynamic_programming;

import java.util.Arrays;

/**
 * Created by mmyrzaku on 17/02/2016.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] data = {10, 2, 9, 33, 21, 40, 45, 60, 90};
        System.out.println("BruteForce: " + iterativeLIS(data));
        System.out.println("Dynamic: " + dynamicLIS(data));
    }

    private static int iterativeLIS(int[] data) {
        int maxLen = 0;
        StringBuilder sol = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int counter = 1;
            int lastMax = data[i];

            StringBuilder sb = new StringBuilder();
            sb.append(lastMax + " ");

            for (int j = i + 1; j < data.length; j++) {
                if (data[j] > lastMax) {
                    lastMax = data[j];
                    ++counter;
                    sb.append(lastMax + " ");
                }
            }

            if (counter > maxLen) {
                maxLen = counter;
                sol = sb;
            }
        }
        System.out.println(sol);

        return maxLen;
    }

    private static int dynamicLIS(int[] data) {
        int[] results = new int[data.length];
        Arrays.fill(results, 1);

        for (int i = 1; i < results.length; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j] && results[i] < results[j] + 1) {
                    results[i] = results[j] + 1;
                }
            }
        }

        int maxLen = 1;
        for (int i = 0; i < results.length; i++) {
            if (maxLen < results[i]) {
                maxLen = results[i];
            }
        }
        return maxLen;
    }
}
