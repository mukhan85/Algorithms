package kz.graph.hackerrank;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Solution {

    public static void main(String... args) {
//        Scanner input = new Scanner(Solution.class.getClassLoader().getResourceAsStream("input"));
      Scanner input = new Scanner(System.in);

        int numTests = input.nextInt();
        for (int eachTestCase = 0; eachTestCase < numTests; eachTestCase++) {
            int numRows = input.nextInt();
            int numCols = input.nextInt();

            Point[] jumpSetup = createJumpSetup(input);
            Point[] waterCells = createWaterCells(input);

            solve(numRows, numCols, jumpSetup, waterCells, (eachTestCase + 1));
//            solveWithBfs(numRows, numCols, jumpSetup, waterCells, (eachTestCase + 1));
        }

        input.close();
    }

    private static void solveWithBfs(int numRows, int numCols, Point[] jumpSetup, Point[] waterCells, int testCase) {
        int[] sol = new int[2];
        Arrays.fill(sol, 0);

        int[][] grid = initGrid(numRows, numCols, waterCells);

        if (grid[0][0] == 0) {
            dfs(grid, jumpSetup, sol, 0, 0);
        }
        System.out.printf("Case %d: %d %d\n", testCase, sol[1], sol[0]);
    }

    private static void dfs(int[][] grid, Point[] jumpSetup, int[] sol, int currentRow, int currentCol) {
        grid[currentRow][currentCol] = 1;
        Map<Point, Boolean> marked = new HashMap<>();

        int[][] direction = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int eachDir = 0; eachDir < direction.length; eachDir++) {
            int newRow = currentRow + direction[eachDir][0] * jumpSetup[0].getRowId();
            int newCol = currentCol + direction[eachDir][1] * jumpSetup[0].getColId();
            Point newPoint = new Point(newRow, newCol);
            exploreNewPoint(grid, jumpSetup, marked, sol, newRow, newCol, newPoint, currentRow, currentCol);

            newRow = currentRow + direction[eachDir][0] * jumpSetup[1].getRowId();
            newCol = currentCol + direction[eachDir][1] * jumpSetup[1].getColId();
            newPoint = new Point(newRow, newCol);
            exploreNewPoint(grid, jumpSetup, marked, sol, newRow, newCol, newPoint, currentRow, currentCol);
        }

        sol[grid[currentRow][currentCol] & 1]++;
    }

    private static void exploreNewPoint(int[][] grid, Point[] jumpSetup, Map<Point, Boolean> marked, int[] sol, int newRow, int newCol, Point newPoint, int currentRow, int currentCol) {
        if (isValidDfsMove(grid, marked, newRow, newCol, newPoint)) {
            grid[currentRow][currentCol]++;
            System.out.printf("(%d, %d) -> (%d, %d)\n", currentRow, currentCol, newRow, newCol);

            marked.put(newPoint, Boolean.TRUE);
            if (grid[newRow][newCol] == 0) {
                dfs(grid, jumpSetup, sol, newRow, newCol);
            }
        }
    }

    private static boolean isValidDfsMove(int[][] grid, Map<Point, Boolean> marked, int newRow, int newCol, Point newPoint) {
        return newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                && grid[newRow][newCol] != -1
                && (marked.get(newPoint) == null || !marked.get(newPoint));
    }

    private static int[][] initGrid(int numRows, int numCols, Point[] waterCells) {
        int[][] grid = new int[numRows][numCols];
        for (int eachRow = 0; eachRow < numRows; eachRow++) {
            for (int eachCol = 0; eachCol < numCols; eachCol++) {
                boolean isWater = false;

                for (int eachWaterCell = 0; eachWaterCell < waterCells.length; eachWaterCell++) {
                    if (waterCells[eachWaterCell].getRowId() == eachRow && waterCells[eachWaterCell].getColId() == eachCol) {
                        isWater = true;
                        break;
                    }
                }

                if (isWater) {
                    grid[eachRow][eachCol] = -1;
                } else {
                    grid[eachRow][eachCol] = 0;
                }
            }
        }
        return grid;
    }

    private static Point[] createWaterCells(Scanner input) {
        int numWaterCells = input.nextInt();
        Point[] waterCells = new Point[numWaterCells];
        for (int eachWaterCell = 0; eachWaterCell < numWaterCells; eachWaterCell++) {
            waterCells[eachWaterCell] = new Point(input.nextInt(), input.nextInt());
        }
        return waterCells;
    }

    private static Point[] createJumpSetup(Scanner input) {
        int rowJump = input.nextInt();
        int colJump = input.nextInt();

        Point[] jumpSetup = new Point[2];
        jumpSetup[0] = new Point(rowJump, colJump);
        jumpSetup[1] = new Point(colJump, rowJump);
        return jumpSetup;
    }

    private static void solve(int numRows, int numCols, Point[] jumpSetup, Point[] waterCells, int testCase) {
        int[] direction = {1, -1};
        int evenCellCount = 0;
        int oddCellCount = 0;

        for (int eachRow = 0; eachRow < numRows; eachRow++) {
            for (int eachCol = 0; eachCol < numCols; eachCol++) {
                if (hasWater(eachRow, eachCol, waterCells)) {
                    continue;
                }
                int cellCount = 0;
                for (int eachJumpSetup = 0; eachJumpSetup < jumpSetup.length; eachJumpSetup++) {
                    for (int eachDir = 0; eachDir < direction.length; eachDir++) {
                        int rowComp = direction[eachDir] * jumpSetup[eachJumpSetup].getRowId();

                        for (int eachDirCol = 0; eachDirCol < direction.length; eachDirCol++) {
                            int colComp = direction[eachDirCol] * jumpSetup[eachJumpSetup].getColId();
                            int nextRow = eachRow + rowComp;
                            int nextCol = eachCol + colComp;

                            if (isValidMove(nextRow, nextCol, waterCells, numRows, numCols)) {
                                ++cellCount;
//                                System.out.printf("(%d, %d) -> (%d, %d) \n", eachRow, eachCol, nextRow, nextCol, rowComp, colComp);
                            } else {
//                                System.out.printf("InValid : (%d, %d) -> (%d, %d) using (%d, %d)\n", eachRow, eachCol, nextRow, nextCol, rowComp, colComp);
                            }
                        }
                    }
                }

                if (cellCount > 0) {
                    if (cellCount % 2 == 0) {
//                        System.out.printf("(%d, %d) is even. With count %d \n", eachRow, eachCol, cellCount);
                        ++evenCellCount;
                    } else {
//                        System.out.printf("(%d, %d) is odd. With count %d \n", eachRow, eachCol, cellCount);
                        ++oddCellCount;
                    }
//                    System.out.println();
                }
            }
        }
        System.out.println("Case " + testCase + ": " + evenCellCount + " " + oddCellCount);
    }

    private static boolean hasWater(int eachRow, int eachCol, Point[] waterCells) {
        for (int i = 0; i < waterCells.length; i++) {
            if (waterCells[i].getRowId() == eachRow && waterCells[i].getColId() == eachCol) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidMove(int newRow, int newCol, Point[] waterCells, int numRows, int numCols) {
        for (int i = 0; i < waterCells.length; i++) {
            if (newRow == waterCells[i].getRowId() && newCol == waterCells[i].getColId()) {
                return false;
            }
        }

        return newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols;
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
    public String toString() {
        return "(" + rowId + ", " + colId + ")";
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
}
