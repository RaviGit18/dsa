package com.practice.dynamicProgrammming;

import java.util.*;

/**
 * Optimal Strategy for a Game
 * You are given an array of coins of different denominations in a line.
 * Two players take turns picking coins from either end.
 * Determine the maximum possible amount of money the first player can collect if both players play optimally.
 */

public class OptimalGameStrategy {

    public static void main(String[] args) {
        int[] coins = {8, 15, 3, 7};
        System.out.println("Coins: " + Arrays.toString(coins));
        int result = optimalStrategy(coins);
        System.out.println("Maximum amount first player can collect = " + result);

        int n = coins.length;

        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        System.out.println("Coins: " + Arrays.toString(coins));
        result = optimalStrategyRecursive(coins, 0, n - 1, memo);
        System.out.println("Maximum amount first player can collect = " + result);
    }

    // Function to calculate maximum amount first player can collect
    /*
    * OptimalStrategy(coins):
        n = length(coins)
        dp = 2D array of size n x n

        for gap = 0 to n-1:
            for i = 0, j = gap; j < n; i++, j++:
                x = (i+2 <= j) ? dp[i+2][j] : 0
                y = (i+1 <= j-1) ? dp[i+1][j-1] : 0
                z = (i <= j-2) ? dp[i][j-2] : 0

                dp[i][j] = max(
                    coins[i] + min(x, y),
                    coins[j] + min(y, z)
                )

        return dp[0][n-1]

    * */
    public static int optimalStrategy(int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][n];

        // gap represents the length of subarray
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                // x, y, z are used to handle cases when opponent plays optimally
                int x = (i + 2 <= j) ? dp[i + 2][j] : 0;
                int y = (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0;
                int z = (i <= j - 2) ? dp[i][j - 2] : 0;

                dp[i][j] = Math.max(
                        coins[i] + Math.min(x, y),  // pick left coin
                        coins[j] + Math.min(y, z)   // pick right coin
                );
            }
        }
        return dp[0][n - 1];
    }

    // Recursive + memoization function
    /*
    * OptimalStrategy(coins, i, j, memo):
        if i > j:
            return 0
        if i == j:
            return coins[i]

        if memo[i][j] != -1:
            return memo[i][j]

        pickLeft = coins[i] + min(
            OptimalStrategy(coins, i+2, j, memo),
            OptimalStrategy(coins, i+1, j-1, memo)
        )

        pickRight = coins[j] + min(
            OptimalStrategy(coins, i+1, j-1, memo),
            OptimalStrategy(coins, i, j-2, memo)
        )

        memo[i][j] = max(pickLeft, pickRight)
        return memo[i][j]

    * */
    public static int optimalStrategyRecursive(int[] coins, int i, int j, int[][] memo) {
        if (i > j) return 0; // no coins left
        if (i == j) return coins[i]; // only one coin left

        if (memo[i][j] != -1) return memo[i][j];

        // If player picks i-th coin:
        // Opponent will play optimally, so player gets min of next scenarios
        int pickLeft = coins[i] + Math.min(
                optimalStrategyRecursive(coins, i + 2, j, memo),   // opponent picks i+1
                optimalStrategyRecursive(coins, i + 1, j - 1, memo) // opponent picks j
        );

        // If player picks j-th coin:
        int pickRight = coins[j] + Math.min(
                optimalStrategyRecursive(coins, i + 1, j - 1, memo), // opponent picks i
                optimalStrategyRecursive(coins, i, j - 2, memo)      // opponent picks j-1
        );

        memo[i][j] = Math.max(pickLeft, pickRight);
        return memo[i][j];
    }
}
