package com.practice.sortAndSearch;

class DutchNationalFlag {
    /*Dutch National Flag
Given an array of 0s, 1s, and 2s representing red, white, and blue, respectively, sort the array in place so that it resembles the Dutch national flag, with all reds (0s) coming first, followed by whites (1s), and finally blues (2s).

Example:
Input: nums = [0, 1, 2, 0, 1, 2, 0]
Output: [0, 0, 0, 1, 1, 2, 2]
*/

    static void main(String[] args) {
        int[] nums = {0, 1, 2, 0, 1, 2, 0};
        sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void sortColors(int[] nums) {
        int mid = 0;
        int left = 0;
        int right = nums.length - 1;

        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left, mid);
                left++;
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, right, mid);
                right--;
                mid++;
            } else {
                mid++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
