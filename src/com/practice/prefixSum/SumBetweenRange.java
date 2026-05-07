package com.practice.prefixSum;

import java.util.Arrays;

class SumBetweenRange {

    /*Sum Between Range
Given an integer array, write a function which returns the sum of values between two indexes.

Example:
Input: nums = [3, -7, 6, 0, -2, 5],
       [sum_range(0, 3), sum_range(2, 4), sum_range(2, 2)]
Output: [2, 4, 6]
Constraints:
nums contains at least one element.

Each sum_range operation will query a valid range of the input array.
*/

    static void main(String[] args) {
        int[] input = new int[] { 3, -7, 6, 0, -2, 5 };
        System.out.println(Arrays.toString(sumRange(input, new int[][] { { 0, 3 }, { 2, 4 }, { 2, 2 } })));
    }

    private static int[] sumRange(int[] input, int[][] ranges) {
        int[] output = new int[ranges.length];

        int[] prefixSum = new int[input.length];
        prefixSum[0] = input[0];
        for (int i = 1; i < input.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + input[i];
        }

        for (int i = 0; i < ranges.length; i++) {
            int[] range = ranges[i];
            if (range[0] == 0) {
                output[i] = prefixSum[range[1]];
            } else {
                output[i] = prefixSum[range[1]] - prefixSum[range[0] - 1];
            }
        }

        return output;
    }
}
