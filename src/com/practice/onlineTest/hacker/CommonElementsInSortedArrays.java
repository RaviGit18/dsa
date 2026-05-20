package com.practice.onlineTest.hacker;

import java.util.HashSet;
import java.util.Set;

/**
 * CommonElementsInSortedArrays - Finds common elements across multiple sorted arrays
 * 
 * Problem: Find elements common to all given arrays using optimal approach
 * 
 * Algorithm: Multiple pointers approach for sorted arrays
 * 
 * Time Complexity: O(N) where N = total elements across all arrays
 * Space Complexity: O(1) - constant extra space
 */
class CommonElementsInSortedArrays {

    /**
     * Example arrays for testing common element finding
     * All arrays are sorted in ascending order
     */
    /*write java program to find common element in all the given arrays. Also explain logic and time and space complexities.
int arr1[] = { 1, 5, 10, 20, 40, 80 };
int arr2[] = { 5, 6, 7, 20, 30, 40, 50, 80 };
int arr3[] = { 3, 4, 15, 20, 30, 40, 50, 60, 77, 80 };
int arr4[] = { 3, 4, 15, 20, 30, 40, 50, 60, 77,78,79, 80 };
int arr5[] = { 3, 4, 15, 20, 30, 40, 50, 60,64,65,69, 77,78,79, 80 };
*/

    /**
     * Algorithm Comparison:
     * 
     * | Approach          | Time | Space | Requires Sorted Arrays |
     * | ----------------- | ---- | ----- | ---------------------- |
     * | Multiple Pointers | O(N) | O(1)  | Yes                    |
     * | HashSet           | O(N) | O(N)  | No                     |
     * 
     * Multiple pointers approach is optimal for sorted arrays
     */
    static void main(String[] args) {
        int[] arr1 = { 1, 5, 10, 20, 40, 80 };
        int[] arr2 = { 5, 6, 7, 20, 30, 40, 50, 80 };
        int[] arr3 = { 3, 4, 15, 20, 30, 40, 50, 60, 77, 80 };
        int[] arr4 = { 3, 4, 15, 20, 30, 40, 50, 60, 77, 78, 79, 80 };
        int[] arr5 = { 3, 4, 15, 20, 30, 40, 50, 60, 64, 65, 69, 77, 78, 79, 80 };

        findCommonElements(arr1, arr2, arr3, arr4, arr5);
        System.out.println();
        findCommonElements1(arr1, arr2, arr3, arr4, arr5);
    }

    /**
     * Multiple Pointers Approach - Optimal for sorted arrays
     * 
     * Algorithm: Use 5 pointers, one for each array, to find common elements
     * 
     * Key Insight: All arrays are sorted, so we can advance pointers systematically
     * 
     * Time Complexity: O(N) where N = total elements across all arrays
     * Space Complexity: O(1) - constant extra space for pointers
     */
    private static void findCommonElements(int[] arr1, int[] arr2, int[] arr3, int[] arr4, int[] arr5) {

        // Initialize 5 pointers, one for each array
        int i = 0, j = 0, k = 0, l = 0, m = 0;
        
        // Continue while all pointers are within array bounds
        while(arr1.length > i && arr2.length > j && arr3.length > k && arr4.length > l && arr5.length > m) {

            // Check if all current elements are equal
            if (arr1[i] == arr2[j] &&
                    arr2[j] == arr3[k] &&
                    arr3[k] == arr4[l] &&
                    arr4[l] == arr5[m]
            ) {
                // Found common element - print and advance all pointers
                System.out.print(arr1[i] + " ");

                i++;
                j++;
                k++;
                l++;
                m++;
            } else {
                // Find minimum among current elements
                int min = Math.min(Math.min(Math.min(arr1[i], arr2[j]), arr3[k]), Math.min(arr4[l], arr5[m]));
                
                // Advance only pointers pointing to minimum values
                // This ensures we don't miss potential matches
                i = arr1[i] == min ? i + 1 : i;
                j = arr2[j] == min ? j + 1 : j;
                k = arr3[k] == min ? k + 1 : k;
                l = arr4[l] == min ? l + 1 : l;
                m = arr5[m] == min ? m + 1 : m;
            }

        }
    }

    /**
     * HashSet Approach - Works for unsorted arrays
     * 
     * Algorithm: Use intersection of sets to find common elements
     * 
     * Steps:
     * 1. Store all elements from first array in HashSet
     * 2. Intersect with each subsequent array
     * 3. Final set contains elements common to all arrays
     * 
     * Time Complexity: O(N) where N = total elements across all arrays
     * Space Complexity: O(N) for HashSet storage
     */
    public static void findCommonElements1(int[] arr1,
                                          int[] arr2,
                                          int[] arr3,
                                          int[] arr4,
                                          int[] arr5) {

        // Store elements of first array in HashSet
        Set<Integer> commonSet = new HashSet<>();

        for (int num : arr1) {
            commonSet.add(num);
        }

        // Intersect with arr2
        commonSet = intersect(commonSet, arr2);

        // Intersect with arr3
        commonSet = intersect(commonSet, arr3);

        // Intersect with arr4
        commonSet = intersect(commonSet, arr4);

        // Intersect with arr5
        commonSet = intersect(commonSet, arr5);

        // Print result
        System.out.println("Common Elements:");

        for (int num : commonSet) {
            System.out.print(num + " ");
        }
    }

    /**
     * Helper method: Intersection of HashSet and array
     * 
     * Algorithm: Iterate through array, add elements that exist in set to result
     * 
     * Time Complexity: O(M) where M = array length
     * Space Complexity: O(M) for result set
     * 
     * @param set Existing set of elements
     * @param arr Array to intersect with
     * @return New set containing intersection
     */
    public static Set<Integer> intersect(Set<Integer> set, int[] arr) {

        Set<Integer> result = new HashSet<>();

        // Check each array element against the set
        for (int num : arr) {
            if (set.contains(num)) {
                result.add(num);
            }
        }

        return result;
    }
}

