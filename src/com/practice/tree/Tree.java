package com.practice.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {

    static void main(String[] args) {

        Tree tree = new Tree();

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Pre-order traversal: ");
        preOrderTraversal(root);
        System.out.println();
        System.out.println("In-order Traversal: ");
        inOrderTraversal(root);
        System.out.println();
        System.out.println("Post-order traversal: ");
        postOrderTraversal(root);
        System.out.println();
        System.out.println("Level-order traversal: ");
        levelOrderTraversal(root);
        System.out.println();


        TreeNode invertBinaryTreeRoot = new TreeNode(5);

        invertBinaryTreeRoot.left = new TreeNode(1);
        invertBinaryTreeRoot.left.left = new TreeNode(7);
        invertBinaryTreeRoot.left.right = new TreeNode(6);

        invertBinaryTreeRoot.right = new TreeNode(8);
        invertBinaryTreeRoot.right.right = new TreeNode(4);

        System.out.print("pre-order for invertBinaryTreeRoot before inversion: ");
        preOrderTraversal(invertBinaryTreeRoot);
        System.out.println();
        //invertBinaryTree(invertBinaryTreeRoot);
        invertBinaryTreeIterative(invertBinaryTreeRoot);
        System.out.print("pre-order for invertBinaryTreeRoot after inversion: ");
        preOrderTraversal(invertBinaryTreeRoot);
        System.out.println();

        TreeNode balancedBinaryTreeValidationRoot = new TreeNode(5);

        balancedBinaryTreeValidationRoot.left = new TreeNode(2);
        balancedBinaryTreeValidationRoot.left.left = new TreeNode(1);
        balancedBinaryTreeValidationRoot.left.right = new TreeNode(4);
        balancedBinaryTreeValidationRoot.left.right.left = new TreeNode(3);

        balancedBinaryTreeValidationRoot.right = new TreeNode(7);
        balancedBinaryTreeValidationRoot.right.right = new TreeNode(9);
        balancedBinaryTreeValidationRoot.right.right.left = new TreeNode(6);

        System.out.println("Is balanced: " + isBalanced(balancedBinaryTreeValidationRoot));

        TreeNode rightMostNodeRoot = new TreeNode(1);

        rightMostNodeRoot.left = new TreeNode(2);
        rightMostNodeRoot.left.left = new TreeNode(4);
        rightMostNodeRoot.left.left.left = new TreeNode(8);
        rightMostNodeRoot.left.left.right = new TreeNode(9);

        rightMostNodeRoot.left.right = new TreeNode(5);
        rightMostNodeRoot.left.right.right = new TreeNode(11);

        rightMostNodeRoot.right = new TreeNode(3);
        rightMostNodeRoot.right.left = new TreeNode(6);

        System.out.print("Rightmost node: ");
        getRightMostNode(rightMostNodeRoot);
        System.out.println();

        TreeNode widestLevelRoot = new TreeNode(1);

        widestLevelRoot.left = new TreeNode(2);
        widestLevelRoot.left.left = new TreeNode(4);
        widestLevelRoot.left.left.left = new TreeNode(8);
        widestLevelRoot.left.left.right = new TreeNode(9);

        widestLevelRoot.left.right = new TreeNode(5);
        widestLevelRoot.left.right.right = new TreeNode(11);

        widestLevelRoot.right = new TreeNode(3);
        widestLevelRoot.right.right = new TreeNode(7);
        widestLevelRoot.right.right.left = new TreeNode(14);

        System.out.print("Width of tree: " + getWidestLevelSize(widestLevelRoot));
        System.out.println();

        TreeNode bstRoot = new TreeNode(5);

        bstRoot.left = new TreeNode(2);
        bstRoot.left.left = new TreeNode(1);
        bstRoot.left.right = new TreeNode(6);

        bstRoot.right = new TreeNode(7);
        bstRoot.right.left = new TreeNode(7);
        bstRoot.right.right = new TreeNode(9);

        System.out.println("Is BST: " + isBST(bstRoot));

        TreeNode lcaRoot = new TreeNode(1);

        lcaRoot.left = new TreeNode(2);
        lcaRoot.left.left = new TreeNode(4);
        lcaRoot.left.right = new TreeNode(5);

        lcaRoot.right = new TreeNode(3);
        lcaRoot.right.left = new TreeNode(6);
        lcaRoot.right.left.left = new TreeNode(8);
        lcaRoot.right.left.right = new TreeNode(9);
        lcaRoot.right.right = new TreeNode(7);

        System.out.println("LCA of 4, 9: " + getLCA(lcaRoot, 4, 9));
        System.out.println("LCA of 8, 7: " + getLCA(lcaRoot, 8, 7));

    }

    private static int getLCA(TreeNode lcaRoot, int p, int q) {

        TreeNode[] lcaWrapper = new TreeNode[1];
        boolean isLcaFound = lcaUtil(lcaRoot, p, q, lcaWrapper);
        return isLcaFound ? lcaWrapper[0].val : -1;
    }

    private static boolean lcaUtil(TreeNode node, int p, int q, TreeNode[] lcaWrapper) {
        if (node == null) return false;

        boolean isNodePorQ = node.val == p || node.val == q;

        boolean isLeftContainsPorQ = lcaUtil(node.left, p, q, lcaWrapper);
        boolean isRightContainsPorQ = lcaUtil(node.right, p, q, lcaWrapper);

        if ((isNodePorQ ? 1 : 0) + (isLeftContainsPorQ ? 1 : 0) + (isRightContainsPorQ ? 1 : 0) == 2) {
            lcaWrapper[0] = node;
        }

        return isNodePorQ || isLeftContainsPorQ || isRightContainsPorQ;
    }

    private static boolean isBST(TreeNode node) {
        return isWithinBounds(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isWithinBounds(TreeNode node, int lowerBound, int upperBound) {
        if (node == null) return true;

        if (!(lowerBound < node.val && upperBound > node.val)) return false;

        if (! isWithinBounds(node.left, lowerBound, node.val)) return false;

        return isWithinBounds(node.right, node.val, upperBound);
    }

    private static int getWidestLevelSize(TreeNode root) {
        if (root == null) return 0;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));

        int widestLevelSize = 0;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            int leftMostIndex = queue.peek().val;
            int rightmostIndex = leftMostIndex;

            while (levelSize > 0) {

                Pair<TreeNode, Integer> pair = queue.poll();
                TreeNode node = pair.key;
                int index = pair.val;

                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * index + 1));
                }

                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * index + 2));
                }

                rightmostIndex = index;

                levelSize--;
            }

            widestLevelSize = Math.max(widestLevelSize, rightmostIndex - leftMostIndex + 1);
        }

        return widestLevelSize;
    }

    private static void getRightMostNode(TreeNode root) {
        if (root == null) {return;}

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (i == levelSize - 1) {
                    System.out.print(node.val + " ");
                }
            }

        }
    }

    private static boolean isBalanced(TreeNode root) {

        if (root == null) {return  false;}

        return getHeightImbalance(root) != -1;

    }

    private static int getHeightImbalance(TreeNode root) {
        if (root == null) {return 0;}

        int leftHeight = getHeightImbalance(root.left);
        int rightHeight = getHeightImbalance(root.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static void invertBinaryTreeIterative(TreeNode invertBinaryTreeRoot) {
        if (invertBinaryTreeRoot == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(invertBinaryTreeRoot);

        while (!stack.isEmpty()) {

            TreeNode node = stack.pop();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }

        }
    }

    private static void invertBinaryTree(TreeNode invertBinaryTreeRoot) {
        if (invertBinaryTreeRoot == null) {
            return;
        }

        TreeNode temp = invertBinaryTreeRoot.left;
        invertBinaryTreeRoot.left = invertBinaryTreeRoot.right;
        invertBinaryTreeRoot.right = temp;

        invertBinaryTree(invertBinaryTreeRoot.left);
        invertBinaryTree(invertBinaryTreeRoot.right);
    }



    private static void levelOrderTraversal(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    private static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    private static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    private static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class Pair<K, V> {
    K key;
    V val;

    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }
}