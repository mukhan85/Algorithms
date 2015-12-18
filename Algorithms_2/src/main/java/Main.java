import kz.graph.api.Graph;
import kz.graph.processor.BreadthFirstSearchProcessor;
import kz.graph.processor.DepthFirstSearchProcessor;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by mukhan on 18/12/2015.
 */

public class Main {
    public static void main(String ... args) throws FileNotFoundException {
        Graph graph = new Graph("tinyGraphInput");
        System.out.println(graph);

//        runDFS(graph);

        runBFS(graph);
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
