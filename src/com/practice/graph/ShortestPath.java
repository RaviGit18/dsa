package com.practice.graph;

import java.util.*;

class ShortestPath {

    /*Shortest Path
Given an integer n representing nodes labeled from 0 to n - 1 in an undirected graph, and an array of non-negative weighted edges, return an array where each index i contains the shortest path length from a specified start node to node i. If a node is unreachable, set its distance to -1.

Each edge is represented by a triplet of positive integers: the start node, the end node, and the weight of the edge.

Example:
Image represents a weighted directed graph, possibly illustrating a shortest path problem or a similar graph traversal algorithm.  The graph consists of six nodes, numbered 0 through 5. Node 0, labeled 'start' in orange, is the designated starting node and is highlighted with an orange border.  The remaining nodes (1, 2, 3, 4, and 5) are represented as simple circles containing their respective numerical labels.  Edges connect the nodes, with each edge labeled with a numerical weight representing the cost or distance between the connected nodes.  Specifically, node 0 connects to node 1 (weight 5) and node 2 (weight 3). Node 1 connects to node 2 (weight 1) and node 3 (weight 4). Node 2 connects to node 4 (weight 5) and node 3 (weight 4). Node 3 connects to node 1 (weight 4). Node 5 is an isolated node, not connected to any other node in the graph.  The arrangement suggests a flow of information or traversal from the starting node (0) towards the other nodes, with the edge weights influencing the path selection in an algorithm.
Input: n = 6,
       edges = [
         [0, 1, 5],
         [0, 2, 3],
         [1, 2, 1],
         [1, 3, 4],
         [2, 3, 4],
         [2, 4, 5],
       ],
       start = 0
Output: [0, 4, 3, 7, 8, -1]
*/

    static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1, 5}, {0, 2, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 4}, {2, 4, 5}};
        int start = 0;
        System.out.println(Arrays.toString(getShortestPath(n, edges, start)));
    }

    private static int[] getShortestPath(int n, int[][] edges, int start) {

        Map<Integer, List<int[]>> graph = new HashMap<>();
        int[] distances = new int[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        for (int[] edge : edges) {
            int source = edge[0];
            int destination = edge[1];
            int weight = edge[2];

            graph.computeIfAbsent(source, k -> new ArrayList<>()).add(new int[]{destination, weight});
            graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(new int[]{source, weight});
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        minHeap.add(new int[]{start, 0});

        while (!minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int currentNode = pair[0];
            int currentWeight = pair[1];

            if (currentWeight > distances[currentNode]) { continue;}

            if (graph.containsKey(currentNode)) {

                for (int[] edge : graph.get(currentNode)) {

                    int neighbourWeight = edge[1] + currentWeight;
                    int neighbourNode = edge[0];

                    if (neighbourWeight < distances[neighbourNode]) {

                        distances[neighbourNode] = neighbourWeight;
                        minHeap.add(new int[]{neighbourNode, neighbourWeight});
                    }
                }
            }
        }

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }

        return  distances;
    }
}
