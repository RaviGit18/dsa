package com.practice.onlineTest.hacker;

import java.util.*;

class StringCompressor {

    /*If the requirement is:

Remove all characters whose frequency is equal to or greater than the given limit.

Then:

"abbcccb", limit = 3
a -> 1
b -> 3
c -> 3
Remove b and c
Output = "a"


"aba", limit = 2
a -> 2
b -> 1
Keep characters with frequency <= 2
Output = "aba"

Time Complexity
Frequency counting: O(N)
Building result: O(N)

Overall:

O(N)
*/
    public static void main(String[] args) {

        String s1 = "abbcccb";
        int limit1 = 3;

        System.out.println(compressString(s1, limit1));
        // Output: a

        String s2 = "aba";
        int limit2 = 3;

        System.out.println(compressString(s2, limit2));
        // Output: aba
    }

    public static String compressString(String str, int limit) {

        // Step 1: Count frequency
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Build result
        StringBuilder result = new StringBuilder();

        for (char ch : str.toCharArray()) {

            // Keep only chars whose frequency is less than limit
            if (freqMap.get(ch) < limit) {
                result.append(ch);
            }
        }

        return result.toString();
    }


}