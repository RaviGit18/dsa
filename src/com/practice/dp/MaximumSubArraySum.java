package com.practice.dp;

class MaximumSubArraySum {
    
    static class SubArrayResult {
        int sum;
        int start;
        int end;
        
        SubArrayResult(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return "sum=" + sum + ", start=" + start + ", end=" + end + 
                   ", subarray=[" + (start + 1) + ".." + (end + 1) + "]";
        }
    }
    /*Maximum Subarray Sum
Given an array of integers, return the sum of the subarray with the largest sum.

Example:
Input: nums = [3, 1, -6, 2, -1, 4, -9]
Output: 5
Explanation: subarray [2, -1, 4] has the largest sum of 5.

Constraints:
The input array contains at least one element.
*/

    static void main(String[] args) {
        int[] nums = {3, 1, -6, 2, -1, 4, -9};
        System.out.println("Maximum subarray sum: " + maxSubArray(nums));
        System.out.println("Maximum subarray sum: " + maxSubArray1(nums));
        System.out.println("Maximum subarray sum recursive: " + maxSubArrayRecursive(nums));
        System.out.println("Maximum subarray with indices: " + maxSubArrayWithIndices(nums));
    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int currSum = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;

        for (int num : nums) {
            currSum = Math.max(currSum + num, num);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    private static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    private static SubArrayResult maxSubArrayWithIndices(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new SubArrayResult(0, -1, -1);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        //int maxEndIndex = 0;
        int currentStart = 0;
        int maxStart = 0;
        int maxEnd = 0;

        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] + nums[i] >= nums[i]) {
                dp[i] = dp[i-1] + nums[i];
            } else {
                dp[i] = nums[i];
                currentStart = i;
            }

            if (dp[i] > maxSum) {
                maxSum = dp[i];
                maxStart = currentStart;
                maxEnd = i;
            }
        }

        return new SubArrayResult(maxSum, maxStart, maxEnd);
    }

    private static int maxSubArrayRecursive(int[] nums) {
        return maxSubArrayRecursiveHelper(nums, 0, nums.length - 1);
    }

    private static int maxSubArrayRecursiveHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        int leftMax = maxSubArrayRecursiveHelper(nums, left, mid);
        int rightMax = maxSubArrayRecursiveHelper(nums, mid + 1, right);
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }
}
