package com.practice.dp;

class LongestCommonSubSequence {

    /*Longest Common Subsequence
Given two strings, find the length of their longest common subsequence (LCS). A subsequence is a sequence of characters that can be derived from a string by deleting zero or more elements, without changing the order of the remaining elements.

Example:
Image represents three pairs of small, peach-colored, circular nodes, each containing a single letter ('a', 'b', 'c', or 'e'), connected by lines to illustrate different graph structures.  The first pair shows a simple linear connection: a node labeled 'a' connects to another node labeled 'a' below it; to the right, a node labeled 'b' connects to a node labeled 'a' and another labeled 'b'.  The second pair, separated by the word 'OR' in gray text, shows a different connection: a node labeled 'a' connects to nodes labeled 'b' and 'a' below it; to the right, a node labeled 'a' connects to nodes labeled 'a' and 'b' below it. The third pair, also separated by 'OR', mirrors the structure of the first pair, with a linear connection of 'a' to 'a' on the left and 'b' connecting to 'a' and 'b' on the right.  Each node is labeled with a single letter, and the connections between nodes are represented by simple lines.  The overall image seems to illustrate alternative graph structures or data flow patterns, possibly within a coding context.
Input: s1 = 'acabac', s2 = 'aebab'
Output: 3
*/

    static void main(String[] args) {
        String s1 = "acabac";
        String s2 = "aebab";
        System.out.println("Length of LCS: " + longestCommonSubsequence(s1, s2));
        System.out.println("Length of LCS: " + longestCommonSubsequence1(s1, s2));
    }

    private static int longestCommonSubsequence(String s1, String s2) {

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length()-1; i >= 0; i--) {
            for (int j = s2.length()-1; j >= 0; j--) {

                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else  {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[0][0];
    }

    private static int longestCommonSubsequence1(String s1, String s2) {

        int[] prevRow = new int[s2.length() + 1];

        for (int i = s1.length()-1; i >= 0; i--) {

            int[] currRow = new int[s2.length() + 1];

            for (int j = s2.length()-1; j >= 0; j--) {

                if (s1.charAt(i) == s2.charAt(j)) {
                    currRow[j] = 1 + prevRow[j+1];
                } else  {
                    currRow[j] = Math.max(prevRow[j], currRow[j+1]);
                }

            }

            prevRow = currRow;
        }

        return prevRow[0];
    }
}
