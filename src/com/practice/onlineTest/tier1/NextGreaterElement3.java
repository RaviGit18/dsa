package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Alice and Bob were given homework to bring a number. As usual, Bob forgot to do it and asked Alice if he could copy her’s. Alice agreed to help him only if he rearranged the digits of her number such that the new number was strictly greater than the old one. Also, the teacher does not like big numbers, so Bob needs to make sure his number is as small as possible.
 *
 * Help Bob rearrange the digits of Alice’s number to make a number greater than that of Alice and is smallest possible or print -1 when no such number exists.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 2 <= N <= 2 * (10^9)
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 225
 * 66
 * Sample Output 1:
 * 252
 * -1
 * Explanation for Sample Input 1:
 * In the first test case, 252 is the smallest possible number that can be made by rearranging the digits of 225 and is greater than 225. Another possible arrangement is 522, but it’s not the smallest possible.
 *
 * In the second test case,  66 can not be rearranged to make any other number.
 * Sample Input 2:
 * 2
 * 342
 * 39
 * Sample Output 2:
 * 423
 * 93
 */
/**
 * Algorithm:
 * - Convert the number to a character array
 * - Find the first position from right where a digit is smaller than the digit to its right
 * - If no such position exists, return -1 (number is in descending order)
 * - Find the smallest digit to the right of this position that is greater than the digit at this position
 * - Swap these two digits
 * - Sort the digits after the swap position in ascending order to get the smallest possible number
 * - Convert back to integer and check if it fits in 32-bit integer range
 * - Return -1 if the result overflows
 *
 * Time Complexity: O(N log N)
 * - N = number of digits
 * - Sorting takes O(N log N)
 *
 * Space Complexity: O(N)
 * - For the character array
 */
public class NextGreaterElement3 {

    public static int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int i = digits.length - 2;

        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        int j = digits.length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        swap(digits, i, j);

        reverse(digits, i + 1, digits.length - 1);

        try {
            long result = Long.parseLong(new String(digits));
            if (result > Integer.MAX_VALUE) {
                return -1;
            }
            return (int) result;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + nextGreaterElement(225));
        System.out.println("Test 2: " + nextGreaterElement(66));
        System.out.println("Test 3: " + nextGreaterElement(342));
        System.out.println("Test 4: " + nextGreaterElement(39));
        System.out.println("Test 5: " + nextGreaterElement(12));
        System.out.println("Test 6: " + nextGreaterElement(21));
    }
}
