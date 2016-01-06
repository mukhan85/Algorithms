package kz.graph.acm;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Main {
    public static void main(String... args) {
//        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream("input"));
      Scanner input = new Scanner(System.in);

        int numTests = input.nextInt();
        for (int i = 0; i < numTests; i++) {
            int size = input.nextInt() - 1;

            int[] data = new int[size];
            for (int j = 0; j < size; j++) {
                data[j] = input.nextInt();
            }

            solve(data, i + 1);
        }
        input.close();
    }

    private static void solve(int[] data, int testNum) {
        int[] summation = new int[data.length];
        summation[0] = Math.max(data[0], 0);
        int max = summation[0];

        int startIndex = 0;
        int endIndex = 0;

        for (int i = 1; i < data.length; i++) {
            summation[i] = Math.max(summation[i - 1] + data[i], 0);
            if(summation[i] == 0) {
                startIndex = i + 1;
            }

            if(max <= summation[i]) {
                endIndex = i;
                max = summation[i];
            }
        }
        if(data.length == 2) {
            startIndex ++ ;
        }

        if(max == 0) {
            System.out.println("Route " + testNum + " has no nice parts");
        } else {
            System.out.printf("The nicest part of route %d is between stops %d and %d\n", testNum, startIndex + 1, endIndex + 2);
        }

//        System.out.printf("(%d, %d)=%d : %s \n", startIndex, endIndex, max, Arrays.toString(summation));
    }
}
