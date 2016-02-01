package kz.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by mmyrzaku on 29/01/2016.
 */
public class QueenPositionFull {

    static int[] rows;
    static int cnt;

    static boolean valid(int colId, int rowId) {
        for (int eachCol = 1; eachCol < colId; ++eachCol) {
            if (rows[eachCol] == rowId || Math.abs(rows[eachCol] - rowId) == Math.abs(eachCol - colId)){
                return false;
            }
        }
        return true;
    }

    static void backtrack(int col) {
        if (isSolution(col)) {
            printSolution();
            return;
        }

        for (int row = 1; row <= 8; ++row) {
            if (valid(col, row)) {
                rows[col] = row;
                backtrack(col + 1);
            }
        }
    }

    private static void printSolution() {
        System.out.printf("%2d     ", cnt++);
        for (int i = 1; i <= 8; ++i) {
            System.out.print(" " + rows[i]);
        }

        System.out.println();
    }

    private static boolean isSolution(int col) {
        return col > 8 ;//&& rows[startCol] == startRow;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int T = 1; //Integer.parseInt(in.readLine());
        boolean first = true;
        while (T-- > 0) {
//            in.readLine();
//            stk = new StringTokenizer(in.readLine());
//            startRow = Integer.parseInt(stk.nextToken());
//            startCol = Integer.parseInt(stk.nextToken());

            if (first) first = false; else System.out.println();
            System.out.println("SOLN       COLUMN");
            System.out.println(" #      1 2 3 4 5 6 7 8");
            System.out.println();

            rows = new int[9];
            cnt = 1;
            backtrack(1);
        }

        in.close();
        System.exit(0);
    }
}