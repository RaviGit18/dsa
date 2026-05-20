package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given 'N' blocks which are numbered from 0 to 'N' - 1 and are arranged in a straight line. Each block has an integer label on it which denotes the index of the next block that you can go to from that block. The labels on each block are given in the array 'ARR'. Your task is to find out the maximum number of distinct blocks that you can reach by starting from any arbitrary block and moving through all the blocks that you are allowed to move from that block and all other subsequent blocks.
 *
 * For example:
 *
 * Consider the array ARR = { 0, 2, 1 } having 3 elements.
 * Starting at Block 0, you can move to only Block 0 and you cannot move to any other blocks.
 * Starting at Block 1, you can move between Block 1 and Block 2.
 * Starting at Block 2, you can move between Block 2 and Block 1.
 * Hence, the maximum number of distinct that you can visit is 2 in this case.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 10^5
 * 0 <= ARR[i] < N
 *
 * All elements of the array ARR are pairwise distinct.
 *
 * Time limit: 1 sec
 * Sample Input 1:
 * 2
 * 4
 * 1 0 2 3
 * 3
 * 2 0 1
 * Sample Output 1:
 * 2
 * 3
 * Explanation for Sample Input 1:
 * For the first test case, if we start from block 0, then we can move to block 1 and then back again to block 0. Hence, the maximum number of distinct blocks is 2 in this case.
 *
 * For the second test case, as all the blocks are reachable from any starting block, the maximum number of distinct blocks is 3 in this case.
 * Sample Input 2:
 * 2
 * 3
 * 2 1 0
 * 4
 * 1 0 3 2
 * Sample Output 2:
 * 2
 * 2
 */
import java.util.*;

public class MaximumDistinctBlocks {
    public int maximumDistinctBlocks(int[] arr) {
        int n = arr.length;
        int maxDistinct = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int count = 0;
                int current = i;
                
                while (!visited[current]) {
                    visited[current] = true;
                    count++;
                    current = arr[current];
                }
                
                maxDistinct = Math.max(maxDistinct, count);
            }
        }
        
        return maxDistinct;
    }

    public static void main(String[] args) {
        MaximumDistinctBlocks mdb = new MaximumDistinctBlocks();
        
        // Test case 1: [1, 0, 2, 3]
        int[] arr1 = {1, 0, 2, 3};
        System.out.println("Test case 1: " + mdb.maximumDistinctBlocks(arr1));
        
        // Test case 2: [2, 0, 1]
        int[] arr2 = {2, 0, 1};
        System.out.println("Test case 2: " + mdb.maximumDistinctBlocks(arr2));
        
        // Test case 3: [2, 1, 0]
        int[] arr3 = {2, 1, 0};
        System.out.println("Test case 3: " + mdb.maximumDistinctBlocks(arr3));
    }
}
