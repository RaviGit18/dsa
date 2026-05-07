package com.practice.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class SlidingWindowMax {

    /*Maximums of Sliding Window
There's a sliding window of size k that slides through an integer array from left to right. Create a new array that records the largest number found in each window as it slides through.

Input: nums = [3, 2, 4, 1, 2, 1, 1], k = 4
Output: [4, 4, 4, 2]
*/

    static void main(String[] args) {
        int[] nums = {3, 2, 4, 1, 2, 1, 1};
        int k = 4;
        int[] output = maxSlidingWindow(nums, k);
        System.out.println("Maximums of Sliding Window: " + Arrays.toString(output));
    }

    private static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<int[]> deque = new LinkedList<>();

        int left = 0, right = 0, index = 0;

        while (right < nums.length) {

            while (!deque.isEmpty() && deque.peekLast()[0] < nums[right]) {
                deque.pollLast();
            }

            deque.offerLast(new int[] { nums[right], right });

            if (right - left + 1 == k) {

                while (!deque.isEmpty() && deque.peekFirst()[1] < left) {
                    deque.pollFirst();
                }

                result[index++] = deque.peekFirst()[0];

                left++;
            }

            right++;

        }

        return result;

    }
}
