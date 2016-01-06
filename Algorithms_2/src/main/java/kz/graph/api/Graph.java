package kz.graph.api;

import java.util.*;

/**
 * Created by mukhan on 18/12/2015.
 */
public class Graph {

    private int numVerts;
    private int numEdges;
    private Map<Integer, ArrayList<Integer>> adjList;

    public Graph(int numVerts) {
        this.numVerts = numVerts;
        initAdjList(numVerts);
    }

    private void initAdjList(int numVerts) {
        this.adjList = new HashMap<>();
        for (int i = 0; i < numVerts; i++) {
            this.adjList.put(i, new ArrayList<Integer>());
        }
    }

    public Graph(String fileName) {
        initFromFile(fileName);
    }

    private void initFromFile(String fileName) {
        Scanner input = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        this.numVerts = input.nextInt();
        this.numEdges = input.nextInt();
        initAdjList(this.numVerts);
        for (int i = 0; i < this.numEdges; i++) {
            addEdge(input.nextInt(), input.nextInt());
        }
        input.close();
    }

    public void addEdge(Integer fromVert, Integer toVert) {
        if(this.adjList.get(fromVert) == null) {
            this.adjList.put(fromVert, new ArrayList<Integer>());
        }
        this.adjList.get(fromVert).add(toVert);

        if(this.adjList.get(toVert) == null) {
            this.adjList.put(toVert, new ArrayList<Integer>());
        }
        this.adjList.get(toVert).add(fromVert);
    }

    public List<Integer> getAdjList(Integer vert) {
        return this.adjList.get(vert);
    }

    public int getNumVerts() {
        return this.numVerts;
    }

    public int getNumEdges() {
        return this.numEdges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Integer eachVert : this.adjList.keySet()) {
            sb.append(eachVert + " -> ");
            for(Integer eachConnectedEdge : getAdjList(eachVert)) {
                sb.append(eachConnectedEdge + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
