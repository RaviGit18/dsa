package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * The node structure of a binary tree is modified such that each node has the reference to its parent node.
 *
 * You are given two nodes: ‘N1’ and ‘N2’, of the above binary tree. Your task is to return the lowest common ancestor (LCA) of the given nodes.
 *
 * Note:
 *
 * Let ‘TREE’ be a binary tree. The lowest common ancestor of two nodes, ‘N1’ and ‘N2’, is defined as the lowest node in ‘TREE’ with ‘N1’ and ‘N2’ as descendants (where we allow a node to be a descendant of itself).
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 100
 * 1 <= N <= 10 ^ 4
 * 1 <= DATA <= 10 ^ 4
 * N1 != N2
 * N1 and N2 exist in the ‘TREE’.
 * The ‘TREE’ contains unique nodes.
 *
 * Where ‘T’ is the number of test cases, ‘N’ is the number of nodes in the ‘TREE’, ‘DATA’ represents the value of the node, ‘N1’ and ‘N2’ represent the nodes of which LCA has to be found.
 *
 * Time limit: 1 sec.
 * Sample Input 1:
 * 2
 * 3 1
 * 4 5 2 3 7 -1 -1 -1 -1 6 5 -1 1 -1 -1 -1 -1
 * 2 10
 * 3 7 4 -1 -1 8 9 2 6 -1 5 -1 -1 -1 10 -1 -1 -1 -1
 * Sample Output 1:
 * 5
 * 8
 * Explanation of Sample Output 1:
 * Test Case 1 :
 *
 * For nodes 3 and 1, common ancestors are 5 and 4. Out of which, 5 is the nearest ancestor to both nodes. So, LCA(3, 1) = 5.
 *
 * Test Case 2 :
 *
 * For nodes 2 and 10, common ancestors are 8, 4 and 3. Out of which, 8 is the nearest ancestor to both nodes. So, LCA(2, 10) = 8.
 * Sample Input 2:
 * 2
 * 6 4
 * 2 1 6 -1 -1 4 -1 -1 -1
 * 3 5
 * 3 -1 4 -1 5 -1 -1
 * Sample Output 2:
 * 6
 * 3
 */
import java.util.*;

public class LcaBst3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
        Set<TreeNode> ancestors = new HashSet<>();
        
        TreeNode current = p;
        while (current != null) {
            ancestors.add(current);
            current = current.parent;
        }
        
        current = q;
        while (current != null) {
            if (ancestors.contains(current)) {
                return current;
            }
            current = current.parent;
        }
        
        return null;
    }

    public static void main(String[] args) {
        LcaBst3 lca = new LcaBst3();
        
        // Create a simple tree with parent pointers
        //       3
        //      / \
        //     5   1
        //    / \ / \
        //   6  2 0  8
        //     / \
        //    7   4
        
        TreeNode root = lca.new TreeNode(3);
        TreeNode node5 = lca.new TreeNode(5);
        TreeNode node1 = lca.new TreeNode(1);
        TreeNode node6 = lca.new TreeNode(6);
        TreeNode node2 = lca.new TreeNode(2);
        TreeNode node0 = lca.new TreeNode(0);
        TreeNode node8 = lca.new TreeNode(8);
        TreeNode node7 = lca.new TreeNode(7);
        TreeNode node4 = lca.new TreeNode(4);
        
        root.left = node5;
        root.right = node1;
        node5.parent = root;
        node1.parent = root;
        
        node5.left = node6;
        node5.right = node2;
        node6.parent = node5;
        node2.parent = node5;
        
        node1.left = node0;
        node1.right = node8;
        node0.parent = node1;
        node8.parent = node1;
        
        node2.left = node7;
        node2.right = node4;
        node7.parent = node2;
        node4.parent = node2;
        
        // Test case: LCA of node5 and node1 should be root (3)
        TreeNode result = lca.lowestCommonAncestor(node5, node1);
        System.out.println("LCA of 5 and 1: " + result.val);
        
        // Test case: LCA of node5 and node4 should be node5 (5)
        TreeNode result2 = lca.lowestCommonAncestor(node5, node4);
        System.out.println("LCA of 5 and 4: " + result2.val);
    }
}
