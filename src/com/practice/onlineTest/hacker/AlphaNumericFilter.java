package com.practice.onlineTest.hacker;

/**
 * AlphaNumericFilter - Filters non-alphanumeric characters from strings
 * 
 * Problem: Remove all characters except letters and digits from input string
 * 
 * Algorithm: Single pass character filtering using Character.isLetterOrDigit()
 * 
 * Time Complexity: O(N) where N = length of input string
 * Space Complexity: O(N) for output string builder
 */
public class AlphaNumericFilter {


    public static void main(String[] args) {

        String input = "a@b#12$c!";

        String output =
                filterAlphaNumeric(input);

        System.out.println(output);
    }

    /**
     * Filters alphanumeric characters from input string
     * 
     * @param input Input string to filter
     * @return String containing only letters and digits, null if input is null
     * 
     * Algorithm:
     * 1. Handle null input edge case
     * 2. Iterate through each character
     * 3. Use Character.isLetterOrDigit() to check validity
     * 4. Append valid characters to result
     * 
     * Time: O(N) - single pass through string
     * Space: O(N) - worst case when all characters are valid
     */
    public static String filterAlphaNumeric(
            String input) {

        if (input == null) {
            return null;
        }

        StringBuilder result =
                new StringBuilder();

        for (char ch : input.toCharArray()) {

            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }
        }

        return result.toString();
    }


}
