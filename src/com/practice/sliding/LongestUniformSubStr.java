package com.practice.sliding;

import java.util.HashMap;
import java.util.Map;

class LongestUniformSubStr {

    /*Longest Uniform Substring After Replacements
    A uniform substring is one in which all characters are identical. Given a string, determine the length of the longest uniform substring that can be formed by replacing up to k characters.

    Example:
    Input: s = 'aabcdcca', k = 2
    Output: 5
    Explanation: if we can only replace 2 characters, the longest uniform substring we can achieve is "ccccc", obtained by replacing 'b' and 'd' with 'c'.
    */

    static void main(String[] args) {
        String str = "aabcdcca";
        int k = 2;
        System.out.println("Longest uniform substring: " + longestUniformSubstr(str, k));
    }

    private static int longestUniformSubstr(String str, int k) {
        int windowStart = 0;
        int windowEnd = 0;
        int maxLen = 0;
        int highestFreq = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (windowEnd < str.length()) {

            map.put(str.charAt(windowEnd), map.getOrDefault(str.charAt(windowEnd), 0) + 1);

            highestFreq = Math.max(highestFreq, map.get(str.charAt(windowEnd)));

            int windowLen = windowEnd - windowStart + 1;
            int numOfCharsToReplace = windowLen - highestFreq;

            if (numOfCharsToReplace > k) {
                map.put(str.charAt(windowStart), map.get(str.charAt(windowStart)) - 1);
                windowStart++;
            }

            maxLen = windowEnd - windowStart + 1;
            windowEnd++;

        }

        return maxLen;
    }
}
