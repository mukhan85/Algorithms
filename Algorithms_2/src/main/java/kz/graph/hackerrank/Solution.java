package kz.graph.hackerrank;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Solution {

    public static void main(String... args) {
        Scanner input = new Scanner(Solution.class.getClassLoader().getResourceAsStream("input"));
//      Scanner input = new Scanner(System.in);

        int numVerts = input.nextInt();
        int numEdges = input.nextInt();
        Graph graph = new Graph(numVerts, numEdges);

        for (int i = 0; i < numEdges; i++) {
            int fromVert = input.nextInt() - 1;
            int toVert = input.nextInt() - 1;
            int distance = input.nextInt();
            graph.addEdge(fromVert, toVert, distance);
        }
        System.out.println(graph.getSortedEdges().size());

        int startNode = input.nextInt();

        Solution sol = new Solution();
        System.out.println(sol.mst(graph));
        input.close();
    }

    private int mst(Graph graph) {
        UnionFind uf = new UnionFind(graph.getNumVerts());
        int mstDistance = 0;

        for(Graph.Edge eachEdge : graph.getSortedEdges()) {
            if(!uf.isSame(eachEdge.getFromVert(), eachEdge.getToVert())) {
                mstDistance += eachEdge.getDistance();
                uf.union(eachEdge.getFromVert(), eachEdge.getToVert());
            }
        }

        return mstDistance;
    }
}

class UnionFind {
    private final int [] id;
    private int componentCount;

    public UnionFind(int numVerts) {
        id = new int[numVerts];
        this.componentCount = numVerts;
        for (int i = 0; i < numVerts; i++) {
            id[i] = i;
        }
    }

    public int find(int source) {
        return id[source];
    }

    public void union(int from, int to) {
        int modifyId = find(from);
        int newId = find(to);

        if(modifyId == newId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if(id[i] == modifyId) {
                id[i] = newId;
            }
        }
        --this.componentCount;
    }

    public boolean isSame(int from, int to) {
        return find(from) == find(to);
    }

    public int getComponentCount() {
        return componentCount;
    }
}

class Graph {
    private final int numVerts;
    private final int numEdges;
    private final Map<Edge, Edge> edges;

    public Graph(int numVerts, int numEdges) {
        this.numVerts = numVerts;
        this.numEdges = numEdges;
        this.edges = new TreeMap<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getDistance().compareTo(o2.getDistance());
            }
        });
    }

    public void addEdge(int fromVert, int toVert, int distance) {
        Edge newEdge = new Edge(fromVert, toVert, distance);
        Edge existingEdge = this.edges.get(newEdge);

        if(existingEdge != null) {
            System.out.println("Existing: " + existingEdge + ", NewEdge: " + newEdge);
            if(newEdge.getDistance() < existingEdge.getDistance()) {
                this.edges.put(newEdge, newEdge);
            }
        } else {
            this.edges.put(newEdge, newEdge);
        }
    }

    public Collection<Edge> getSortedEdges() {
        return edges.values();
    }

    public int getNumVerts() {
        return numVerts;
    }

    class Edge {

        private final Integer fromVert;
        private final Integer toVert;
        private final Integer distance;

        public Edge(Integer fromVert, Integer toVert, Integer distance) {
            this.fromVert = fromVert;
            this.toVert = toVert;
            this.distance = distance;
        }

        public Integer getFromVert() {
            return fromVert;
        }

        public Integer getToVert() {
            return toVert;
        }

        public Integer getDistance() {
            return distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (fromVert != null ? !fromVert.equals(edge.fromVert) : edge.fromVert != null) return false;
            if (toVert != null ? !toVert.equals(edge.toVert) : edge.toVert != null) return false;
            return distance != null ? distance.equals(edge.distance) : edge.distance == null;

        }

        @Override
        public int hashCode() {
            int result = fromVert != null ? fromVert.hashCode() : 0;
            result = 31 * result + (toVert != null ? toVert.hashCode() : 0);
            result = 31 * result + (distance != null ? distance.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "{" + fromVert + "," + toVert + "=" + distance + '}';
        }
    }
}