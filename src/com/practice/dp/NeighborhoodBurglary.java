package com.practice.dp;

class NeighborhoodBurglary {

    /*  Neighborhood Burglary
You plan to rob houses in a street where each house stores a certain amount of money. The neighborhood has a security system that sets off an alarm when two adjacent houses are robbed. Return the maximum amount of cash that can be stolen without triggering the alarms.

Example:
Image represents a horizontal arrangement of four stylized houses from left to right. The houses each consist of a rectangular body and a triangular roof. The first and third houses from the left have light orange bodies, while the second and fourth houses have light grey bodies. Each house body contains a black dollar value label: the first house has $200, the second has $300, the third has $200, and the fourth has $50. Below the first house, slightly to the right of its center, is a black dot. Below the third house, also slightly to the right of its center, is another black dot. A curved black line originates from the dot below the first house and curves upwards and then downwards to connect to the dot below the third house, forming an arc that spans beneath the second house. Text labels are placed below the dots: +$200 is positioned below the dot for the first house, and another +$200 is positioned below the dot for the third house. The overall diagram appears to depict a process or interaction where the first and third houses, both valued at $200, are selected or involved in an operation, possibly adding or transferring a value of $200 at each point.
Input: houses = [200, 300, 200, 50]
Output: 400
Explanation: Stealing from the houses at indexes 0 and 2 yields 200 + 200 = 400 dollars.
*/

    static void main(String[] args) {
        int[] houses = {200, 300, 200, 50};
        System.out.println("Maximum cash stolen: " + maxCashStolen(houses));
        System.out.println("Maximum cash stolen another approach: " + maxCashStolen1(houses));
        System.out.println("Maximum cash stolen recursive: " + maxCashStolenRecursive(houses));
    }

    private static int maxCashStolen(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];

        int[] dp = new int[houses.length];

        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            dp[i] = Math.max(dp[i-1] , dp[i-2] + houses[i]);
        }

        return dp[houses.length - 1];
    }

    private static int maxCashStolen1(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];



        int prevPrevMaxProfit = houses[0];
        int prevMaxProfit = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            int currMaxProfit = Math.max(prevMaxProfit , prevPrevMaxProfit + houses[i]);

            prevPrevMaxProfit = prevMaxProfit;
            prevMaxProfit = currMaxProfit;
        }

        return prevMaxProfit;
    }

    private static int maxCashStolenRecursive(int[] houses) {
        return maxCashStolenRecursiveHelper(houses, houses.length - 1);
    }

    private static int maxCashStolenRecursiveHelper(int[] houses, int index) {
        if (index < 0) return 0;
        if (index == 0) return houses[0];
        
        int robCurrent = houses[index] + maxCashStolenRecursiveHelper(houses, index - 2);
        int skipCurrent = maxCashStolenRecursiveHelper(houses, index - 1);
        
        return Math.max(robCurrent, skipCurrent);
    }
}
