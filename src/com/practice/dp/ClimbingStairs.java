package com.practice.dp;

import java.util.HashMap;
import java.util.Map;

class ClimbingStairs {

    /*Climbing Stairs
Determine the number of distinct ways to climb a staircase of n steps by taking either 1 or 2 steps at a time.
Input: n = 4
Output: 5
*/

    static void main(String[] args) {
        int n = 4;

        System.out.println("Number of ways to climb stairs: " + climbStairs(n));
    }

    private static int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = 0;
        //result = climbStairsTopDown(n, memo);
        //result = climbStairsBottomUp(n);
        result = climbStairsBottomUpOptimized(n);
        return  result;
    }

    private static int climbStairsBottomUpOptimized(int n) {
        if (n <= 2) {
            return n;
        }

        int oneStepBefore = 2;
        int twoStepBefore = 1;

        for (int i = 3; i <= n; i++) {
            int current = oneStepBefore + twoStepBefore;

            twoStepBefore = oneStepBefore;
            oneStepBefore = current;
        }

        return oneStepBefore;
    }

    private static int climbStairsBottomUp(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    private static int climbStairsTopDown(int n, Map<Integer, Integer> memo) {
        if (n <= 2) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = climbStairsTopDown(n - 1, memo) + climbStairsTopDown(n - 2, memo);
        memo.put(n, result);

        return result;
    }
}
