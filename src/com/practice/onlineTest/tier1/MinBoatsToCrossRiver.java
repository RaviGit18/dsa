package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Ninja and his friends are on a trip. They have come across a river and want to cross the river with the help of boats. There are a total of 'N' people on the trip including Ninja himself. The weight of each person is given in an array 'ARR'. One boat can accommodate at most two persons only if the sum of their weight does not exceed 'L', i.e., the maximum weight capacity of the boat, otherwise, the boat will accommodate only one person. Given the weight of each person and the maximum weight capacity 'L', your task is to find the minimum number of boats required to ensure that everyone crosses the river.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= L <= 10^9
 * 1 <= N <= 10^5
 * 1 <= ARR[i] <= L
 *
 * Where 'T' denotes the number of test cases, 'N' denotes the number of elements in the array, 'L' denotes the maximum weight capacity of one boat, and 'ARR[i]' denotes the 'i'th' element of the array 'ARR'.
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 4 5
 * 2 4 3 3
 * 6 8
 * 5 1 2 7 8 5
 * Sample Output 1:
 * 3
 * 4
 * Explanation For Sample Input 1:
 * For the first test case,
 * If the person having weight 2 and one of the people having weight 3 goes in the same boat, while the other two persons cross the river using different boats. Then the total number of boats used will be 3, which is the minimum possible. Hence, the answer is 3 in this case.
 *
 * For the second test case,
 * If the person having weight 2 and one of the person having weight 5 goes in the same boat and the persons having weight 1 and 7 goes in the same boat, while the other two persons cross the river using different boats. Then the total number of boats used will be 4, which is the minimum possible. Hence, the answer is 4 in this case.
 * Sample Input 2:
 * 2
 * 5 6
 * 3 4 5 6 6
 * 6 3
 * 1 3 1 2 2 3
 * Sample Output 2:
 * 5
 * 4
 * Explanation For Sample Input 2:
 * For the first test case,
 * No two or more can go on same boat. Hence, the answer is 5 in this case.
 *
 * For the second test case,
 * If the person having weight 1 and one of the person having weight 2 goes in the same boat and the persons having weight 1 and 2 goes in the same boat, while the other two persons cross the river using different boats. Then the total number of boats used will be 4, which is the minimum possible. Hence, the answer is 4 in this case.
 */
import java.util.*;

public class MinBoatsToCrossRiver {
    public int minBoatsToCrossRiver(int[] arr, int limit) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        int boats = 0;
        
        while (left <= right) {
            if (left == right) {
                boats++;
                break;
            }
            
            if (arr[left] + arr[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            boats++;
        }
        
        return boats;
    }

    public static void main(String[] args) {
        MinBoatsToCrossRiver mb = new MinBoatsToCrossRiver();
        
        // Test case 1: [2, 4, 3, 3], limit = 5
        int[] arr1 = {2, 4, 3, 3};
        System.out.println("Test case 1: " + mb.minBoatsToCrossRiver(arr1, 5));
        
        // Test case 2: [5, 1, 2, 7, 8, 5], limit = 8
        int[] arr2 = {5, 1, 2, 7, 8, 5};
        System.out.println("Test case 2: " + mb.minBoatsToCrossRiver(arr2, 8));
        
        // Test case 3: [3, 4, 5, 6, 6], limit = 6
        int[] arr3 = {3, 4, 5, 6, 6};
        System.out.println("Test case 3: " + mb.minBoatsToCrossRiver(arr3, 6));
    }
}
