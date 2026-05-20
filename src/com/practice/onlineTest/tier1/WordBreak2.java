package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a non-empty string S containing no spaces’ and a dictionary of non-empty strings (say the list of words). You are supposed to construct and return all possible sentences after adding spaces in the originally given string ‘S’, such that each word in a sentence exists in the given dictionary.
 *
 * Note :
 *
 * The same word in the dictionary can be used multiple times to make sentences.
 * Assume that the dictionary does not contain duplicate words.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 1 <= K <= 100
 * 1 <= | word | <= 16
 * 1 <= | S | <= 13
 *
 * where |word| is the length of each word in the dictionary and |S| is the length of the string S.
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 1
 * 6
 * god is now no where here
 * godisnowherenowhere
 * Sample Output 1:
 * god is no where no where
 * god is no where now here
 * god is now here no where
 * god is now here now here
 * Explanation to Sample Input 1:
 * One way to make sentences is to take “god” and append a space, then take “is”  and append space, take “now” from the dictionary and take “here” as well.
 * Similarly, for other sentences also, we can add space to get other possible sentences. Note that we can reuse dictionary words as “no” and “now” are used two times in the same sentence.
 * Sample Input 2:
 * 1
 * 4
 * god is no here
 * godisnowhere
 * Sample Output 2:
 * No output to be printed
 * Explanation to Sample Input 2:
 * We can not make any sentence because after making “god is no” we will be stuck with “where”. There is no way to break “where” further such that we can get any word from the dictionary.
 */
/**
 * Algorithm:
 * - Use backtracking with memoization to find all possible sentences
 * - For each position in the string, try all possible word endings from the dictionary
 * - If a word matches, recursively try to break the remaining substring
 * - Use memoization to avoid recomputing the same subproblems
 * - Build sentences by joining words with spaces
 * - Return all valid sentences
 *
 * Time Complexity: O(N * 2^N)
 * - N = length of string
 * - In worst case, we might explore all possible partitions
 *
 * Space Complexity: O(N * 2^N)
 * - For storing all possible sentences in memoization
 */
import java.util.*;

public class WordBreak2 {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, dict, memo);
    }

    private static List<String> dfs(String s, int start, Set<String> dict, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> result = new ArrayList<>();

        if (start == s.length()) {
            result.add("");
            return result;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (dict.contains(word)) {
                List<String> subSentences = dfs(s, end, dict, memo);
                for (String sub : subSentences) {
                    if (sub.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + sub);
                    }
                }
            }
        }

        memo.put(start, result);
        return result;
    }

    public static void main(String[] args) {
        List<String> dict1 = Arrays.asList("god", "is", "now", "no", "where", "here");
        String s1 = "godisnowherenowhere";
        List<String> result1 = wordBreak(s1, dict1);
        System.out.println("Test 1:");
        for (String sentence : result1) {
            System.out.println(sentence);
        }

        List<String> dict2 = Arrays.asList("god", "is", "no", "here");
        String s2 = "godisnowhere";
        List<String> result2 = wordBreak(s2, dict2);
        System.out.println("Test 2:");
        if (result2.isEmpty()) {
            System.out.println("No output to be printed");
        } else {
            for (String sentence : result2) {
                System.out.println(sentence);
            }
        }
    }
}
