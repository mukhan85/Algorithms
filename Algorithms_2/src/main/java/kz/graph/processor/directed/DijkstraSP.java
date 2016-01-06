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

        Queue<WeightedNeighbourVertex> sortedDistanceQueue = new PriorityQueue<>(graph.getNumEdges(), new Comparator<WeightedNeighbourVertex>() {
            @Override public int compare(WeightedNeighbourVertex o1, WeightedNeighbourVertex o2) {
                return o1.getWeight() - o2.getWeight();
            }});

        sortedDistanceQueue.add(new WeightedNeighbourVertex(this.sourceVert, 0));

        while (!sortedDistanceQueue.isEmpty()) {
            WeightedNeighbourVertex fromVert = sortedDistanceQueue.poll();
            Integer fromVertId = fromVert.getVertId();

            for (WeightedNeighbourVertex eachAdjVert : graph.getAdjList(fromVert.getVertId())) {
                Integer adjVertId = eachAdjVert.getVertId();
                Integer distance = eachAdjVert.getWeight();

                if(shortestDist[adjVertId] > shortestDist[fromVertId] + distance) {
                    shortestDist[adjVertId] = shortestDist[fromVertId] + distance;
                    pathTo[adjVertId] = fromVertId;
                    WeightedNeighbourVertex relaxedVert = new WeightedNeighbourVertex(adjVertId, shortestDist[adjVertId]);

                    sortedDistanceQueue.add(relaxedVert);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(DijkstraSP.class.getClassLoader().getResourceAsStream("dijkstra_sp_input"));
        WeightedDigraph graph = initGraphFromFile(input);
        graph.printGraph();

        DijkstraSP dijkstraProcesor = new DijkstraSP(graph);
        int[] distance = dijkstraProcesor.getShortestDistances();

        for (int i = 0; i < distance.length; i++) {
            System.out.printf("(%d -> %d): %d\n", graph.getSourceVert(), i, distance[i]);
        }

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

    private final Map<Integer, List<WeightedNeighbourVertex>> adjList;

    public WeightedDigraph(Integer numVerts, Integer numEdges, Integer sourceVert) {
        this.numVerts = numVerts;
        this.numEdges = numEdges;
        this.sourceVert = sourceVert;
        this.adjList = new HashMap<>(numVerts);
        for (int eachVert = 0; eachVert < numVerts; eachVert++) {
            this.adjList.put(eachVert, new ArrayList<>());
        }
    }

    public void addEdge(Integer fromVert, Integer toVert, Integer weigth) {
        this.adjList.get(fromVert).add(new WeightedNeighbourVertex(toVert, weigth));
    }

    public List<WeightedNeighbourVertex> getAdjList(Integer fromVert) {
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
        for (int i = 0; i < numVerts; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < this.adjList.get(i).size() - 1; j++) {
                System.out.print(this.adjList.get(i).get(j) + " -> ");
            }

            if(!this.adjList.get(i).isEmpty()) {
                System.out.println(this.adjList.get(i).get(adjList.get(i).size() - 1));
            } else {
                System.out.println("()");
            }
        }
    }
}

class WeightedNeighbourVertex {
    private final Integer toVert;
    private final Integer weight;

    public WeightedNeighbourVertex(Integer toVert, Integer weight) {
        this.toVert = toVert;
        this.weight = weight;
    }

    public Integer getVertId() {
        return toVert;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + toVert + ", " + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedNeighbourVertex that = (WeightedNeighbourVertex) o;

        if (toVert != null ? !toVert.equals(that.toVert) : that.toVert != null) return false;
        return weight != null ? weight.equals(that.weight) : that.weight == null;
    }

    @Override
    public int hashCode() {
        int result = toVert != null ? toVert.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}