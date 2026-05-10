package com.practice.backtracking;

import java.util.ArrayList;
import java.util.List;

class CombinationSum {

    /*Combinations of a Sum
Given an integer array and a target value, find all unique combinations in the array where the numbers in each combination sum to the target. Each number in the array may be used an unlimited number of times in the combination.

Example:
Input: nums = [1, 2, 3], target = 4
Output: [[1, 1, 1, 1], [1, 1, 2], [1, 3], [2, 2]]
Constraints:
All integers in nums are positive and unique.
The target value is positive.
The output must not contain duplicate combinations. For example, [1, 1, 2] and [1, 2, 1] are considered the same combination.
*/

    static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println("Combinations: " + combinationSum(nums, target));
    }

    private static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        //backtrack(nums, target, 0, new ArrayList<>(), result);
        backtrack1(nums, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int target, int startIndex, ArrayList<Integer> combination, List<List<Integer>> result) {

        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {

            combination.add(nums[i]);

            backtrack(nums, target - nums[i], i, combination, result);

            combination.remove(combination.size() - 1);

        }
    }

    private static void backtrack1(int[] nums, int target, int startIndex, ArrayList<Integer> combination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        if (target < 0 || startIndex >= nums.length) {
            return;
        }
        
        // Skip current element
        backtrack1(nums, target, startIndex + 1, combination, result);
        
        // Include current element (can reuse same element)
        combination.add(nums[startIndex]);
        backtrack1(nums, target - nums[startIndex], startIndex, combination, result);
        combination.remove(combination.size() - 1);
    }
}
