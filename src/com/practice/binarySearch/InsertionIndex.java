package com.practice.binarySearch;

class InsertionIndex {

    /*Find the Insertion Index
    You are given a sorted array that contains unique values, along with an integer target.

    If the array contains the target value, return its index.
    Otherwise, return the insertion index. This is the index where the target would be if it were inserted in order, maintaining the sorted sequence of the array.
    Example 1:
    Input: nums = [1, 2, 4, 5, 7, 8, 9], target = 4
    Output: 2
    Example 2:
    Input: nums = [1, 2, 4, 5, 7, 8, 9], target = 6
    Output: 4
    Explanation: 6 would be inserted at index 4 to be positioned between 5 and 7: [1, 2, 4, 5, 6, 7, 8, 9].
    */

    static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 7, 8, 9};
        int target = 4;
        System.out.println("Insertion index: " + getInsertionIndex(nums, target));

        target = 6;
        System.out.println("Insertion index: " + getInsertionIndex(nums, target));
    }

    private static int getInsertionIndex(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return  left;
    }
}
