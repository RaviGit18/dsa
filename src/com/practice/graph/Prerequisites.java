package com.practice.graph;

import java.util.*;

class Prerequisites {

    /*Prerequisites
Given an integer n representing the number of courses labeled from 0 to n - 1, and an array of prerequisite pairs, determine if it's possible to enroll in all courses.

Each prerequisite is represented as a pair [a, b], indicating that course a must be taken before course b.

Example:
Image represents a directed graph illustrating a simple coding pattern, possibly related to data flow or state transitions.  The graph contains three nodes represented as circles.  One node is labeled 'θ' (theta), suggesting an initial state or input. Another node is labeled '1', and the third is labeled '2'.  A directed edge (arrow) connects the 'θ' node to the '1' node, indicating a unidirectional flow of information or a transition from 'θ' to '1'.  Two curved, bidirectional edges connect nodes '1' and '2', showing a reciprocal relationship or iterative interaction between these two states; information or control flows in both directions between '1' and '2'.  The overall structure suggests a sequence where an initial input ('θ') triggers a process ('1') that interacts iteratively with another component ('2') before potentially completing or transitioning to another state (not shown).
Input: n = 3, prerequisites = [[0, 1], [1, 2], [2, 1]]
Output: False
Explanation: Course 1 cannot be taken without first completing course 2 and, and vice versa.

Constraints:
For any prerequisite [a, b], a will not equal b.*/

    static void main(String[] args) {
        int n = 3;
        int[][] prerequisites = {{0, 1}, {1, 2}, {2, 1}};
        System.out.println("Is possible to enroll in all courses: " + canEnrollInAllCourses(n, prerequisites));
    }

    private static boolean canEnrollInAllCourses(int n, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegrees = new int[n];

        for (int[] pair : prerequisites) {
            int prerequisite = pair[0];
            int course = pair[1];

            graph.computeIfAbsent(prerequisite, k -> new ArrayList<>()).add(course);

            inDegrees[course]++;
        }

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int enrolledCourse = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            enrolledCourse++;

            if (graph.containsKey(current)) {
                for (int neighbour : graph.get(current)) {
                    inDegrees[neighbour]--;

                    if (inDegrees[neighbour] == 0) {
                        queue.add(neighbour);
                    }
                }
            }
        }

        return enrolledCourse == n;
    }
}
