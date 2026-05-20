package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * A ninja needs to complete ‘n’ tasks. Each task is represented by an uppercase letter of the English alphabet. Different letters are assigned to different tasks. A ninja can complete tasks in any order. He takes one unit of time to complete one task. For each unit of time, he could complete either one task or just be idle.
 *
 * Ninja easily gets bored by doing the same task again. So he decided to keep at least ‘t’ units of time between any two same tasks.
 *
 * You are given a string ‘tasks’ consisting of ‘n’ uppercase letters of the English alphabet, representing the tasks ninja need to complete, and an integer ‘t’ representing the least units of time between any two same tasks. Find out the minimum total units of time ninja will take to complete all ‘n’ tasks.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 50
 * 1 <= n <= 10^4
 * 1 <= t <= 100
 *
 * Where ‘T’ is the number of test cases and ‘n’ is the number of tasks and ‘t’ is the least units of time between any two same tasks.
 *
 * Time Limit : 1 sec
 * Sample Input 1 :
 * 2
 * 6 2
 * AAABBB
 * 1 5
 * A
 * Sample Output 1 :
 * 8
 * 1
 * Explanation of the Sample Input1 :
 * Test case 1 :
 *
 * One possible way to complete tasks is :-
 *
 * Doing task ‘A’ in the first unit of time.
 *
 * Doing task ‘B’ in the second unit of time because ninjas want to have at least 2 units of time between the same task, so we could not choose A.
 *
 * Be Idle in the third unit of time  because ninjas want to have at least 2 units of time between the same task, so we could not choose any one of  A or B.
 *
 * Doing task ‘A’ in the fourth unit of time.
 *
 * Doing task ‘B’ in the fifth unit of time.
 *
 * Be Idle in the sixth unit of time.
 *
 * Doing task ‘A’ in the seventh unit of time.
 *
 * Doing task ‘B’ in the third unit of time.
 *
 * Note that, there are at least 2 units of time between any two same tasks. We can show that there is no way to complete the task in less than 8 units of time.
 *
 * Test case 2 :
 *
 * There is only one task that can be completed in one unit of time.
 * Sample Input 2 :
 * 2
 * 5 0
 * AAABB
 * 2 10
 * ZZ
 * Sample Output 2 :
 * 5
 * 12
 * Explanation of the Sample Input 2 :
 * Test case 1 :
 *
 * Since ‘t’ = 0, a ninja can complete tasks without becoming idle at any unit of time.
 *
 * Test case 2 :
 *
 * A ninja can complete the first task ‘Z’ in the first unit of time then becomes idle for the next 10 seconds and then complete the second task ‘Z’ in the twelfth unit of time.
 */
/**
 * Algorithm:
 * - Count frequency of each task
 * - Find the task with maximum frequency (maxFreq)
 * - Count how many tasks have this maximum frequency (countMax)
 * - The minimum time is calculated as: max((maxFreq - 1) * (t + 1) + countMax, n)
 * - Formula explanation:
 *   - (maxFreq - 1) * (t + 1): number of full cycles needed for the most frequent task
 *   - Each cycle has t + 1 slots (t cooldown + 1 for the task itself)
 *   - + countMax: add slots for all tasks with maximum frequency in the last cycle
 *   - max(..., n): if we have more tasks than idle slots, we need at least n time units
 *
 * Time Complexity: O(N)
 * - N = number of tasks
 * - Single pass to count frequencies
 *
 * Space Complexity: O(1)
 * - Fixed size array for 26 letters (uppercase English alphabet)
 */
import java.util.*;

public class TaskSchedulers {

    public static int leastInterval(String tasks, int t) {
        int n = tasks.length();
        if (t == 0) {
            return n;
        }

        int[] freq = new int[26];
        for (char c : tasks.toCharArray()) {
            freq[c - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int countMax = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                countMax++;
            }
        }

        int minTime = (maxFreq - 1) * (t + 1) + countMax;
        return Math.max(minTime, n);
    }

    public static void main(String[] args) {
        String tasks1 = "AAABBB";
        int t1 = 2;
        System.out.println("Test 1: " + leastInterval(tasks1, t1));

        String tasks2 = "A";
        int t2 = 5;
        System.out.println("Test 2: " + leastInterval(tasks2, t2));

        String tasks3 = "AAABB";
        int t3 = 0;
        System.out.println("Test 3: " + leastInterval(tasks3, t3));

        String tasks4 = "ZZ";
        int t4 = 10;
        System.out.println("Test 4: " + leastInterval(tasks4, t4));
    }
}
