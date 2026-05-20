package com.practice.onlineTest.hacker;

import java.util.*;

class PriorityReducer {

    /*java program for  reduce priorities to the next minimum but maintain order and the limit was 0 to 99. Example:

Input: [2,3,2,6]

Output: [1,2,1,3]*/

    /*You can solve this by compressing the priorities/ranks while preserving their relative order.

Idea
Find all unique values
Sort them
Assign compressed ranks starting from 1
Replace original values with their ranks

Example:

Input:

[2,3,2,6]

Unique sorted values:

[2,3,6]

Mapping:

2 -> 1
3 -> 2
6 -> 3

Output:

[1,2,1,3]


Time Complexity
Sorting unique elements: O(N log N)
Mapping + replacement: O(N)

Overall:

O(N log N)
*/

    public static void main(String[] args) {

        int[] input = {2, 3, 2, 6};

        int[] output = reducePriorities(input);

        System.out.println("Input  : " + Arrays.toString(input));
        System.out.println("Output : " + Arrays.toString(output));
    }

    public static int[] reducePriorities(int[] arr) {

        // Step 1: Store unique values in sorted order
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : arr) {
            set.add(num);
        }

        // Step 2: Assign ranks
        Map<Integer, Integer> rankMap = new HashMap<>();

        int rank = 1;

        for (int num : set) {
            rankMap.put(num, rank++);
        }

        // Step 3: Replace original values with ranks
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }


}