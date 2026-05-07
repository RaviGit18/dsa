package com.practice.tree;

import java.util.*;

class BinaryTreeColumn {

    /*Binary Tree Columns
Given the root of a binary tree, return a list of arrays where each array represents a vertical column of the tree. Nodes in the same column should be ordered from top to bottom. Nodes in the same row and column should be ordered from left to right.

Example:
Output: [[2], [9], [5, 1, 4], [3], [7]]
*/

    static void main(String[] args) {
        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println(verticalOrder(root));
    }

    private static List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        Queue<ColumnPair> queue = new LinkedList<>();
        queue.offer(new ColumnPair(root, 0));

        int leftMostColumn = 0;
        int rightMostColumn = 0;

        Map<Integer, List<Integer>> columnMap = new HashMap<>();

        while (!queue.isEmpty()) {
            ColumnPair columnPair = queue.poll();

            TreeNode node = columnPair.node;
            int column = columnPair.column;

            columnMap.putIfAbsent(column, new ArrayList<>());
            columnMap.get(column).add(node.val);

            leftMostColumn = Math.min(leftMostColumn, column);
            rightMostColumn = Math.max(rightMostColumn, column);

            if (node.left != null) queue.offer(new ColumnPair(node.left, column - 1));
            if (node.right != null)queue.offer(new ColumnPair(node.right, column + 1));

        }

        for (int i = leftMostColumn; i <= rightMostColumn; i++) {
            res.add(columnMap.get(i));
        }

        return res;
    }
}

class ColumnPair {
    TreeNode node;
    int column;

    ColumnPair(TreeNode node, int column) {
        this.node = node;
        this.column = column;
    }
}