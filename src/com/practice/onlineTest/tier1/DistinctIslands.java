package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a two-dimensional array/list of integers consisting of 0s and 1s. In the list, 1 represents land and 0 represents water.
 *
 * The task is to find the number of distinct islands where a group of connected 1s(horizontally or vertically) forms an island.
 *
 * Note:
 * Two islands are considered to be the same if and only if one island is equal to another(not rotated or reflected) i.e if we can translate one island on another without rotating or reflecting then it would be considered as the same islands.
 * For example:
 * 1 1 0
 * 0 0 1
 * 0 0 1
 *
 * In this example, we have two islands and they would be considered as distinct islands as we can not translate them on one another even if they have the same no of 1's.
 * For example :
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 0 1 1
 * 0 0 0 1 1
 *
 * In this example, we have two islands and they are the same as we can translate one island onto another island, so our answer should be 1.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints
 *  0 <= N <= 1000
 *  0 <= M <= 1000
 *  0 <= elements of array <= 1
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 *  4
 *  5
 *  1 1 0 1 1
 *  1 0 0 0 0
 *  0 0 0 0 1
 *  1 1 0 1 1
 * Sample Output 1:
 *  3
 * Explanation For Sample Input 1:
 * Distinct islands in the example above are:
 *
 * 1st -> at the top left corner;
 *
 * 2nd -> at the top right corner
 *
 * 3rd -> at the bottom right corner.
 *
 * We ignore the island at the bottom left corner since it is identical to the top right corner.
 * Sample Input 2:
 * 3
 * 2
 * 1 0
 * 0 1
 * 1 1
 * Sample Output 2:
 * 2
 */
import java.util.*;

public class DistinctIslands {
    public static int distinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        Set<String> islands = new HashSet<>();
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, i, j, visited, shape, i, j);
                    islands.add(shape.toString());
                }
            }
        }
        
        return islands.size();
    }
    
    private static void dfs(int[][] grid, int i, int j, boolean[][] visited, StringBuilder shape, int baseI, int baseJ) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        
        visited[i][j] = true;
        shape.append(i - baseI).append(",").append(j - baseJ).append(";");
        
        dfs(grid, i + 1, j, visited, shape, baseI, baseJ);
        dfs(grid, i - 1, j, visited, shape, baseI, baseJ);
        dfs(grid, i, j + 1, visited, shape, baseI, baseJ);
        dfs(grid, i, j - 1, visited, shape, baseI, baseJ);
    }
    
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        int[][] grid = {
            {1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}
        };
        
        System.out.println(distinctIslands(grid));
    }
}


