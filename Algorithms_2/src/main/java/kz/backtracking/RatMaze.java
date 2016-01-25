package kz.backtracking;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by mmyrzaku on 22/01/2016.
 */
public class RatMaze {

    private static final Point [] dir = {new Point(0, 1), new Point(1, 0)};
    private static final Point start = new Point(0, 0);
    private static final Point dest = new Point(3, 3);
    private static final Stack<Point> path = new Stack<>();

    public static void main(String[] args) {
        int[][]maze = {{1, 1, 1, 0},
                       {1, 0, 0, 1},
                       {1, 1, 0, 0},
                       {1, 1, 1, 1}};

        int[][] sol = new int[maze.length][maze.length];

        for (int i = 0; i < sol.length; i++) {
            Arrays.fill(sol[i], 0);
        }

        if (solve(maze, start.getRowId(), start.getColId(), sol)) {
            while(!path.isEmpty()) {
                System.out.print(path.pop() + " ");
            }

            System.out.println();

            for (int i = 0; i < sol.length; i++) {
                for (int j = 0; j < sol.length; j++) {
                    System.out.print(sol[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution.");
        }
    }

    private static boolean solve(int[][] maze, int rowId, int colId, int[][] sol) {
        sol[rowId][colId] = 1;
        path.push(new Point(rowId, colId));

        if (isDestination(rowId, colId)) {
            return true;
        }

        for (int i = 0; i < dir.length; i++) {
            int newRowId = rowId + dir[i].getRowId();
            int newColId = colId + dir[i].getColId();

            if (isWithinMaze(newRowId, newColId) && isFreeCell(maze, newRowId, newColId)) {
                boolean isSolution = solve(maze, newRowId, newColId, sol);
                if (isSolution) {
                    return true;
                } else {
                    sol[newRowId][newColId] = 0;
                    path.pop();
                }
            }
        }

        return false;
    }

    private static boolean isFreeCell(int[][] maze, int rowId, int colId) {
        return maze[rowId][colId] == 1;
    }

    private static boolean isDestination(int rowId, int colId) {
        return rowId == dest.getRowId() && colId == dest.getColId();
    }

    private static boolean isWithinMaze(int rowId, int colId) {
        return rowId >= 0 && rowId <= dest.getRowId() && colId >= 0 && colId <= dest.getColId();
    }
}

class Point {
    private final int rowId;
    private final int colId;

    public Point(int rowId, int colId) {
        this.rowId = rowId;
        this.colId = colId;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColId() {
        return colId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (rowId != point.rowId) return false;
        return colId == point.colId;

    }

    @Override
    public int hashCode() {
        int result = rowId;
        result = 31 * result + colId;
        return result;
    }

    @Override
    public String toString() {
        return "{" + rowId + ", " + colId +'}';
    }
}