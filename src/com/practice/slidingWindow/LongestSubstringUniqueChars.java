package com.practice.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Algorithm: Sliding Window with Hash Set/Array
 * 
 * This solution finds the length of the longest substring without repeating characters
 * using a sliding window approach with character tracking.
 * 
 * Approach:
 * 1. Use a sliding window with two pointers (left and right)
 * 2. Track characters currently in the window using a hash set or frequency array
 * 3. Expand window by moving right pointer
 * 4. When duplicate is found, shrink window by moving left pointer
 * 5. Keep track of maximum window size encountered
 * 
 * Time Complexity: O(n) where n is the length of the string
 * - Each character is visited at most twice (once by right pointer, once by left pointer)
 * - Hash set/array operations are O(1) on average
 * 
 * Space Complexity: O(min(n, m)) where m is the size of character set
 * - In worst case, stores all unique characters in current window
 * - For ASCII/Unicode characters, this is bounded by character set size
 */
public class LongestSubstringUniqueChars {

    /**
     * Method 1: Sliding Window with Frequency Array
     * 
     * Algorithm:
     * 1. Use a frequency array to track character counts in current window
     * 2. Expand window by moving right pointer and incrementing character count
     * 3. When duplicate found (count > 1), shrink window by moving left pointer
     * 4. Track maximum window size encountered
     * 
     * Time Complexity: O(n) where n is string length
     * - Each character visited at most twice (right pointer, then left pointer)
     * - Array operations are O(1)
     * 
     * Space Complexity: O(1) - Fixed size array (128) for ASCII characters
     * 
     * @param s the input string
     * @return length of longest substring with unique characters
     */
    public int findLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int maxLength = 0;
        int left = 0;
        
        // Using int array for character frequency (assuming ASCII)
        int[] charCount = new int[128];
        
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            charCount[currentChar]++;
            
            // If character count > 1, we have a duplicate, shrink window
            while (charCount[currentChar] > 1) {
                char leftChar = s.charAt(left);
                charCount[leftChar]--;
                left++;
            }
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    /**
     * Method 2: Sliding Window with HashSet
     * 
     * Algorithm:
     * 1. Use HashSet to store unique characters in current window
     * 2. Expand window by moving right pointer
     * 3. If duplicate encountered, shrink window from left until duplicate removed
     * 4. Track maximum window size
     * 
     * Time Complexity: O(n) where n is string length
     * - Each character added and removed at most once
     * - HashSet operations (add, remove, contains) are O(1) on average
     * 
     * Space Complexity: O(min(n, m)) where m is character set size
     * - Stores unique characters in current window
     * - Worst case: all characters are unique
     * 
     * @param s the input string
     * @return length of longest substring with unique characters
     */
    private int findLongestSubstringUsingHashSet(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLen = 0;
        HashSet<Character> hashSet = new HashSet<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            // If we encounter a duplicate character in the window, shrink the window until
            // it’s no longer a duplicate.
            while (hashSet.contains(s.charAt(right))) {
                hashSet.remove(s.charAt(left));
                left += 1;
            }
            // Once there are no more duplicates in the window, update 'maxLen' if the
            // current window is larger.
            maxLen = Math.max(maxLen, right - left + 1);
            hashSet.add(s.charAt(right));
            // Expand the window.
            right += 1;
        }
        return maxLen;
    }

    /**
     * Method 3: Optimized Sliding Window with Position Array
     * 
     * Algorithm:
     * 1. Use array to store last seen position of each character
     * 2. When duplicate found, jump left pointer directly to position after previous occurrence
     * 3. No need to shrink window gradually - direct jump optimization
     * 4. Track maximum window size
     * 
     * Time Complexity: O(n) where n is string length
     * - Single pass through string
     * - Array operations are O(1)
     * - More efficient than method 1 (no while loop for shrinking)
     * 
     * Space Complexity: O(1) - Fixed size array (128) for ASCII characters
     * 
     * @param s the input string
     * @return length of longest substring with unique characters
     */
    public int findLongestSubstringOptimized(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int maxLength = 0;
        int left = 0;
        
        // Using array to store last seen positions
        int[] lastSeen = new int[128];
        
        // Initialize array with -1 (character not seen)
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }
        
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            
            // If character was seen before and is within current window
            if (lastSeen[currentChar] >= left) {
                left = lastSeen[currentChar] + 1;
            }
            
            // Update last seen position
            lastSeen[currentChar] = right;
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }


    /**
     * Method 4: Optimized Sliding Window with HashMap
     * 
     * Algorithm:
     * 1. Use HashMap to store last seen index of each character
     * 2. When duplicate found in current window, jump left pointer directly
     * 3. Similar to Method 3 but uses HashMap instead of array
     * 4. Track maximum window size
     * 
     * Time Complexity: O(n) where n is string length
     * - Single pass through string
     * - HashMap operations (get, put, containsKey) are O(1) on average
     * - More efficient than Method 2 (no while loop for shrinking)
     * 
     * Space Complexity: O(min(n, m)) where m is character set size
     * - Stores last seen positions of unique characters
     * - Worst case: all characters are unique
     * 
     * @param s the input string
     * @return length of longest substring with unique characters
     */
    private int findLongestSubstringOptimizedUsingHashMap(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int maxLen = 0;
        HashMap<Character, Integer> prevIndexes = new HashMap<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char currChar = s.charAt(right);
            // If a previous index of the current character is present in the current
            // window, it's a duplicate character in the window.
            if (prevIndexes.containsKey(currChar) && prevIndexes.get(currChar) >= left) {
                // Shrink the window to exclude the previous occurrence of this character.
                left = prevIndexes.get(currChar) + 1;
            }
            // Update 'maxLen' if the current window is larger.
            maxLen = Math.max(maxLen, right - left + 1);
            prevIndexes.put(currChar, right);
            // Expand the window.
            right++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringUniqueChars solution = new LongestSubstringUniqueChars();

        // Test cases
        String[] testStrings = {
            "abcabcbb",    // Expected: 3 ("abc")
            "bbbbb",       // Expected: 1 ("b")
            "pwwkew",      // Expected: 3 ("wke")
            "",            // Expected: 0
            " ",           // Expected: 1 (" ")
            "au",          // Expected: 2 ("au")
            "dvdf",        // Expected: 3 ("vdf")
            "abccba",      // Expected: 3 ("abc" or "cba")
            "abcdefghijklmnopqrstuvwxyz", // Expected: 26
            "aab",         // Expected: 2 ("ab")
            null           // Expected: 0
        };

        int[] expected = {3, 1, 3, 0, 1, 2, 3, 3, 26, 2, 0};

        System.out.println("Testing Longest Substring with Unique Characters:");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < testStrings.length; i++) {
            String input = testStrings[i];
            int expectedOutput = expected[i];
            
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: \"" + input + "\"");
            
            int result1 = solution.findLongestSubstring(input);
            int result2 = solution.findLongestSubstringOptimized(input);
            int result3 = solution.findLongestSubstringUsingHashSet(input);
            int result4 = solution.findLongestSubstringOptimizedUsingHashMap(input);
            
            System.out.println("Output (Array Method): " + result1);
            System.out.println("Output (Optimized Method): " + result2);
            System.out.println("Output (HashSet Method): " + result3);
            System.out.println("Output (Optimized HashMap Method): " + result4);
            System.out.println("Expected: " + expectedOutput);
            
            boolean passed = (result1 == expectedOutput) && (result2 == expectedOutput) && (result3 == expectedOutput) && (result4 == expectedOutput);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            System.out.println();
        }
    }
}
