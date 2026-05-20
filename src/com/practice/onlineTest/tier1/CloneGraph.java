package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a reference/address of a node in a connected undirected graph containing N nodes and M edges. You are supposed to return a clone of the given graph which is nothing but a deep copy. Each node in the graph contains an integer “data” and an array/list of its neighbours.
 *
 * The structure of the graphNode class is as follows:
 *
 * class graphNode
 * {
 *     public:
 *         int data;
 *         vector<graphNode*> neighbours;
 * }
 * Note :
 * 1. Nodes are numbered from 1 to N.
 *
 * 2. Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 5
 * 2 <= N <= 100000
 * 1 <= M <= min(N(N-1)/2,100000)
 * 1 <= E[i][0], E[i][1] <= N
 *
 * Where  ‘N’ is the number of nodes in the given graph, ‘M’ denotes the number of edges and ‘E’ denotes the edge matrix.
 *
 * Time Limit: 1 sec.
 * Sample Input 1 :
 * 2
 * 5
 * 6
 * 1 2
 * 4 1
 * 2 4
 * 3 4
 * 5 2
 * 1 3
 * 3
 * 2
 * 1 2
 * 1 3
 * Sample Output 1 :
 * true
 * true
 * Explanation of Input 1 :
 * In the first test case, the returned graph contains 5 nodes and 6 edges which are:
 * 1 2
 * 4 1
 * 2 4
 * 3 4
 * 5 2
 * 1 3
 * Since it is similar to the given graph with different address nodes then the solution is correct.
 *
 * In the second test case, the returned graph contains 3 nodes and 2 edges which are:
 * 1 2
 * 1 3
 * Since it is similar to the given graph with different address nodes then the solution is correct.
 * Sample Input 2 :
 * 2
 * 5
 * 4
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 2
 * 1
 * 1 2
 * Sample Output 2 :
 * true
 * true
 * Explanation of Input 2 :
 * In the first test case, the returned graph contains 5 nodes and 4 edges which are:
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * Since it is similar to the given graph with different address nodes then the solution is correct.
 *
 * In the second test case, the returned graph contains 2 nodes and 1 edge which is:
 * 1 2
 *
 * Since it is similar to the given graph with different address nodes then the solution is correct.
 */
import java.util.*;

class GraphNode {
    int data;
    List<GraphNode> neighbours;
    
    public GraphNode(int data) {
        this.data = data;
        this.neighbours = new ArrayList<>();
    }
}

public class CloneGraph {
    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;
        
        Map<GraphNode, GraphNode> visited = new HashMap<>();
        return cloneGraphHelper(node, visited);
    }
    
    private GraphNode cloneGraphHelper(GraphNode node, Map<GraphNode, GraphNode> visited) {
        if (node == null) return null;
        
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        GraphNode cloneNode = new GraphNode(node.data);
        visited.put(node, cloneNode);
        
        for (GraphNode neighbor : node.neighbours) {
            cloneNode.neighbours.add(cloneGraphHelper(neighbor, visited));
        }
        
        return cloneNode;
    }
    
    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();
        
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        
        node1.neighbours.add(node2);
        node1.neighbours.add(node4);
        node2.neighbours.add(node1);
        node2.neighbours.add(node3);
        node3.neighbours.add(node2);
        node3.neighbours.add(node4);
        node4.neighbours.add(node1);
        node4.neighbours.add(node3);
        
        GraphNode cloned = solution.cloneGraph(node1);
        System.out.println("Graph cloned successfully. Node data: " + cloned.data);
    }
}
