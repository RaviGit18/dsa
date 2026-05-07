package com.practice.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeOverlappingIntervals {

    /*Merge Overlapping Intervals
Merge an array of intervals so there are no overlapping intervals, and return the resultant merged intervals.

Input: intervals = [[3, 4], [7, 8], [2, 5], [6, 7], [1, 4]]
Output: [[1, 5], [6, 8]]
Constraints:
The input contains at least one interval.

For every index i in the array, intervals[i].start ≤ intervals[i].end.
*/

    static void main(String[] args) {
        int[][] intervals = new int[][] { { 3, 4 }, { 7, 8 }, { 2, 5 }, { 6, 7 }, { 1, 4 } };

        int[][] mergedIntervals = merge(intervals);

        System.out.println(Arrays.deepToString(mergedIntervals));
    }

    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            if (last[1] >= intervals[i][0]) {
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                merged.add(intervals[i]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

}