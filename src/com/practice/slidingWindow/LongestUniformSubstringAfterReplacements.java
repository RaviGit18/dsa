package com.practice.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Longest Uniform Substring After Replacements
 * 
 * Given a string s and an integer k, you can replace at most k characters in the string
 * with any other character. Find the length of the longest substring containing the same
 * character after at most k replacements.
 * 
 * Algorithm: Sliding Window with HashMap
 * 
 * 1. Use sliding window approach with two pointers (left and right)
 * 2. Maintain a hashmap to store frequency of characters in the current window
 * 3. Keep track of the most frequent character count in the current window
 * 4. If (window size - max frequency) > k, shrink the window from left
 * 5. Update the maximum window size whenever the condition is satisfied
 * 
 * Time Complexity: O(n) - Each character is visited at most twice (once by right pointer, once by left pointer)
 * Space Complexity: O(1) - Hashmap stores at most 26 characters for lowercase English letters
 */
public class LongestUniformSubstringAfterReplacements {
    
    /**
     * Optimized version that avoids recalculating maxFrequency in the while loop.
     * This works because maxFrequency can only stay the same or increase as we expand the window.
     * It never decreases, which is sufficient for this problem.
     */
    public static int longestUniformSubstringOptimized(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        
        Map<Character, Integer> charFrequency = new HashMap<>();
        int left = 0;
        int maxFrequency = 0;
        int maxLength = 0;
        
        int right = 0;
        while (right < s.length()) {
            char currentChar = s.charAt(right);
            charFrequency.put(currentChar, charFrequency.getOrDefault(currentChar, 0) + 1);
            maxFrequency = Math.max(maxFrequency, charFrequency.get(currentChar));
            
            // If we need more than k replacements, shrink the window
            if ((right - left + 1) - maxFrequency > k) {
                char leftChar = s.charAt(left);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        
        return maxLength;
    }
    
    /**
     * Test method to demonstrate the functionality
     */
    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
            "AABABBA",
            "ABAB",
            "AAAA",
            "ABCDEF",
            "AAABBB"
        };
        
        int[] testKValues = {1, 2, 0, 2, 1};
        
        System.out.println("Testing Longest Uniform Substring After Replacements:");
        System.out.println("=====================================================");
        
        int i = 0;
        while (i < testStrings.length) {
            String s = testStrings[i];
            int k = testKValues[i];

            int optimizedResult = longestUniformSubstringOptimized(s, k);
            
            System.out.println("String: \"" + s + "\", k = " + k);
            System.out.println("Optimized Result: " + optimizedResult);
            System.out.println();
            i++;
        }
    }
}
