package com.practice.graph;

class CountIsland {
    /*
    * Count Islands
Given a binary matrix representing 1s as land and 0s as water, return the number of islands.

An island is formed by connecting adjacent lands 4-directionally (up, down, left, and right).
* */

    static void main(String[] args) {
        CountIsland solution = new CountIsland();
        
        // Test case 1: Simple grid with multiple islands
        int[][] grid1 = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 1, 1},
            {0, 1, 0, 0, 0}
        };
        System.out.println("Grid 1 has " + solution.countIsland(grid1) + " islands");
        
        // Test case 2: All water
        int[][] grid2 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Grid 2 has " + solution.countIsland(grid2) + " islands");
        
        // Test case 3: All land (one big island)
        int[][] grid3 = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        System.out.println("Grid 3 has " + solution.countIsland(grid3) + " islands");
        
        // Test case 4: Single cell islands
        int[][] grid4 = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        System.out.println("Grid 4 has " + solution.countIsland(grid4) + " islands");
        
        // Test case 5: Complex shape
        int[][] grid5 = {
            {1, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 1, 1}
        };
        System.out.println("Grid 5 has " + solution.countIsland(grid5) + " islands");
    }

    private int countIsland(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }

        return  count;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) {
            return;
        }

        grid[r][c] = -1;

        int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        for (int[] dir : dirs) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            dfs(grid, newRow, newCol);
        }
    }


}
