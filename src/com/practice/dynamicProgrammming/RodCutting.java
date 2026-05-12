package com.practice.dynamicProgrammming;

import java.util.Arrays;

/*
* Rod Cutting problem
* You are given a rod of length n and an array of prices that contains prices of all pieces of size smaller than n.
* Find the maximum value obtainable by cutting up the rod and selling the pieces.
* */


public class RodCutting {

    public static void main(String[] args) {
        // Example input
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8; // rod length

        int maxProfit = rodCutting(prices, n);
        System.out.println("Maximum Obtainable Value: " + maxProfit);


        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        maxProfit = rodCuttingRecursive(prices, n, memo);
        System.out.println("Maximum Obtainable Value (Recursive): " + maxProfit);
    }

    // Function to calculate maximum profit
    /*
* function rodCut(prices, n):
    dp = array[n+1]
    dp[0] = 0

    for i in 1..n:
        maxVal = -∞
        for j in 1..i:
            maxVal = max(maxVal, prices[j] + dp[i-j])
        dp[i] = maxVal

    return dp[n]
*/
    /*
    * Bottom-Up Dynamic Programming
        Iteratively build solutions for rod lengths from 1 to n.

        Initialize array dp[0..n] with dp[0] = 0

        For each length i from 1 to n:

            Set maxVal = -∞

            For each cut j from 1 to i:

                Compute maxVal = max(maxVal, price[j-1] + dp[i-j])

            Store dp[i] = maxVal

        Return dp[n] as maximum profit
    * */
    public static int rodCutting(int[] prices, int n) {
        int[] dp = new int[n + 1]; // dp[i] stores max profit for rod length i

        // Bottom-up DP approach
        for (int i = 1; i <= n; i++) {
            int maxVal = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                maxVal = Math.max(maxVal, prices[j - 1] + dp[i - j]);
            }
            dp[i] = maxVal;
        }

        return dp[n];
    }

    // Recursive function with memoization
    /*
    * Recursive + Memoization
    Solve subproblems recursively and cache results to avoid recomputation.

    Define function rodCut(n):

        If n == 0, return 0

        If memo[n] is not -1, return memo[n]

        Initialize maxVal = -∞

        For each cut i from 1 to n:

            Compute maxVal = max(maxVal, price[i-1] + rodCut(n-i))

        Store memo[n] = maxVal

        Return memo[n]

    Initialize memo[0..n] with -1

    Call rodCut(n)
    * */
    public static int rodCuttingRecursive(int[] prices, int n, int[] memo) {
        if (n == 0) return 0; // base case

        if (memo[n] != -1) return memo[n]; // return cached result

        int maxVal = Integer.MIN_VALUE;

        // Try all possible cuts
        for (int i = 1; i <= n; i++) {
            maxVal = Math.max(maxVal, prices[i - 1] + rodCuttingRecursive(prices, n - i, memo));
        }

        memo[n] = maxVal; // store result
        return maxVal;
    }
}

