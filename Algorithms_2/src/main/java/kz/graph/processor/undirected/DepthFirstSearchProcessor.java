package kz.graph.processor.undirected;

import kz.graph.api.Graph;

import java.util.Stack;

/**
 * Created by mukhan on 18/12/2015.
 */
public class DepthFirstSearchProcessor {
    private boolean [] visited;
    private int[] pathTo;
    private Integer startVert;

    private final Graph graph;

    public DepthFirstSearchProcessor(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getNumVerts()];
        this.pathTo = new int[graph.getNumVerts()];
        this.startVert = null;
    }

    public void dfs(Integer sourceVert) {
        if(this.startVert == null) {
            this.startVert = sourceVert;
        }

        this.visited[sourceVert] = true;

        for(Integer eachAdjVert : graph.getAdjList(sourceVert)) {
            if(!this.visited[eachAdjVert]) {

                dfs(eachAdjVert);
                this.pathTo[eachAdjVert] = sourceVert;
            }
        }
    }

    public boolean hasPathTo(Integer destVert) {
        return this.visited[destVert];
    }

    public Iterable<Integer> getPath(Integer destVert) {

        Stack<Integer> path = new Stack<>();
        if(!hasPathTo(destVert)) {
            return path;
        }

        for(Integer x = destVert; x != this.startVert; x = this.pathTo[x]) {
            path.push(x);
        }
        path.push(this.startVert);
        return path;
    }

}
