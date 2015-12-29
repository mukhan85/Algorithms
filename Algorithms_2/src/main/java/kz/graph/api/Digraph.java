package kz.graph.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by mukhan on 24/12/2015.
 */
public class Digraph {

    private int numEdges;
    private int numVerts;
    private Map<Integer, List<Integer>> adjList;

    public Digraph(String fileName) {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        this.numVerts = sc.nextInt();
        this.numEdges = sc.nextInt();
        initGraph();

        for (int i = 0; i < numEdges; i++) {
            Integer fromVert = sc.nextInt();
            Integer toVert = sc.nextInt();
            addEdge(fromVert, toVert);
        }
        sc.close();
    }

    private void initGraph() {
        for (int i = 0; i < this.numVerts; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(Integer fromVert, Integer toVert) {
        this.adjList.get(fromVert).add(toVert);
    }

    public List<Integer> getAdjList(Integer fromVert) {
        return this.adjList.get(fromVert);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVerts; i++) {
            sb.append(i + " : ");
            for (Integer adjVert : getAdjList(i)) {
                sb.append(adjVert + " -> " );
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getNumVerts() {
        return numVerts;
    }
}
