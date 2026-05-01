package com.practice.slidingWindow;

/**
 * Algorithm: Sliding Window with Frequency Arrays
 * 
 * This solution finds the number of substrings in string s that are anagrams of string t
 * using a sliding window approach with character frequency counting.
 * 
 * Approach:
 * 1. Create frequency arrays for string t and the current sliding window
 * 2. Initialize the window with the first t.length() characters
 * 3. Slide the window one character at a time, updating frequencies
 * 4. Compare frequency arrays at each position to check for anagram
 * 
 * Time Complexity: O(n) where n is the length of string s
 * - Building initial frequency array: O(m) where m is length of t
 * - Sliding window iteration: O(n - m)
 * - Frequency comparison: O(26) for each window (constant)
 * Overall: O(n + m) ≈ O(n)
 * 
 * Space Complexity: O(1)
 * - Two frequency arrays of size 26 (constant for lowercase letters)
 * - No additional space that scales with input size
 */
public class SubstringAnagrams {

    private int findAnagramCount(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        if (lenT > lenS) {
            return 0;
        }
        int count = 0;
        int[] expectedFreqs = new int[26];
        int[] windowFreqs = new int[26];
        // Populate 'expectedFreqs' with the characters in string 't'.
        for (char c : t.toCharArray()) {
            expectedFreqs[c - 'a'] += 1;
        }
        int left = 0, right = 0;
        while (right < lenS) {
            // Add the character at the right pointer to 'windowFreqs' before sliding the
            // window.
            windowFreqs[s.charAt(right) - 'a'] += 1;
            // If the window has reached the expected fixed length, we advance the left
            // pointer as well as the right pointer to slide the window.
            if (right - left + 1 == lenT) {
                boolean isMatch = true;
                for (int i = 0; i < 26; i++) {
                    if (windowFreqs[i] != expectedFreqs[i]) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    count += 1;
                }
                // Remove the character at the left pointer from 'windowFreqs' before
                // advancing the left pointer.
                windowFreqs[s.charAt(left) - 'a'] -= 1;
                left += 1;
            }
            right += 1;
        }

        return count;
    }


    public static void main(String[] args) {
        SubstringAnagrams solution = new SubstringAnagrams();

        String s1 = "caabab";
        String t1 = "aba";
        System.out.println("Input: s = '" + s1 + "', t = '" + t1 + "'");
        System.out.println("Output: " + solution.findAnagramCount(s1, t1));
        System.out.println("Expected: 2");
        System.out.println();

        String s2 = "abab";
        String t2 = "ab";
        System.out.println("Input: s = '" + s2 + "', t = '" + t2 + "'");
        System.out.println("Output: " + solution.findAnagramCount(s2, t2));
        System.out.println("Expected: 3");
        System.out.println();

        String s3 = "aaaaa";
        String t3 = "aa";
        System.out.println("Input: s = '" + s3 + "', t = '" + t3 + "'");
        System.out.println("Output: " + solution.findAnagramCount(s3, t3));
        System.out.println("Expected: 4");
        System.out.println();

        String s4 = "abc";
        String t4 = "abcd";
        System.out.println("Input: s = '" + s4 + "', t = '" + t4 + "'");
        System.out.println("Output: " + solution.findAnagramCount(s4, t4));
        System.out.println("Expected: 0");
    }
}
