package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given an array of integers 'arr' and an integer 'limit'.
 *
 *
 *
 * Your task is to find the smallest positive integer divisor, such that upon dividing all the elements of the given array by it, the sum of the division's result is less than or equal to the given integer's limit.
 *
 *
 *
 * Note:
 * Each result of the division is rounded to the nearest integer greater than or equal to that element. For Example, 7/3 = 3.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 5
 * 1 2 3 4 5
 * 8
 * Sample Output 1 :
 * 3
 * Explanation for Sample Input 1 :
 * We can get a sum of 15(1 + 2 + 3 + 4 + 5) if we choose 1 as a divisor.
 * The sum is 9(1 + 1 + 2 + 2 + 3)  if we choose 2 as a divisor, and the sum is 7(1 + 1 + 1 + 2 + 2) if we choose 3 as a divisor, which is less than the 'limit'.
 * Hence we return 3.
 * Sample Input 2 :
 * 4
 * 8 4 2 3
 * 10
 * Sample Output 2 :
 * 2
 * Explanation for Sample Input 2:
 * We can get a sum of 17(8 + 4 + 2 + 3) if we choose 1 as a divisor.
 * The sum is 9(4 + 2 + 1 + 2) if we choose 2 as a divisor, which is less than the 'limit'.
 * Hence, we return 2.
 * Sample Input 3:
 * 5
 * 2 3 5 7 11
 * 11
 * Sample Output 3 :
 * 3
 * Constraints :
 * 1 <= n <= 10 ^ 5
 * 1 <= arr[i] <= 10 ^ 6
 * N <= limit <= 10 ^ 4
 *
 * Time Limit: 1 sec.
 */
/**
 * Algorithm:
 * - Use binary search to find the smallest divisor
 * - Search space: [1, max(arr)]
 * - For each mid value, calculate the sum of ceil(arr[i] / mid) for all elements
 * - If sum <= limit, try smaller divisors (search left)
 * - If sum > limit, try larger divisors (search right)
 * - Return the minimum valid divisor
 *
 * Time Complexity: O(N * log(max(arr)))
 * - N = length of array
 * - Binary search takes log(max) iterations
 * - Each iteration requires O(N) to calculate sum
 *
 * Space Complexity: O(1)
 * - Constant extra space
 */
public class SmallestDivisor {

    public static int smallestDivisor(int[] arr, int limit) {
        int left = 1;
        int right = 0;

        for (int num : arr) {
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canDivide(arr, limit, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canDivide(int[] arr, int limit, int divisor) {
        int sum = 0;

        for (int num : arr) {
            sum += (num + divisor - 1) / divisor;
            if (sum > limit) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int limit1 = 8;
        System.out.println("Test 1: " + smallestDivisor(arr1, limit1));

        int[] arr2 = {8, 4, 2, 3};
        int limit2 = 10;
        System.out.println("Test 2: " + smallestDivisor(arr2, limit2));

        int[] arr3 = {2, 3, 5, 7, 11};
        int limit3 = 11;
        System.out.println("Test 3: " + smallestDivisor(arr3, limit3));
    }
}
