package kz.graph.hackerrank;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Solution {

    public static void main(String... args) {
        Scanner input = new Scanner(Solution.class.getClassLoader().getResourceAsStream("input"));
//      Scanner input = new Scanner(System.in);

        Solution sol = new Solution();
        UndirectedGraphNode root = new UndirectedGraphNode(1);
        root.neighbors.add(new UndirectedGraphNode(2));

        UndirectedGraphNode cloned = sol.cloneGraph(root);
        System.out.println(cloned.neighbors.get(0) == root.neighbors.get(0));

        input.close();
    }

    public UndirectedGraphNode cloneDfsGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return cloneGraphHelper(map, node);
    }

    private UndirectedGraphNode cloneGraphHelper(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);

        for (UndirectedGraphNode nei : node.neighbors) {
            if (!map.containsKey(nei)) {
                cloneGraphHelper(map, nei);
            }
            copy.neighbors.add(map.get(nei));
        }

        return copy;
    }

    Map<UndirectedGraphNode, UndirectedGraphNode> originalToCopy = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {
        if(root == null) {
            return null;
        }

        UndirectedGraphNode rootCopy = new UndirectedGraphNode(root.label);
        originalToCopy.put(root, rootCopy);

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            UndirectedGraphNode currentNode = queue.poll();

            for(UndirectedGraphNode eachNeighbor : currentNode.neighbors) {
                if(originalToCopy.containsKey(eachNeighbor)) {
                    originalToCopy.get(eachNeighbor).neighbors.add(originalToCopy.get(eachNeighbor));
                } else {
                    UndirectedGraphNode neighborCopy = new UndirectedGraphNode(eachNeighbor.label);
                    originalToCopy.put(eachNeighbor, neighborCopy);
                    originalToCopy.get(currentNode).neighbors.add(neighborCopy);
                    queue.add(eachNeighbor);
                }
            }
        }

        return rootCopy;
    }
}

class UndirectedGraphNode {

    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}