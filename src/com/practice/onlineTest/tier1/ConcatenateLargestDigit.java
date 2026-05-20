package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given three non-zero numbers ‘A’, ‘B’, and ‘C’.
 * The task is to find the number formed by
 * concatenating the largest digit from each of these numbers in the order of ‘A’, ‘B’, and ‘C’.
 *
 * For Example :
 * A = 5678, B = 45 and C = 769
 * The largest digit in ‘A’ is ‘8’, ‘B’ is ‘5’, and ‘C’ is ‘9’.
 * The new number formed by concatenating the largest digit from each of these numbers is ‘859’. So, the answer is ‘859’.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10^4
 * 1 <= A, B, C <= 10^6
 *
 * Time limit: 1 Sec
 * Sample Input 1 :
 * 2
 * 4554 292 123567
 * 90909 111 876
 * Sample Output 1 :
 * 597
 * 918
 * Explanation Of Sample Input 1 :
 * Test Case 1:
 * a = 4554, b = 292 and c = 123567
 * The largest digit in ‘a’ is ‘5’, ‘b’ is ‘9’, and ‘c’ is ‘7’. The new number formed by concatenating the largest digit from each of these numbers is ‘597’. So, the answer is ‘597’.
 *
 * Test Case 2:
 * a = 90909, b = 111 and c = 876
 * The largest digit in ‘a’ is ‘9’, ‘b’ is ‘1’, and ‘c’ is ‘8’. The new number formed by concatenating the largest digit from each of these numbers is ‘918’. So, the answer is ‘918’.
 * Sample Input 2 :
 * 2
 * 324865 123 456
 * 1 22 333
 * Sample Output 2 :
 * 836
 * 123
 */

public class ConcatenateLargestDigit {
    public static int concatenateLargestDigit(int a, int b, int c) {
        int maxA = getMaxDigit(a);
        int maxB = getMaxDigit(b);
        int maxC = getMaxDigit(c);
        
        return maxA * 100 + maxB * 10 + maxC;
    }
    
    private static int getMaxDigit(int num) {
        int max = 0;
        while (num > 0) {
            int digit = num % 10;
            max = Math.max(max, digit);
            num /= 10;
        }
        return max;
    }
    
    public static void main(String[] args) {
        int t = 2;
        int[][] testCases = {
            {4554, 292, 123567},
            {90909, 111, 876}
        };
        
        for (int i = 0; i < t; i++) {
            int a = testCases[i][0];
            int b = testCases[i][1];
            int c = testCases[i][2];
            System.out.println(concatenateLargestDigit(a, b, c));
        }
    }
}
