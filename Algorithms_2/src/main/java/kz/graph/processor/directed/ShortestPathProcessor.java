package kz.graph.processor.directed;

import kz.graph.api.WeightedDigraph;

import java.util.Scanner;

/**
 * Created by mukhan on 01/01/2016.
 */
public class ShortestPathProcessor {

    private final WeightedDigraph graph;
    private Double minWeight;

    public static void main(String[] args) {
        Scanner input = new Scanner(ShortestPathProcessor.class.getClassLoader().getResourceAsStream("shortestPathGraph"));
        int numVerts = input.nextInt();

        WeightedDigraph graph = new WeightedDigraph(numVerts);

        int numEdges = input.nextInt();
        for (int i = 0; i < numEdges; i++) {
            graph.addEdge(input.nextInt(), input.nextInt(), input.nextDouble());
        }

        ShortestPathProcessor spp = new ShortestPathProcessor(graph, 0);
        spp.printShortesPaths();
        System.out.printf("Total path weight: %f", spp.getMinWeight());

        input.close();
    }

    private void printShortesPaths() {

    }

    public ShortestPathProcessor(WeightedDigraph graph, Integer source) {
        this.graph = graph;

    }

    public Double getMinWeight() {
        return minWeight;
    }
}
