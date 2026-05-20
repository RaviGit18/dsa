package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * There is a multi-core CPU. A single-core in this CPU can execute only a single task at a given time, so if two tasks need to be executed simultaneously, we will need at least two different cores to process them.
 *
 * You have been given a list of ‘N’ tasks that need to be executed, this list contains starting and ending times of each task.
 *
 * Find out the minimum number of cores required in this CPU so that all the tasks can be executed at their scheduled time.
 *
 * Note:
 * If a core is busy executing task-1 then it won’t be able to execute task-2 if the starting time of task-2 is less than the end time of task-1.
 * Example :
 * If N = 4 and the timestamps of the tasks are: { {0,10}, {5,20}, {10,25}, {30,40} }
 *
 * Then the minimum number of cores required is equal to 2.
 * Task-1 will arrive at 0 and core-1 will execute it till 10.
 * Task-2 will arrive at 5 and as core-1 is busy executing task-1, so core-2 will be assigned and it will execute this task till 20
 * Task-3 will arrive at 10, core-1 has just finished executing task-1 and is now free, so core-1 will execute this task till 25.
 * Task-4 will arrive at 30, both core-1 and core-2 are free and any one of them will execute this task.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 1 <= N <= 1000
 * 0 <= Start[i] < End[i] <= 1’000’000
 *
 * Time limit: 1 sec
 * Sample Input 1 :
 * 2
 * 4
 * 0 10
 * 5 20
 * 10 25
 * 30 40
 * 3
 * 1 5
 * 2 4
 * 3 4
 * Sample Output 1 :
 * 2
 * 3
 * Explanation For Sample Input 1 :
 * For test case 1 :
 * We will return 2, because:
 * Task-1 will arrive at 0 and core-1 will execute it till 10.
 * Task-2 will arrive at 5 and as core-1 is busy, so core-2 will be assigned and it will execute this task till 20
 * Task-3 will arrive at 10, core-1 has just finished executing task-1 and is now free, so core-1 will execute this task by 25.
 * Task-4 will arrive at 30, both core-1 and core-2 are free and any one of them will execute this task.
 *
 *
 * For test case 2 :
 * We will return 3, because:
 * Task-1 will arrive at 1 and core-1 will execute it till 5.
 * Task-2 will arrive at 2 and as core-1 is busy, so core-2 will execute this task till 4.
 * Task-3 will arrive at 3, as both core-1 and core-2 are busy executing other tasks, so core-3 will be assigned to execute task-3.
 * Sample Input 2 :
 * 2
 * 2
 * 5 10
 * 2 8
 * 1
 * 10 11
 * Sample Output 2 :
 * 2
 * 1
 */
import java.util.Arrays;

public class CPUTaskScheduler {

    public static void main(String[] args) {
        int t = 2;
        int[][][] testCases = {
                {{0, 10}, {5, 20}, {10, 25}, {30, 40}},
                {{1, 5}, {2, 4}, {3, 4}}
        };

        for (int i = 0; i < t; i++) {
            int[][] tasks = testCases[i];
            System.out.println(minCoresRequired(tasks));
        }
    }

    public static int minCoresRequired(int[][] tasks) {
        if (tasks == null || tasks.length == 0) return 0;
        
        Integer[] events = new Integer[tasks.length * 2];
        int idx = 0;
        for (int[] task : tasks) {
            events[idx++] = task[0];
            events[idx++] = -task[1];
        }
        
        Arrays.sort(events, (a, b) -> {
            if (Math.abs(a) != Math.abs(b)) {
                return Math.abs(a) - Math.abs(b);
            }
            return a - b;
        });
        
        int maxCores = 0;
        int currentCores = 0;
        
        for (int event : events) {
            if (event > 0) {
                currentCores++;
                maxCores = Math.max(maxCores, currentCores);
            } else {
                currentCores--;
            }
        }
        
        return maxCores;
    }
    

}
