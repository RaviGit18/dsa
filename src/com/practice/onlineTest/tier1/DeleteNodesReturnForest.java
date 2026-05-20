package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a binary tree that contains unique elements. You have to delete all nodes with value in array 'delNodes[]'. Once the nodes are deleted, you will have a set of multiple disjoint trees. We define this set as a forest.
 *
 * Your task is to return a list of pointers to the root node of the disjoint trees,
 *
 * Example :
 *
 *
 * If the nodes marked in red are deleted we will have the following disjoint trees:
 *
 *
 * Therefore the result will be [ (1), (10), (6) ].
 *
 * Here (1) means a pointer to the node with the value 1.
 *
 * Note :
 *
 * The order of elements within the lists doesn’t matter.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 5
 * 1 <= N <= 3000
 * 1 <= K <= N
 * 0 <= Node Data <= 5000
 *
 * Time Limit: 1sec
 * Sample Input 1 :
 * 1
 * 1 2 5 7 9 10 6 -1 -1 -1 -1 -1 -1 -1 -1
 * 2
 * 5 7
 * Sample Output 1 :
 * [ (1) , (10), (6) ]
 * Explanation For Sample Input 1 :
 * After deletion of nodes we will have 3 disjoint trees:
 *
 * The root node of the three trees is with values 1, 10, 6.
 * Sample Input 2 :
 * 2
 * 4 10 -1 -1 11 -1 15 14 -1 -1 -1
 * 2
 * 15 4
 * 11 16 17 6 15 -1 -1 -1 -1 -1 -1
 * 0
 * Sample Output 2 :
 * [ (10) (14) ]
 * [ (11) ]
 */
import java.util.*;

class TreeNode6 {
    int val;
    TreeNode6 left;
    TreeNode6 right;
    TreeNode6() {}
    TreeNode6(int val) { this.val = val; }
    TreeNode6(int val, TreeNode6 left, TreeNode6 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class DeleteNodesReturnForest {
    public List<TreeNode6> delNodes(TreeNode6 root, int[] to_delete) {
        List<TreeNode6> forest = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();
        for (int val : to_delete) {
            deleteSet.add(val);
        }
        deleteNodes(root, deleteSet, forest, true);
        return forest;
    }
    
    private TreeNode6 deleteNodes(TreeNode6 node, Set<Integer> deleteSet, List<TreeNode6> forest, boolean isRoot) {
        if (node == null) return null;
        
        boolean shouldDelete = deleteSet.contains(node.val);
        if (isRoot && !shouldDelete) {
            forest.add(node);
        }
        
        node.left = deleteNodes(node.left, deleteSet, forest, shouldDelete);
        node.right = deleteNodes(node.right, deleteSet, forest, shouldDelete);
        
        return shouldDelete ? null : node;
    }
    
    public static void main(String[] args) {
        DeleteNodesReturnForest solution = new DeleteNodesReturnForest();

        TreeNode6 root = new TreeNode6(1);
        root.left = new TreeNode6(2);
        root.right = new TreeNode6(3);
        root.left.left = new TreeNode6(4);
        root.left.right = new TreeNode6(5);
        root.right.left = new TreeNode6(6);
        root.right.right = new TreeNode6(7);
        
        int[] to_delete = {3, 5};
        List<TreeNode6> result = solution.delNodes(root, to_delete);
        
        System.out.println("Number of trees in forest: " + result.size());
    }
}
