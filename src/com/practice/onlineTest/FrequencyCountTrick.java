package com.practice.onlineTest;

import java.util.*;

class FrequencyCountTrick {

    /*java program to to calculating occurrence of a number in an array with a trick
    A very common interview “trick” to count occurrences in an array is:

Problem

Count frequency of numbers in an array without using extra space.

This trick works when:

Array elements are in range 0 to n-1
n = array length
Example

Input:

[2, 3, 3, 2, 1]

Output:

1 -> 1
2 -> 2
3 -> 2
Core Trick

Use the same array to store frequency.

For every element:

arr[arr[i] % n] += n

Why % n?
Because values get modified after increments.

Frequency becomes:

arr[i] / n
Dry Run

Input:

[2,3,3,2,1]
n = 5

After processing:

[2,8,13,13,1]

Now:

0 -> 2/5 = 0
1 -> 8/5 = 1
2 -> 13/5 = 2
3 -> 13/5 = 2
4 -> 1/5 = 0

Time Complexity
O(N)
Space Complexity
O(1)

Important Constraint

This trick works only when:

0 <= arr[i] < n

Otherwise:

use HashMap
or coordinate compression
Interview Follow-up

Interviewers often ask:

What if numbers are from 1 to n instead of 0 to n-1?

Use:

int index = (arr[i] - 1) % n;
Alternative Trick (Negative Marking)

Useful for:

duplicate detection
missing numbers

Example:

arr[Math.abs(arr[i])] *= -1;

Commonly asked in FAANG interviews.
    * */
    public static void main(String[] args) {

        int[] arr = {2, 3, 3, 2, 1};

        countFrequency(arr);
    }

    public static void countFrequency(int[] arr) {

        int n = arr.length;

        // Step 1: Encode frequencies
        for (int i = 0; i < n; i++) {

            int index = arr[i] % n;

            arr[index] += n;
        }

        // Step 2: Decode frequencies
        System.out.println("Occurrences:");

        for (int i = 0; i < n; i++) {

            int freq = arr[i] / n;

            if (freq > 0) {
                System.out.println(i + " -> " + freq);
            }
        }
    }


}
