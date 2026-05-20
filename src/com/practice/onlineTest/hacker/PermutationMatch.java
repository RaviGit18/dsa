package com.practice.onlineTest.hacker;

import java.util.*;

class PermutationMatch {

    /*Find whether any permutation (anagram) of a target string exists inside another string.

Example:

Input:

text = "oidbcaf"
pattern = "abc"

Permutations of "abc":

abc, acb, bac, bca, cab, cba

Substring "bca" exists in "oidbcaf".

Output:

true
Efficient Approach — Sliding Window

Instead of generating all permutations:

Count frequency of pattern characters
Maintain a window of same size
Compare frequencies

This gives: O(N) time complexity.

Time Complexity
O(N + M)

Where:

N = text length
M = pattern length
Space Complexity
O(K)

Where K = unique characters.

Optimized Version Using Array

For lowercase English letters:

int[] freq = new int[26];

This is faster than HashMap.
*/
    public static void main(String[] args) {

        String text = "oidbcaf";
        String pattern = "abc";

        boolean result =
                containsPermutation(text, pattern);

        System.out.println(result);
    }

    public static boolean containsPermutation(String text,
                                              String pattern) {

        if (pattern.length() > text.length()) {
            return false;
        }

        Map<Character, Integer> freqMap = new HashMap<>();

        // Build pattern frequency map
        for (char ch : pattern.toCharArray()) {
            freqMap.put(ch,
                    freqMap.getOrDefault(ch, 0) + 1);
        }

        int windowStart = 0;
        int matched = 0;

        // Sliding window
        for (int windowEnd = 0;
             windowEnd < text.length();
             windowEnd++) {

            char rightChar = text.charAt(windowEnd);

            if (freqMap.containsKey(rightChar)) {

                freqMap.put(rightChar,
                        freqMap.get(rightChar) - 1);

                if (freqMap.get(rightChar) == 0) {
                    matched++;
                }
            }

            // If all chars matched
            if (matched == freqMap.size()) {
                return true;
            }

            // Shrink window
            if (windowEnd >= pattern.length() - 1) {

                char leftChar = text.charAt(windowStart++);

                if (freqMap.containsKey(leftChar)) {

                    if (freqMap.get(leftChar) == 0) {
                        matched--;
                    }

                    freqMap.put(leftChar,
                            freqMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }


}
