package kz.graph.processor.directed;

import kz.graph.api.Digraph;

/**
 * Created by mukhan on 24/12/2015.
 */
public class DFSProcessor {
    private final Digraph graph;
    private final boolean [] isVisited;
    private final int [] edgeTo;

    public DFSProcessor(Digraph graph) {
        this.graph = graph;
        this.isVisited = new boolean[graph.getNumVerts()];
        this.edgeTo = new int[graph.getNumVerts()];
    }

    public void dfs(Integer source) {
        isVisited[source] = true;

        for(Integer eachAdjVert : graph.getAdjList(source)) {
            if(!isVisited[eachAdjVert]) {
                edgeTo[eachAdjVert] = source;
                dfs(eachAdjVert);
            }
        }
    }


}
