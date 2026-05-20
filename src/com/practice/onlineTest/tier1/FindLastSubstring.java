package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a string ‘Str’ of length ‘N’. Find the last substring of ‘Str’ in lexicographical order.
 *
 * In Lexicographical order string ‘S1’ comes before string ‘S2’ if S1 is the prefix of S2 and (|S1|<|S2|),
 * or if none of them is a prefix of the other and at the first position where they differ,
 * the character in ‘S1’ is smaller than the character in ‘S2’.
 *
 * Example :
 * Consider string ‘Str’ = “abba”,
 * then its substring in lexicographical order is [“a”, “ab”, “abb”, “abba”, “b”, “bb”, “bba”].
 * Clearly, the last substring in lexicographical order is  “bba”.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 50
 * 1 <= N <= 10^4
 * ‘Str’ contains only lowercase English letters.
 *
 * Time limit: 1 sec
 */
public class FindLastSubstring {

    static void main(String[] args) {
        String str = "abba";
        System.out.println(findLastSubstring(str));
        System.out.println(findLastSubstring("codingninjas"));
        System.out.println(findLastSubstring("zdca"));
    }
    public static String findLastSubstring(String str)
    {
        // Write your code here.
        int n = str.length();
        int i = 0, j = 1, k = 0;

        while(j + k < n) {

            if (str.charAt(i + k) == str.charAt(j + k)) {
                k++;
            } else if(str.charAt(i + k) < str.charAt(j + k)) {
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            } else {
                j = j + k + 1;
                k = 0;
            }
        }

        return str.substring(i);
    }
}
