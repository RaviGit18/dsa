package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a binary tree having 'n' nodes.
 *
 *
 *
 * The boundary nodes of a binary tree include the nodes from the left and right boundaries and the leaf nodes, each node considered once.
 *
 *
 *
 * Figure out the boundary nodes of this binary tree in an Anti-Clockwise direction starting from the root node.
 *
 *
 *
 * Example :
 * Input: Consider the binary tree A as shown in the figure:
 *
 * Output: [10, 5, 3, 7, 18, 25, 20]
 *
 * Explanation: As shown in the figure
 *
 * The nodes on the left boundary are [10, 5, 3]
 *
 * The nodes on the right boundary are [10, 20, 25]
 *
 * The leaf nodes are [3, 7, 18, 25].
 *
 * Please note that nodes 3 and 25 appear in two places but are considered once.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 10 5 20 3 8 18 25 -1 -1 7 -1 -1 -1 -1 -1 -1 -1
 * Sample Output 1:
 * 10 5 3 7 18 25 20
 * Explanation of Sample Input 1:
 * The nodes on the left boundary are [10, 5, 3]
 *
 * The nodes on the right boundary are [10, 20, 25]
 *
 * The leaf nodes are [3, 7, 18, 25].
 *
 * Please note that nodes 3 and 25 appear in two places but are considered once.
 * Sample Input 2:
 * 100 50 150 25 75 140 200 -1 30 70 80 -1 -1 -1 -1 -1 35 -1 -1 -1 -1 -1 -1
 * Sample Output 2:
 * 100 50 25 30 35 70 80 140 200 150
 * Constraints:
 * 1 <= n <= 10000
 *
 * Where 'n' is the total number of nodes in the binary tree.
 *
 * Time Limit: 1 sec
 */
import java.util.*;

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2() {}
    TreeNode2(int val) { this.val = val; }
    TreeNode2(int val, TreeNode2 left, TreeNode2 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BoundaryTraversalBST {
    public List<Integer> boundaryTraversal(TreeNode2 root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        result.add(root.val);
        
        leftBoundary(root.left, result);
        leaves(root.left, result);
        leaves(root.right, result);
        rightBoundary(root.right, result);
        
        return result;
    }
    
    private void leftBoundary(TreeNode2 node, List<Integer> result) {
        if (node == null || (node.left == null && node.right == null)) return;
        
        result.add(node.val);
        if (node.left != null) {
            leftBoundary(node.left, result);
        } else {
            leftBoundary(node.right, result);
        }
    }
    
    private void rightBoundary(TreeNode2 node, List<Integer> result) {
        if (node == null || (node.left == null && node.right == null)) return;
        
        if (node.right != null) {
            rightBoundary(node.right, result);
        } else {
            rightBoundary(node.left, result);
        }
        result.add(node.val);
    }
    
    private void leaves(TreeNode2 node, List<Integer> result) {
        if (node == null) return;
        
        if (node.left == null && node.right == null) {
            result.add(node.val);
            return;
        }
        
        leaves(node.left, result);
        leaves(node.right, result);
    }
    
    public static void main(String[] args) {
        BoundaryTraversalBST solution = new BoundaryTraversalBST();

        TreeNode2 root = new TreeNode2(10);
        root.left = new TreeNode2(5);
        root.right = new TreeNode2(20);
        root.left.left = new TreeNode2(3);
        root.left.right = new TreeNode2(8);
        root.left.right.left = new TreeNode2(7);
        root.right.left = new TreeNode2(18);
        root.right.right = new TreeNode2(25);
        
        List<Integer> result = solution.boundaryTraversal(root);
        System.out.println("Boundary Traversal: " + result);
    }
}
