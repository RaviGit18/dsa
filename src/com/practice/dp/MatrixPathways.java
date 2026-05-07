package com.practice.dp;

import java.util.Arrays;

class MatrixPathways {

    /*Matrix Pathways
You are positioned at the top-left corner of a m × n matrix, and can only move downward or rightward through the matrix. Determine the number of unique pathways you can take to reach the bottom-right corner of the matrix.

Example:
Image represents six distinct diagrams, each enclosed within a black square and overlaid on a 3x3 grid.  Each diagram depicts a path formed by a thick, orange line connecting two orange circles; one circle is located at the top-left corner of the grid in every diagram, representing a starting point, while the other circle, representing an endpoint, is positioned at various locations within the grid in each diagram. The orange line connecting these circles follows a path constrained by the grid lines, forming different right-angled routes across the grid in each of the six diagrams.  The paths vary in length and direction, demonstrating different possible routes from the top-left starting point to various endpoints within the 3x3 grid.  The endpoint circles have a slightly larger, lighter orange ring around them, visually distinguishing them from the starting point circles.
Input: m = 3, n = 3
Output: 6
Constraints:
m, n ≥ 1
*/

    static void main(String[] args) {

        int res = 0;
        //res = matrixPathways(3, 3);
        //res = matrixPathways1(3, 3);
        res = matrixPathways2(3, 3);
        System.out.println("Number of unique pathways: " + res);
    }

    private static int matrixPathways2(int m, int n) {
        int[] prevRow = new int[n];

        Arrays.fill(prevRow, 1);

        for (int r = 1; r < m; r++) {

            int[] currRow = new int[n];

            Arrays.fill(currRow, 1);

            for (int c = 1; c < n; c++) {
                currRow[c] = prevRow[c] + currRow[c - 1];
            }

            prevRow = currRow;
        }

        return  prevRow[n - 1];
    }

    private static int matrixPathways1(int m, int n) {

        int[][] dp = new int[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                if (r == 0 || c == 0) {
                    dp[r][c] = 1;
                } else {
                    dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    /*Recursive breakdown for matrixPathways(3,3):

paths(3,3) = paths(2,3) + paths(3,2)
           = [paths(1,3) + paths(2,2)] + [paths(2,2) + paths(3,1)]
           = [1 + paths(2,2)] + [paths(2,2) + 1]
           = 2 + 2×paths(2,2)
           = 2 + 2×[paths(1,2) + paths(2,1)]
           = 2 + 2×[1 + 1] = 6
Time & Space Complexity
Time: O(2^(m+n)) - exponential due to repeated subproblems
Space: O(m+n) - recursion stack depth
Key Insight
This is the recursive solution with overlapping subproblems. The other methods (matrixPathways1 and matrixPathways2) optimize this using dynamic programming with tabulation and space optimization
*/
    private static int matrixPathways(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        return matrixPathways(m - 1, n) + matrixPathways(m, n - 1);
    }

}
