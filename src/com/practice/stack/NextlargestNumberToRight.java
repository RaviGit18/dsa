package com.practice.stack;

import java.util.Arrays;
import java.util.Stack;

class NextlargestNumberToRight {

    /*Next Largest Number to the Right
Given an integer array nums, return an output array res where, for each value nums[i], res[i] is the first number to the right that's larger than nums[i]. If no larger number exists to the right of nums[i], set res[i] to ‐1.
Input: nums = [5, 2, 4, 6, 1]
Output: [6, 4, 6, -1, -1]
*/

    static void main(String[] args) {
        int[] nums = {5, 2, 4, 6, 1};
        int[] output = nextLargestNumberToRight(nums);
        System.out.println("Next largest numbers: " + Arrays.toString(output));
    }

    private static int[] nextLargestNumberToRight(int[] nums) {
        int[] output = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }

            output[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i]);

        }

        return  output;
    }

}
