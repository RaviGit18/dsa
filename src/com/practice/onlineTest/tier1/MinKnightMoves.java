package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given an infinite chessboard (ie: the x-coordinates and y-coordinates can be anything between -infinity to +infinity).
 *
 * You have a knight placed at coordinates ‘(0, 0)’. Find the minimum number of steps needed to move the knight to ‘(X, Y)’.
 *
 * The knight has 8 possible moves, each move is two units in a cardinal direction, then one unit in an orthogonal direction.
 *
 * For example :
 *
 * As depicted in the photo below, the knight currently at (0, 0) can move to any of the 8 positions: (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2), (-2, -1), (-2, 1), (-1, 2).
 *
 * Example :
 * If X = 1 and Y = -1, then we need to find out the minimum number of steps to move the knight from (0, 0) to (1, -1).
 *
 * We need at least 2 steps to move the knight to the desired position.
 *
 * First move: (0, 0) -> (2, 1)
 *
 * Second move: (2,1) -> (1, -1)
 *
 * Here we can see that there are many ways, but we need at least 2 steps. Therefore we will return the value 2.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * -100 <= X, Y <= 100
 *
 * Time limit: 1 sec
 * Sample Input 1 :
 * 2
 * 1 1
 * 1 0
 * Sample Output 1 :
 * 2
 * 3
 * Explanation For Sample Input 1 :
 * For test case 1 :
 * (0, 0)  to (2, -1) to (1,1), therefore 2 steps are required. The other possible way is (0, 0) to (-1, 2) to (1, 1), but we require at least 2 steps to move from (0,0) to (1,-1).
 *
 * Hence return value 2. Refer the image for better understanding:
 *
 * For test case 2 :
 * (0, 0) to (2, 1) to (0, 2) to (1, 0), therefore 3 steps are required. Refer the image for better understanding:
 *
 * Sample Input 2 :
 * 2
 * 12 5
 * 5 12
 * Sample Output 2 :
 * 7
 * 7
 */
import java.util.*;

public class MinKnightMoves {
    private static final int[][] MOVES = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        
        if (x == 0 && y == 0) return 0;
        if (x == 1 && y == 0) return 3;
        if (x == 2 && y == 2) return 4;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                
                for (int[] move : MOVES) {
                    int newX = current[0] + move[0];
                    int newY = current[1] + move[1];
                    String key = newX + "," + newY;
                    
                    if (newX == x && newY == y) {
                        return steps;
                    }
                    
                    if (!visited.contains(key) && newX >= -2 && newY >= -2) {
                        visited.add(key);
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        MinKnightMoves mkm = new MinKnightMoves();
        
        // Test case 1: (1, 1)
        System.out.println("Test case 1 (1,1): " + mkm.minKnightMoves(1, 1));
        
        // Test case 2: (1, 0)
        System.out.println("Test case 2 (1,0): " + mkm.minKnightMoves(1, 0));
        
        // Test case 3: (2, 1)
        System.out.println("Test case 3 (2,1): " + mkm.minKnightMoves(2, 1));
        
        // Test case 4: (5, 5)
        System.out.println("Test case 4 (5,5): " + mkm.minKnightMoves(5, 5));
    }
}
