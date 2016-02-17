package kz.dynamic_programming;

/**
 * Created by mmyrzaku on 17/02/2016.
 */
public class EditDistance {
    public static void main(String[] args) {
        String first = "sunday";
        String second = "saturday";
        int lenOne = first.length();
        int lenTwo = second.length();

        System.out.println("Recursive: " + recursiveEditDistance(first, second, lenOne, lenTwo));
        System.out.println("Dynamic: " + dynamicEditDistance(first, second));
    }

    private static int recursiveEditDistance(String first, String second, int lenOne, int lenTwo) {
        if (lenOne == 0) {
            return lenTwo;
        }
        if (lenTwo == 0) {
            return lenOne;
        }

        if (first.charAt(lenOne - 1) == second.charAt(lenTwo - 1)) {
            return recursiveEditDistance(first, second, lenOne - 1, lenTwo - 1);
        }

        return 1 + min(recursiveEditDistance(first, second, lenOne, lenTwo - 1), // insert
                recursiveEditDistance(first, second, lenOne - 1, lenTwo), // remove.
                recursiveEditDistance(first, second, lenOne - 1, lenTwo - 1) // replace
        );
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static int dynamicEditDistance(String first, String second) {
        int[][] memory = new int[first.length() + 1][second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0) {
                    memory[i][j] = j;
                } else if (j == 0) {
                    memory[i][j] = i;
                }else if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    memory[i][j] = memory[i - 1][j - 1];
                } else {
                    memory[i][j] = 1 + min(
                            memory[i][j - 1],       // insert
                            memory[i - 1][j],       // remove
                            memory[i - 1][j - 1]);  // replace
                }
            }
        }

        return memory[first.length()][second.length()];
    }
}
