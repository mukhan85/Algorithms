package coursera.princeton.algorithms;

import java.util.Arrays;

/**
 * Created by mmyrzaku on 01/02/2016.
 */
public class Percolation {

    public static final int BLOCKED = 0;
    public static final int EMPTY_OPEN = 1;
    public static final int FULL_OPEN = 2;

    public final int size;
    private final int[][] grid;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid grid size");
        }

        this.size = N + 1;
        this.grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(grid, BLOCKED);
        }

        for (int i = 0; i < size; i++) {
            grid[0][i] = EMPTY_OPEN;
            grid[i][0] = EMPTY_OPEN;
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            openUp(i, j);
        }
    }

    private void openUp(int i, int j) {
        this.grid[i][j] = EMPTY_OPEN;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return this.grid[i][j] == EMPTY_OPEN;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return this.grid[i][j] == FULL_OPEN;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    public static void main(String[] args) {

    }
}
