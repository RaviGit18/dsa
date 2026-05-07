package com.practice.tree;

class BinaryTreeSymmetry {

/*Binary Tree Symmetry
Determine if a binary tree is vertically symmetric. That is, the left subtree of the root node is a mirror of the right subtree.
*/

    static void main(String[] args) {
        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);

        root.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.left.right = new TreeNode(3);

        root.right.right = new TreeNode(1);

        System.out.println(isSymmetric(root));
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {

        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
