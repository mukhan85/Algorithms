package kz.dynamic_programming;

/**
 * Created by mmyrzaku on 16/02/2016.
 */
public class Fibonachi {
    private static final int size = 10;
    private static int[] results = new int[size];

    public static void main(String[] args) {
        System.out.println(fibonRecursive(10));
        System.out.println(fibonDynamic(5));
        System.out.println(fibonDynamic(10));
        System.out.println(fibonDynamic(15));
    }

    private static int fibonDynamic(int n) {
        if (results[results.length - 1] == 0) {
            compute(results.length - 1);
        }

        if (n <= results.length - 1) {
            if (results[n] != 0) {
                return results[n];
            }
        }
        resize(n);
        return results[n];
    }

    private static void resize(int n) {
        int[] temp = new int[results.length * 2 + 1];
        for (int i = 0; i < results.length; i++) {
            temp[i] = results[i];
        }

        for (int i = results.length - 1; i <= n; i++) {
            temp[i] = i * temp[i - 1];
        }
        results = temp;
    }

    private static void compute(int n) {
        results[0] = 1;
        results[1] = 1;
        for (int i = 2; i <= n; i++) {
            results[i] = i * results[i - 1];
        }
    }

    private static int fibonRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * fibonRecursive(n - 1);
    }
}
