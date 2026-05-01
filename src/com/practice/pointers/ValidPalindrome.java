package com.practice.pointers;

class ValidPalindrome {
    /*
    Is Palindrome Valid
        A palindrome is a sequence of characters that reads the same forward and backward.

        Given a string, determine if it's a palindrome after removing all non-alphanumeric characters.
        A character is alphanumeric if it's either a letter or a number.

        Example 1:
            Input: s = 'a dog! a panic in a pagoda.'
            Output: True
        Example 2:
            Input: s = 'abc123'
            Output: False
        Constraints:
            The string may include a combination of lowercase English letters, numbers, spaces, and punctuations.
    */

    static void main(String[] args) {
        //String s = "a dog! a panic in a pagoda.";
        String s = "abc123";
        System.out.println(isPalindrome(s));
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }

            if (s.charAt(left) != s.charAt(right)) {
                return  false;
            }

            left++;
            right--;
        }

        return  true;
    }
}
