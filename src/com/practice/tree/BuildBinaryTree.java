package com.practice.tree;

import java.util.HashMap;
import java.util.Map;


class BuildBinaryTree {
    /*Build Binary Tree From Preorder and Inorder Traversals
Construct a binary tree using arrays of values obtained after a preorder traversal and an inorder traversal of the tree.
Input: preorder = [5, 9, 2, 3, 4, 7], inorder = [2, 9, 5, 4, 3, 7]
Constraints:
The tree consists of unique values.
*/

    public static void main(String[] args) {
        int[] preorder = new int[] {5, 9, 2, 3, 4, 7};
        int[] inorder = new int[] {2, 9, 5, 4, 3, 7};

        TreeNode root = buildTree(preorder, inorder);

        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
    }

    private static void inOrderTraversal(TreeNode root) {
        if (root == null) { return;}

        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    private static void preOrderTraversal(TreeNode root) {
        if (root == null) { return;}

        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder) {

        //Root Cause: Java passes primitives by value, so incrementing preOrderIndex in recursive calls didn't affect the original variable.
        // Using an array wrapper allows the index to persist across all recursive calls.
        int[] preOrderIndex = {0};
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTreeUtil(preorder, 0, preorder.length - 1, indexMap, preOrderIndex);
    }

    private static TreeNode buildTreeUtil(int[] preorder, int left, int right, Map<Integer, Integer> indexMap, int[] preOrderIndex) {
        if (left > right) { return null;}

        int rootVal = preorder[preOrderIndex[0]];

        int rootIndex = indexMap.get(rootVal);

        TreeNode root = new TreeNode(rootVal);

        preOrderIndex[0]++;
        root.left = buildTreeUtil(preorder, left, rootIndex - 1, indexMap, preOrderIndex);
        root.right = buildTreeUtil(preorder, rootIndex + 1, right, indexMap, preOrderIndex);

        return root;
    }
}
