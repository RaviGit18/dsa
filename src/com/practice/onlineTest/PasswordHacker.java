package com.practice.onlineTest;

import java.util.*;

/**
 *
 * java program for Password Hacker:
 * Use recursion and memoization to determine if a string can be formed by a set of words
 */
public class PasswordHacker {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("abra", "ka", "dabra");
        String password = "abrakadabra";
        System.out.println(solve(password, words)); // Output: abra ka dabra
    }

    /*
    * This is a classic variation of the Word Break problem.
    * the interviewer isn't just looking for the answer;
    * they want to see memoization to avoid \(O(2^n)\) exponential time and clean, readable code.
    * Java Solution:
    * Top-Down Recursion with MemoizationWe use a HashSet for \(O(1)\) lookups
    * and a Boolean array to store results of sub-problems we've already solved.
    *
    * Talking Points
    * Time Complexity: \(O(n^2)\) due to the two nested states (start and end) and string slicing.
    * Without memoization, it's \(O(2^n)\).
    * Space Complexity: \(O(n)\) for the HashMap and recursion stack depth.
    * Trie Optimization: If the wordSet is massive, mention that using a Trie (Prefix Tree) is more efficient than substring() calls because you can stop searching a branch as soon as the prefix doesn't exist in the Trie.
    * Immutability: Note that String in Java is immutable.
    * In a high-performance system, frequent substring() calls create many short-lived objects.
    * A StringBuilder or index-based comparison is preferred for optimization.
    * */
    public static String solve(String password, List<String> words) {
        Set<String> wordSet = new HashSet<>(words);
        // Map to store if a suffix starting at index 'start' can be cracked
        Map<Integer, String> memo = new HashMap<>();

        String result = crack(password, wordSet, 0, memo);
        return result == null ? "WRONG PASSWORD" : result.trim();
    }

    private static String crack(String pwd, Set<String> wordSet, int start, Map<Integer, String> memo) {
        // Base case: we reached the end of the password
        if (start == pwd.length()) return "";

        // Return cached result if we've seen this index before
        if (memo.containsKey(start)) return memo.get(start);

        for (int end = start + 1; end <= pwd.length(); end++) {
            String prefix = pwd.substring(start, end);

            if (wordSet.contains(prefix)) {
                String suffixResult = crack(pwd, wordSet, end, memo);

                if (suffixResult != null) {
                    // Path found! Store and return it
                    String currentPath = prefix + " " + suffixResult;
                    memo.put(start, currentPath);
                    return currentPath;
                }
            }
        }

        // Mark this index as impossible
        memo.put(start, null);
        return null;
    }


}
