package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a class named as BSTIterator that represents an iterator over inorder traversal of a binary search tree. You need to implement the following things as follows:
 *
 * 1. BSTIterator(Node root) - It is a parameterized constructor in which you are given the root of the Binary search tree. It will be called whenever an object of BSTIterator is created.
 *
 * 2. next() - This member function will return the next smallest element in the in-order traversal of the binary search tree. You need to implement this function.
 *
 * 3. hasNext() - This function will return true if there exists the next smallest element in the traversal else it will return false. You need to implement this function
 * The binary search tree has ‘N’ nodes you need to print the inorder traversal of the tree using the iterator.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 10^4
 * 1 <= A[i] <= 10^9
 *
 * Where ‘T’ is the number of test cases, ‘N’ is the number of nodes, and A[i] is the value of a node.
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 2 1 3 -1 -1 -1 -1
 * 10 5 -1 2 -1 -1 -1
 * Sample Output 1:
 * 1 2 3
 * 2 5 10
 * Explanation for sample input 1:
 * Test case 1:
 * The tree will look like this :
 *
 * The inorder traversal of the tree will be 1, 2, 3.
 *
 * Test case 2:
 * The tree will look like this:
 *
 * The inorder traversal of the tree will be 2, 5, 10.
 * Sample Input 2:
 * 2
 * 6 -1 7 -1 8 -1 -1
 * 3 2 4 1 -1 -1 -1 -1 -1
 * Sample Output 2:
 * 6 7 8
 * 1 2 3 4
 */
/**
 * Algorithm:
 * - Use a stack to simulate inorder traversal iteratively
 * - In constructor: push all left nodes starting from root onto stack
 * - next(): pop top node, then push all left nodes of its right child
 * - hasNext(): check if stack is not empty
 * - This gives O(h) space where h is tree height, and amortized O(1) time per operation
 *
 * Time Complexity:
 * - Constructor: O(h) where h is height of tree
 * - next(): Amortized O(1) - each node is pushed and popped exactly once
 * - hasNext(): O(1)
 *
 * Space Complexity: O(h)
 * - h = height of the BST
 * - Stack stores at most h nodes at any time
 */
import java.util.*;

class TreeNode5 {
    int val;
    TreeNode5 left;
    TreeNode5 right;
    TreeNode5() {}
    TreeNode5(int val) { this.val = val; }
    TreeNode5(int val, TreeNode5 left, TreeNode5 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BSTIterator {
    private Stack<TreeNode5> stack;
    
    public BSTIterator(TreeNode5 root) {
        stack = new Stack<>();
        pushLeft(root);
    }
    
    private void pushLeft(TreeNode5 node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    public int next() {
        TreeNode5 node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    public static void main(String[] args) {
        TreeNode5 root = new TreeNode5(7);
        root.left = new TreeNode5(3);
        root.right = new TreeNode5(15);
        root.right.left = new TreeNode5(9);
        root.right.right = new TreeNode5(20);
        
        BSTIterator iterator = new BSTIterator(root);
        System.out.print("Inorder traversal: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}
