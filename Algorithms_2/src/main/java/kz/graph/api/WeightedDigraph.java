package kz.graph.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mukhan on 03/01/2016.
 */
public class WeightedDigraph {

    private final int numVerts;
    private final Map<Integer, List<WeightedEdge>> adjList;

    public WeightedDigraph(int numVerts) {
        this.numVerts = numVerts;
        this.adjList = new HashMap<>();
        for (int i = 0; i < numVerts; i++) {
            this.adjList.put(i, new ArrayList<WeightedEdge>());
        }
    }

    public void addEdge(Integer fromVert, Integer toVert, Double weight) {
        this.adjList.get(fromVert).add(new WeightedEdge(fromVert, toVert, weight));
    }
}

class WeightedEdge {

    private final Integer fromVert;
    private final Integer toVert;
    private final Double weight;

    public WeightedEdge(Integer fromVert, Integer toVert, Double weight) {
        this.fromVert = fromVert;
        this.toVert = toVert;
        this.weight = weight;
    }

    public Integer getFromVert() {
        return fromVert;
    }

    public Integer getToVert() {
        return toVert;
    }

    public Double getWeight() {
        return weight;
    }
}
