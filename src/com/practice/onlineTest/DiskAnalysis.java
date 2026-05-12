package com.practice.onlineTest;

import java.util.*;

/**
 * java program for Disk Space Analysis:
 * Given an array of integers representing disk segments and a contiguous subarray of length k,
 * find the minimum of each segment and return the maximum of those minimums.
 */
public class DiskAnalysis {

    public static void main(String[] args) {
        List<Integer> diskSpace = Arrays.asList(8, 2, 4, 6);
        int k = 2;
        System.out.println("Maximum of Minimums: " + segment(k, diskSpace));
        // Output: 4 (Windows: [8,2]->min 2; [2,4]->min 2; [4,6]->min 4. Max is 4.)
    }

    /*
    * To solve the Disk Space Analysis problem efficiently (especially for large datasets on HackerRank),
    * the most optimal approach uses a Deque (Double-Ended Queue).
    * This allows you to find the minimum of each sliding window in \(O(n)\) time, rather than \(O(n \times k)\).
    * Java Solution:
    * Sliding Window MinimumThis implementation uses java.util.ArrayDeque to track the indices of the minimum elements for each segment.
    *
    * Key Technical Details:
    * Time Complexity: \(O(n)\), where \(n\) is the number of segments.
    * Each element is added and removed from the Deque exactly once.
    * Space Complexity: \(O(k)\) to store the indices in the ArrayDeque.
    * Edge Cases:
    * Always check if \(k=1\) (the answer is simply the maximum element in the array) or if \(k\) equals the array size (the answer is the minimum element of the entire array).
    * */
    public static int segment(int k, List<Integer> space) {
        Deque<Integer> deque = new ArrayDeque<>();
        int maxOfMins = Integer.MIN_VALUE;

        for (int i = 0; i < space.size(); i++) {
            // 1. Remove indices that are out of the current window (k)
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. Remove indices of elements larger than the current element
            // (They can't be the minimum if the current element is smaller)
            while (!deque.isEmpty() && space.get(deque.peekLast()) >= space.get(i)) {
                deque.pollLast();
            }

            // 3. Add current element's index
            deque.offerLast(i);

            // 4. Once the first window is complete, track the max of the minimums
            if (i >= k - 1) {
                maxOfMins = Math.max(maxOfMins, space.get(deque.peekFirst()));
            }
        }

        return maxOfMins;
    }


}