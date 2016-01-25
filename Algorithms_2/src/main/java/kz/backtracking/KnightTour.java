package kz.backtracking;

/**
 * Created by mmyrzaku on 21/01/2016.
 */
class KnightTour {
    private static int N = 8;

    /* A utility function to check if (i,j) are valid indexes for N*N chessboard */
    static boolean isWithinBoard(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    /* This function solves the Knight Tour problem using Backtracking.  This  function mainly
       uses solveKTUtil() to solve the problem. It returns false if no complete tour is possible,
       otherwise return true and prints the tour. Please note that there may be more than one
       solutions, this function prints one of the feasible solutions */

    static boolean solve(int i, int j) {
        int[][] sol = initSolutionMatrix();

       /* valid moves for Knight */
        int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Since the Knight is initially at the first block
        sol[i][j] = 0;

        /* Start from 0,0 and explore all tours using solveKTUtil() */
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } else {
            printSolution(sol);
        }

        return true;
    }

    /* A utility function to print solution matrix sol[N][N] */
    static void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                System.out.print(sol[x][y] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] initSolutionMatrix() {
        int sol[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sol[i][j] = -1;
            }
        }
        return sol;
    }

    /* A recursive utility function to solve Knight Tour problem */
    static boolean solveKTUtil(int rowId, int colId, int cellId, int sol[][], int [] moveRow, int [] moveCol) {
        int nextRow, nextCol;
        if (cellId == N * N) {
            return true;
        }

        System.out.printf("Trying (%d, %d) \n", rowId, colId);
        if (cellId > N * N) {
            return false;
        }

        /* Try all next moves from the current coordinate rowId, colId */
        for (int i = 0; i < N; i++) {
            nextRow = rowId + moveRow[i];
            nextCol = colId + moveCol[i];

            if (isWithinBoard(nextRow, nextCol, sol)) {
                sol[nextRow][nextCol] = cellId;
                if (solveKTUtil(nextRow, nextCol, cellId + 1, sol, moveRow, moveCol)) {
                    return true;
                } else {
                    sol[nextRow][nextCol] = -1; // backtracking
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("Trying (%d, %d) \n", i, j);
                if (!solve(i, j)) {
                    System.out.printf("No solution for (%d, %d) \n", i, j);
                }
            }
        }
    }
}