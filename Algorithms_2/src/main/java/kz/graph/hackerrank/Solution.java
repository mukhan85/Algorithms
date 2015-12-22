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
        for (int i = 0; i < numTests; i++) {
            int numVerts = input.nextInt();
            int numEdges = input.nextInt();

            Graph graph = new Graph(numVerts, numEdges);
            for (int j = 0; j < numEdges; j++) {
                int fromVert = input.nextInt() - 1;
                int toVert = input.nextInt() - 1;
                graph.addEdge(fromVert, toVert);
            }

            int startVert = input.nextInt() - 1;
            graph.printShortestPaths(startVert);
        }
        input.close();
    }
}

class Graph {
    private final int numVerts;
    private final int numEdges;
    private final int[] distTo;
    private final boolean[] visited;
    private final Map<Integer, List<Integer>> adjList;

    private final Queue<Integer> queue = new LinkedList<>();

    public Graph(int numVerts, int numEdges) {
        this.numVerts = numVerts;
        this.numEdges = numEdges;
        this.adjList = new HashMap<>();
        this.distTo = new int[numVerts];
        this.visited = new boolean[numVerts];
        initGraph();
    }

    private void initGraph() {
        for (int i = 0; i < numVerts; i++) {
            this.adjList.put(i, new ArrayList<>());
            this.distTo[i] = -1;
        }
    }

    public void addEdge(Integer fromVert, Integer toVert) {
        getAdjList(fromVert).add(toVert);
        getAdjList(toVert).add(fromVert);
    }

    public List<Integer> getAdjList(Integer fromVert) {
        return this.adjList.get(fromVert);
    }

    public void bfs(Integer sourceVert) {
        visited[sourceVert] = true;
        this.queue.add(sourceVert);

        while(!queue.isEmpty()) {
            Integer nextVert = queue.poll();
            for(Integer eachAdjVert : getAdjList(nextVert)) {
                if(!visited[eachAdjVert]) {
                    visited[eachAdjVert] = true;
                    if(distTo[nextVert] == -1) {
                        distTo[nextVert] = 0;
                    }
                    distTo[eachAdjVert] = distTo[nextVert] + EDGE_LENGTH;
                    queue.add(eachAdjVert);
                }
            }
        }
    }

    public void printShortestPaths(int startVert) {
        bfs(startVert);

        for (int i = 0; i < numVerts; i++) {
            if(i != startVert) {
                System.out.print(distTo[i] + " ");
            }
        }
        System.out.println();
    }

    private final int EDGE_LENGTH = 6;
}