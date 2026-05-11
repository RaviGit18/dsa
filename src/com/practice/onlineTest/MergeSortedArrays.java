package com.practice.onlineTest;

import java.util.*;
import java.util.stream.IntStream;

class MergeSortedArrays {

    /*Merge 2 Sorted Arrays and Remove Duplicates
Example

Input:

arr1 = [1, 2, 4, 5, 6]
arr2 = [2, 3, 5, 7]

Output:

[1, 2, 3, 4, 5, 6, 7]
Efficient Approach — Two Pointers

Since arrays are already sorted:

use two pointers
merge like merge-sort
skip duplicates while inserting

Time Complexity

Since each array is traversed once:

O(m + n)

Where:

m = arr1.length
n = arr2.length
Space Complexity
O(m + n)
(for output array)



*/

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 4, 5, 6};
        int[] arr2 = {2, 3, 5, 7};

        int[] result =
                mergeAndRemoveDuplicates(arr1, arr2);

        System.out.println(
                Arrays.toString(result));

        System.out.println();

        result = mergeAndRemoveDuplicatesUsingStream(arr1, arr2);

        System.out.println(
                Arrays.toString(result));
    }

    private static int[] mergeAndRemoveDuplicatesUsingStream(int[] arr1, int[] arr2) {

        return IntStream.concat(
                        Arrays.stream(arr1),
                        Arrays.stream(arr2))
                .distinct()
                .sorted()
                .toArray();

    }

    public static int[] mergeAndRemoveDuplicates(
            int[] arr1,
            int[] arr2) {

        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < arr1.length &&
                j < arr2.length) {

            int value;

            if (arr1[i] < arr2[j]) {
                value = arr1[i++];
            }
            else if (arr1[i] > arr2[j]) {
                value = arr2[j++];
            }
            else {
                value = arr1[i];
                i++;
                j++;
            }

            // Avoid duplicates
            if (result.isEmpty() ||
                    result.get(result.size() - 1) != value) {

                result.add(value);
            }
        }

        // Remaining elements of arr1
        while (i < arr1.length) {

            int value = arr1[i++];

            if (result.isEmpty() ||
                    result.get(result.size() - 1) != value) {

                result.add(value);
            }
        }

        // Remaining elements of arr2
        while (j < arr2.length) {

            int value = arr2[j++];

            if (result.isEmpty() ||
                    result.get(result.size() - 1) != value) {

                result.add(value);
            }
        }

        // Convert List to array
        int[] merged = new int[result.size()];

        for (int k = 0; k < result.size(); k++) {
            merged[k] = result.get(k);
        }

        return merged;
    }


}
