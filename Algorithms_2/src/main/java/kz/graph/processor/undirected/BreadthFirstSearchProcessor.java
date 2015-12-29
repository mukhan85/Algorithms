package kz.graph.processor.undirected;

import kz.graph.api.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by mukhan on 18/12/2015.
 */

public class BreadthFirstSearchProcessor {
    private final Graph graph;

    private Queue<Integer> queue;
    private boolean[] visited;
    private Integer[] edgeTo;
    private int[] distTo;
    private Integer startVert;

    public BreadthFirstSearchProcessor(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getNumVerts()];
        this.queue = new LinkedList<>();
        this.edgeTo = new Integer[graph.getNumVerts()];
        this.distTo = new int[graph.getNumVerts()];
        Arrays.fill(this.distTo, 0);
    }

    public void bfs(Integer sourceVert) {
        this.startVert = sourceVert;
        this.visited[sourceVert] = true;

        this.queue = new LinkedList<>();
        this.queue.add(sourceVert);

        while(!this.queue.isEmpty()) {
            Integer nextVert = this.queue.poll();

            for(Integer eachAdjVert : graph.getAdjList(nextVert)) {
                if(!this.visited[eachAdjVert]) {
                    this.queue.add(eachAdjVert);

                    this.visited[eachAdjVert] = true;
                    this.distTo[eachAdjVert] = distTo[nextVert] + 1;
                    this.edgeTo[eachAdjVert] = nextVert;
                }
            }
        }
    }

    public Iterable<Integer> getPathTo(Integer destination) {
        Stack<Integer> path = new Stack<>();
        if(!hasPathTo(destination)) {
            return  path;
        }

        for(Integer x = destination; x != this.startVert; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(destination);

        return path;
    }

    private boolean hasPathTo(Integer destination) {
        return visited[destination];
    }

    public int[] getDistance() {
        return this.distTo;
    }

    public Integer[] getPath() {
        return this.edgeTo;
    }
}
