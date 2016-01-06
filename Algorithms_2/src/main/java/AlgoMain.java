import kz.graph.api.Digraph;
import kz.graph.api.Graph;
import kz.graph.processor.directed.CycleDetector;
import kz.graph.processor.directed.TopologicalSortProcessor;
import kz.graph.processor.undirected.BreadthFirstSearchProcessor;
import kz.graph.processor.undirected.DepthFirstSearchProcessor;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by mukhan on 18/12/2015.
 */

public class AlgoMain {

    public static void main(String ... args) throws FileNotFoundException {
        Digraph graph = new Digraph("graph_input");
        System.out.println(graph);
        runCycleDetector(graph);
    }

    private static void runCycleDetector(Digraph graph) {
        CycleDetector cycleDetector = new CycleDetector(graph);
        if(cycleDetector.hasCycle()) {
            for(Integer eachVert : cycleDetector.getCyclePath()) {
                System.out.print(eachVert + " ");
            }
        } else {
            System.out.println("No Cycle detected.");
            TopologicalSortProcessor topProcessor = new TopologicalSortProcessor(graph);
            System.out.println("Topological order: ");
            for(Integer eachVert : topProcessor.reversePost()) {
                System.out.print(eachVert + " ");
            }
            System.out.println();
        }
    }

    private static void runTopologicalSort(Digraph graph) {
        TopologicalSortProcessor topProcessor = new TopologicalSortProcessor(graph);
        System.out.println("Running topological sort: ");
        for(Integer eachVert : topProcessor.reversePost()) {
            System.out.print(eachVert + " ");
        }
    }

    private static void runBFS(Graph graph) {
        BreadthFirstSearchProcessor bfsProcessor = new BreadthFirstSearchProcessor(graph);
        bfsProcessor.bfs(0);

        System.out.println(Arrays.toString(bfsProcessor.getDistance()));
        System.out.println(Arrays.toString(bfsProcessor.getPath()));
    }

    private static void runDFS(Graph graph) {
        DepthFirstSearchProcessor dfsProcessor = new DepthFirstSearchProcessor(graph);
        dfsProcessor.dfs(10);

        for (int i = 0; i < graph.getNumVerts(); i++) {
            System.out.print(i + " : ");
            for(Integer eachVert : dfsProcessor.getPath(i)) {
                System.out.print(eachVert + " -> ");
            }
            System.out.println("\n---------");
        }
    }
}
