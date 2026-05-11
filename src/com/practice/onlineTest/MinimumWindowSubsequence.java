package com.practice.onlineTest;

class MinimumWindowSubsequence {

    /*Problem

Given two strings:

S -> main string
T -> target subsequence

Find the minimum contiguous substring W of S such that:

T is a subsequence of W
Important Difference

This is NOT the classic:

minimum window substring

Here:

characters of T
must appear:
in order
but not necessarily contiguous

inside the window.

Example

Input:

S = "abcdebdde"
T = "bde"

Possible windows:

"bcde"
"bdde"

Minimum window:

"bcde"

Because:

length = 4
smaller starting index
Efficient Approach

We use:

Forward scan → find subsequence
Backward scan → minimize window
Algorithm
Step 1 — Move forward in S

Try matching all chars of T.

Step 2 — Once matched

Move backward to shrink the window.

Step 3

Keep track of minimum window.

Dry Run

Input:

S = abcdebdde
T = bde

Forward match:

b -> d -> e

Window:

abcde

Backward shrink:

bcde
Time Complexity

Worst case:

O(N × M)

Where:

N = length of S
M = length of T
Space Complexity
O(1)

Key Interview Insight

Difference between:

Problem	Requirement
Minimum Window Substring	all chars anywhere
Minimum Window Subsequence	chars must appear in order

This problem is harder because order matters.
*/
    public static void main(String[] args) {

        String s = "abcdebdde";
        String t = "bde";

        String result = minWindow(s, t);

        System.out.println(
                "Minimum Window = " + result);
    }

    public static String minWindow(String s,
                                   String t) {

        int sLen = s.length();
        int tLen = t.length();

        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;

        int i = 0;

        while (i < sLen) {

            int j = 0;

            // Forward scan
            while (i < sLen) {

                if (s.charAt(i) == t.charAt(j)) {
                    j++;
                }

                if (j == tLen) {
                    break;
                }

                i++;
            }

            // No subsequence found
            if (i == sLen) {
                break;
            }

            // End index of valid window
            int end = i;

            // Backward scan to minimize window
            j = tLen - 1;

            while (j >= 0) {

                if (s.charAt(i) == t.charAt(j)) {
                    j--;
                }

                i--;
            }

            // Correct start position
            i++;

            // Update minimum window
            int windowLen = end - i + 1;

            if (windowLen < minLen) {

                minLen = windowLen;
                startIndex = i;
            }

            // Continue searching
            i = i + 1;
        }

        return startIndex == -1
                ? ""
                : s.substring(startIndex,
                startIndex + minLen);
    }


}