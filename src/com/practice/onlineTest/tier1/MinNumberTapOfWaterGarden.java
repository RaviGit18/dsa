package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * The gardener wants to water the garden by opening the minimum number of taps. The garden is one-dimensional along the x-axis of length N i.e. the garden starts from point 0 and ends at point N. There are N + 1 tap located at points [0, 1, 2, …, N] in the garden.
 *
 * You are given an integer N, and an array named “ranges” of size N + 1(0-indexed). The ith tap, if opened, can water the gardener from point (i - ranges[i]) to (i + ranges[i]) including both. The task is to find the minimum number of taps that should be open to water the whole garden, return -1 if the garden can not be watered.
 *
 * Example :
 *
 * Follow Up:
 * Can you solve the problem in O(N) time?
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 10^4
 * 0 <= ranges[i] <= 100
 *
 * Time Limit: 1 sec
 * Sample Input 1 :
 * 2
 * 3
 * 0 0 0 0
 * 7
 * 1 2 1 0 2 1 0 1
 * Sample Output 1 :
 * -1
 * 3
 * Explanation For Sample Input 1 :
 * In test case 1, the ranges of taps are as follows : [ [ 0, 0 ], [ 1, 1 ], [ 2, 2 ] ]. So in the worst case, if we open all the taps, then it’s impossible to fill the gaps i.e (0-1), (1,2), (2,3). So it’s impossible to fill the garden.
 *
 * In test case 2, the ranges of taps are as follows : [ [ -1, 1 ],[ -1, 3 ],[ 1, 3 ],[ 3, 3 ],[ 2, 6 ],[ 4, 6 ],[ 6, 6 ],[ 6, 8 ] ]. To fill the garden i.e [ 0, 7 ] , the gardener needs to open a minimum of three taps i.e. tap 2: [ -1, 3 ] , tap 5: [ 2, 6 ], tap 8: [ 6, 8 ] to fill the whole garden.
 * Sample Input 2 :
 * 2
 * 8
 * 4 0 0 0 0 0 0 0 4
 * 8
 * 4 0 0 0 4 0 0 0 4
 * Sample Output 2 :
 * 2
 * 1
 */
public class MinNumberTapOfWaterGarden {
    public int minTaps(int n, int[] ranges) {
        int[] maxReach = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxReach[left] = Math.max(maxReach[left], right);
        }
        
        int taps = 0;
        int currentEnd = 0;
        int nextEnd = 0;
        
        for (int i = 0; i <= n; i++) {
            if (i > nextEnd) {
                return -1;
            }
            
            if (i > currentEnd) {
                taps++;
                currentEnd = nextEnd;
            }
            
            nextEnd = Math.max(nextEnd, maxReach[i]);
        }
        
        return taps;
    }

    public static void main(String[] args) {
        MinNumberTapOfWaterGarden mnt = new MinNumberTapOfWaterGarden();
        
        // Test case 1: n=3, ranges=[0,0,0,0]
        int[] ranges1 = {0, 0, 0, 0};
        System.out.println("Test case 1: " + mnt.minTaps(3, ranges1));
        
        // Test case 2: n=7, ranges=[1,2,1,0,2,1,0,1]
        int[] ranges2 = {1, 2, 1, 0, 2, 1, 0, 1};
        System.out.println("Test case 2: " + mnt.minTaps(7, ranges2));
        
        // Test case 3: n=8, ranges=[4,0,0,0,0,0,0,0,4]
        int[] ranges3 = {4, 0, 0, 0, 0, 0, 0, 0, 4};
        System.out.println("Test case 3: " + mnt.minTaps(8, ranges3));
    }
}
