package kz.graph.processor.directed;

import java.util.*;

/**
 * Created by mmyrzaku on 06/01/2016.
 */
public class DijkstraSP {

    private final WeightedDigraph graph;
    private final Integer sourceVert;
    private final int[] shortestDist;
    private final int[] pathTo;

    public DijkstraSP(WeightedDigraph graph) {
        this.graph = graph;
        this.sourceVert = graph.getSourceVert();
        this.pathTo = new int[graph.getNumVerts()];
        this.shortestDist = new int[graph.getNumVerts()];

        runShortestPath();
    }

    private void runShortestPath() {
        Arrays.fill(this.shortestDist, INF);
        this.shortestDist[this.sourceVert] = 0;

        PriorityQueue<WeightedDirectedEdge> sortedDistanceQueue = new PriorityQueue<>(graph.getNumEdges(), new Comparator<WeightedDirectedEdge>() {
            @Override public int compare(WeightedDirectedEdge o1, WeightedDirectedEdge o2) {
                return o1.getWeight() - o2.getWeight();
            }});

        sortedDistanceQueue.add(new WeightedDirectedEdge(this.sourceVert, this.sourceVert, 0));
        while (!sortedDistanceQueue.isEmpty()) {
            WeightedDirectedEdge currentEdge = sortedDistanceQueue.poll();

            if (shortestDist[currentEdge.getToVert()] < shortestDist[currentEdge.getFromVert()] + currentEdge.getWeight()) {
                System.out.println("Skipping : " + currentEdge);
                continue;
            }

            for (WeightedDirectedEdge eachAjdEdge : graph.getAdjList(currentEdge.getToVert())) {
                if(shortestDist[eachAjdEdge.getToVert()] > shortestDist[currentEdge.getFromVert()] + eachAjdEdge.getWeight()) {
                    pathTo[eachAjdEdge.getToVert()] = currentEdge.getFromVert();
                    shortestDist[eachAjdEdge.getToVert()] = shortestDist[currentEdge.getFromVert()] + eachAjdEdge.getWeight();
                    WeightedDirectedEdge relaxedEdge = new WeightedDirectedEdge(currentEdge.getFromVert(), eachAjdEdge.getToVert(), shortestDist[eachAjdEdge.getToVert()]);
                    System.out.println("Adding : " + relaxedEdge);
                    sortedDistanceQueue.add(relaxedEdge);
                } else {
                    System.out.println("already have shorter path: " + eachAjdEdge);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(DijkstraSP.class.getClassLoader().getResourceAsStream("dijkstra_sp_input"));
        WeightedDigraph graph = initGraphFromFile(input);
        graph.printGraph();

        DijkstraSP dijkstraProcesor = new DijkstraSP(graph);
        System.out.println(Arrays.toString(dijkstraProcesor.getShortestDistances()));
        System.out.println(Arrays.toString(dijkstraProcesor.getPaths()));
        input.close();
    }

    public int[] getShortestDistances() {
        return this.shortestDist;
    }

    private static WeightedDigraph initGraphFromFile(Scanner input) {
        Integer numVerts = input.nextInt();
        Integer numEdges = input.nextInt();
        Integer sourceVert = input.nextInt();

        WeightedDigraph graph = new WeightedDigraph(numVerts, numEdges, sourceVert);

        for (int i = 0; i < numEdges; i++) {
            Integer fromVert = input.nextInt();
            Integer toVert = input.nextInt();
            Integer weight = input.nextInt();
            graph.addEdge(fromVert, toVert, weight);
        }
        return graph;
    }

    public static final int INF = 1000000000;

    public int[] getPaths() {
        return this.pathTo;
    }
}


class WeightedDigraph {

    private final Integer numVerts;
    private final Integer numEdges;
    private final Integer sourceVert;

    private final Map<Integer, List<WeightedDirectedEdge>> adjList;

    public WeightedDigraph(Integer numVerts, Integer numEdges, Integer sourceVert) {
        this.numVerts = numVerts;
        this.numEdges = numEdges;
        this.sourceVert = sourceVert;
        this.adjList = new HashMap<>(numVerts);
        for (int eachVert = 0; eachVert < numVerts; eachVert++) {
            this.adjList.put(eachVert, new ArrayList<WeightedDirectedEdge>());
        }
    }

    public void addEdge(Integer fromVert, Integer toVert, Integer weigth) {
        this.adjList.get(fromVert).add(new WeightedDirectedEdge(fromVert, toVert, weigth));
    }

    public List<WeightedDirectedEdge> getAdjList(Integer fromVert) {
        return  this.adjList.get(fromVert);
    }

    public Integer getNumVerts() {
        return numVerts;
    }

    public Integer getSourceVert() {
        return sourceVert;
    }

    public Integer getNumEdges() {
        return numEdges;
    }

    public void printGraph() {
        for (Integer eachVert : adjList.keySet()) {
            System.out.print(eachVert + " : ");
            for (WeightedDirectedEdge eachAdjEdge : adjList.get(eachVert)) {
                System.out.print(eachAdjEdge + " -> ");
            }
            System.out.println();
        }
    }
}

class WeightedDirectedEdge {

    private final Integer fromVert;
    private final Integer toVert;
    private final Integer weight;

    public WeightedDirectedEdge(Integer fromVert, Integer toVert, Integer weight) {
        this.fromVert = fromVert;
        this.toVert = toVert;
        this.weight = weight;
    }

    public Integer getToVert() {
        return toVert;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getFromVert() {
        return fromVert;
    }

    @Override
    public String toString() {
        return "{" + fromVert + " -> " + toVert + ":" + weight + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedDirectedEdge that = (WeightedDirectedEdge) o;

        if (fromVert != null ? !fromVert.equals(that.fromVert) : that.fromVert != null) return false;
        if (toVert != null ? !toVert.equals(that.toVert) : that.toVert != null) return false;
        return weight != null ? weight.equals(that.weight) : that.weight == null;

    }

    @Override
    public int hashCode() {
        int result = fromVert != null ? fromVert.hashCode() : 0;
        result = 31 * result + (toVert != null ? toVert.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}