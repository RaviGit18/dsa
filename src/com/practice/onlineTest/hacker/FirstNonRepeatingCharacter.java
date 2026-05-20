package com.practice.onlineTest.hacker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class FirstNonRepeatingCharacter {

    /*Find First Non-Repeating Character in String
Example

Input:

"swiss"

Character frequencies:

s -> 3
w -> 1
i -> 1

First non-repeating character:

w
Best Approach — HashMap + Maintain Order

We:

Count frequency of each character
Traverse string again
Return first char with frequency 1

*/

    public static void main(String[] args) {

        String input = "swiss";

        Character result =
                firstNonRepeating(input);

        if (result != null) {

            System.out.println(
                    "First non-repeating character = "
                            + result);
        }
        else {

            System.out.println(
                    "No non-repeating character found");
        }


        char result1 = firstNonRepeatingOptimized(input);

        if (result1 != '\0') {

            System.out.println(
                    "First non-repeating character = "
                            + result1);
        }

        firstNonRepeatingUsingStream(input);
    }

    /*Time Complexity
O(N)
Space Complexity
O(K)

Where:

K = unique characters*/
    public static Character firstNonRepeating(String str) {

        Map<Character, Integer> freqMap =
                new LinkedHashMap<>();

        // Count frequency
        for (char ch : str.toCharArray()) {

            freqMap.put(ch,
                    freqMap.getOrDefault(ch, 0) + 1);
        }

        // Find first non-repeating char
        for (char ch : str.toCharArray()) {

            if (freqMap.get(ch) == 1) {
                return ch;
            }
        }

        return null;
    }


    public static char firstNonRepeatingOptimized(String str) {

        int[] freq = new int[26];

        // Count frequencies
        for (char ch : str.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Find first non-repeating
        for (char ch : str.toCharArray()) {

            if (freq[ch - 'a'] == 1) {
                return ch;
            }
        }

        return '\0';
    }

    public static void firstNonRepeatingUsingStream(String str) {
        Character result =
                str.chars()
                        .mapToObj(c -> (char)c)
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

        System.out.println(result);
    }

}