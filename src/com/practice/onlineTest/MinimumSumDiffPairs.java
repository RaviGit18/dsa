package com.practice.onlineTest;

import java.util.*;

class MinimumSumDiffPairs {

    /*
        problem is:

Find all pairs in an array having the minimum absolute difference (minimum sum difference between pairs).

Example:

Input:

[4, 2, 1, 3]

Sorted:

[1, 2, 3, 4]

Differences:

(1,2) -> 1
(2,3) -> 1
(3,4) -> 1

Minimum difference:

1

Output:

[[1,2], [2,3], [3,4]]
Efficient Approach
Sort the array
Find minimum adjacent difference
Collect all pairs with that difference

Because in a sorted array, the minimum difference always occurs between adjacent elements.

Time Complexity

Sorting:

O(N log N)

Scanning:

O(N)

Overall:

O(N log N)
    */

    public static void main(String[] args) {

        int[] arr = {4, 2, 1, 3};

        List<int[]> pairs = findMinPairs(arr);

        System.out.println("Minimum Difference Pairs:");

        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }
    }

    public static List<int[]> findMinPairs(int[] arr) {

        List<int[]> result = new ArrayList<>();

        if (arr == null || arr.length < 2) {
            return result;
        }

        // Step 1: Sort array
        Arrays.sort(arr);

        // Step 2: Find minimum difference
        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {

            int diff = arr[i] - arr[i - 1];

            minDiff = Math.min(minDiff, diff);
        }

        // Step 3: Collect all pairs
        for (int i = 1; i < arr.length; i++) {

            int diff = arr[i] - arr[i - 1];

            if (diff == minDiff) {

                result.add(new int[]{
                        arr[i - 1],
                        arr[i]
                });
            }
        }

        return result;
    }


}
