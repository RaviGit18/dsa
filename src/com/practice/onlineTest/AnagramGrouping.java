package com.practice.onlineTest;

import java.util.*;

/**
 *
 * Anagram Grouping
 * Problem Statement:
 * Given an array of strings, group the strings that are anagrams of each other.
 * Two strings are anagrams if they contain the same characters in the same frequency, but possibly in a different order.
 *
 * Example Input:
 *
 * Code
 * ["eat", "tea", "tan", "ate", "nat", "bat"]
 * Expected Output:
 *
 * Code
 * [["eat","tea","ate"], ["tan","nat"], ["bat"]]
 * Constraints:
 *
 * 1 ≤ length of string ≤ 100
 *
 * All strings consist of lowercase English letters.
 */
public class AnagramGrouping {

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(input);

        // Print grouped anagrams
        for (List<String> group : result) {
            System.out.println(group);
        }

        System.out.println("\nOptimized Solution:");
        List<List<String>> result2 = groupAnagramsOptimized(input);
        for (List<String> group : result2) {
            System.out.println(group);
        }
    }

    /*
    * Explanation
    Step 1: Convert each string into a character array and sort it.

        Example: "eat" → "aet", "tea" → "aet".

    Step 2: Use the sorted string as a key in a HashMap.
    
        All anagrams share the same key.

    Step 3: Group strings under the same key.

    Step 4: Return the grouped values as a list of lists.
    * */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Map to hold sorted string as key and list of anagrams as value
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Sort characters in the string to form the key
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Add the string to the corresponding group
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        // Return all grouped anagrams
        return new ArrayList<>(map.values());
    }

    public static List<List<String>> groupAnagramsOptimized(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Frequency array for 26 lowercase letters
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Build a unique key from frequency counts
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : freq) {
                keyBuilder.append('#').append(count);
            }
            String key = keyBuilder.toString();

            // Group by frequency key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

}

