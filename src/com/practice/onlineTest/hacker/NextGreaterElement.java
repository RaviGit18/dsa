package com.practice.onlineTest.hacker;

import java.util.*;

class NextGreaterElement {

    /*java program for Monotonic stack problem
    *
    * Monotonic Stack in Java

A Monotonic Stack is a stack that maintains elements in:

increasing order
OR
decreasing order

Used in many interview problems like:

Next Greater Element
Stock Span
Largest Rectangle Histogram
Daily Temperatures
Visible People in Queue
Classic Problem — Next Greater Element
Problem

For every element in the array:
find the next greater element on the right.

If none exists:

-1
Example

Input:

[2, 1, 2, 4, 3]

Output:

[4, 2, 4, -1, -1]

Explanation:

Element	Next Greater
    | Element | Next Greater |
    | ------- | ------------ |
    | 2       | 4            |
    | 1       | 2            |
    | 2       | 4            |
    | 4       | -1           |
    | 3       | -1           |

Monotonic Stack Idea

We maintain a decreasing stack.

While current element is greater than stack top:

pop elements
current element becomes their answer
*
* Dry Run

Input:

[2,1,2,4,3]

Stack behavior:

| Current | Stack   | Action   |
| ------- | ------- | -------- |
| 2       | [2]     | push     |
| 1       | [2,1]   | push     |
| 2       | pop 1   | answer=2 |
| 4       | pop 2,2 | answer=4 |
| 3       | push    |          |

Why It Is Called Monotonic

The stack always remains:

Decreasing Stack

stack[0]>stack[1]>stack[2]>...

OR

Increasing Stack

stack[0]<stack[1]<stack[2]<...

depending on problem.

Time Complexity

Each element:

pushed once
popped once

Thus:

O(N)
Space Complexity
O(N)
Common Monotonic Stack Problems
    | Problem              | Stack Type |
    | -------------------- | ---------- |
    | Next Greater Element | Decreasing |
    | Next Smaller Element | Increasing |
    | Daily Temperatures   | Decreasing |
    | Stock Span           | Decreasing |
    | Largest Histogram    | Increasing |
    | Trapping Rain Water  | Increasing |

Template
*
Next Greater
while (!stack.isEmpty() &&
       current > stack.peek()) {

    stack.pop();
}
*
Next Smaller
while (!stack.isEmpty() &&
       current < stack.peek()) {

    stack.pop();
}
    * */
    public static void main(String[] args) {

        int[] arr = {2, 1, 2, 4, 3};

        int[] result =
                nextGreater(arr);

        System.out.println(
                Arrays.toString(result));
    }

    public static int[] nextGreater(int[] arr) {

        int n = arr.length;

        int[] result = new int[n];

        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        // Store indices
        for (int i = 0; i < n; i++) {

            // Current element greater
            while (!stack.isEmpty() &&
                    arr[i] > arr[stack.peek()]) {

                int index = stack.pop();

                result[index] = arr[i];
            }

            stack.push(i);
        }

        return result;
    }


}
