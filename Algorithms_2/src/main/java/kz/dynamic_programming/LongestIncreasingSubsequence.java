package kz.dynamic_programming;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by mmyrzaku on 17/02/2016.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] data = {-7, 10, 9, 2, 3, 8, 8, 6};
//        System.out.println("BruteForce: " + iterativeLIS(data));
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

    // FIXME: print out the actual sequence
    private static int dynamicLIS(int[] data) {
        int[] results = new int[data.length];
        int[] path = new int[data.length];

        Arrays.fill(results, 1);

        for (int i = 1; i < results.length; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j] && results[i] < results[j] + 1) {
                    results[i] = results[j] + 1;
                    path[i] = j;
                }
            }
        }

        int maxLen = 1;
        int index = -1;
        for (int i = 0; i < results.length; i++) {
            if (maxLen < results[i]) {
                maxLen = results[i];
                index = i;
            }
        }

        Stack<Integer> seq = new Stack<>();
        seq.add(data[index]);
        while (path[index] != index) {
            seq.add(data[path[index]]);
            index = path[index];
        }

        while (!seq.isEmpty()) {
            System.out.println(seq.pop());
        }
        return maxLen;
    }
}
