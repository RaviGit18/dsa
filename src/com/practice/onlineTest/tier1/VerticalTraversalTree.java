package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Given a binary tree, return the vertical order traversal of the values of the nodes of the given tree.
 *
 * For each node at position (X, Y), (X-1, Y-1) will be its left child position while (X+1, Y-1) will be the right child position.
 *
 * Running a vertical line from X = -infinity to X = +infinity, now whenever this vertical line touches some nodes, we need to add those values of the nodes in order starting from top to bottom with the decreasing ‘Y’ coordinates.
 *
 * Note:
 * If two nodes have the same position, then the value of the node that is added first will be the value that is on the left side.
 * For example:
 * For the binary tree in the image below.
 *
 * The vertical order traversal will be {2, 7, 5, 2, 6, 5, 11, 4, 9}.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= 'T' <= 100
 * 0 <= 'N' <= 3000
 * 0 <= 'VAL' <= 10^5
 *
 * Where 'VAL' is the value of any binary tree node.
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 1 2 3 4 -1 -1 -1 -1 -1
 * 1 -1 2 -1 -1
 * Sample Output 1:
 * 4 2 1 3
 * 1 2
 * Explanation of Sample Input 1:
 * For the first test case, the vertical order traversal of the given binary tree will be {{4}, {2}, {1}, {3}}.
 *
 * For the second test case, the vertical order traversal of the given binary tree will be {{1}, {2}}.
 * Sample Input 2:
 * 2
 * 2 1 -1 -1 -1
 * 0 1 2 4 5 3 6 -1 -1 7 -1 -1 -1 -1 -1 -1 -1
 * Sample Output 2:
 * 1 2
 * 4 1 7 0 5 3 2 6
 * Explanation of Sample Input 2:
 * For the first test case, the vertical order traversal of the given binary tree will be {{1}, {2}}.
 *
 * For the second test case, the vertical order traversal of the given binary tree will be {{4}, {1, 7}, {0, 5, 3}, {2}, {6}}.
 */
/**
 * Algorithm:
 * - Use BFS traversal to visit nodes level by level
 * - Track horizontal distance (x) and vertical distance (y) for each node
 * - Root is at (0, 0), left child at (x-1, y-1), right child at (x+1, y-1)
 * - Group nodes by their x coordinate using a TreeMap (sorted by x)
 * - For each x, store nodes with their y coordinate and value
 * - Sort nodes within each column by y (descending) and by value (ascending for same position)
 * - Return the vertical order traversal
 *
 * Time Complexity: O(N log N)
 * - N = number of nodes
 * - BFS visits each node once
 * - Sorting within each column takes O(N log N)
 *
 * Space Complexity: O(N)
 * - For storing nodes in the map and queue
 */
import java.util.*;

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode1(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class VerticalTraversalTree {

    public static List<List<Integer>> verticalOrderTraversal(TreeNode1 root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        Queue<Object[]> queue = new LinkedList<>();

        queue.offer(new Object[]{root, 0, 0});

        while (!queue.isEmpty()) {
            Object[] curr = queue.poll();
            TreeNode1 node = (TreeNode1) curr[0];
            int x = (int) curr[1];
            int y = (int) curr[2];

            map.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{y, node.val});

            if (node.left != null) {
                queue.offer(new Object[]{node.left, x - 1, y - 1});
            }
            if (node.right != null) {
                queue.offer(new Object[]{node.right, x + 1, y - 1});
            }
        }

        for (int x : map.keySet()) {
            List<int[]> nodes = map.get(x);
            nodes.sort((a, b) -> {
                if (a[0] != b[0]) {
                    return b[0] - a[0];
                }
                return a[1] - b[1];
            });

            List<Integer> column = new ArrayList<>();
            for (int[] node : nodes) {
                column.add(node[1]);
            }
            result.add(column);
        }

        return result;
    }

    public static TreeNode1 buildTree(int[] arr, int index) {
        if (index >= arr.length || arr[index] == -1) {
            return null;
        }

        TreeNode1 node = new TreeNode1(arr[index]);
        node.left = buildTree(arr, 2 * index + 1);
        node.right = buildTree(arr, 2 * index + 2);

        return node;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, -1, -1, -1, -1, -1};
        TreeNode1 root1 = buildTree(arr1, 0);
        List<List<Integer>> result1 = verticalOrderTraversal(root1);
        System.out.print("Test 1: ");
        for (List<Integer> col : result1) {
            for (int val : col) {
                System.out.print(val + " ");
            }
        }
        System.out.println();

        int[] arr2 = {1, -1, 2, -1, -1};
        TreeNode1 root2 = buildTree(arr2, 0);
        List<List<Integer>> result2 = verticalOrderTraversal(root2);
        System.out.print("Test 2: ");
        for (List<Integer> col : result2) {
            for (int val : col) {
                System.out.print(val + " ");
            }
        }
        System.out.println();
    }
}
