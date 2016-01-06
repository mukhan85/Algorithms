package kz.graph.processor.directed;

import kz.graph.api.Digraph;

import java.util.Stack;

/**
 * Created by mukhan on 27/12/2015.
 */
public class CycleDetector {

    private final Digraph graph;
    private final boolean[] marked;
    private final int [] edgeTo;
    private final boolean[] onStack;
    private Stack<Integer> cyclePath;

    public CycleDetector(Digraph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.getNumVerts()];
        this.edgeTo = new int[graph.getNumVerts()];
        this.onStack = new boolean[graph.getNumVerts()];

        for (int i = 0; i < graph.getNumVerts(); i++) {
            if(!marked[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(Integer fromVert) {
        this.onStack[fromVert] = true;
        this.marked[fromVert] = true;

        for(Integer nextVert : graph.getAdjList(fromVert)) {
            if(hasCycle()) {
                return;
            } else if(!marked[nextVert]) {
                this.edgeTo[nextVert] = fromVert;
                dfs(nextVert);
            } else if(onStack[nextVert]) {
                this.cyclePath = new Stack<>();
                for (int x = fromVert; x != nextVert; x = edgeTo[x]) {
                    this.cyclePath.push(x);
                }
                this.cyclePath.push(nextVert);
                this.cyclePath.push(fromVert);
            }
        }

        this.onStack[fromVert] = false;
    }

    public boolean hasCycle() {
        return this.cyclePath != null;
    }

    public Iterable<Integer> getCyclePath() {
        return this.cyclePath;
    }

}