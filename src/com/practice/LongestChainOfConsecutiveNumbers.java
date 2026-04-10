package com.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*Find the longest chain of consecutive numbers in an array. Two numbers are consecutive if they have a difference of 1.

Example:
Input: nums = [1, 6, 2, 5, 8, 7, 10, 3]
Output: 4
Explanation: The longest chain of consecutive numbers is 5, 6, 7, 8.
*/
class LongestChainOfConsecutiveNumbers {

    static void main() {
        LongestChainOfConsecutiveNumbers longestChainOfConsecutiveNumbers = new LongestChainOfConsecutiveNumbers();
        List<Integer> nums = Arrays.asList(1, 6, 2, 5, 8, 7, 10, 3);
        System.out.println(longestChainOfConsecutiveNumbers.getLongestChainSizeBruteForce(nums));
        System.out.println(longestChainOfConsecutiveNumbers.getLongestChainSizeOptimized(nums));
    }

    /*Time Complexity : O(n^3)
    Space Complexity : O(1)*/
    private int getLongestChainSizeBruteForce(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return 0;
        }
        int longestChain = 0;
        // Look for chains of consecutive numbers that start from each number.
        for (int num : nums) {
            int currentNum = num;
            int currentChain = 1;
            // Continue to find the next consecutive numbers in the chain.
            while (nums.contains(currentNum + 1)) {
                currentNum += 1;
                currentChain += 1;
            }
            longestChain = Math.max(longestChain, currentChain);
        }
        return longestChain;
    }

    /*Time Complexity : O(n)
    Space Complexity : O(n)*/
    private int getLongestChainSizeOptimized(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return 0;
        }

        HashSet<Integer> numSet = new HashSet<>(nums);
        int longestChain = 0;

        for (int num : numSet) {

            // If the current number is the smallest number in its chain, search for
            // the length of its chain.
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentChain = 1;
                // Continue to find the next consecutive numbers in the chain.
                while (nums.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentChain += 1;
                }
                longestChain = Math.max(longestChain, currentChain);
            }

        }
        return longestChain;
    }
}
