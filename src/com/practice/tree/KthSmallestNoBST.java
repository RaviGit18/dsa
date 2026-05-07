package com.practice.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class KthSmallestNoBST {
    /*
        Kth Smallest Number in a Binary Search Tree
Given the root of a binary search tree (BST) and an integer k, find the kth smallest node value.

Constraints:
n ≥ 1, where n denotes the number of nodes in the tree.
1 ≤ k ≤ n
    */
    public static void main(String[] args) {
        TreeNode bstRoot = new TreeNode(5);

        bstRoot.left = new TreeNode(2);
        bstRoot.left.left = new TreeNode(1);
        bstRoot.left.right = new TreeNode(4);

        bstRoot.right = new TreeNode(7);
        bstRoot.right.left = new TreeNode(6);
        bstRoot.right.right = new TreeNode(9);

        System.out.println(kthSmallest(bstRoot, 3));
        System.out.println(kthSmallestIterative(bstRoot, 3));
    }

    private static int kthSmallestIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (!stack.isEmpty() || curr != null) {

            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            if (--k == 0) return curr.val;

            curr = curr.right;
        }

        return -1;
    }

    private static int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        inOrderTraversal(root, k, res);
        return res.get(k - 1);
    }

    private static void inOrderTraversal(TreeNode root, int k, List<Integer> res) {
        if (root == null || res.size() >= k) return;

        inOrderTraversal(root.left, k, res);
        
        if (res.size() < k) {
            res.add(root.val);
        }
        
        inOrderTraversal(root.right, k, res);
    }
}
