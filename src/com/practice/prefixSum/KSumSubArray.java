package com.practice.prefixSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class KSumSubArray {

    /*
    K-Sum Subarrays
Find the number of subarrays in an integer array that sum to k.

Example:
Input: nums = [1, 2, -1, 1, 2], k = 3
Output: 3
    */
    static void main(String[] args) {
        int[] input = new int[] { 1, 2, -1, 1, 2 };
        System.out.println(kSumSubArray(input, 3));
        System.out.println(kSumSubArrayBruteForce(input, 3));
    }

    private static int kSumSubArrayBruteForce(int[] input, int k) {
        int n = input.length;
        int count = 0;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + input[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefixSum[j] - prefixSum[i - 1] == k) {
                    count++;
                }
            }
        }

        return  count;
    }

    private static int kSumSubArray(int[] input, int k) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> sumFrequency = new HashMap<>();
        sumFrequency.put(0, 1); // Initialize with sum 0 occurring once
        
        for (int num : input) {
            prefixSum += num;
            
            // Check if (prefixSum - k) exists in the map
            if (sumFrequency.containsKey(prefixSum - k)) {
                count += sumFrequency.get(prefixSum - k);
            }
            
            // Update the frequency of current prefix sum
            sumFrequency.put(prefixSum, sumFrequency.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
}
