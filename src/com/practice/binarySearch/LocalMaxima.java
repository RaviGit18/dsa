package com.practice.binarySearch;

class LocalMaxima {

    /*Local Maxima in Array
A local maxima is a value greater than both its immediate neighbors. Return any local maxima in an array. You may assume that an element is always considered to be strictly greater than a neighbor that is outside the array.

Input: nums = [1, 4, 3, 2, 3]
Output: 1 # index 4 is also acceptable
Constraints:
No two adjacent elements in the array are equal.
*/
    static void main(String[] args) {
        int[] nums = {1, 4, 3, 2, 3};
        System.out.println("Local maxima: " + findLocalMaxima(nums));
    }

    private static int findLocalMaxima(int[] nums) {

        int left = 0, right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
