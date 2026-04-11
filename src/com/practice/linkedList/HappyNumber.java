package com.practice.linkedList;

/*Happy Number
In number theory, a happy number is defined as a number that, when repeatedly subjected to the process of squaring its digits and summing those squares, eventually leads to 1. An unhappy number will never reach 1 during this process, and will get stuck in an infinite loop [1].

Given an integer, determine if it's a happy number.

Example:
Input: n = 23
Output: True
Explanation: 2 ^ 2  + 3 ^ 3  = 13 ⇒ 1 ^ 1  + 3 ^ 3  = 10 ⇒ 1 ^ 1  + 0 ^ 0  = 1
*/
public class HappyNumber {
    static void main() {
        System.out.println(isHappy(23));
    }

    static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        while (true) {
            slow = getNextNum(slow);
            fast = getNextNum(getNextNum(fast));
            if (fast == 1) {
                return true;
            }
            // If the fast and slow pointers meet, a cycle is detected. Hence, 'n' is not
            // a happy number.
            if (fast == slow) {
                return false;
            }
        }
    }

    private static int getNextNum(int x) {
        int next_num = 0;
        while (x > 0) {
            // Extract the last digit of 'x'.
            int digit = x % 10;
            // Truncate (remove) the last digit from 'x' using floor division.
            x /= 10;
            // Add the square of the extracted digit to the sum.
            next_num += digit * digit;
        }
        return next_num;
    }
}
