package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given two words START and END and a dictionary of words WORDDIC. All the words in WORDDIC are unique. START and END may or may not be present in WORDDIC.
 *
 * You can make a transition from one word to another if both the words are present in WORDDIC and both words have exactly one letter differ. Your task is to find the number of minimum distance paths from START to END using the transitions explained above.
 *
 * The distance between two words is the number of transitions used to go from one word to another.
 *
 * For example:
 *
 * START = “cow” END = “mot”
 * WORDDIC = [“cow”, “mow”, “cot”, “mot”]
 * Here answer is 2 there are two paths of distance 2 ,
 * “cow” -> “mow” -> “mot”
 * “cow” -> “cot”  -> “mot”
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 5000
 * 1 <= len(WORD) <= 10
 *
 * Where ‘N’ is the length of WORDDIC, len(WORD) is the length of START, END, and words in WORDDIC. All the words are lower case English words.
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * ab xy
 * 5
 * xb az eb ab xy
 * hat cat
 * 5
 * hat let  mat set gat
 * Sample Output 1:
 * 1
 * 0
 * Explanation of Sample Input 1:
 * For the first test case, there is only one path from “ab” to “xy”,   “ab” -> “xb” -> “xy”.
 *
 * For the second test case, END is not in WORDDIC.
 * Sample Input 2:
 * 2
 * a c
 * 7
 * a b c d e f g
 * ninjas kinjas
 * 2
 * ninjas kinjas
 * Sample Output 2:
 * 1
 * 1
 * Explanation of Sample Input 2:
 * For the first test case, there is only one path from “a” to “c”,   “a” -> “c”.
 *
 * For the second test case, there is only one word change from “ninjas” to “kinjas”.
 */
import java.util.*;

public class MinWordsDistance {
    public int minWordsDistance(String start, String end, List<String> wordDict) {
        if (!wordDict.contains(end)) {
            return 0;
        }
        
        Set<String> wordSet = new HashSet<>(wordDict);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                List<String> neighbors = getNeighbors(current, wordSet);
                
                for (String neighbor : neighbors) {
                    if (neighbor.equals(end)) {
                        return distance;
                    }
                    
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return 0;
    }
    
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != original) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    
                    if (wordSet.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            
            chars[i] = original;
        }
        
        return neighbors;
    }

    public static void main(String[] args) {
        MinWordsDistance mwd = new MinWordsDistance();
        
        // Test case 1: start="cow", end="mot", wordDict=["cow", "mow", "cot", "mot"]
        List<String> dict1 = Arrays.asList("cow", "mow", "cot", "mot");
        System.out.println("Test case 1: " + mwd.minWordsDistance("cow", "mot", dict1));
        
        // Test case 2: start="hat", end="cat", wordDict=["hat", "let", "mat", "set", "gat"]
        List<String> dict2 = Arrays.asList("hat", "let", "mat", "set", "gat");
        System.out.println("Test case 2: " + mwd.minWordsDistance("hat", "cat", dict2));
        
        // Test case 3: start="a", end="c", wordDict=["a", "b", "c", "d", "e", "f", "g"]
        List<String> dict3 = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        System.out.println("Test case 3: " + mwd.minWordsDistance("a", "c", dict3));
    }
}
