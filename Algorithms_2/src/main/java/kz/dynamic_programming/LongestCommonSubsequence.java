package kz.dynamic_programming;

/**
 * Created by mmyrzaku on 17/02/2016.
 */
public class LongestCommonSubsequence {
    private static int counter = 0;

    public static void main(String[] args) {
        char [] first = "AGGTAB".toCharArray();
        char [] second = "GXTXAYB".toCharArray();
        int firstLen = first.length;
        int secondLen = second.length;

        System.out.println("Recursive LCS: " + recursiveLCS(first, second, firstLen, secondLen));
        System.out.println("Dynamic LCS: " + dynamicLCS(first, second));
    }

    private static int recursiveLCS(char[] first, char[] second, int firstLen, int secondLen) {
        ++counter;
//      printCall(first, second, firstLen, secondLen);
        if (firstLen == 0 || secondLen == 0) {
            return 0;
        }

        if (first[firstLen - 1] == second[secondLen - 1]) {
            return 1 + recursiveLCS(first, second, firstLen - 1, secondLen - 1);
        } else {
            return Math.max(
                    recursiveLCS(first, second, firstLen - 1, secondLen),
                    recursiveLCS(first, second, firstLen, secondLen - 1));
        }
    }

    private static void printCall(char[] first, char[] second, int firstLen, int secondLen) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            sb.append(" ");
        }
        sb.append("(");
        for (int i = 0; i < firstLen; i++) {
            sb.append(first[i]);
        }
        sb.append("), (");
        for (int i = 0; i < secondLen; i++) {
            sb.append(second[i]);
        }
        sb.append(")");
        System.out.println(sb.toString());
    }

    public static int dynamicLCS(char[] first, char[] second) {
        int[][] memory = new int[first.length + 1][second.length + 1];

        for (int i = 0; i <= first.length; i++) {
            for (int j = 0; j <= second.length; j++) {
                if (i == 0 || j == 0) {
                    memory[i][j] = 0;
                } else if (first[i - 1] == second[j - 1]) {
                    memory[i][j] = memory[i - 1][j - 1] + 1;
                } else {
                    memory[i][j] = Math.max(memory[i - 1][j], memory[i][j - 1]);
                }
            }
        }

        return memory[first.length][second.length];
    }
}
