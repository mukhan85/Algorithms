package kz.backtracking;

import java.util.Arrays;

/**
 * Created by mmyrzaku on 22/01/2016.
 */
public class QueenPosition {
    private static final int boardSize = 4;

    public static void main(String[] args) {

        int[][] queenPositions = new int[boardSize][boardSize];
        init(queenPositions);

        boolean hasSolution = solve(queenPositions, 0);

        if (hasSolution) {
            for (int i = 0; i < boardSize; i++) {
                System.out.println(Arrays.toString(queenPositions[i]));
            }
        } else {
            System.out.println("No Solution.");
        }
    }

    private static boolean solve(int[][] queenPositions, int colId) {
        if (colId >= boardSize) {
            return true;
        }

        for (int tryEachRow = 0; tryEachRow < boardSize; tryEachRow++) {
            if (isValidPosition(tryEachRow, colId, queenPositions)) {
                queenPositions[tryEachRow][colId] = 1;

                if (solve(queenPositions, colId + 1)) {
                    return true;
                } else {
                    queenPositions[tryEachRow][colId] = 0;
                }
            }
        }

        return false;
    }

    private static boolean isValidPosition(int rowId, int colId, int[][] queenPositions) {
        return withinBoard(rowId, colId)
                && safeColumn(colId, queenPositions)
                && safeRow(rowId, queenPositions)
                && isEmptyCell(rowId, colId, queenPositions)
                && safeDiagonal(rowId, colId, queenPositions);
    }

    private static boolean safeDiagonal(int rowId, int colId, int[][] queenPositions) {
        return safeUpperDiagonal(rowId, colId, queenPositions)
                && safeLowerDiagonal(rowId, colId, queenPositions);
    }

    private static boolean safeLowerDiagonal(int rowId, int colId, int[][] queenPositions) {
        for (int i = rowId, j = colId; j >= 0 && i < queenPositions.length; i++, j--) {
            if (queenPositions[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean safeUpperDiagonal(int rowId, int colId, int[][] queenPositions) {
        for (int i = rowId, j = colId; i >= 0 && j >= 0; i--, j--) {
            if (queenPositions[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEmptyCell(int rowId, int colId, int[][] queenPositions) {
        return queenPositions[rowId][colId] == 0;
    }

    private static boolean safeColumn(int colId, int[][] queenPositions) {
        for (int i = 0; i < boardSize; i++) {
            if (queenPositions[i][colId] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean safeRow(int row, int[][] queenPositions) {
        for (int i = 0; i < boardSize; i++) {
            if (queenPositions[row][i] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean withinBoard(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    private static void init(int[][] queenPositions) {
        for (int i = 0; i < queenPositions.length; i++) {
            Arrays.fill(queenPositions[i], 0);
        }
    }
}
