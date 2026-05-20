package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Given a starting point (startX, startY) and target point(endX, endY) .
 * Return true if there is a sequence of moves to transform the starting point into the ending point.
 * In one move you can transform (x, y) to either (x + y, y) or (x, x + y).
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints
 * 1 <= ’T’ <= 50
 * 1 <= ’startX’, ’startY’, ’targetX’, ’targetY’ <= 10^9
 * ‘startX’, ’startY’ denotes starting coordinates.
 * ‘endX’, ’endY’ denotes target  coordinates.
 *
 * Time Limit: 1 sec
 * Sample Input 1
 * 2
 * 1 1 5 8
 * 3 5 7 9
 * Sample Output 1:
 * true
 * false
 * Explanation For Sample Input 1:
 * For the first test case, the sequence of moves
 * (1,1)->(2,1)
 * (2,1)->(2,3)
 * (2,3)->(5,3)
 * (5,3)->(5,8)
 * Hence the answer for this case is true.
 *
 * For the second test case, there is not a possible sequence to reach the final points.
 * Sample Input 2
 * 2
 * 1 1 1 1000
 * 1 1 1000 1
 * Sample Output 2
 * true
 * true
 */
public class ReachingPoints {

    static void main(String[] args) {
        System.out.println(isReachable(1, 1, 5, 8)); //true
        System.out.println(isReachable(3, 5, 7, 9)); // false
    }

    public static boolean isReachable(int startX, int startY, int endX, int endY) {
        // Write your code here.
		/*if (startX > endX || startY > endY) {
			return false;
		}
		if (startX == endX && startY == endY) {
			return true;
		}

		int newStart =  startX + startY;

		return isReachable(newStart, startY, endX, endY) || isReachable(startX, newStart, endX, endY);
		*/

        while (endX > startX && endY > startY) {

            if (endX > endY) {
                endX %= endY;
            } else {
                endY %= endX;
            }
        }

        if (endX == startX && endY >= startY) {
            return (endY - startY) % startX == 0;
        }

        if (endY == startY && endX >= startX) {
            return (endX - startX) % startY == 0;
        }

        return false;
    }
}
