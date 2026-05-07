package com.practice.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class LargestOverlapOfIntervals {
    /*Largest Overlap of Intervals
Given an array of intervals, determine the maximum number of intervals that overlap at any point. Each interval is half-open, meaning it includes the start point but excludes the end point.

Example:
Input: intervals = [[1, 3], [5, 7], [2, 6], [4, 8]]
Output: 3
Constraints:
The input will contain at least one interval.
For every index i in the list, intervals[i].start < intervals[i].end.
*/

    static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 3 }, { 5, 7 }, { 2, 6 }, { 4, 8 } };
        int maxOverlap = getMaxOverlap(intervals);
        System.out.println(maxOverlap);
    }

    private static int getMaxOverlap(int[][] intervals) {
        List<EventPoint> events = new ArrayList<>();
        for (int[] interval : intervals) {
            events.add(new EventPoint(interval[0], 'S'));
            events.add(new EventPoint(interval[1], 'E'));
        }

        Collections.sort(events, new Comparator<EventPoint>() {
            @Override
            public int compare(EventPoint e1, EventPoint e2) {
                if (e1.time != e2.time) {
                    return Integer.compare(e1.time, e2.time);
                }
                return Character.compare(e1.type, e2.type); // 'E' < 'S' in ASCII
            }
        });

        int activeIntervals = 0;
        int maxOverlap = 0;

        for (EventPoint event : events) {
            if (event.type == 'S') {
                activeIntervals++;
            } else {
                activeIntervals--;
            }

            maxOverlap = Math.max(maxOverlap, activeIntervals);
        }

        return  maxOverlap;
    }
}

class EventPoint {
    int time;
    char type;

    public EventPoint(int time, char type) {
        this.time = time;
        this.type = type;
    }


}
