package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a 2-dimensional array/list having N rows and M columns, which is filled with ones(1) and zeroes(0). 1 signifies land, and 0 signifies water.
 *
 * A cell is said to be connected to another cell, if one cell lies immediately next to the other cell, in any of the eight directions (two vertical, two horizontal, and four diagonals).
 *
 * A group of connected cells having value 1 is called an island. Your task is to find the number of such islands present in the matrix.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= N <= 10^3
 * 1 <= M <= 10^3
 * 0 <= ARR[i][j] <= 1
 *
 * Time limit: 1sec
 * Sample Input 1 :
 * 4 5
 * 0 1 1 0 0
 * 1 0 0 1 0
 * 0 0 1 0 0
 * 1 0 0 0 1
 * Sample Output 1 :
 * 3
 * Explanation For Sample Input 1 :
 * The first island of connected 1s is signified by: {0, 1}, {0, 2}, {1, 0}, {1, 3}, {2, 2}.
 *
 * The second island being: {3, 0}.
 *
 * The third island being: {3, 4}.
 * Sample Input 2 :
 * 4 4
 * 1 0 0 1
 * 0 1 1 0
 * 0 1 1 0
 * 1 0 0 1
 * Sample Output 2 :
 * 1
 */
/**
 * Algorithm:
 * - Use DFS/BFS to explore and mark all connected land cells
 * - Iterate through each cell in the matrix
 * - When a land cell (1) is found, increment island count
 * - Perform DFS/BFS to mark all connected land cells as visited (set to 0)
 * - Continue until all cells are processed
 * - Check all 8 directions (horizontal, vertical, and diagonal)
 *
 * Time Complexity: O(N * M)
 * - N = number of rows, M = number of columns
 * - Each cell is visited at most once
 *
 * Space Complexity: O(N * M)
 * - For the recursion stack in worst case
 */
public class NumberOfIslands {

    public static int countIslands(int[][] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        int m = arr[0].length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    count++;
                    dfs(arr, i, j, n, m);
                }
            }
        }

        return count;
    }

    private static void dfs(int[][] arr, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || arr[i][j] == 0) {
            return;
        }

        arr[i][j] = 0;

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            dfs(arr, i + dx[d], j + dy[d], n, m);
        }
    }

    public static void main(String[] args) {
        int[][] arr1 = {
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 0, 0, 1}
        };
        System.out.println("Test 1: " + countIslands(arr1));

        int[][] arr2 = {
            {1, 0, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {1, 0, 0, 1}
        };
        System.out.println("Test 2: " + countIslands(arr2));
    }
}
