package kz.backtracking;

import java.util.Arrays;

/**
 * Input:
 * 1) A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix
 * representation of the graph. A value graph[i][j] is 1 if there is a
 * direct edge from i to j, otherwise graph[i][j] is 0.
 * <p>
 * 2) An integer m which is maximum number of colors that can be used.
 * <p>
 * Output:
 * An array color[V] that should have numbers from 1 to m. color[i] should represent
 * the color assigned to the ith vertex. The code should also return false
 * if the graph cannot be colored with m colors.
 */

public class GraphColorer {
    private static final int UNCOLORED = 0;

    private final int[] colors;
    private final int numColors;
    private final int[][] graph;
    private final int numVerts;

    public GraphColorer(int[][] graph, int numColors) {
        this.graph = graph;
        this.numVerts = graph.length;
        this.colors = new int[numVerts];
        this.numColors = numColors;
    }

    public static void main(String args[]) {
///
//  Create following graph and test whether it is
//  3 colorable
//  (3)---(2)
//   |   / |
//   |  /  |
//   | /   |
//  (0)---(1)
        int numColors = 3;
        int graph[][] = {{0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}};

        GraphColorer colorer = new GraphColorer(graph, numColors);
//        System.out.println(colorer.graphColoring());
        colorer.graphColoringFull(0);
    }

    private boolean graphColoring() {

        if (!graphColoringUtil(0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution();
        return true;
    }

    private void printSolution() {
        System.out.println(Arrays.toString(this.colors));
    }

    private void graphColoringFull(int vertId) {
        if (vertId >= numVerts) {
            printSolution();
            return;
        }

        for (int tryColor = 1; tryColor <= numColors; tryColor++) {
            if (isValidColoring(vertId, tryColor)) {
                this.colors[vertId] = tryColor;
                graphColoringFull(vertId + 1);
                this.colors[vertId] = UNCOLORED;
            }
        }
    }

    private boolean graphColoringUtil(int vertId) {
        // base we were able to color each vertex.
        if (vertId == this.numVerts) {
            return true;
        }

        for (int tryColor = 1; tryColor <= numColors; tryColor++) {
            if (isValidColoring(vertId, tryColor)) {
                colors[vertId] = tryColor;
                if (graphColoringUtil(vertId + 1)) {
                    return true;
                } else {
                    colors[vertId] = UNCOLORED;
                }
            }
        }
        return false;
    }

    private boolean isValidColoring(int vertId, int colorNum) {
        for (int i = 0; i < numVerts; i++) {
            if (this.graph[vertId][i] == 1 && colorNum == this.colors[i]) {
                return false;
            }
        }
        return true;
    }
}
