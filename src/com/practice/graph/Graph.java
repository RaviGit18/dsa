package com.practice.graph;

import java.util.*;

class Graph {

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
        
        System.out.print("BFS traversal starting from node 1: ");
        bfs(node1);
        System.out.println();
    }

    public static void dfs(GraphNode<Integer> node) {
        dfs(node, new HashSet<GraphNode<Integer>>());
    }

    private static void dfs(GraphNode<Integer> node, HashSet<GraphNode<Integer>> visited) {

        visited.add(node);

        System.out.print(node.value + " ");

        for (GraphNode<Integer> neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    private static void bfs(GraphNode<Integer> node) {
        Set<GraphNode<Integer>> visited = new HashSet<>();
        Queue<GraphNode<Integer>> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {

            GraphNode<Integer> curr = queue.poll();

            if (!visited.contains(curr)) {

                visited.add(curr);
                System.out.print(curr.value + " ");
                for (GraphNode<Integer> neighbor : curr.neighbors) {
                    queue.offer(neighbor);
                }
            }

        }
    }

}

class GraphNode<T> {
    T value;
    List<GraphNode<T>> neighbors;

    public GraphNode(T value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

}
