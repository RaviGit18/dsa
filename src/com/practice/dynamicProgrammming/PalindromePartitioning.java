package com.practice.dynamicProgrammming;

import java.util.*;

/**
 * Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum number of cuts needed to partition the string.
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aab";
        System.out.println("Input string: " + s);
        int result = minCuts(s);
        System.out.println("Minimum cuts needed = " + result);
    }

    // Function to check if substring s[i..j] is palindrome
    /*
    * MinCuts(s):
        n = length(s)
        dp = array of size n
        palindrome = 2D array [n][n]

        // Precompute palindrome substrings
        for i = 0 to n-1:
            palindrome[i][i] = true
        for len = 2 to n:
            for i = 0 to n-len:
                j = i + len - 1
                if s[i] == s[j]:
                    if len == 2:
                        palindrome[i][j] = true
                    else:
                        palindrome[i][j] = palindrome[i+1][j-1]

        // Compute minimum cuts
        for i = 0 to n-1:
            if palindrome[0][i]:
                dp[i] = 0
            else:
                dp[i] = ∞
                for j = 0 to i-1:
                    if palindrome[j+1][i]:
                        dp[i] = min(dp[i], dp[j] + 1)

        return dp[n-1]

    * */
    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    // Function to find minimum cuts
    public static int minCuts(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] palindrome = new boolean[n][n];

        // Precompute palindrome substrings
        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2) palindrome[i][j] = true;
                    else palindrome[i][j] = palindrome[i + 1][j - 1];
                }
            }
        }

        // DP for minimum cuts
        for (int i = 0; i < n; i++) {
            if (palindrome[0][i]) {
                dp[i] = 0; // no cut needed
            } else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (palindrome[j + 1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }


}

