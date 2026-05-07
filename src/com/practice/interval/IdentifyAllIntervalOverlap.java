package com.practice.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IdentifyAllIntervalOverlap {

    /*Identify All Interval Overlaps
Return an array of all overlaps between two arrays of intervals; intervals1 and intervals2. Each individual interval array is sorted by start value, and contains no overlapping intervals within itself.

Input: intervals1 = [[1, 4], [5, 6], [9, 10]],
       intervals2 = [[2, 7], [8, 9]]
Output: [[2, 4], [5, 6], [9, 9]]
Constraints:
For every index i in intervals1, intervals1[i].start < intervals1[i].end.

For every index j in intervals2, intervals2[j].start < intervals2[j].end.
*/

    static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 4 }, { 5, 6 }, { 9, 10 } };
        int[][] intervals1 = new int[][] { { 2, 7 }, { 8, 9 } };
        int[][] overllapedIntervals = getOverllapedIntervals(intervals, intervals1);

        System.out.println(Arrays.deepToString(overllapedIntervals));

    }

    private static int[][] getOverllapedIntervals(int[][] intervals, int[][] intervals1) {
        List<int[]> overllapedIntervals = new ArrayList<>();

        int i = 0, j = 0;

        while (i < intervals.length && j < intervals1.length) {

            int[] A = intervals[i];
            int[] B = intervals1[j];

            if (A[0] > B[0]) {
                int[] temp = A;
                A = B;
                B = temp;
            }

            if (A[1] >= B[0]) {
                overllapedIntervals.add(new int[]{B[0], Math.min(A[1], B[1])});
            }

            if (A[1] > B[1]) {
                j++;
            } else {
                i++;
            }
        }

        return overllapedIntervals.toArray(new int[overllapedIntervals.size()][]);
    }
}
