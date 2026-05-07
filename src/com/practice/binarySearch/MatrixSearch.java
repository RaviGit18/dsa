package com.practice.binarySearch;

class MatrixSearch {

    /*Matrix Search
Determine if a target value exists in a matrix. Each row of the matrix is sorted in non-decreasing order, and the first value of each row is greater than or equal to the last value of the previous row.

Example:
Input: matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]], target = 5
Output: true

Input: matrix = [[2, 3, 4, 6], [7, 10, 11, 17], [20, 21, 24, 33]], target = 21
Output: true
*/

    static void main(String[] args) {
        int[][] matrix = {{2, 3, 4, 6}, {7, 10, 11, 17}, {20, 21, 24, 33}};
        int target = 21;
        System.out.println("Target found: " + searchMatrix(matrix, target));
    }

    private static boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int r = mid / cols;
            int c = mid % cols;

            int midVal = matrix[r][c];

            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
