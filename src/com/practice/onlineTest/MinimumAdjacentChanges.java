package com.practice.onlineTest;

class MinimumAdjacentChanges {

    /*Problem

Given a string, return the minimum number of character changes required so that:

No two adjacent characters are the same

A change means:

modifying one character into another character.
Example 1

Input:

"aab"

Change second 'a' → 'c'

Result:

"acb"

Minimum changes:

1
Example 2

Input:

"aaaa"

Possible transformation:

"abab"

Minimum changes:

2
Key Observation

Whenever:

s[i] == s[i-1]

we must change one character.

Greedy works:

change current character
skip next character to avoid unnecessary changes.
Optimal Formula

For a consecutive block of same characters:

Example:

"aaaaa"

Length:

5

Minimum changes needed: [length/2]

Dry Run

Input:

"aaaa"

Process:

| Index | String | Changes |
| ----- | ------ | ------- |
| 1     | a#aa   | 1       |
| 3     | a#a#   | 2       |

Answer:

2
Time Complexity

Single traversal:

O(N)
Space Complexity
O(N)

due to char array.

Can be optimized to:

O(1)

if only count required.
*/

    public static void main(String[] args) {

        System.out.println(
                minChanges("aab"));    // 1

        System.out.println(
                minChanges("aaaa"));   // 2

        System.out.println(
                minChanges("abba"));   // 0  -- need to check

        System.out.println(
                minChangesOptimized("aab"));    // 1

        System.out.println(
                minChangesOptimized("aaaa"));   // 2

        System.out.println(
                minChangesOptimized("abba"));   // 0
    }

    public static int minChanges(String s) {

        int changes = 0;

        for (int i = 1; i < s.length(); i++) {

            // Adjacent characters same
            if (s.charAt(i) == s.charAt(i - 1)) {

                changes++;
            }
        }

        return changes / 2;
    }

    //Space Optimized Version
    /*Why i++ Works

After changing one character in a pair:

aa

the next comparison becomes independent.

This avoids overcounting.*/
    public static int minChangesOptimized(String s) {

        int changes = 0;
        int i = 1;

        while (i < s.length()) {

            if (s.charAt(i) == s.charAt(i - 1)) {

                changes++;
                i += 2; // Skip next character
            } else {
                i++;
            }
        }

        return changes / 2;
    }
}
