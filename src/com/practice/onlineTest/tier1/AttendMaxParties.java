package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * There are ‘N’ parties organised and
 * you are also given a matrix ‘Party’
 * where Party[i] contains two integers the starting date and the ending date (both inclusive) of the i’th party.
 *
 * You are only allowed to attend a single party each day,
 * you are a party animal and want to attend a maximum number of different parties,
 * find the maximum parties that you can attend.
 *
 * Example :
 * If ‘N’ = 5 and ‘Party’ = { {1, 1}, {2, 2}, {1, 3}, {4, 4}, {3, 3}, }
 *
 * You can attend a maximum of 4 different parties,
 * you can attend the 1’st party on the 1’st day, 2’nd party on the 2’nd day,
 * 3’rd party on the 3’rd day and 4’th party on the 4’th day.
 * But it is impossible to attend the 5’th (last) party,
 * as if we were to attend this party then we would have to attend it instead of the 3’rd party (3’rd day),
 * there may be many other combinations possible, but no combination will result in a maximum number of different parties attend greater than four.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 ≤ T ≤ 10
 * 1 ≤ N ≤ 5000
 * 1 ≤ Party[i][0] ≤ Party[i][1] ≤ 5000
 *
 * Time limit: 1 sec
 *
 * Sample Input 1 :
 * 2
 * 5
 * 1 1
 * 2 2
 * 1 3
 * 4 4
 * 3 3
 * 2
 * 100 200
 * 300 400
 * Sample Output 1 :
 * 4
 * 2
 * Explanation For Sample Input 1 :
 * For test case 1 :
 * We will print 4 because:
 * We can attend a maximum of 4 different parties, you can attend the 1’st party on the 1’st day, 2’nd party on the 2’nd day, 3’rd party on the 3’rd day and 4’th party on the 4’th day. But it is impossible to attend the 5’th (last) party, as if we were to attend this party then we would have to attend it instead of the 3’nd party (3’rd day), there may be many other combinations possible, but no combination will result in a maximum number of different parties attend greater than four.
 *
 * For test case 2 :
 * We will print 2 because:
 * We can attend both the parties, we may attend the 1’th party on the 100’th day and the 2’nd party on the 300’th day.
 *
 * Sample Input 2 :
 * 2
 * 3
 * 1 3
 * 1 3
 * 1 3
 * 4
 * 1 3
 * 1 3
 * 1 3
 * 1 3
 * Sample Output 2 :
 * 3
 * 3
 */
/**
 * Algorithm:
 * - Sort parties by their ending date in ascending order (greedy approach)
 * - This ensures we select parties that finish earliest, leaving more room for other parties
 * - Initialize count with 1 (first party) and track the last ending time
 * - Iterate through sorted parties, selecting each party that starts after the last selected party ends
 * - Return the total count of parties that can be attended
 *
 * Time Complexity: O(N log N)
 * - N = number of parties
 * - Sorting takes O(N log N)
 * - Single pass through sorted array takes O(N)
 *
 * Space Complexity: O(1)
 * - Constant extra space (sorting may use O(log N) for recursion stack in some implementations)
 */
import java.util.Arrays;

public class AttendMaxParties {
    public static int attendMaxParties(int[][] parties) {
        if (parties == null || parties.length == 0) return 0;
        
        Arrays.sort(parties, (a, b) -> a[1] - b[1]);
        
        int count = 1;
        int lastEnd = parties[0][1];
        
        for (int i = 1; i < parties.length; i++) {
            if (parties[i][0] > lastEnd) {
                count++;
                lastEnd = parties[i][1];
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int t = 2;
        int[][][] testCases = {
            {{1, 1}, {2, 2}, {1, 3}, {4, 4}, {3, 3}},
            {{100, 200}, {300, 400}}
        };
        
        for (int i = 0; i < t; i++) {
            int[][] parties = testCases[i];
            System.out.println(attendMaxParties(parties));
        }
    }
}
