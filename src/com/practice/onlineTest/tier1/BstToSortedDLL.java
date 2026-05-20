package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are provided with a Binary Search Tree (BST), all you have to do is to convert it into the sorted doubly linked list (DLL).
 *
 * For Example:
 *
 *
 * Consider the above BST, it will be converted into the below sorted DLL.
 *
 *
 * Here, 20 is the head node and 80 is the tail node.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 0 <= N <= 10^4
 * -10^5 <= DATA <= 10^5
 *
 * Time Limit: 1sec
 * Sample Input 1:
 * 2
 * 50 30 70 20 40 60 80 -1 -1 -1 -1 -1 -1 -1 -1
 * -1
 * Sample Output 1:
 * 20 30 40 50 60 70 80 -1
 * -1
 * Explanation Of Sample Input 1:
 * For the first test case, the explanation is given in the description. -1 represents the end of DLL.
 *
 * In the second test case, there is no node in BST and so, there is also no node in DLL.
 * Sample Input 2:
 * 2
 * 0 -2 -1 -3 -1 -1 -1
 * 1 -1 2 -1 3 -1 -1
 * Sample Output 2:
 * -3 -2 0 -1
 * 1 2 3 -1
 * Explanation Of Sample Input 2:
 * In the first test case, the sorted DLL formed is [-3, -2, 0].
 *
 * In the second test case, the sorted DLL formed is [1, 2, 3].
 */
import java.util.*;

class TreeNode4 {
    int val;
    TreeNode4 left;
    TreeNode4 right;
    TreeNode4() {}
    TreeNode4(int val) { this.val = val; }
    TreeNode4(int val, TreeNode4 left, TreeNode4 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class DLLNode {
    int data;
    DLLNode next;
    DLLNode prev;
    DLLNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class BstToSortedDLL {
    private TreeNode4 prev;
    private DLLNode head;
    private DLLNode dllPrev;
    
    public DLLNode bstToSortedDLL(TreeNode4 root) {
        if (root == null) return null;
        
        inorder(root);
        return head;
    }
    
    private void inorder(TreeNode4 node) {
        if (node == null) return;
        
        inorder(node.left);
        
        DLLNode dllNode = new DLLNode(node.val);
        if (head == null) {
            head = dllNode;
        } else {
            dllPrev.next = dllNode;
            dllNode.prev = dllPrev;
        }
        dllPrev = dllNode;
        
        inorder(node.right);
    }
    
    public static void main(String[] args) {
        BstToSortedDLL solution = new BstToSortedDLL();

        TreeNode4 root = new TreeNode4(50);
        root.left = new TreeNode4(30);
        root.right = new TreeNode4(70);
        root.left.left = new TreeNode4(20);
        root.left.right = new TreeNode4(40);
        root.right.left = new TreeNode4(60);
        root.right.right = new TreeNode4(80);
        
        DLLNode dllHead = solution.bstToSortedDLL(root);
        
        System.out.print("Sorted DLL: ");
        DLLNode curr = dllHead;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println("-1");
    }
}
