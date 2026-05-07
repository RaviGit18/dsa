package com.practice.binarySearch;

class OccuranceIndex {

    /*First and Last Occurrences of a Number
Given an array of integers sorted in non-decreasing order, return the first and last indexes of a target number. If the target is not found, return [-1, -1] .

Example 1:
Input: nums = [1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11],
       target = 4
Output: [3, 5]
Explanation: The first and last occurrences of number 4 are indexes 3 and 5, respectively.*/

    static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11};
        int target = 4;
        int[] output = getFirstAndLast(nums, target);
        System.out.println("First and last occurrences: " + output[0] + " " + output[1]);
    }

    private static int[] getFirstAndLast(int[] nums, int target) {
        int lower = getLower(nums, target);
        int upper = getUpper(nums, target);
        return new int[] { lower, upper };
    }

    private static int getUpper(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = (left + (right -left) / 2) + 1;

            if (nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                left = mid;
            }
        }

        return nums[right] == target ? right : -1;
    }

    private static int getLower(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid;
            }

        }

        return nums[left] == target ? left : -1;

    }
}
