package com.practice.tree;

class MaxSumOfContinuousPath {
    /*Maximum Sum of a Continuous Path in a Binary Tree
Return the maximum sum of a continuous path in a binary tree. A path is defined by the following characteristics:

Consists of a sequence of nodes that can begin and end at any node in the tree.
Each consecutive pair of nodes in the sequence is connected by an edge.
The path must be a single continuous sequence of nodes that doesn't split into multiple paths.

Constraints:
The tree contains at least one node.
*/

    static void main(String[] args) {
        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(-10);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(11);

        root.left.right = new TreeNode(-7);
        root.left.right.left = new TreeNode(-1);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(9);

        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(6);
        root.right.right.right = new TreeNode(-3);

        System.out.println(maxPathSum(root));

    }

    private static int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};

        maxPathSumUtil(root, maxSum);
        
        return maxSum[0];
    }

    private static int maxPathSumUtil(TreeNode root, int[] maxSum) {
        if (root == null) { return 0;}

        int leftSum = Math.max(0, maxPathSumUtil(root.left, maxSum));
        int rightSum = Math.max(0, maxPathSumUtil(root.right, maxSum));

        maxSum[0] = Math.max(maxSum[0], root.val + leftSum + rightSum);

        return root.val + Math.max(leftSum, rightSum);

    }
}
