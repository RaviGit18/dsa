package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You have to cross a river, and there are several stones across the length of the river.
 * You are given an array of ‘stones’ representing the distance of each stone from your side of the river.
 * Your task is to reach from stone at position 0 to the last stone.
 * If the last jump was ‘x’ units, the next jump could be either ‘x’, ‘x + 1’ or ‘x - 1’ units.
 * The ‘stones’ array will be in ascending order.
 *
 * Note:
 * Assume the first jump to be 1 unit.
 *
 * You can jump only on a stone.
 *
 * For example:
 * You are given ‘stones’ = [0, 1, 3, 5],
 * in this array you can go from 0 -> 1 (1 unit), then from 1 -> 3 (2 units), 3 -> 5 (2 units). Hence the answer is True.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 2
 * 4
 * 0 1 3 5
 * 5
 * 0 1 3 6 7
 * Sample Output 1:
 * True
 * False
 * Explanation:
 * For the first test case, ‘stones’ = [0, 1, 3, 5],
 * in this array you can go from 0 -> 1 (1 unit), then from 1 -> 3 (2 units), 3 -> 5 (2 units). Hence the answer is True.
 *
 * For the second test, ‘stones’ = [0, 1, 3, 6, 7],
 * in this array you can go from 0 -> 1 (1 unit), then from 1 -> 3 (2 units), 3 -> 6 (3 units).
 * Then possible moves are of length 3, 4 or 2, but none of the moves can reach 7 from 6. Hence the answer is False.
 *
 * Sample Input 2:
 * 2
 * 4
 * 1 3 5 6
 * 3
 * 1 2 3
 * Sample Output 2:
 * False
 * True
 */
import java.util.*;

public class CrossRiver {
    public static boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return false;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }
        map.get(0).add(0);
        
        for (int stone : stones) {
            Set<Integer> jumps = map.get(stone);
            for (int jump : jumps) {
                for (int nextJump = jump - 1; nextJump <= jump + 1; nextJump++) {
                    if (nextJump > 0 && map.containsKey(stone + nextJump)) {
                        map.get(stone + nextJump).add(nextJump);
                    }
                }
            }
        }
        
        return map.get(stones[stones.length - 1]).size() > 0;
    }
    
    public static void main(String[] args) {
        int t = 2;
        int[][] testCases = {
            {0, 1, 3, 5},
            {0, 1, 3, 6, 7}
        };
        
        for (int i = 0; i < t; i++) {
            int[] stones = testCases[i];
            System.out.println(canCross(stones));
        }
    }
}
