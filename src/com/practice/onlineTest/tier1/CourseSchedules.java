package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You have been given ‘N’ courses and some courses may have prerequisites.
 * Now consider a matrix ‘PREREQUISITES’ of size 'M' x 2 which represents that
 * you must complete the course 'PREREQUISITES[i][1]' before the course 'PREREQUISITES[i][0]'.
 *
 *
 *
 * Your task is to return the order of courses you should take to finish all courses.
 *
 *
 *
 * Note:
 * If it is impossible to finish all courses, return an empty array. If there are multiple answers, return any one.
 *
 *
 * For example:
 * Input:
 * 3 2
 * 1 2
 * 2 3
 *
 * There are three courses to take.
 * To start with, First course 3 is taken. Then course 2 is taken for which course 3 must be completed.
 *
 * At last course 1 is taken for which course 2 must be completed. So the correct course order is [3,2,1].
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 4 4
 * 2 1
 * 3 1
 * 4 2
 * 4 3
 * Sample Output 1:
 * 1
 * Explanation of Sample Output 1:
 * There are a total of 4 courses to take. To take course 4 you should have finished both courses 2 and 3.
 *
 * Both courses 2 and 3 should be taken after you finish course 1. So one correct course order is [1,2,3,4]. Another correct ordering is [1,3,2,4].
 *
 * When the ordering is one of the above two sets then output is 1.
 * Sample Input 2:
 * 2 1
 * 2 1
 * Sample Output 2:
 * 1
 * Constraints :
 * 2 <= 'N' <= 10^5
 * 0 <= 'M' <= 10^5
 *
 * Where ‘PREREQUISITES’ denotes the prerequisites matrix.
 *
 * Time limit: 1 sec
 */
/**
 * Algorithm:
 * - Use topological sort (Kahn's algorithm) to find valid course order
 * - Build adjacency list for graph and calculate in-degree for each node
 * - Use a queue to process nodes with in-degree 0 (no prerequisites)
 * - For each node processed, reduce in-degree of its neighbors
 * - Add neighbors with in-degree 0 to the queue
 * - If all nodes are processed, return the order; otherwise, return empty array (cycle detected)
 *
 * Time Complexity: O(V + E)
 * - V = number of courses (vertices)
 * - E = number of prerequisites (edges)
 * - Building graph: O(E)
 * - Topological sort: O(V + E)
 *
 * Space Complexity: O(V + E)
 * - For adjacency list and in-degree array
 * - For the queue and result array
 */
import java.util.*;

public class CourseSchedules {

    public static void main(String[] args) {
        // Test case 1: Sample Input 1
        int n1 = 4;
        int[][] prerequisites1 = {{2, 1}, {3, 1}, {4, 2}, {4, 3}};
        int[] result1 = findOrder(n1, prerequisites1);
        System.out.print("Test 1: ");
        if (result1.length == 0) {
            System.out.println("No valid order");
        } else {
            for (int course : result1) {
                System.out.print(course + " ");
            }
            System.out.println();
        }

        // Test case 2: Sample Input 2
        int n2 = 2;
        int[][] prerequisites2 = {{2, 1}};
        int[] result2 = findOrder(n2, prerequisites2);
        System.out.print("Test 2: ");
        if (result2.length == 0) {
            System.out.println("No valid order");
        } else {
            for (int course : result2) {
                System.out.print(course + " ");
            }
            System.out.println();
        }

        // Test case 3: Cycle detection
        int n3 = 2;
        int[][] prerequisites3 = {{1, 0}, {0, 1}};
        int[] result3 = findOrder(n3, prerequisites3);
        System.out.print("Test 3 (cycle): ");
        if (result3.length == 0) {
            System.out.println("No valid order");
        } else {
            for (int course : result3) {
                System.out.print(course + " ");
            }
            System.out.println();
        }
    }

    public static int[] findOrder(int n, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[n];
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            adj.get(prerequisite).add(course);
            inDegree[course]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[n];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[index++] = node;
            
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (index == n) {
            return result;
        }
        
        return new int[0];
    }
    

}
