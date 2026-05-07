package com.practice.binarySearch;

class FindInRotatedSortedArray {

    /*Find the Target in a Rotated Sorted Array
A rotated sorted array is an array of numbers sorted in ascending order, in which a portion of the array is moved from the beginning to the end. For example, a possible rotation of [1, 2, 3, 4, 5] is [3, 4, 5, 1, 2] , where the first two numbers are moved to the end.

Given a rotated sorted array of unique numbers, return the index of a target value. If the target value is not present, return -1.

Example:
Input: nums = [8, 9, 1, 2, 3, 4, 5, 6, 7], target = 1
Output: 2
*/
    static void main(String[] args) {
        int[] nums = {8, 9, 1, 2, 3, 4, 5, 6, 7};
        int target = 1;
        System.out.println("Target index: " + findTarget(nums, target));
    }

    private static int findTarget(int[] nums, int target) {
        int left = 0, right = nums.length -1 ;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <=  nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return nums[left] == target ? left : -1;
    }
}
