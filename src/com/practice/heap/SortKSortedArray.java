package com.practice.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

class SortKSortedArray {

    /*Sort a K-Sorted Array
Given an integer array where each element is at most k positions away from its sorted position, sort the array in a non-decreasing order.

Example:
Input: nums = [5, 1, 9, 4, 7, 10], k = 2
Output: [1, 4, 5, 7, 9, 10]
*/
    static void main(String[] args) {
        int[] nums = new int[] { 5, 1, 9, 4, 7, 10 };
        int k = 2;

        sort(nums, k);
    }

    private static void sort(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i <= Math.min(nums.length - 1, k); i++) {
            minHeap.add(nums[i]);
        }

        int index = 0;

        for (int i = k + 1; i < nums.length; i++) {
            nums[index++] = minHeap.poll();
            minHeap.offer(nums[i]);
        }

        while (!minHeap.isEmpty()) {
            nums[index++] = minHeap.poll();
        }

        System.out.println(Arrays.toString(nums));
    }
}
