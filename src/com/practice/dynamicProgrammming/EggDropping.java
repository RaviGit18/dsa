package com.practice.dynamicProgrammming;

import java.util.*;

/**
 * Egg Dropping Puzzle
 * You are given k eggs and a building with n floors.
 * Find the minimum number of attempts needed in the worst case to determine
 * the highest floor from which an egg can be dropped without breaking.
 */
public class EggDropping {


    public static void main(String[] args) {
        int eggs = 2;
        int floors = 10;

        System.out.println("Eggs: " + eggs + ", Floors: " + floors);
        int result = eggDrop(eggs, floors);
        System.out.println("Minimum number of attempts in worst case = " + result);

        int[][] memo = new int[eggs + 1][floors + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        System.out.println("Eggs: " + eggs + ", Floors: " + floors);
        result = eggDropRecursive(eggs, floors, memo);
        System.out.println("Minimum number of attempts in worst case = " + result);
    }

    // Function to calculate minimum number of attempts
    /*
    * EggDrop(eggs, floors):
        create dp[eggs+1][floors+1]

        for i = 1 to eggs:
            dp[i][0] = 0
            dp[i][1] = 1

        for j = 1 to floors:
            dp[1][j] = j

        for e = 2 to eggs:
            for f = 2 to floors:
                dp[e][f] = ∞
                for x = 1 to f:
                    res = 1 + max(dp[e-1][x-1], dp[e][f-x])
                    dp[e][f] = min(dp[e][f], res)

        return dp[eggs][floors]

    * */
    public static int eggDrop(int eggs, int floors) {
        int[][] dp = new int[eggs + 1][floors + 1];

        // Base cases
        for (int i = 1; i <= eggs; i++) {
            dp[i][0] = 0; // 0 floors → 0 trials
            dp[i][1] = 1; // 1 floor → 1 trial
        }
        for (int j = 1; j <= floors; j++) {
            dp[1][j] = j; // 1 egg → j trials (linear search)
        }

        // Fill the rest of the table
        for (int e = 2; e <= eggs; e++) {
            for (int f = 2; f <= floors; f++) {
                dp[e][f] = Integer.MAX_VALUE;
                for (int x = 1; x <= f; x++) {
                    int res = 1 + Math.max(dp[e - 1][x - 1], dp[e][f - x]);
                    dp[e][f] = Math.min(dp[e][f], res);
                }
            }
        }

        return dp[eggs][floors];
    }


    // Recursive + memoization function
    /*
    * EggDrop(eggs, floors, memo):
        if floors == 0 or floors == 1:
            return floors
        if eggs == 1:
            return floors

        if memo[eggs][floors] != -1:
            return memo[eggs][floors]

        minAttempts = ∞
        for x = 1 to floors:
            breakCase = EggDrop(eggs-1, x-1, memo)
            surviveCase = EggDrop(eggs, floors-x, memo)
            worst = 1 + max(breakCase, surviveCase)
            minAttempts = min(minAttempts, worst)

        memo[eggs][floors] = minAttempts
        return minAttempts

    * */
    public static int eggDropRecursive(int eggs, int floors, int[][] memo) {
        // Base cases
        if (floors == 0 || floors == 1) return floors;
        if (eggs == 1) return floors; // must try all floors

        if (memo[eggs][floors] != -1) return memo[eggs][floors];

        int minAttempts = Integer.MAX_VALUE;

        // Try dropping from each floor x
        for (int x = 1; x <= floors; x++) {
            int breakCase = eggDropRecursive(eggs - 1, x - 1, memo); // egg breaks
            int surviveCase = eggDropRecursive(eggs, floors - x, memo); // egg survives
            int worst = 1 + Math.max(breakCase, surviveCase);
            minAttempts = Math.min(minAttempts, worst);
        }

        memo[eggs][floors] = minAttempts;
        return minAttempts;
    }
}

