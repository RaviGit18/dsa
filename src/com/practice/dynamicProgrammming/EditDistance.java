package com.practice.dynamicProgrammming;

import java.util.*;

/*Problem Statement
Given two strings A and B, find the minimum number of operations required to convert A into B.
Allowed operations:

Insert a character

Delete a character

Replace a character*/

/*
* function editDistance(A, B):
    n = length(A)
    m = length(B)
    dp = array[n+1][m+1]

    for i in 0..n:
        dp[i][0] = i
    for j in 0..m:
        dp[0][j] = j

    for i in 1..n:
        for j in 1..m:
            if A[i-1] == B[j-1]:
                dp[i][j] = dp[i-1][j-1]
            else:
                dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])

    return dp[n][m]
*/

public class EditDistance {

    public static void main(String[] args) {

        String A = "kitten";
        String B = "sitting";

        int result = editDistance(A, B);

        // Output
        System.out.println("Minimum Edit Distance: " + result);

        result = editDistanceOptimized(A, B);
        System.out.println("Minimum Edit Distance (Optimized): " + result);

        result = editDistanceRecursive(A, B);
        System.out.println("Minimum Edit Distance (Recursive): " + result);
    }

    public static int editDistance(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];

        // Base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i; // delete all characters
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j; // insert all characters
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],      // delete
                            Math.min(dp[i][j - 1], // insert
                                    dp[i - 1][j - 1]) // replace
                    );
                }
            }
        }

        return dp[n][m];
    }


    //Space Optimized
    public static int editDistanceOptimized(String A, String B) {
        int n = A.length();
        int m = B.length();

        // Only two rows needed
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        // Base case: converting empty A to B
        for (int j = 0; j <= m; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= n; i++) {
            curr[0] = i; // converting first i chars of A to empty B
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    curr[j] = prev[j - 1]; // no operation
                } else {
                    curr[j] = 1 + Math.min(
                            prev[j],       // delete
                            Math.min(curr[j - 1], // insert
                                    prev[j - 1]) // replace
                    );
                }
            }
            // Move current row to previous for next iteration
            prev = curr.clone();
        }

        return prev[m];
    }

    //Recursive + Memoization
    public static int editDistanceRecursive(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] memo = new int[n + 1][m + 1];

        // Initialize memo table with -1
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return helper(A, B, n, m, memo);
    }

    private static int helper(String A, String B, int i, int j, int[][] memo) {
        // Base cases
        if (i == 0) return j; // need j insertions
        if (j == 0) return i; // need i deletions

        if (memo[i][j] != -1) return memo[i][j];

        if (A.charAt(i - 1) == B.charAt(j - 1)) {
            memo[i][j] = helper(A, B, i - 1, j - 1, memo);
        } else {
            int insertOp = helper(A, B, i, j - 1, memo);
            int deleteOp = helper(A, B, i - 1, j, memo);
            int replaceOp = helper(A, B, i - 1, j - 1, memo);

            memo[i][j] = 1 + Math.min(insertOp, Math.min(deleteOp, replaceOp));
        }

        return memo[i][j];
    }
}

