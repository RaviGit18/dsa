package com.practice.graph;

import java.util.HashMap;

import static com.practice.graph.Graph.dfs;

class GraphDeepCopy {

    /*Graph Deep Copy
Given a reference to a node within an undirected graph, create a deep copy (clone) of the graph. The copied graph must be completely independent of the original one. This means you need to make new nodes for the copied graph instead of reusing any nodes from the original graph.

Constraints:
The value of each node is unique.
Every node in the graph is reachable from the given node.
*/
    static void main(String[] args) {

        // Create a sample graph:
        //     1
        //    / \
        //   2---3
        //    \ /
        //     4
        //     |
        //     5

        GraphNode<Integer> node1 = new GraphNode<>(1);
        GraphNode<Integer> node2 = new GraphNode<>(2);
        GraphNode<Integer> node3 = new GraphNode<>(3);
        GraphNode<Integer> node4 = new GraphNode<>(4);
        GraphNode<Integer> node5 = new GraphNode<>(5);

        // Add edges
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node2.neighbors.add(node4);

        node3.neighbors.add(node1);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node2);
        node4.neighbors.add(node3);
        node4.neighbors.add(node5);

        node5.neighbors.add(node4);

        System.out.print("DFS traversal starting from node 1: ");
        dfs(node1);
        System.out.println();
        System.out.println("Clone graph starting from node 1: ");
        GraphNode<Integer> clonedNode = cloneGraph(node1);
        dfs(clonedNode);
        System.out.println();
    }

    private static GraphNode<Integer> cloneGraph(GraphNode<Integer> node) {
        if (node == null) {
            return null;
        }

        HashMap<GraphNode<Integer>, GraphNode<Integer>> visited = new HashMap<>();

        return cloneGraphHelper(node, visited);
    }

    private static GraphNode<Integer> cloneGraphHelper(GraphNode<Integer> node, HashMap<GraphNode<Integer>, GraphNode<Integer>> visited) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        GraphNode<Integer> clone = new GraphNode<>(node.value);
        visited.put(node, clone);

        for (GraphNode<Integer> neighbor : node.neighbors) {
            if (!visited.containsKey(neighbor)) {
                clone.neighbors.add(cloneGraphHelper(neighbor, visited));
            }
        }
        return clone;
    }
}
