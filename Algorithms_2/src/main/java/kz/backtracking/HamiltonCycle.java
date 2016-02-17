package kz.backtracking;

/**
 * Created by mmyrzaku on 16/02/2016.
 */
class HamiltonianCycle {
    private final int numVerts = 5;
    int path[];

    /**
     *  A utility function to check if the vertex v can be
     *  added at index 'pos'in the Hamiltonian Cycle
     *  constructed so far (stored in 'path[]')
     */
    boolean isSafe(int nextVert, int graph[][], int path[], int currentVert) {
        /* Check if this vertex is an adjacent vertex of the previously added vertex. */
        if (graph[path[currentVert - 1]][nextVert] == 0) {
            return false;
        }

        /* Check if the vertex has already been included.
           This step can be optimized by creating an array of size numVerts */
        for (int i = 0; i < currentVert; i++) {
            if (path[i] == nextVert) {
                return false;
            }
        }
        return true;
    }

    /* A recursive utility function to solve hamiltonian cycle problem */
    boolean hamCycleUtil(int graph[][], int path[], int currentVert) {
        /* base case: If all vertices are included in Hamiltonian Cycle */
        if (isSolution(currentVert)) {
            // And if there is an edge from the last included vertex to the first vertex
            if (graph[path[currentVert - 1]][path[0]] == 1) {
                return true;
            } else {
                return false;
            }
        }

        // Try different vertices as a next candidate in
        // Hamiltonian Cycle. We don't try for 0 as we
        // included 0 as starting point in in hamCycle()
        for (int eachVert = 1; eachVert < numVerts; eachVert++) {
            /* Check if this vertex can be added to Hamiltonian Cycle */
            if (isSafe(eachVert, graph, path, currentVert)) {
                path[currentVert] = eachVert;

                /* recur to construct rest of the path */
                if (hamCycleUtil(graph, path, currentVert + 1) == true) {
                    return true;
                }
                /* If adding vertex v doesn't lead to a solution then remove it */
                path[currentVert] = -1;
            }
        }

        /* If no vertex can be added to Hamiltonian Cycle constructed so far, then return false */
        return false;
    }

    private boolean isSolution(int currentVert) {
        return currentVert == numVerts;
    }

    /* This function solves the Hamiltonian Cycle problem using
       Backtracking. It mainly uses hamCycleUtil() to solve the
       problem. It returns false if there is no Hamiltonian Cycle
       possible, otherwise return true and prints the path.
       Please note that there may be more than one solutions,
       this function prints one of the feasible solutions. */
    int hamCycle(int graph[][]) {
        path = new int[numVerts];
        for (int i = 0; i < numVerts; i++) {
            path[i] = -1;
        }

        /* Let us put vertex 0 as the first vertex in the path.
           If there is a Hamiltonian Cycle, then the path can be
           started from any point of the cycle as the graph is
           undirected */
        path[0] = 0;
        if (hamCycleUtil(graph, path, 1) == false) {
            System.out.println("\nSolution does not exist");
            return 0;
        }

        printSolution(path);
        return 1;
    }

    void printSolution(int path[]) {
        System.out.println("Solution Exists: Following" + " is one Hamiltonian Cycle");
        for (int i = 0; i < numVerts; i++) {
            System.out.print(" " + path[i] + " ");
        }

        // Let us print the first vertex again to show the complete cycle
        System.out.println(" " + path[0] + " ");
    }

    // driver program to test above function
    public static void main(String args[]) {
        HamiltonianCycle hamiltonian = new HamiltonianCycle();
        /**
         * Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)-------(4)
         */
        int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

        hamiltonian.hamCycle(graph1);

        /* Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)       (4)    */
        int graph2[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };

        hamiltonian.hamCycle(graph2);
    }
}