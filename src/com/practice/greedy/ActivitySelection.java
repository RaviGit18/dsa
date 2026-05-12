package com.practice.greedy;

import java.util.*;

/**
 *
 * Activity Selection (Greedy Algorithm)
 * Problem Statement:
 * You are given n activities with their start and finish times.
 * Select the maximum number of activities that can be performed by a single person,
 * assuming that a person can only work on one activity at a time.
 *
 * Example Input:
 *
 * Code
 * Activities:
 * Start =  [1, 3, 0, 5, 8, 5]
 * Finish = [2, 4, 6, 7, 9, 9]
 * Expected Output:
 *
 * Code
 * Maximum activities: 4
 * Selected activities: (1,2), (3,4), (5,7), (8,9)
 * Constraints:
 *
 * 1 ≤ n ≤ 10^5
 *
 * Start[i] < Finish[i]
 */
public class ActivitySelection {

    public static void main(String[] args) {
        // Example input
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] finish = {2, 4, 6, 7, 9, 9};

        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            activities.add(new Activity(start[i], finish[i]));
        }

        List<Activity> selected = selectActivities(activities);

        System.out.println("Maximum activities: " + selected.size());
        System.out.println("Selected activities:");
        for (Activity act : selected) {
            System.out.println("(" + act.start + ", " + act.finish + ")");
        }
    }

    // Activity class to hold start and finish times
    static class Activity {
        int start, finish;
        Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    /*
    * Explanation
        Sort activities by finish time (greedy choice).

        Iterate through activities:

            If the current activity’s start time is ≥ last selected activity’s finish time, select it.

        This ensures maximum non-overlapping activities are chosen.
    * */
    public static List<Activity> selectActivities(List<Activity> activities) {
        // Sort activities by finish time
        activities.sort(Comparator.comparingInt(a -> a.finish));

        List<Activity> result = new ArrayList<>();
        int lastFinishTime = -1;

        for (Activity activity : activities) {
            if (activity.start >= lastFinishTime) {
                result.add(activity);
                lastFinishTime = activity.finish;
            }
        }
        return result;
    }


}

