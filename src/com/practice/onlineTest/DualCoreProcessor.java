package com.practice.onlineTest;

import java.util.*;

/**
 * DualCoreProcessor - Game Theory + Dynamic Programming for optimal process assignment
 * 
 * Problem: Two cores compete for processes using a latch mechanism to maximize their own total time
 * 
 * Algorithm: Recursive DP with memoization for optimal assignment from each state
 * 
 * Time Complexity: O(N) where N = number of processes
 * Space Complexity: O(N) for memoization table
 */
public class DualCoreProcessor {

    /*java program for There is a dual core processor with one queue for each core. There are n processes,
    where the time to complete the (i)th process is denoted by time[i] (0 <= i <= n-1).
    There is a latch that helps decide which process is completed by which core.
    Initially, the first core has the latch.
    Suppoes the latch is currently with the (c)th core, where c is either 1 or 2, and the (i)th process needs to be assigned.
    Then one of the following operations must be performed:

The (i)th process is assigned to the core c, and the latch is given to the other core.
The (i)th process is assigned to the other core, and the latch is retained by the core c.
The aim of each core is to have a maximum sum of time of processes with them for better performance.
So, while assigning the (i)th ,
the core with the latch decides the operation to be performed such that
the total sum of time of processes assigned to it is maximized after all the processes are assigned.

Return the sum of time taken by the first process and the second process i.e. S1 and S2 in an array.

For eg: time = [10, 21, 10, 21, 10]
Output = {41, 31}

Explanation:
Optimal assignment from both cores would be:
C1 takes time[1], time[2], time[4] => Sum = 41
C2 takes time[0] and time[3] => Sum = 31
*/

    /*Idea

At every process:

the core holding the latch makes the decision
each core acts selfishly to maximize its own final total time

This is a classic:

Game Theory + Dynamic Programming

problem.

State Definition

We define:

solve(i, latch)

Where:

i = current process index
latch = current latch owner (1 or 2)

The function returns:

optimal sums for both cores from process i onward.
Choices

If latch is with Core 1:

Option 1

Assign process to Core 1:

Core 1 gets current time
latch moves to Core 2

Option 2

Assign process to Core 2:

latch remains with Core 1

Core 1 chooses the option maximizing its own total.

Same logic applies for Core 2.

Optimal Assignment

Core 1:

21 + 10 + 10 = 41

Core 2:

10 + 21 = 31
Time Complexity

States:

N × 2

Each state has 2 choices.

Overall:

O(N)

with memoization.

Space Complexity

Memo table:

O(N)
*/

    /**
     * Main method demonstrating optimal dual core process assignment
     * 
     * Example: time = [10, 21, 10, 21, 10]
     * Output: [41, 31] (Core1 sum, Core2 sum)
     */
    public static void main(String[] args) {

        int[] time =
                {10, 21, 10, 21, 10};

        System.out.println("Original DP solution:");
        int[] result = optimalAssignment(time);
        System.out.println("Core1 sum: " + result[0] + ", Core2 sum: " + result[1]);

    }

    /**
     * Helper class to store results for both cores
     * s1: total time for Core 1
     * s2: total time for Core 2
     */
    static class Result {

        int s1;
        int s2;

        Result(int s1, int s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }

    // Memoization table for DP: memo[process_index][latch_owner]
    static Result[][] memo;

    /**
     * Original DP solution with memoization
     * 
     * Algorithm: Recursive game theory with optimal substructure
     * 
     * Time Complexity: O(N) where N = number of processes
     * Space Complexity: O(N) for memoization table
     * 
     * @param time Array of process times
     * @return Array [Core1_sum, Core2_sum]
     */
    public static int[] optimalAssignment(int[] time) {

        int n = time.length;

        // memo[index][latch] stores optimal result from this state
        memo = new Result[n][3];

        Result ans = solve(time, 0, 1);

        return new int[]{ans.s1, ans.s2};
    }

    /**
     * Core recursive DP method for optimal assignment
     * 
     * Algorithm: Game theory with two options at each state
     * 
     * State: (i, latch) - current process index and latch owner
     * 
     * Options:
     * 1. Give current process to latch owner, pass latch to other core
     * 2. Give current process to other core, keep latch
     * 
     * Each core chooses option maximizing its own total time
     * 
     * Time Complexity: O(N) with memoization
     * Space Complexity: O(N) for memo table
     * 
     * @param time Array of process times
     * @param i Current process index
     * @param latch Current latch owner (1 or 2)
     * @return Optimal result from this state
     */
    private static Result solve(int[] time,
                                int i,
                                int latch) {

        // Base case: no more processes
        if (i == time.length) {
            return new Result(0, 0);
        }

        // Check memoization cache
        if (memo[i][latch] != null) {
            return memo[i][latch];
        }

        int current = time[i];
        Result best;

        // Latch with Core 1 - Core 1 makes decision
        if (latch == 1) {

            // Option 1: Keep process, give latch to Core 2
            Result r1 = solve(time, i + 1, 2);
            Result takeSelf = new Result(r1.s1 + current, r1.s2);

            // Option 2: Give process to Core 2, keep latch
            Result r2 = solve(time, i + 1, 1);
            Result giveOther = new Result(r2.s1, r2.s2 + current);

            // Core 1 maximizes its own sum (s1)
            best = (takeSelf.s1 >= giveOther.s1) ? takeSelf : giveOther;
        }
        // Latch with Core 2 - Core 2 makes decision
        else {

            // Option 1: Keep process, give latch to Core 1
            Result r1 = solve(time, i + 1, 1);
            Result takeSelf = new Result(r1.s1, r1.s2 + current);

            // Option 2: Give process to Core 1, keep latch
            Result r2 = solve(time, i + 1, 2);
            Result giveOther = new Result(r2.s1 + current, r2.s2);

            // Core 2 maximizes its own sum (s2)
            best = (takeSelf.s2 >= giveOther.s2) ? takeSelf : giveOther;
        }

        // Cache result for memoization
        memo[i][latch] = best;
        return best;
    }


}
