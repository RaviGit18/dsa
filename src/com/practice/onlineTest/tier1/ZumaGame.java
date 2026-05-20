package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You have several balls on the table in the form of a string named 'BOARD'. The colors of the balls can be red(R), blue(B), green(G), white(W), and yellow(Y). You also have several balls in your hand in the form of a string named 'hand'. Each time, you can do the following operations:
 *
 * 1. Choose a ball from the string 'HAND', and insert it anywhere on the string 'BOARD'.
 *
 * 2. If there is a group of strictly more than 2 balls of the same color touching each other, remove them from the string 'BOARD'. Keep doing this until the string 'board' becomes empty or no more balls can satisfy this condition.
 * Your task is to find the minimum number of insertions required to make the 'BOARD' empty. If it is not possible to make the string 'BOARD' empty, then print -1.
 *
 * Note:
 *
 * 1) Both strings will be non-empty and will only contain the characters ‘R’, ‘B’, ‘G’, ‘W’, and ‘Y’.
 *
 * 2) Initially, the string 'BOARD' won’t have more than 2 balls of the same colors touching each other.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= |BOARD| <= 10
 * 1 <= |HAND| <= 4
 *
 * Where ‘T’ denotes the number of test cases.
 *
 * Time Limit: 1 sec.
 * Sample Input 1:
 * 1
 * YYRR
 * YR
 * Sample Output 1:
 * 2
 * Explanation for sample input 1:
 * One of the possible order of insertions: YYRR -> YY[Y]RR -> RR -> RR[R] -> empty. You required 2 insertions. There is no possible solution with only one insertion.
 * Sample Input 2:
 * 1
 * RR
 * YG
 * Sample Output 2:
 * -1
 * Explanation for sample input 2:
 * It is not possible to make the string board empty.
 *
 * Algorithm:
 * - Use Depth First Search (DFS) with backtracking to explore all possible insertion strategies
 * - First, try to complete existing consecutive groups (e.g., if 2 balls of same color are together, add 1 more to make 3)
 * - If no direct completion possible, try inserting each available ball at every position on the board
 * - After each insertion, recursively remove all groups of 3+ consecutive balls
 * - Track the minimum number of insertions needed to clear the board
 * - Return -1 if no valid sequence exists
 *
 * Time Complexity: O((|HAND| * |BOARD|)^(|HAND| + |BOARD|))
 * - In worst case, we try inserting each ball at each position recursively
 * - The branching factor is O(|HAND| * |BOARD|)
 * - Maximum depth is O(|HAND| + |BOARD|)
 *
 * Space Complexity: O(|HAND| + |BOARD|)
 * - For the hand count array (26 characters)
 * - For the recursion stack depth
 * - For string manipulations during board state transitions
 */
import java.util.*;

public class ZumaGame {

    public static void main(String[] args) {
        // Test case 1: Sample Input 1
        String board1 = "YYRR";
        String hand1 = "YR";
        System.out.println("Test 1: " + findMinInsertions(board1, hand1)); // Expected: 2

        // Test case 2: Sample Input 2
        String board2 = "RR";
        String hand2 = "YG";
        System.out.println("Test 2: " + findMinInsertions(board2, hand2)); // Expected: -1

        // Additional test cases
        String board3 = "WRRBBW";
        String hand3 = "RB";
        System.out.println("Test 3: " + findMinInsertions(board3, hand3));

        String board4 = "WWRRBBWW";
        String hand4 = "WRBRW";
        System.out.println("Test 4: " + findMinInsertions(board4, hand4));
    }

    public static int findMinInsertions(String board, String hand) {
        int[] handCount = new int[26];
        for (char c : hand.toCharArray()) {
            handCount[c - 'A']++;
        }
        int result = dfs(board, handCount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int dfs(String board, int[] handCount) {
        if (board.isEmpty()) {
            return 0;
        }

        int minInsertions = Integer.MAX_VALUE;

        // Try to find consecutive groups
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                j++;
            }
            int need = 3 - (j - i); // balls needed to remove this group

            if (need > 0 && handCount[board.charAt(i) - 'A'] >= need) {
                // Use 'need' balls from hand
                handCount[board.charAt(i) - 'A'] -= need;
                String newBoard = board.substring(0, i) + board.substring(j);
                newBoard = removeConsecutive(newBoard);
                int subResult = dfs(newBoard, handCount);
                if (subResult != Integer.MAX_VALUE) {
                    minInsertions = Math.min(minInsertions, subResult + need);
                }
                handCount[board.charAt(i) - 'A'] += need;
            }
            i = j;
        }

        // If no group can be removed directly, try inserting each available ball at each position
        if (minInsertions == Integer.MAX_VALUE) {
            for (int c = 0; c < 26; c++) {
                if (handCount[c] > 0) {
                    char ball = (char) ('A' + c);
                    handCount[c]--;
                    // Try inserting at each position
                    for (int pos = 0; pos <= board.length(); pos++) {
                        String newBoard = board.substring(0, pos) + ball + board.substring(pos);
                        newBoard = removeConsecutive(newBoard);
                        int subResult = dfs(newBoard, handCount);
                        if (subResult != Integer.MAX_VALUE) {
                            minInsertions = Math.min(minInsertions, subResult + 1);
                        }
                    }
                    handCount[c]++;
                }
            }
        }

        return minInsertions;
    }

    private static String removeConsecutive(String board) {
        boolean changed;
        do {
            changed = false;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < board.length()) {
                int j = i;
                while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                    j++;
                }
                if (j - i >= 3) {
                    changed = true;
                } else {
                    sb.append(board.substring(i, j));
                }
                i = j;
            }
            board = sb.toString();
        } while (changed);
        return board;
    }


}
