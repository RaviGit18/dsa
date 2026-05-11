package com.practice.onlineTest;

import java.util.*;

class LevenshteinDistance {

    /*Levenshtein Distance Algorithm

The Levenshtein Distance measures the minimum number of single-character operations required to transform one string into another.

Allowed operations:

Insert
Delete
Replace
Example

Transform:

kitten → sitting

Operations:

kitten → sitten   (replace k → s)
sitten → sittin   (replace e → i)
sittin → sitting  (insert g)

Distance:

3
Dynamic Programming Formula

We build a DP table where:

dp[i][j]

means:

Minimum operations needed to convert first i chars of word1 into first j chars of word2.

Core recurrence:

dp[i][j]=min(dp[i−1][j]+1, dp[i][j−1]+1, dp[i−1][j−1]+cost)

Where:

+1 = insert/delete
cost = 0 if characters match
cost = 1 otherwise

Time Complexity

If:

m = length(word1)
n = length(word2)

Then:

Time:  O(m × n)
Space: O(m × n)
Recursive Formula

Recursive definition:

Lev(i,j)=min(Lev(i−1,j)+1, Lev(i,j−1)+1, Lev(i−1,j−1)+cost)
*/
    public static void main(String[] args) {

        String s1 = "kitten";
        String s2 = "sitting";

        int distance = minDistance(s1, s2);

        System.out.println(
                "Levenshtein Distance = "
                        + distance);
    }

    public static int minDistance(String word1,
                                  String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1)
                        == word2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j], // delete
                            Math.min(
                                    dp[i][j - 1], // insert
                                    dp[i - 1][j - 1] // replace
                            )
                    );
                }
            }
        }

        return dp[m][n];
    }


}