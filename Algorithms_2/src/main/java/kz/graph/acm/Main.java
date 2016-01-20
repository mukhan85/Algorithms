package kz.graph.acm;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Main {

    public static void main(String... args) {
        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream("input"));
//        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {

            int numVerts = Integer.parseInt(input.nextLine());
            if (numVerts == 0) {
                break;
            }

            Graph graph = new Graph(numVerts);
            while (true) {
                int[] data = getData(input);

                if (data.length == 1 && data[0] == -1) {
                    solve(graph);
                    break;
                } else {
                    addEdges(graph, data);
                }
            }
        }
        input.close();
    }

    private static void addEdges(Graph graph, int[] data) {
        for (int i = 1; i < data.length; i++) {
            graph.addEdge(data[0], data[i]);
        }
    }

    private static void solve(Graph graph) {
        DfsProcessor dfsProcessor = new DfsProcessor(graph);
        System.out.println(dfsProcessor.countArticulationPoints());
    }

    private static int[] getData(Scanner input) {
        String[] line = input.nextLine().split("\\s");
        int[] data = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            data[i] = Integer.parseInt(line[i]) - 1;
        }
        return data;
    }
}

class DfsProcessor {
    private static final int DFS_WHITE = -1;

    private final Graph graph;
    private final int[] dfsLow;
    private final int[] dfsNum;
    private int articulationPointCounter;
    private int visitCounter = 0;
    private int[] dfsParent;
    private final boolean [] articulationVert;
    private int root;
    private int rootChildrenCounter;

    public DfsProcessor(Graph graph) {
        this.graph = graph;
        dfsLow = new int[graph.getNumVerts()];
        dfsNum = new int[graph.getNumVerts()];
        this.articulationVert = new boolean[graph.getNumVerts()];
        Arrays.fill(this.dfsLow, DFS_WHITE);
        Arrays.fill(this.dfsNum, DFS_WHITE);
        this.articulationPointCounter = 0;
        dfsParent = new int[graph.getNumVerts()];

        for (int i = 0; i < graph.getNumVerts(); i++) {
            if (dfsNum[i] == DFS_WHITE) {
                root = i;
                dfs(i);
                this.articulationVert[i] = (rootChildrenCounter > 1);
            }
        }
    }

    private void dfs(int currentVert) {
        dfsNum[currentVert] = visitCounter;
        dfsLow[currentVert] = visitCounter;
        ++visitCounter;

        for (Integer eachNeighbour : graph.getAdjList(currentVert)) {
            if (dfsNum[eachNeighbour] == DFS_WHITE) {
                dfsParent[eachNeighbour] = currentVert;
                if (currentVert == root) {
                    ++rootChildrenCounter;
                }

                dfs(eachNeighbour);

                if (dfsLow[eachNeighbour] >= dfsNum[currentVert]) {
                    this.articulationVert[currentVert] = true;
                }
                dfsLow[currentVert] = Math.min(dfsLow[currentVert], dfsLow[eachNeighbour]);
            } else if (dfsParent[eachNeighbour] != currentVert) {
                // Found a cycle.
                dfsLow[currentVert] = Math.min(dfsLow[currentVert], dfsNum[eachNeighbour]);
            }
        }
    }

    public int countArticulationPoints() {
        for (int i = 0; i < this.articulationVert.length; i++) {
            if (articulationVert[i]) {
                ++this.articulationPointCounter;
            }
        }
        return this.articulationPointCounter;
    }
}

class Graph {
    private final Map<Integer, List<Integer>> adjList;
    private final int numVerts;

    public Graph(int numVerts) {
        this.numVerts = numVerts;
        this.adjList = new HashMap<>(numVerts);
        for (int i = 0; i < numVerts; i++) {
            adjList.put(i, new ArrayList<Integer>());
        }
    }

    public void addEdge(int fromVert, int toVert) {
        this.adjList.get(fromVert).add(toVert);
        this.adjList.get(toVert).add(fromVert);
    }

    public List<Integer> getAdjList(Integer fromVert) {
        return adjList.get(fromVert);
    }

    public int getNumVerts() {
        return numVerts;
    }
}