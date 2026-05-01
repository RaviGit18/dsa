package com.practice.pointers;

class NextLexographicSequence {

    /*Next Lexicographical Sequence
        Given a string of lowercase English letters,
        rearrange the characters to form a new string representing the next immediate sequence in lexicographical (alphabetical) order.
        If the given string is already last in lexicographical order among all possible arrangements,
        return the arrangement that's first in lexicographical order.

        Example 1:
            Input: s = 'abcd'
            Output: 'abdc'
        Explanation: "abdc" is the next sequence in lexicographical order after rearranging "abcd".

        Example 2:
            Input: s = 'dcba'
            Output: 'abcd'
        Explanation: Since "dcba" is the last sequence in lexicographical order, we return the first sequence: "abcd".

        Constraints:
            The string contains at least one character.
    */
    static void main(String[] args) {
         //String input = "abcd";
         String input = "dcba";
         String output = nextLexographicSequence(input);
         System.out.println(output);
    }

    private static String nextLexographicSequence(String input) {

        char[] letters = input.toCharArray();

        int pivot = letters.length - 2;

        while (pivot >= 0 && letters[pivot] >= letters[pivot + 1]) {
            pivot--;
        }

        if (pivot == -1) {
            reverse(letters, 0, letters.length - 1);
            return new String(letters);
        }

        int rightmostSuccessor = letters.length - 1;
        while (rightmostSuccessor >= 0 && letters[rightmostSuccessor] <= letters[pivot]) {
            rightmostSuccessor--;
        }

        swap(letters, pivot, rightmostSuccessor);

        reverse(letters, pivot+1, letters.length - 1);

        return new String(letters);
    }

    private static void reverse(char[] letters, int left, int right) {
        while (left < right) {
            swap(letters, left, right);
            left++;
            right--;
        }
    }

    private static void swap(char[] letters, int pivot, int rightmostSuccessor) {
        char temp = letters[pivot];
        letters[pivot] = letters[rightmostSuccessor];
        letters[rightmostSuccessor] = temp;
    }
}
