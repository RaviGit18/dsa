package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You have been given a root node of a binary search tree and a positive integer ‘K’. You need to split the given BST into two BST such that first BST has all nodes with values less than or equal to the given value ‘K’, and second tree has all nodes with values greater than the given value ‘K’.
 *
 * Note:
 *
 * 1. A binary search tree is a binary tree data structure, with the following properties
 *     a. The left subtree of any node contains nodes with the value less than the node’s value.
 *     b. The right subtree of any node contains nodes with the value equal to or greater than the node’s value.
 *     c. Right, and left subtrees are also binary search trees.
 * 2. It is guaranteed that all nodes in the given tree will have distinct positive integral values .
 * 3. The given tree may or may not contain a node of value equal to the given value ‘K’.
 * Example, below the tree, is a binary search tree.
 *
 *
 * Below the tree is not a BST as node ‘2’ is less than node ‘3’ but ‘2’ is the right child of ‘3’, and node ‘6’ is greater than node ‘5’ but it is in the left subtree of node ‘5’.
 *
 *
 * Note:
 *
 * 1. You have to split the given tree in such a way that the structure of both returned trees is similar to the originally given tree, i.e. if a parent node ‘P’ and child node ‘C’ lies on the same tree after splitting, then ‘C must be the same child of ‘P’.
 * 2. If there is no valid tree, then return ‘NULL’ node in its place.
 */
/**
 * Algorithm:
 * - Recursively split the BST based on the value K
 * - If current node value <= K, it belongs to the first tree
 *   - Recursively split the right subtree
 *   - Attach the result's first tree as the right child of current node
 *   - Return current node and the result's second tree
 * - If current node value > K, it belongs to the second tree
 *   - Recursively split the left subtree
 *   - Attach the result's second tree as the left child of current node
 *   - Return the result's first tree and current node
 *
 * Time Complexity: O(N)
 * - N = number of nodes in the BST
 * - Each node is visited exactly once
 *
 * Space Complexity: O(H)
 * - H = height of the BST
 * - For the recursion stack
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class SplitBST {

    public static TreeNode[] splitBST(TreeNode root, int K) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        if (root.val <= K) {
            TreeNode[] rightSplit = splitBST(root.right, K);
            root.right = rightSplit[0];
            return new TreeNode[]{root, rightSplit[1]};
        } else {
            TreeNode[] leftSplit = splitBST(root.left, K);
            root.left = leftSplit[1];
            return new TreeNode[]{leftSplit[0], root};
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        TreeNode[] result = splitBST(root, 2);

        System.out.println("First BST (values <= 2):");
        printTree(result[0]);
        System.out.println("\nSecond BST (values > 2):");
        printTree(result[1]);
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}
