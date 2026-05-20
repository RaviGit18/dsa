package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given an array ‘ARR’ of size ‘N’ and an integer ‘M’. You have to split the array into ‘M’ non-overlapping, non-empty subarrays such that the maximum of all the subarray’s sum is the minimum possible. Your task is to return the minimum of the maximum of all the subarray’s sum.
 *
 * For example:
 * You are given ‘ARR’ = [7, 2, 6, 10, 8] and ‘M’ = 2. We split the array as [ 7, 2, 6] and [10, 8], the maximum of 7 + 2 + 6  and 10 + 8 is 18, which is the minimum possible.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints
 * 1 <= T <= 10
 * 1 <= N <= 10^5
 * 1 <= M <= 100
 * 0 <= ARR[i] <= 10^9
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 5 2
 * 7 2 6 10 8
 * 3 3
 * 1 4 4
 * Sample Output 1:
 * 18
 * 4
 * Explanation:
 * For the first test case, ‘ARR’= [7, 2, 6, 10,8] and M=’2’ when we split the array as [ 7, 2, 6] and [10, 8], the maximum of 7 + 2 + 6  and 10 + 8, is 18, which is the minimum possible.
 *
 * For the first test case, ‘ARR’= [1, 4, 4] and M = 3 when we split the array as [1], [4], and [4], the maximum of 1, 4  and 4, is 4, which is the minimum possible.
 * Sample Input 2:
 * 2
 * 5 2
 * 1 2 3 4 5
 * 2 2
 * 1 2
 * Sample Output 2:
 *  9
 *  2
 */
/**
 * Algorithm:
 * - Use binary search to find the minimum possible maximum subarray sum
 * - Search space: [max(arr), sum(arr)]
 * - For each mid value, check if we can split array into M subarrays with max sum <= mid
 * - If possible, try smaller values (search left)
 * - If not possible, try larger values (search right)
 * - Return the minimum valid value
 *
 * Time Complexity: O(N * log(sum(arr) - max(arr)))
 * - N = length of array
 * - Binary search takes log(sum) iterations
 * - Each iteration requires O(N) to check feasibility
 *
 * Space Complexity: O(1)
 * - Constant extra space
 */
public class SplitArray {

    public static int splitArray(int[] arr, int m) {
        int left = 0;
        int right = 0;

        for (int num : arr) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canSplit(arr, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canSplit(int[] arr, int m, int maxSum) {
        int count = 1;
        int currentSum = 0;

        for (int num : arr) {
            currentSum += num;
            if (currentSum > maxSum) {
                count++;
                currentSum = num;
                if (count > m) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 2, 6, 10, 8};
        int m1 = 2;
        System.out.println("Test 1: " + splitArray(arr1, m1));

        int[] arr2 = {1, 4, 4};
        int m2 = 3;
        System.out.println("Test 2: " + splitArray(arr2, m2));

        int[] arr3 = {1, 2, 3, 4, 5};
        int m3 = 2;
        System.out.println("Test 3: " + splitArray(arr3, m3));

        int[] arr4 = {1, 2};
        int m4 = 2;
        System.out.println("Test 4: " + splitArray(arr4, m4));
    }
}
