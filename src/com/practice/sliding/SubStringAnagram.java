package com.practice.sliding;

class SubStringAnagram {

    /*Substring Anagrams
Given two strings, s and t , both consisting of lowercase English letters, return the number of substrings in s that are anagrams of t.

An anagram is a word or phrase formed by rearranging the letters of another word or phrase, using all the original letters exactly once.

Example:
Input: s = 'caabab', t = 'aba'
Output: 2
Explanation: There is an anagram of t starting at index 1 ("caabab") and another starting at index 2 ("caabab")*/

    static void main(String[] args) {
        String str = "caabab";
        String target = "aba";

        System.out.println("Number of substrings: " + countAnagrams(str, target));
    }

    private static int countAnagrams(String str, String target) {

        if (str == null || target == null || str.length() < target.length()) {
            return 0;
        }

        int count = 0;
        int expectedFreq[] = new int[26];
        int windowFreq[] = new int[26];

        for (int i = 0; i < target.length(); i++) {
            expectedFreq[target.charAt(i) - 'a']++;
        }


        int windowStart = 0;
        int windowEnd = 0;

        while (windowEnd < str.length()) {

            windowFreq[str.charAt(windowEnd) - 'a']++;

            if (windowEnd - windowStart + 1 == target.length()) {

                boolean isMatch = true;

                for (int i = 0; i < 26; i++ ) {
                    if (expectedFreq[i] != windowFreq[i]) {
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) {
                    count++;
                }

                windowFreq[str.charAt(windowStart) - 'a']--;
                windowStart++;

            }

            windowEnd++;
        }

        return count;
    }
}
