package com.practice.graph;

import java.util.LinkedList;
import java.util.Queue;

class MatrixInfection {

    /*Matrix Infection
You are given a matrix where each cell is either:

0: Empty
1: Uninfected
2: Infected

With each passing second, every infected cell (2) infects its uninfected neighboring cells (1) that are 4-directionally adjacent. Determine the number of seconds required for all uninfected cells to become infected. If this is impossible, return ‐1.

Example:
Input: matrix = [[1, 1, 1, 0], [0, 0, 2, 1], [0, 1, 1, 0]]
Output: 3
*/
    static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, 0}, {0, 0, 2, 1}, {0, 1, 1, 0}};
        System.out.println("Number of seconds required: " + countSecondsToInfect(matrix));
    }

    private static int countSecondsToInfect(int[][] matrix) {
        int rows = matrix.length;

        if (rows == 0) {
            return 0;
        }

        int cols = matrix[0].length;

        int seconds = 0;
        int ones = 0;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 2) {
                    queue.offer(new int[] {r, c});
                } else if (matrix[r][c] == 1) {
                    ones++;
                }
            }
        }

        while (!queue.isEmpty() && ones > 0) {
            seconds++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for (int[] dir : dirs) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];

                    if (isWithinBounds(nextR, nextC, rows, cols)) {

                        if (matrix[nextR][nextC] == 1) {
                            matrix[nextR][nextC] = 2;
                            queue.offer(new int[] {nextR, nextC});
                            ones--;
                        }

                    }
                }
            }
        }

        return ones > 0 ? -1 : seconds;

    }

    private static boolean isWithinBounds(int r, int c, int rows, int cols) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}
