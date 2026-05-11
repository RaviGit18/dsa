package com.practice.onlineTest;

import java.util.*;

class MinimumPathWeight {

    /*java program for to find the minimum path weight to reach at point y from a arbitrary point x in a 2d grid.
This is a classic Shortest Path in a 2D Grid problem.

Usually:

Each cell contains a weight/cost
You start from point X
Need minimum total weight to reach point Y

This can be solved using:

Dijkstra’s Algorithm (best for weighted grids)
BFS works only when all weights are equal
Problem Example

Grid:

1 3 1
1 5 1
4 2 1

Start:

(0,0)

Destination:

(2,2)

Minimum path:

1 → 3 → 1 → 1 → 1

Answer:

7
Approach — Dijkstra on Grid

Each cell is treated as a graph node.

From every cell, you can move:

Up
Down
Left
Right

We maintain:

minimum distance for every cell
priority queue (min-heap)


Time Complexity

Using Dijkstra:

O(M × N × log(M × N))

Where:

M = rows
N = columns

If Diagonal Moves Are Allowed

Add these directions:

static int[] dr = {-1,1,0,0,-1,-1,1,1};
static int[] dc = {0,0,-1,1,-1,1,-1,1};
    * */
    public static void main(String[] args) {

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = minPathCost(grid,
                0, 0,
                2, 2);

        System.out.println("Minimum Path Cost = " + result);
    }

    static class Cell {
        int row;
        int col;
        int cost;

        Cell(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    // Directions: up, down, left, right
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static int minPathCost(int[][] grid,
                                  int startRow,
                                  int startCol,
                                  int endRow,
                                  int endCol) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] dist = new int[m][n];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Cell> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        dist[startRow][startCol] = grid[startRow][startCol];

        pq.offer(new Cell(startRow,
                startCol,
                grid[startRow][startCol]));

        while (!pq.isEmpty()) {

            Cell current = pq.poll();

            int r = current.row;
            int c = current.col;
            int cost = current.cost;

            // Reached destination
            if (r == endRow && c == endCol) {
                return cost;
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < m &&
                        nc >= 0 && nc < n) {

                    int newCost = cost + grid[nr][nc];

                    if (newCost < dist[nr][nc]) {

                        dist[nr][nc] = newCost;

                        pq.offer(new Cell(nr,
                                nc,
                                newCost));
                    }
                }
            }
        }

        return -1; // No path found
    }


}