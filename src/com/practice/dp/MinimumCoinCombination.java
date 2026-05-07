package com.practice.dp;

import java.util.HashMap;

class MinimumCoinCombination {
    /*Minimum Coin Combination
You are given an array of coin values and a target amount of money. Return the minimum number of coins needed to total the target amount. If this isn't possible, return ‐1. You may assume there's an unlimited supply of each coin.

Example 1:
Input: coins = [1, 2, 3], target = 5
Output: 2
Explanation: Use one 2-dollar coin and one 3-dollar coin to make 5 dollars.

Example 2:
Input: coins = [2, 4], target = 5
Output: -1
*/

    static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int target = 5;
        System.out.println("Minimum coins needed: " + minimumCoinCombination(coins, target));
    }

    private static int minimumCoinCombination(int[] coins, int target) {
        //int res = topDown(coins, target, new HashMap<>());
        int res = bottomUp(coins, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int bottomUp(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;

        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= target; i++) {

            for (int coin : coins) {

                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[target] == Integer.MAX_VALUE ? -1 : dp[target];
    }

    private static int topDown(int[] coins, int target, HashMap<Integer, Integer> memo) {
        if (target == 0) return 0;

        if (memo.containsKey(target)) return memo.get(target);

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {

            if (coin <= target) {

                int res = topDown(coins, target - coin, memo);

                if (res != Integer.MAX_VALUE) {
                    minCoins= Math.min(minCoins, 1 + res);
                }
            }

        }

        memo.put(target, minCoins);
        return minCoins;
    }
}
