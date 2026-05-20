package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Ninja is a log cutter. He has a ‘K’ number of axes and an infinite amount of logs. But, Ninja has a log cutting stand that has a capacity of ‘N’, which means he can only try to cut at max ‘N’ logs in one go. All the axes are exactly the same and can cut up to some logs in one go. If we try to cut more logs than its capacity the axe will break.
 *
 * To improve efficiency, Ninja wants to know how many logs he can cut with an axe in one go without breaking it. But, he wants to know this is the minimum number of moves and in the allotted number of axes.
 *
 * Can you calculate the minimum number of moves in which Ninja can know the limit of axes?
 *
 * Some points to notice about axes are:
 * 1. An axe that is used to cut a lesser or equal number of logs than its limit can be used again.
 *
 * 2. An axe that is used to cut more logs than its limit will be broken. Thus, it cannot be used again.
 *
 * 3. All the axes have the same limit of cutting logs until broken.
 *
 * 4. An axe may also cut N logs or may not even cut a single log.
 * Example
 *
 * Let the number of axes (K) be 2 & the capacity of the log cutting stand (N) be 6.
 *
 * From the above example, we can see that the maximum number of moves is 3 for 2 axes and a capacity of 6 logs.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= K <= 100
 * 1 <= N <= 10000
 * Sample Input 1:
 * 2
 * 2 6
 * 3 3
 * Sample Output 1:
 * 3
 * 2
 * Explanation:
 * For test case 1: Refer to the example explained above.
 *
 * For test case 2:
 * First, try to cut 2 logs:
 * If it cuts 2 logs, try to cut 3 logs.
 * If it couldn’t cut 2 logs, try to cut 1 log.
 *
 * Thus, the maximum number of moves required are 3.
 * Sample Input 2:
 * 2
 * 4 11
 * 3 8
 * Sample Output 2:
 * 4
 * 4
 */

public class CutLogs {
    public static int cutLogs(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = j;
                for (int x = 1; x < j; x++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]));
                }
            }
        }
        
        return dp[k][n];
    }
    
    public static void main(String[] args) {
        int t = 2;
        int[][] testCases = {
            {2, 6},
            {3, 3}
        };
        
        for (int i = 0; i < t; i++) {
            int k = testCases[i][0];
            int n = testCases[i][1];
            System.out.println(cutLogs(k, n));
        }
    }
}
