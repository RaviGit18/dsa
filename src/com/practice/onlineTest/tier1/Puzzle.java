package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Martha is a very bright student. She loves solving high-level puzzles. She has a list of ‘N’ puzzles. Each puzzle has some difficulty level. There is a rule that one can only solve a puzzle with difficulty ‘X’ if she has already solved all the puzzles with difficulty less than ‘X’. She can’t wait to get a puzzle having a difficulty level higher than the current puzzle.
 *
 * Your task is to tell Martha how long she has to wait to get a puzzle having a higher difficulty level than the current puzzle. If there is no puzzle ahead with a higher difficulty level, just print "0".
 *
 * For Example :
 * Let ‘N’ = 5 and ‘PUZZLE’ = [ 30, 40, 80, 50, 70 ]
 *
 * After solving the first puzzle, the very next puzzle has a difficulty level 40 and 40 > 30.
 * Then after 40, the very next puzzle has a difficulty level 80 and 80 > 40.
 * But for 80, there is no puzzle having a difficulty level greater than 80.
 * For 50, the very next puzzle has a difficulty level 70 and 70 > 50.
 * Again for 70, there is no puzzle having a difficulty level greater than 70.
 *
 * So the output will be [1, 1, 0, 1, 0].
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= ‘T’ <= 50
 * 1 <= ‘N’ <= 10^4
 * 30 <= ‘PUZZLE[i]’ <= 100
 * Where, 'PUZZLE[i]' represents the difficulty level of puzzle 'i'.
 *
 * Time limit: 1sec
 * Sample Input 1 :
 * 2
 * 8
 * 31 56 30 33 32 90 60 54
 * 1
 * 50
 * Sample Output 1 :
 * 1 4 1 2 1 0 0 0
 * 0
 * Explanation For Sample Input 1 :
 * The first test case for 31 next increasing difficulty = 56 (index diff = 1).
 * For 56 next increasing difficulty = 90 (index diff = 4).
 * Similarly, check for 30,33, and 32.
 * For, 90,60, and 54 there are no greater elements.
 *
 * In the second test case, there is only one puzzle, so the answer will be zero.
 * Sample Input 2 :
 * 2
 * 5
 * 90 80 70 60 50
 * 5
 * 50 60 70 80 90
 * Sample Output 2 :
 * 0 0 0 0 0
 * 1 1 1 1 0
 */
/**
 * Algorithm:
 * - For each element in the array, find the next greater element to its right
 * - Use a stack to keep track of elements in decreasing order
 * - Iterate from right to left
 * - For each element, pop elements from stack that are smaller or equal
 * - The top of stack is the next greater element (if exists)
 * - Push current element onto stack
 * - If stack is empty after popping, no greater element exists (result is 0)
 *
 * Time Complexity: O(N)
 * - N = length of array
 * - Each element is pushed and popped at most once
 *
 * Space Complexity: O(N)
 * - For the stack
 */
import java.util.*;

public class Puzzle {

    public static int[] findNextGreater(int[] puzzle) {
        int n = puzzle.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= puzzle[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek();
            }

            stack.push(puzzle[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] puzzle1 = {31, 56, 30, 33, 32, 90, 60, 54};
        int[] result1 = findNextGreater(puzzle1);
        System.out.print("Test 1: ");
        for (int r : result1) {
            System.out.print(r + " ");
        }
        System.out.println();

        int[] puzzle2 = {50};
        int[] result2 = findNextGreater(puzzle2);
        System.out.print("Test 2: ");
        for (int r : result2) {
            System.out.print(r + " ");
        }
        System.out.println();

        int[] puzzle3 = {90, 80, 70, 60, 50};
        int[] result3 = findNextGreater(puzzle3);
        System.out.print("Test 3: ");
        for (int r : result3) {
            System.out.print(r + " ");
        }
        System.out.println();

        int[] puzzle4 = {50, 60, 70, 80, 90};
        int[] result4 = findNextGreater(puzzle4);
        System.out.print("Test 4: ");
        for (int r : result4) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}
