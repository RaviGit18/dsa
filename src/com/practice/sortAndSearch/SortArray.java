package com.practice.sortAndSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SortArray {

    /*Sort Array
Given an integer array, sort the array in ascending order.

Example:
Input: nums = [6, 8, 4, 2, 7, 3, 1, 5]
Output: [1, 2, 3, 4, 5, 6, 7, 8]
*/
    static void main(String[] args) {
        int[] nums = {6, 8, 4, 2, 7, 3, 1, 5};
        sortArray(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        List<Integer> countingSortList = countSort(nums);
        System.out.println("Count Sort: " + countingSortList);
    }

    private static void sortArray(int[] nums) {
        quickSortOptimized(nums, 0, nums.length - 1);
        //quickSort(nums, 0, nums.length - 1);
    }

    private static List<Integer> countSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        for (int k : nums) {
            if (k > max) {
                max = k;
            }
        }

        int[] count = new int[max + 1];
        for (int num : nums) {
            count[num]++;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                res.add(i);
            }
        }

        return res;
    }

    private static void quickSortOptimized(int[] nums, int left, int right) {
        if (left >= right) {return;}

        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;

        swap(nums, randomIndex, right);

        int pivotIndex = partition(nums, left, right);

        quickSortOptimized(nums, left, pivotIndex - 1);
        quickSortOptimized(nums, pivotIndex + 1, right);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {return;}

        int pivot = partition(nums, left, right);

        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {

        int pivot = nums[right];
        int lo = left;

        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, lo, j);
                lo++;
            }
        }

        swap(nums, lo, right);

        return lo;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
