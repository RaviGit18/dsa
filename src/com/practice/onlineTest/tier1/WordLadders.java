package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given two strings BEGIN and END and an array of strings DICT. Your task is to find the length of the shortest transformation sequence from BEGIN to END such that in every transformation you can change exactly one alphabet and the word formed after each transformation must exist in DICT.
 *
 * Note:
 *
 * 1. If there is no possible path to change BEGIN to END then just return -1.
 * 2. All the words have the same length and contain only lowercase english alphabets.
 * 3. The beginning word i.e. BEGIN will always be different from the end word i.e. END (BEGIN != END).
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 5
 * 1 <= N<= 10^2
 * 1 <= |S| <= 10^2
 *
 * Where ‘T’ is the total number of test cases, ‘N’ denotes the length of the DICT array and |S| represents the length of each string.
 */
/**
 * Algorithm:
 * - Use Breadth First Search (BFS) to find the shortest path from BEGIN to END
 * - Start BFS from BEGIN word, exploring all possible one-character transformations
 * - For each word, generate all possible words by changing each character to 'a'-'z'
 * - Check if the transformed word exists in the dictionary and hasn't been visited
 * - Track visited words to avoid cycles
 * - Return the number of transformations (edges) when END is reached
 * - Return -1 if queue is exhausted without reaching END
 *
 * Time Complexity: O(N * M * 26)
 * - N = number of words in dictionary
 * - M = length of each word
 * - For each word, we try 26 characters at each position
 *
 * Space Complexity: O(N * M)
 * - For the dictionary set and visited set
 * - For the BFS queue
 */
import java.util.*;

public class WordLadders {

    public static int findLadderLength(String begin, String end, String[] dict) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(dict));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(begin);
        visited.add(begin);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(end)) {
                    return level;
                }

                char[] chars = current.toCharArray();
                for (int pos = 0; pos < chars.length; pos++) {
                    char original = chars[pos];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;

                        chars[pos] = c;
                        String transformed = new String(chars);

                        if (wordSet.contains(transformed) && !visited.contains(transformed)) {
                            visited.add(transformed);
                            queue.offer(transformed);
                        }
                    }

                    chars[pos] = original;
                }
            }

            level++;
        }

        return -1;
    }

    public static void main(String[] args) {
        String begin1 = "hit";
        String end1 = "cog";
        String[] dict1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println("Test 1: " + findLadderLength(begin1, end1, dict1));

        String begin2 = "hit";
        String end2 = "cog";
        String[] dict2 = {"hot", "dot", "dog", "lot", "log"};
        System.out.println("Test 2: " + findLadderLength(begin2, end2, dict2));

        String begin3 = "talk";
        String end3 = "walk";
        String[] dict3 = {"walk", "talks", "walks", "talk"};
        System.out.println("Test 3: " + findLadderLength(begin3, end3, dict3));
    }
}
