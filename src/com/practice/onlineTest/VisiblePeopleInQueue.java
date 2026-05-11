package com.practice.onlineTest;

import java.util.*;

public class VisiblePeopleInQueue {

    /*
java program for https://leetcode.com/problems/number-of-visible-people-in-a-queue/
Number of Visible People in a Queue
here are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of distinct integers where heights[i] represents the height of the ith person.

A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).

Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.
Example 2:

Input: heights = [5,1,2,3,10]
Output: [4,1,1,1,0]


Constraints:

n == heights.length
1 <= n <= 105
1 <= heights[i] <= 105
All the values of heights are unique.
    */


    /*Idea — Monotonic Stack

For each person:

they can see:
all shorter people until a taller person appears
and also the first taller person

This is a classic:

Monotonic Decreasing Stack

problem.

Key Observation

While processing from right to left:

Pop all shorter people
they are visible
If stack still has someone
first taller person is also visible
Example

Input:

[10,6,8,5,11,9]

For person 10:

sees 6
sees 8
5 hidden by 8
sees 11
cannot see 9

Count:

3

Why Monotonic Stack Works

Stack maintains decreasing heights.

Invariant:

stack[0]>stack[1]>stack[2]>...

This helps efficiently identify:

visible shorter people
first blocking taller person
Time Complexity

Each element:

pushed once
popped once

Thus:

O(N)
Space Complexity

Stack:

O(N)
*/
    public static void main(String[] args) {

        int[] heights =
                {10,6,8,5,11,9};

        int[] result =
                canSeePersonsCount(heights);

        System.out.println(
                Arrays.toString(result));
    }

    public static int[] canSeePersonsCount(
            int[] heights) {

        int n = heights.length;

        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {

            int visible = 0;

            // Pop shorter people
            while (!stack.isEmpty() &&
                    heights[i] > stack.peek()) {

                stack.pop();
                visible++;
            }

            // First taller person is also visible
            if (!stack.isEmpty()) {
                visible++;
            }

            answer[i] = visible;

            // Push current person
            stack.push(heights[i]);
        }

        return answer;
    }


}
