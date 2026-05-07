package com.practice.graph;

class LongestIncreasingpPath {

    /*Longest Increasing Path
Find the longest strictly increasing path in a matrix of positive integers. A path is a sequence of cells where each one is 4-directionally adjacent (up, down, left, or right) to the previous one.

Example:
Image represents a 3x3 grid, visually similar to a Sudoku grid, with numerical values (1, 3, 4, 5, 7, 8, 9, 2, 4) placed within its cells.  The grid is labeled with row and column indices (0, 1, 2) along its top and left side, respectively.  A path is highlighted, visually represented by a peach-colored fill encompassing specific cells (containing 1, 3, 4, 5, 8), and orange arrows indicate the direction of movement along this path. The path starts at cell (0,0) with the value 1, moves down to cell (1,0) with the value 3, then right to cell (1,1) with the value 4, then up to cell (0,1) with the value 5, and finally right to cell (0,2) with the value 8. The remaining cells (containing 7, 9, 2, 4) are not part of the highlighted path.
Output: 5
*/

    static void main(String[] args) {
        int[][] matrix = {{1, 5, 8}, {3, 4, 4}, {7, 9, 2}};
        System.out.println("Longest increasing path: " + longestIncreasingPath(matrix));
    }

    private static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] memo = new int[rows][cols];
        int maxPath = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maxPath = Math.max(maxPath, dfs(matrix, memo, r, c));
            }
        }

        return maxPath;
    }

    private static int dfs(int[][] matrix, int[][] memo, int r, int c) {

        if (memo[r][c] != 0) return memo[r][c];

        int maxPath = 1;

        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};

        for (int[] dir : dirs) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];

            if (isWithinBounds(nextR, nextC, matrix) && matrix[nextR][nextC] > matrix[r][c]) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, memo, nextR, nextC));
            }
        }

        memo[r][c] = maxPath;

        return maxPath;
    }

    private static boolean isWithinBounds(int nextR, int nextC, int[][] matrix) {
        return nextR >= 0 && nextR < matrix.length && nextC >= 0 && nextC < matrix[0].length;
    }
}
