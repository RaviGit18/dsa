package com.practice.LL;

class HappyNo {

    /*Happy Number
    In number theory, a happy number is defined as a number that, when repeatedly subjected to the process of squaring its digits and summing those squares, eventually leads to 1. An unhappy number will never reach 1 during this process, and will get stuck in an infinite loop [1].

    Given an integer, determine if it's a happy number.

    Example:
    Input: n = 23
    Output: True

    Explanation: 2 ^ 2  + 3 ^ 3  = 13 ⇒ 1 ^ 1  + 3 ^ 3  = 10 ⇒ 1 ^ 1  + 0 ^ 0  = 1
    */
    static void main() {

        System.out.println("is 23 a happy number: "+ isHappyNumber(23));
    }

    private static boolean isHappyNumber(int n) {

        int slow = n;
        int fast = n;

        while (true) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));

            if (fast == 1) {
                return true;
            }

            if (slow == fast) {
                return false;
            }
        }

    }

    private static int getNext(int n) {
        int nextNum = 0;

        while (n > 0) {
            int digit = n % 10;

            n = n / 10;

            nextNum += digit * digit;
        }

        return nextNum;
    }
}
