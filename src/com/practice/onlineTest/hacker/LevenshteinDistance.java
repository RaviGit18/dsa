package com.practice.onlineTest.hacker;

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

        int dist = minDistanceTopDown(s1, s2);

        System.out.println(
                "Levenshtein Distance = "
                        + dist);
        
        int optimizedDist = minDistanceOptimized(s1, s2);
        
        System.out.println(
                "Optimized Distance = "
                        + optimizedDist);
    }

    public static int minDistance(String word1,
                                  String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        /*What These Base Cases Mean:
1. dp[i][0] = i - Converting to Empty String:

Scenario: Transform first i characters of word1 into an empty string
Operations: Need i deletions
Example: dp[3][0] = 3 means "cat" → "" requires 3 deletions
2. dp[0][j] = j - Converting from Empty String:

Scenario: Transform empty string into first j characters of word2
Operations: Need j insertions
Example: dp[0][4] = 4 means "" → "word" requires 4 insertions
Why Base Cases Are Essential:
Foundation for DP Recurrence:

The main recurrence dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+cost) depends on previous states
Base cases provide the starting point for building the DP table
Without base cases, the algorithm would have no foundation to build upon
Boundary Conditions:

Handle edge cases where one string is empty
Prevent ArrayIndexOutOfBoundsException when accessing dp[i-1][j] or dp[i][j-1]
Example with "kitten" → "sitting":
dp[0][0] = 0  // "" → "" (0 operations)
dp[1][0] = 1  // "k" → "" (1 deletion)
dp[2][0] = 2  // "ki" → "" (2 deletions)
...
dp[0][1] = 1  // "" → "s" (1 insertion)
dp[0][2] = 2  // "" → "si" (2 insertions)
...
The base cases initialize the DP table's first row and column, providing the foundation for calculating all other cells through the recurrence relation!
*/
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Convert first i chars of word1 to empty string
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Convert empty string to first j chars of word2
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

    /**
     * Space Optimized Bottom-Up Approach: minDistanceOptimized
     * 
     * Space Optimization: O(min(m,n)) instead of O(m×n)
     * 
     * Key Insight: Only need previous row and current row for DP calculation
     * 
     * Algorithm:
     * 1. Use two 1D arrays instead of 2D DP table
     * 2. Calculate current row based on previous row
     * 3. Swap references for next iteration
     * 
     * Time Complexity: O(m×n) where m,n = string lengths
     * Space Complexity: O(min(m,n)) - optimal space usage
     * 
     * @param word1 First string
     * @param word2 Second string  
     * @return Minimum edit distance
     */
    public static int minDistanceOptimized(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // Use two 1D arrays for space optimization
        int[] prev = new int[n + 1];  // Previous row
        int[] curr = new int[n + 1];  // Current row
        
        // Initialize first row (convert empty string to prefixes of word2)
        for (int j = 0; j <= n; j++) {
            prev[j] = j;  // Insert j characters into empty string
        }
        
        // Fill DP table row by row
        for (int i = 1; i <= m; i++) {
            curr[0] = i;  // Delete i characters to get empty string
            
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match - no cost
                    curr[j] = prev[j - 1];
                } else {
                    // Characters don't match - take minimum of three operations
                    int deleteCost = prev[j] + 1;      // Delete from word1
                    int insertCost = curr[j - 1] + 1;  // Insert into word1
                    int replaceCost = prev[j - 1] + 1; // Replace character
                    
                    curr[j] = Math.min(deleteCost, 
                                        Math.min(insertCost, replaceCost));
                }
            }
            
            // Swap arrays for next iteration
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }



    private static int minDistanceTopDown(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(word1, word2, word1.length(), word2.length(), memo);
    }

    private static int solve(String word1, String word2, int i, int j, int[][] memo) {
        // Base cases
        if (i == 0) return j;  // Insert j characters
        if (j == 0) return i;  // Delete i characters

        // Check memoization
        if (memo[i][j] != -1) return memo[i][j];

        // Recurrence
        if (word1.charAt(i-1) == word2.charAt(j-1)) {
            memo[i][j] = solve(word1, word2, i-1, j-1, memo);
        } else {
            memo[i][j] = 1 + Math.min(
                    solve(word1, word2, i-1, j, memo),    // Delete
                    Math.min(solve(word1, word2, i, j-1, memo),    // Insert
                            solve(word1, word2, i-1, j-1, memo)) // Replace
            );
        }

        return memo[i][j];
    }

}