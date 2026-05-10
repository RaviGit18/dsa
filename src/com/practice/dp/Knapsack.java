package com.practice.dp;

class Knapsack {
    /*0/1 Knapsack
You are a thief planning to rob a store. However, you can only carry a knapsack with a maximum capacity of cap units. Each item (i) in the store has a weight (weights[i]) and a value (values[i]).

Find the maximum total value of items you can carry in your knapsack.

Example:
Image represents a visual depiction of a knapsack problem, a classic optimization problem in computer science.  Four items are shown, each labeled 'item 0,' 'item 1,' 'item 2,' and 'item 3,' respectively. Each item has an associated value (price) represented by a dollar amount ($70, $50, $40, $0) displayed in a gray rectangle, and a weight (w) indicated numerically (5, 3, 4, 1). A larger, brown rectangular box labeled 'cap = 7' represents the knapsack with a weight capacity of 7. Inside the knapsack are items 1 and 2, indicating that these items have been selected.  The arrangement shows that items 1 and 2, with a combined weight of 7 (3 + 4), fill the knapsack to its capacity.  The other items are not included, suggesting they were not selected due to weight constraints or to optimize the value within the capacity limit.  The image illustrates a solution to the knapsack problem where the goal is to maximize the total value of items within the knapsack's weight constraint.
Input: cap = 7, weights = [5, 3, 4, 1], values = [70, 50, 40, 10]
Output: 90
Explanation: The most valuable combination of items that can fit in the knapsack together are items 1 and 2 . These items have a combined value of 50 + 40 = 90 and a total weight of 3 + 4 = 7 , which fits within the knapsack's capacity.
*/

    static void main(String[] args) {
        int[] weights = {5, 3, 4, 1};
        int[] values = {70, 50, 40, 10};
        int capacity = 7;
        System.out.println("Maximum value: " + knapSack(capacity, weights, values));
        System.out.println("Maximum value: " + knapSack1(capacity, weights, values));
    }

    private static int knapSack(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int c = 1; c <= capacity; c++) {

                if (weights[i] <= c) {
                    dp[i][c] = Math.max(values[i] + dp[i + 1][c - weights[i]], dp[i + 1][c]);
                } else  {
                    dp[i][c] = dp[i + 1][c];
                }
            }
        }

        return  dp[0][capacity];

    }

    private static int knapSack1(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        int[] prevRow = new int[capacity + 1];

        for (int i = n - 1; i >= 0; i--) {

            int[] currentRow = new int[capacity + 1];

            for (int c = 1; c <= capacity; c++) {

                if (weights[i] <= c) {
                    currentRow[c] = Math.max(values[i] + prevRow[c - weights[i]], prevRow[c]);
                } else  {
                    currentRow[c] = prevRow[c];
                }
            }

            prevRow = currentRow;
        }

        return  prevRow[capacity];

    }
}
