package com.practice.pointers;

import java.util.HashMap;
import java.util.Map;

class PairSum {

    static void main(String[] args) {

        /*SORTED ARRAY*/
        /*Pair Sum - Sorted
Given an array of integers sorted in ascending order and a target value, return the indexes of any pair of numbers in the array that sum to the target. The order of the indexes in the result doesn't matter. If no pair is found, return an empty array.

Example 1:
Input: nums = [-5, -2, 3, 4, 6], target = 7
Output: [2, 3]
Explanation: nums[2] + nums[3] = 3 + 4 = 7

Example 2:
Input: nums = [1, 1, 1], target = 2
Output: [0, 1]
Explanation: other valid outputs could be [1, 0], [0, 2], [2, 0], [1, 2] or [2, 1].*/

        int[] nums = {-5, -2, 3, 4, 6};
        //int target = 1;
        int target = 7;

        //int[] nums = {1, 1, 1};
        //int target = 2;

        System.out.println(pairSumBruteForce(nums, target)[0] + ", " + pairSumBruteForce(nums, target)[1]);
        System.out.println(pairSum(nums, target)[0] + ", " + pairSum(nums, target)[1]);

        /*UNSORTED ARRAY*/
        /*Pair Sum - Unsorted
Given an array of integers, return the indexes of any two numbers that add up to a target. The order of the indexes in the result doesn't matter. If no pair is found, return an empty array.

Example:
Input: nums = [-1, 3, 4, 2], target = 3
Output: [0, 2]
Explanation: nums[0] + nums[2] = -1 + 4 = 3

Constraints:
The same index cannot be used twice in the result.*/

        int[] nums1 = {3, -5, 6, 4, -2};
        System.out.println(pairSumUnSorted(nums, target)[0] + ", " + pairSumUnSorted(nums, target)[1]);
        System.out.println(pairSumUnSortedOptimized(nums, target)[0] + ", " + pairSumUnSortedOptimized(nums, target)[1]);
    }

    private static int[] pairSumUnSorted(int[] nums, int target) {
        int[] indexOutput = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                indexOutput[0] = i;
                indexOutput[1] = map.get(complement);
                return indexOutput;
            }
        }

        return new int[]{-1, -1};
    }

    private static int[] pairSumUnSortedOptimized(int[] nums, int target) {
        int[] indexOutput = new int[2];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {

                indexOutput[0] = map.get(complement);
                indexOutput[1] = i;

                return indexOutput;
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[]{-1, -1};
    }

    private  static int[] pairSum(int[] nums, int target) {
        int[] result = new int[2];
        int i = 0, j = nums.length - 1;

        while (i < j) {
             if (nums[i] + nums[j] == target) {
                result[0] = i;
                result[1] = j;
                return result;
             } else if (nums[i] + nums[j] < target) {
                i++;
             } else {
                j--;
             }
        }

        return result;
    }

    //This will work with sorted and unsorted array both
    private static int[] pairSumBruteForce(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

}
