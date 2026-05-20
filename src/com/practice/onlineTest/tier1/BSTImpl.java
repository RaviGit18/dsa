package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Ninja is learning tree data structure these days. While learning, she came across learn about the Binary Search tree. She found BST quite interesting. She decided to make her own Binary Search Tree. Being a newbie, she made a mistake and swap two nodes of the Binary Search Tree.
 *
 * Your responsibility being an expert and a good friend is to correct the Binary Search Tree made by Ninja and recover the correct Binary Search Tree.
 *
 * A binary search tree (BST) is a binary tree data structure with the following properties.
 *
 * • The left subtree of a node contains only nodes with data less than the node’s data.
 * • The right subtree of a node contains only nodes with data greater than the node’s data.
 * • Both the left and right subtrees must also be binary search trees.
 * Example
 *
 * This is the binary search tree made by Ninja. But here, the left node of 8 is greater, and the right node is smaller.
 * If we swap the right and the left node of 8 Ninja’s Binary Search Tree will become correct.
 *
 * This is the correct binary Search Tree.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 100
 * 2 <= N <= 5000
 * 0 <= X <= 2^31 - 1
 *
 * Where 'N' is the number of nodes in the BST and 'X' is the value at the node.
 *
 * Time limit: 1 sec
 * Sample Input 1:
 * 1
 * 1 3 -1 -1  2 -1 -1
 * Sample Output 1:
 * 3 1 -1 -1 2 -1 -1
 * Explanation Of Sample Input 1:
 * Test case 1:
 *
 * Here the highlighted nodes are swapped as 3 is the left child of 1, which can’t be greater than 1.
 * Hence if we swap 1 and 3, we can recover Ninja’s BST.
 *
 * Hence our answer will be 3 1 -1 -1 2 -1 -1.
 * Sample Input 2:
 * 1
 * 3 1 4 -1 -1 2 -1 -1
 * Sample Output 2:
 * 2 1 4 -1 -1 3 -1 -1
 * Explanation Of Sample Input 2:
 * Test case 1:
 *
 * In this case, the highlighted nodes are swapped as the right subtree of 3 has a value smaller than 3. If we swap 3 and 2, Ninja’s BST will be correctly recovered.
 * .
 */
import java.util.*;

class TreeNode3 {
    int val;
    TreeNode3 left;
    TreeNode3 right;
    TreeNode3() {}
    TreeNode3(int val) { this.val = val; }
    TreeNode3(int val, TreeNode3 left, TreeNode3 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BSTImpl {
    private TreeNode3 first;
    private TreeNode3 second;
    private TreeNode3 prev;
    
    public void recoverTree(TreeNode3 root) {
        inorder(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
    
    private void inorder(TreeNode3 node) {
        if (node == null) return;
        
        inorder(node.left);
        
        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;
            }
            second = node;
        }
        prev = node;
        
        inorder(node.right);
    }
    
    public static void main(String[] args) {
        BSTImpl solution = new BSTImpl();

        TreeNode3 root = new TreeNode3(1);
        root.left = new TreeNode3(3);
        root.left.right = new TreeNode3(2);
        
        System.out.println("Before recovery:");
        printInorder(root);
        
        solution.recoverTree(root);
        
        System.out.println("\nAfter recovery:");
        printInorder(root);
    }
    
    private static void printInorder(TreeNode3 node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
