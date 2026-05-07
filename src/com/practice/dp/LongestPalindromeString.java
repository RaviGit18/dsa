package com.practice.dp;

class LongestPalindromeString {

    /*Longest Palindrome in a String
Return the longest palindromic substring within a given string.

Example:
Input: s = 'abccbaba'
Output: 'abccba'
*/

    static void main(String[] args) {
        String s = "abccbaba";
        System.out.println("Longest palindromic substring: " + longestPalindromicSubstring(s));
        System.out.println("Longest palindromic substring: " + longestPalindromicSubstring1(s));
    }

    private static String longestPalindromicSubstring1(String s) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        int startIndex = 0;
        int maxLength = 1;

        for (int center = 0; center < s.length(); center++) {

            int[] odd = expandPalindrome(s, center, center);
            int oddStart = odd[0];
            int oddLength = odd[1];

            if (oddLength > maxLength) {
                startIndex = oddStart;
                maxLength = oddLength;
            }

            if (center < s.length() -1 && s.charAt(center) == s.charAt(center+1)) {

                int[] even = expandPalindrome(s, center, center + 1);
                int evenStart = even[0];
                int evenLength = even[1];

                if (evenLength > maxLength) {
                    startIndex = evenStart;
                    maxLength = evenLength;
                }
            }

        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    private static int[] expandPalindrome(String s, int left, int right) {

        while (left > 0 && right < s.length() -1 && s.charAt(left - 1) == s.charAt(right + 1)) {
            left--;
            right++;
        }

        return  new int[]{left , right - left + 1};
    }

    private static String longestPalindromicSubstring(String s) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLength = 1;
        int startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                startIndex = i;
                maxLength = 2;
            }
        }

        for (int subStrLen = 3; subStrLen <= s.length(); subStrLen++) {

            for (int i = 0; i <= s.length() - subStrLen; i++) {

                int j = i + subStrLen - 1;

                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    startIndex = i;
                    maxLength = subStrLen;
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLength);
    }
}
