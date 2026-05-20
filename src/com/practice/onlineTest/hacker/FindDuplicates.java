package com.practice.onlineTest.hacker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class FindDuplicates {

    /*
Find Duplicates in Array — Java Program
Example

Input:

[1, 2, 3, 2, 4, 5, 1, 6]

Output:

Duplicates: 1 2
    */
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 2, 4, 5, 1, 6};

        printDuplicates(arr);
        System.out.println();
        findDuplicates(arr);
        System.out.println();
        arr = new int[]{1, 2, 3, 2, 4, 5, 1, 6};
        findDuplicatesUsingStream(arr);
    }

    /**
     * Approach 1: Using HashSet for duplicate detection
     * 
     * Algorithm: Track seen elements, collect duplicates when add() returns false
     * 
     * Time Complexity: O(N) where N = array length
     * Space Complexity: O(N) for HashSet storage
     */
    private static void printDuplicates(int[] arr) {

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : arr) {

            // If add() returns false,
            // element already exists
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }

        System.out.println("Duplicates:");

        for (int num : duplicates) {
            System.out.print(num + " ");
        }
    }

    //Approach 2 — Without Extra Space (Negative Marking Trick)
    /*Works when: numbers are in range 1 to n
Example
Input: [1, 2, 3, 2, 4, 5, 1]

Important Constraint: This trick works only if: 1 <= arr[i] < arr.length
*/
    public static void findDuplicates(int[] arr) {

        System.out.println("Duplicates:");

        for (int i = 0; i < arr.length; i++) {

            int index = Math.abs(arr[i]);

            // Already negative => duplicate
            if (arr[index] < 0) {
                System.out.print(index + " ");
            } else {
                arr[index] = -arr[index];
            }
        }
    }

    //Approach 3 — Using Frequency Map
    /*Useful when:

array contains large numbers
negative numbers
custom objects*/
    public static void findDuplicatesUsingStream(int[] arr) {
        Map<Integer, Long> freqMap =
                Arrays.stream(arr)
                        .boxed()
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

        System.out.println("Duplicates:");
        
        freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(num -> System.out.print(num + " "));
        System.out.println();

    }


}
