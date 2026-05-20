package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * ‘N’ players are playing an online game and you are given the time in seconds each one requires to finish the game in the form of an array ‘A’. Now, the game requires to form pairs of players such that the total time taken is divisible by 60 seconds. More formally you need to find pairs with indices ( ‘i’ , ‘j’ ) such that ‘i’ < ‘j’ and ( A[i] + A[j] ) is divisible by 60.
 *
 * Output the total number of valid pairs.
 *
 * Note that a single player can be a part of multiple pairs.
 *
 * Example :
 * N = 5
 * A = [ 30, 20, 30,40, 100 ]
 *
 * Explanation :
 *
 * The valid pairs are :
 *
 * ( 30, 30 ) as the sum is 60.
 * ( 20, 40 ) as the sum is 60.
 * ( 20, 100 ) as the sum is 120 which is divisible by 60.
 *
 * Thus we output 3.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 1 <= N <= 10^5
 * 1 <= A[i] <= 10^9
 *
 * Time Limit : 1 sec
 * Sample Input 1 :
 * 2
 * 3
 * 60 60 60
 * 4
 * 150 20 30 100
 * Sample Output 1 :
 * 3
 * 2
 * Explanation Of Sample Input 1 :
 * For test case 1 we have,
 *
 * There are three pairs with indices ( 1, 2) , ( 1, 3) and ( 2, 3).
 *
 * So, we output 3.
 *
 * For test case 2 we have,
 *
 * The valid pairs are :
 *
 * ( 150, 30 ) with sum 180 divisible by 60.
 * ( 20, 100 ) with sum 120 divisible by 60.
 *
 * So, we output 2.
 * Sample Input 2 :
 * 2
 * 2
 * 40 20
 * 5
 * 30 20 90 40 60
 * Sample Output 2 :
 * 1
 * 2
 */
/**
 * Algorithm:
 * - For each time value, compute its remainder when divided by 60
 * - Two numbers sum to a multiple of 60 if their remainders sum to 0 or 60
 * - Use a frequency array to count occurrences of each remainder (0-59)
 * - For remainder 0: pairs are count * (count - 1) / 2
 * - For remainder 30: pairs are count * (count - 1) / 2
 * - For other remainders r: pairs are count[r] * count[60 - r]
 * - Sum all valid pairs
 *
 * Time Complexity: O(N)
 * - N = number of players
 * - Single pass through the array
 *
 * Space Complexity: O(1)
 * - Fixed size array of 60 for remainders
 */
import java.util.*;

public class TimingSum {

    public static int countPairs(int[] arr) {
        int[] remainderCount = new int[60];
        int totalPairs = 0;

        for (int time : arr) {
            int remainder = time % 60;
            remainderCount[remainder]++;
        }

        totalPairs += remainderCount[0] * (remainderCount[0] - 1) / 2;
        totalPairs += remainderCount[30] * (remainderCount[30] - 1) / 2;

        for (int r = 1; r < 30; r++) {
            totalPairs += remainderCount[r] * remainderCount[60 - r];
        }

        return totalPairs;
    }

    public static void main(String[] args) {
        int[] arr1 = {60, 60, 60};
        System.out.println("Test 1: " + countPairs(arr1));

        int[] arr2 = {150, 20, 30, 100};
        System.out.println("Test 2: " + countPairs(arr2));

        int[] arr3 = {40, 20};
        System.out.println("Test 3: " + countPairs(arr3));

        int[] arr4 = {30, 20, 90, 40, 60};
        System.out.println("Test 4: " + countPairs(arr4));
    }
}
