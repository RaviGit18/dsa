package com.practice.graph;

class BiPartiteGraphValidation {

    /*Bipartite Graph Validation
Given an undirected graph, determine if it's bipartite. A graph is bipartite if the nodes can be colored in one of two colors, so that no two adjacent nodes are the same color.

The input is presented as an adjacency list, where graph[i] is a list of all nodes adjacent to node i.

Example:
Image represents a graph, specifically a cycle graph of five nodes (vertices) arranged in a circular pattern.  Nodes 0, 2, and 3 are depicted with light-blue circles and have a light-blue outline, while nodes 1 and 4 are shown with light-orange circles and an orange outline.  Each node contains a numerical label (0 through 4).  Undirected edges (lines) connect node 0 to node 1, node 1 to node 2, node 2 to node 3, node 3 to node 4, and node 4 back to node 0, forming a closed loop.  No other connections exist between the nodes. The color-coding of the nodes might represent a classification or grouping, with nodes 1 and 4 belonging to one group and nodes 0, 2, and 3 to another, although the specific meaning of this color distinction is not explicitly provided in the image itself.
Input: graph = [[1, 4], [0, 2], [1], [4], [0, 3]]
Output: True
*/

    static void main(String[] args) {
        int[][] graph = {{1, 4}, {0, 2}, {1}, {4}, {0, 3}};
        System.out.println("Is bipartite: " + isBipartite(graph));
    }

    private static boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                if (!dfs(graph, colors, i, 1)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {

            if (colors[neighbor] == color) {
                return false;
            }

            if (colors[neighbor] == 0) {
                if (!dfs(graph, colors, neighbor, -color)) {
                    return false;
                }
            }
        }

        return true;
    }
}

