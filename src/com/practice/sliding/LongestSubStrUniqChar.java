package com.practice.sliding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class LongestSubStrUniqChar {

    /*Longest Substring With Unique Characters
Given a string, determine the length of its longest substring that consists only of unique characters.

Example:
Input: s = 'abcba'
Output: 3
Explanation: Substring "abc" is the longest substring of length 3 that contains unique characters ("cba" also fits this description).
*/

    static void main(String[] args) {
        String str = "abcba";
        System.out.println("Longest substring with unique characters: " + longestSubstrWithUniqChar(str));
        System.out.println("Longest substring with unique characters: " + longestSubstrWithUniqCharOptimized(str));
        System.out.println("Longest substring with unique characters: " + longestSubstrWithUniqCharOptimized("cabcdea"));
    }

    private static int longestSubstrWithUniqChar(String str) {

        int maxLen = 0;
        int windowStart = 0;
        int windowEnd = 0;

        HashSet<Character> set = new HashSet<>();

        while (windowEnd < str.length()) {

            while (set.contains(str.charAt(windowEnd))) {
                set.remove(str.charAt(windowStart));
                windowStart++;
            }

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);

            set.add(str.charAt(windowEnd));
            windowEnd++;
        }

        return maxLen;
    }

    private static int longestSubstrWithUniqCharOptimized(String str) {

        int maxLen = 0;
        int windowStart = 0;
        int windowEnd = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (windowEnd < str.length()) {

            if (map.containsKey(str.charAt(windowEnd)) && map.get(str.charAt(windowEnd)) >= windowStart) {

                windowStart = map.get(str.charAt(windowEnd)) + 1;
            }

            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);

            map.put(str.charAt(windowEnd), windowEnd);
            windowEnd++;
        }

        return maxLen;
    }
}
