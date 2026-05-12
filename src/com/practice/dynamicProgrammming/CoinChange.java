package com.practice.dynamicProgrammming;

import java.util.*;

/*
* Given a set of coin denominations and a target amount:
* Variant 1 (Count ways): Find the total number of ways to make the amount using the coins.
* Variant 2 (Min coins): Find the minimum number of coins required to make the amount.
* */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Amount: " + amount);

        // Variant 1
        int ways = countWays(coins, amount);
        System.out.println("Number of ways to make " + amount + " = " + ways);

        // Variant 2
        int min = minCoins(coins, amount);
        if (min == -1) {
            System.out.println("Not possible to make " + amount);
        } else {
            System.out.println("Minimum coins required to make " + amount + " = " + min);
        }

        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Amount: " + amount);

        // Variant 1
        int waysRecursive = countWaysRecursive(coins, amount, 0, new HashMap<>());
        System.out.println("Number of ways to make " + amount + " = " + waysRecursive);

        // Variant 2
        int minRecursive = minCoinsRecursive(coins, amount, new HashMap<>());
        if (minRecursive == Integer.MAX_VALUE) {
            System.out.println("Not possible to make " + amount);
        } else {
            System.out.println("Minimum coins required to make " + amount + " = " + minRecursive);
        }
    }

    // Variant 1: Count ways to make the amount
    public static int countWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case: one way to make 0

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    // Variant 2: Minimum coins to make the amount
    public static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // initialize with large value
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount]; // -1 if not possible
    }

    // Variant 1: Count ways (recursive + memoization)
    public static int countWaysRecursive(int[] coins, int amount, int index, Map<String, Integer> memo) {
        if (amount == 0) return 1; // one valid way
        if (amount < 0 || index >= coins.length) return 0; // no way

        String key = index + "-" + amount;
        if (memo.containsKey(key)) return memo.get(key);

        // Choice: include coin[index] OR skip it
        int include = countWaysRecursive(coins, amount - coins[index], index, memo);
        int exclude = countWaysRecursive(coins, amount, index + 1, memo);

        memo.put(key, include + exclude);
        return memo.get(key);
    }

    // Variant 2: Min coins (recursive + memoization)
    public static int minCoinsRecursive(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;

        if (memo.containsKey(amount)) return memo.get(amount);

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = minCoinsRecursive(coins, amount - coin, memo);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }

        memo.put(amount, min);
        return min;
    }
}
