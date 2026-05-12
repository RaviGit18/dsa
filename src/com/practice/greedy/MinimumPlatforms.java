package com.practice.greedy;

import java.util.*;

/**
 *
 * Minimum Number of Platforms (Railway Scheduling)
 * Problem Statement:
 * Given arrival and departure times of trains at a railway station,
 * find the minimum number of platforms required so that no train waits.
 *
 * Example Input:
 *
 * Code
 * Arrival =  [900, 940, 950, 1100, 1500, 1800]
 * Departure = [910, 1200, 1120, 1130, 1900, 2000]
 * Expected Output:
 *
 * Code
 * Minimum platforms needed: 3
 * Constraints:
 *
 * 1 ≤ n ≤ 10^5
 *
 * Times are in 24-hour format (HHMM).
 */
public class MinimumPlatforms {

    public static void main(String[] args) {
        int[] arrival = {900, 940, 950, 1100, 1500, 1800};
        int[] departure = {910, 1200, 1120, 1130, 1900, 2000};

        int minPlatforms = findMinPlatforms(arrival, departure);
        System.out.println("Minimum platforms needed: " + minPlatforms);
    }

    /*
    * Explanation
        Sort arrival and departure arrays.

        Use two pointers (i for arrival, j for departure).

        Traverse both arrays:

            If a train arrives before the previous one departs → increment platform count.

            Else → decrement platform count (train departed).

        Track the maximum platforms needed at any time.
    * */
    public static int findMinPlatforms(int[] arrival, int[] departure) {
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int platformsNeeded = 1, result = 1;
        int i = 1, j = 0;
        int n = arrival.length;

        while (i < n && j < n) {
            // If next train arrives before the last one departs → need a new platform
            if (arrival[i] <= departure[j]) {
                platformsNeeded++;
                i++;
            } else {
                // Train has departed → free a platform
                platformsNeeded--;
                j++;
            }
            result = Math.max(result, platformsNeeded);
        }
        return result;
    }


}

