package kz.graph.processor.directed;

import kz.graph.api.Digraph;

import java.util.Stack;

/**
 * Created by mukhan on 24/12/2015.
 */
public class TopologicalSortProcessor {

    private final Digraph graph;
    private final boolean [] marked;
    private Stack<Integer> reversePost;

    public TopologicalSortProcessor(Digraph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.getNumVerts()];
    }

    private void dfs(Integer source) {
        marked[source] = true;
        for(Integer eachAdjVert : graph.getAdjList(source)) {
            if(!marked[eachAdjVert]) {
                dfs(eachAdjVert);
            }
            reversePost.push(source);
        }
    }

    public Iterable<Integer> reversePost() {
        return this.reversePost;
    }

}
