package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a positive integer N. Your task is to create the lexicographically largest sequence of length 2*N - 1 containing integers between 1 to N such that:
 *
 * 1. 1 occurs in the sequence exactly once.
 * 2. Each integer between 2 to N(inclusive) occurs in the sequence exactly twice.
 * 3. For each integer i between 2 to N, the distance between the two occurrences of i should be exactly i.
 * Note:
 *
 * 1. A sequence A is lexicographically larger than a sequence B (of the same length), if in the first position where A and B differ, sequence A has a number greater than the corresponding number in B.
 * 2. It is guaranteed that under the given constraints, there is always a solution.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 100
 * 1 <= N <= 20
 *
 * Time Limit: 1 second
 * Sample Input 1:
 * 1
 * 2
 * Sample Output 1:
 * 2 1 2
 * Explanation Of Sample Input 1:
 * In test case 1: Since 1 occurs exactly once and 2 occurs exactly twice, and the distance between the two occurrences of 2 is 2. The sequence {2,1,2} is also the lexicographically largest sequence possible that satisfies the given condition.
 * Sample Input 2:
 * 1
 * 3
 * Sample Output 2:
 * 3 1 2 3 2
 * Explanation Of Sample Input 2:
 * In test case 1: [3, 1, 2, 3, 2] is the largest lexicographically sequence which we need.
 */
import java.util.*;

public class LexicographicallyLargestValidSequence {
    private int[] result;
    private boolean found;
    
    public int[] lexicographicallyLargestValidSequence(int n) {
        result = new int[2 * n - 1];
        found = false;
        int[] used = new int[n + 1];
        backtrack(n, 0, used);
        return result;
    }
    
    private void backtrack(int n, int pos, int[] used) {
        if (found) return;
        
        if (pos == 2 * n - 1) {
            found = true;
            return;
        }
        
        for (int i = n; i >= 1; i--) {
            if (i == 1) {
                if (used[1] == 0) {
                    result[pos] = 1;
                    used[1] = 1;
                    backtrack(n, pos + 1, used);
                    if (found) return;
                    used[1] = 0;
                }
            } else {
                if (used[i] == 0 && pos + i < 2 * n - 1 && result[pos + i] == 0) {
                    result[pos] = i;
                    result[pos + i] = i;
                    used[i] = 2;
                    backtrack(n, pos + 1, used);
                    if (found) return;
                    used[i] = 0;
                    result[pos] = 0;
                    result[pos + i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        LexicographicallyLargestValidSequence llvs = new LexicographicallyLargestValidSequence();
        
        // Test case 1: n = 2, expected output: [2, 1, 2]
        int[] result1 = llvs.lexicographicallyLargestValidSequence(2);
        System.out.println("Test case 1 (n=2): " + Arrays.toString(result1));
        
        // Test case 2: n = 3, expected output: [3, 1, 2, 3, 2]
        int[] result2 = llvs.lexicographicallyLargestValidSequence(3);
        System.out.println("Test case 2 (n=3): " + Arrays.toString(result2));
        
        // Test case 3: n = 4
        int[] result3 = llvs.lexicographicallyLargestValidSequence(4);
        System.out.println("Test case 3 (n=4): " + Arrays.toString(result3));
    }
}
