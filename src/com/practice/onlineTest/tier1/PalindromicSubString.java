package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You have been given a string STR. Your task is to find the total number of palindromic substrings of STR.
 *
 * Example :
 * If the input string is "abbc", then all the possible palindromic substrings would be: ["a", "b", "b", c", "bb"] and hence, the output will be 5 since we have 5 substrings in total which form a palindrome.
 * Note :
 * A string is said to be a 'Palindrome' if it is read the same forwards and backwards.
 * For example, “abba” is a palindrome, but “abbc” is not.
 *
 * A 'Substring' is a contiguous sequence of characters within a string.
 * For example, "a", "b", "c", "ab", "bc", "abc" are substrings of "abc".
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= t <= 100
 * 0 <= N <= 1000
 *
 * Where 't' is the number of test cases, 'N' is the length of the given string.
 * Time Limit: 1 sec.
 * Sample Input 1 :
 * 1
 * abc
 * Sample Output 1 :
 * 3
 * Explanation For Sample Output 1:
 * All the substrings of the given string are "a", "b", "c", "ab", "bc", "abc".
 * The plaindromics substrings are "a", "b", "c". So the output will be 3.
 * Sample Input 2 :
 * 1
 * aaa
 * Sample Output 2 :
 * 6
 */
/**
 * Algorithm:
 * - Use expand around center approach to count all palindromic substrings
 * - For each character in the string, treat it as center of odd-length palindrome
 * - For each gap between characters, treat it as center of even-length palindrome
 * - Expand from each center while characters match
 * - Count each valid palindrome found
 *
 * Time Complexity: O(N^2)
 * - N = length of string
 * - For each center, we might expand up to N characters
 *
 * Space Complexity: O(1)
 * - Constant extra space
 */
public class PalindromicSubString {

    public static int countPalindromicSubstrings(String str) {
        int count = 0;
        int n = str.length();

        for (int i = 0; i < n; i++) {
            count += expandAroundCenter(str, i, i);
            count += expandAroundCenter(str, i, i + 1);
        }

        return count;
    }

    private static int expandAroundCenter(String str, int left, int right) {
        int count = 0;

        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            count++;
            left--;
            right++;
        }

        return count;
    }

    public static void main(String[] args) {
        String str1 = "abc";
        System.out.println("Test 1: " + countPalindromicSubstrings(str1));

        String str2 = "aaa";
        System.out.println("Test 2: " + countPalindromicSubstrings(str2));

        String str3 = "abbc";
        System.out.println("Test 3: " + countPalindromicSubstrings(str3));
    }
}
