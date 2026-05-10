package com.practice.sortAndSearch;

import java.util.PriorityQueue;
import java.util.Random;

class KthlargestInteger {

    /*Kth Largest Integer
Return the kth largest integer in an array.

Example:
Input: nums = [5, 2, 4, 3, 1, 6], k = 3
Output: 4
Constraints:
The array contains no duplicates.
The array contains at least one element.
1 ≤ k ≤ n, where n denotes the length of the array.
*/

    static void main(String[] args) {
        int[] nums = {5, 2, 4, 3, 1, 6};
        int k = 3;
        System.out.println("Kth largest integer: " + kthLargestInteger(nums, k));
        System.out.println("Kth largest integer: " + kthLargestInteger1(nums, k));
    }

    private static int kthLargestInteger1(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private static int quickSelect(int[] nums, int left, int right, int k) {
        int n = nums.length;

        if (left >= right) {
            return nums[left];
        }

        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;

        swap(nums, randomIndex, right);
        
        int pivotIndex = partition(nums, left, right);

        if (pivotIndex < n - k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else if (pivotIndex > n - k) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return nums[pivotIndex];
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];

        int lo = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, lo);
                lo++;
            }
        }

        swap(nums, right, lo);

        return lo;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static int kthLargestInteger(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        if (nums == null || nums.length < k) return -1;

        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }

        return minHeap.peek();
    }
}
