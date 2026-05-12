package com.practice.dynamicProgrammming;

import java.util.*;

/**
* Matrix Chain Multiplication.
* Given a sequence of matrices, find the most efficient way to multiply these matrices together.
* The problem is not to perform the multiplications but
* to decide the order in which to multiply them to minimize the total number of scalar multiplications.
*/

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        // Example: matrices A1(10x30), A2(30x5), A3(5x60)
        int[] dims = {10, 30, 5, 60};

        System.out.println("Matrix dimensions: " + Arrays.toString(dims));
        int minCost = matrixChainOrder(dims);
        System.out.println("Minimum number of multiplications = " + minCost);

        int n = dims.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        System.out.println("Matrix dimensions: " + Arrays.toString(dims));
        int minCostRecursive = matrixChainMemo(dims, 0, n - 1, memo);
        System.out.println("Minimum number of multiplications = " + minCostRecursive);
    }

    // Function to compute minimum multiplication cost
    /**
     * MatrixChainOrder(dims):
     *     n = length(dims)
     *     dp = 2D array of size n x n
     *
     *     for len = 2 to n-1:
     *         for i = 0 to n-len-1:
     *             j = i + len
     *             dp[i][j] = ∞
     *             for k = i+1 to j-1:
     *                 cost = dp[i][k] + dp[k][j] + dims[i] * dims[k] * dims[j]
     *                 dp[i][j] = min(dp[i][j], cost)
     *
     *     return dp[0][n-1]
     */
    public static int matrixChainOrder(int[] dims) {
        int n = dims.length;
        int[][] dp = new int[n][n];

        // dp[i][j] = minimum cost to multiply matrices from i to j
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + dims[i] * dims[k] * dims[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1];
    }

    // Recursive + memoization function

    /**
     * MatrixChainMemo(dims, i, j, memo):
     *     if i+1 == j:
     *         return 0
     *
     *     if memo[i][j] != -1:
     *         return memo[i][j]
     *
     *     minCost = ∞
     *     for k = i+1 to j-1:
     *         cost = MatrixChainMemo(dims, i, k, memo)
     *              + MatrixChainMemo(dims, k, j, memo)
     *              + dims[i] * dims[k] * dims[j]
     *         minCost = min(minCost, cost)
     *
     *     memo[i][j] = minCost
     *     return minCost
     */
    public static int matrixChainMemo(int[] dims, int i, int j, int[][] memo) {
        if (i + 1 == j) return 0; // only one matrix, no multiplication needed

        if (memo[i][j] != -1) return memo[i][j];

        int minCost = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = matrixChainMemo(dims, i, k, memo)
                    + matrixChainMemo(dims, k, j, memo)
                    + dims[i] * dims[k] * dims[j];
            minCost = Math.min(minCost, cost);
        }

        memo[i][j] = minCost;
        return minCost;
    }
}
