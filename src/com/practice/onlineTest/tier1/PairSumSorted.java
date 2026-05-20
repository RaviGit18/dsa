package com.practice.onlineTest.tier1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PairSumSorted {

    static void main(String[] args) {
        int[] nums = {-5, -2, 3, 4, 6};
        //int target = 1;
        int target = 7;

        //int[] nums = {1, 1, 1};
        //int target = 2;

        System.out.println(pairSumBruteForce(nums, target)[0] + ", " + pairSumBruteForce(nums, target)[1]);
        System.out.println(pairSum(nums, target)[0] + ", " + pairSum(nums, target)[1]);

        List<int[]> result = pairSumListBruteForce(nums, target);
        for (int[] arr : result) {
            System.out.print(Arrays.toString(arr));
        }
    }

    private static int[] pairSum(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while(left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    private static int[] pairSumBruteForce(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static List<int[]> pairSumListBruteForce(int[] nums, int target) {

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result.add(new int[]{nums[i], nums[j]});
                }
            }
        }
        return result;
    }
}
