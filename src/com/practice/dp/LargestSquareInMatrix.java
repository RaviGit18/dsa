package com.practice.dp;

class LargestSquareInMatrix {
    /*Largest Square in a Matrix
Determine the area of the largest square of 1's in a binary matrix.

Example:
Image represents a 5x5 matrix, visually depicted as a grid of cells, each containing a binary digit (either '0' or '1').  The matrix is enclosed within a bold black border.  The cells are arranged in five rows and five columns. A rectangular region encompassing the bottom three rows and the first three columns is highlighted with a light peach or orange fill.  No arrows or other explicit connections between cells are shown; the information flow is implicitly represented by the spatial arrangement of the binary digits within the matrix.  The numerical values within each cell are simply data points, with no further labels, URLs, or parameters associated with them. The overall structure suggests a representation of data, possibly used as input or output for a coding pattern algorithm, where the highlighted region might indicate a specific area of interest or a subset of the data.
Output: 9
*/

    static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
        System.out.println("Largest square area: " + largestSquare(matrix));
        System.out.println("Largest square area: " + largestSquare1(matrix));
    }

    private static int largestSquare1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] prevRow = new int[cols];
        int maxLen = 0;

        for (int i = 0; i < rows; i++) {

            int[] currentRow = new int[cols];
            for (int j = 0; j < cols; j++) {

                if (i == 0 || j == 0) {
                    currentRow[j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    currentRow[j] = 1 + Math.min(prevRow[j], Math.min(currentRow[j - 1], prevRow[j - 1]));
                }

                maxLen = Math.max(maxLen, currentRow[j]);
            }

            prevRow = currentRow;
        }

        return maxLen * maxLen;
    }

    private static int largestSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        int maxLen = 0;

        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 1) {
                dp[0][i] = 1;
                maxLen = 1;
            }
        }

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                maxLen = 1;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }

                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        return maxLen * maxLen;
    }
}
