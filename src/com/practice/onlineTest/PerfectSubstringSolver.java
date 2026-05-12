package com.practice.onlineTest;

import java.util.*;

/**
 * java program for Perfect Substrings:
 * Count the number of substrings in a given string where every character appears exactly k times.
 */
public class PerfectSubstringSolver {

    public static void main(String[] args) {
        String s = "1102021222";
        int k = 2;
        System.out.println("Perfect Substrings: " + countPerfectSubstrings(s, k));
        // Example Output for "aabbcc", k=2: 6 (aa, bb, cc, aabb, bbcc, aabbcc)
    }

    /*
    * To solve the Perfect Substrings problem, you must find substrings where every distinct character present appears exactly \(k\) times. For a lead-level role, while a naive \(O(n^2)\) approach is often the starting point, optimizing or clearly explaining the character frequency tracking is key.
    * Java Solution: Optimized Frequency TrackingThis approach iterates through all possible starting points and expands the substring, maintaining a frequency count for each.
    * Technical Breakdown for Senior InterviewsTime Complexity: \(O(n^2 \cdot m)\), where \(n\) is the string length and \(m\) is the alphabet size (e.g., 10 for digits, 26 for letters). The check function runs in constant time relative to the alphabet.Space Complexity: \(O(1)\) (or \(O(m)\)) since the frequency array size is fixed regardless of the input string length.Optimization Tip: For very large strings, mention that the maximum possible length of a "Perfect Substring" is \(m \times k\). This allows you to limit the inner loop, potentially bringing the complexity closer to \(O(n \cdot m)\).Lead-Level Discussion: Be prepared to discuss how to handle Unicode characters (using a HashMap instead of a fixed array) or how to parallelize the search for independent starting indices using Java Streams.
    * */
    public static int countPerfectSubstrings(String s, int k) {
        int result = 0;
        int n = s.length();

        // Iterate through each starting position
        for (int i = 0; i < n; i++) {
            int[] freq = new int[10]; // Assuming numeric string; use 26 for lowercase 'a'-'z'

            // Expand the substring from the starting point
            for (int j = i; j < n; j++) {
                int charIdx = s.charAt(j) - '0';
                freq[charIdx]++;

                // Optimization: if any char exceeds k, no more perfect substrings from this 'i'
                if (freq[charIdx] > k) break;

                // Check if all characters currently in the window have frequency exactly k
                if (check(freq, k)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean check(int[] freq, int k) {
        for (int count : freq) {
            // A character must have frequency 0 (not present) or exactly k
            if (count != 0 && count != k) {
                return false;
            }
        }
        return true;
    }


}
