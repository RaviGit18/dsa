package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You have been given an array/list 'WORDS' of 'N' non-empty words, and an integer 'K'. Your task is to return the 'K' most frequent words sorted by their frequency from highest to lowest.
 *
 * Note:
 *
 * If two words have the same frequency then the lexicographically smallest word should come first in your answer.
 * Follow up:
 *
 * Can you solve it in O(N * logK) time and O(N) extra space?
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= N <= 10^5
 * 1 <= K <= number of unique words
 *
 * Time Limit: 1sec
 * Sample Input 1:
 * 6 2
 * i love codingninjas i love coding
 * Sample Output 1:
 * i love
 * Sample Input 2:
 * 8 3
 * the sky is blue the weather is hot
 * Sample Output 2:
 * is the blue
 * Explanation for Sample Input 2:
 * “is” and “the” are words with a frequency of 2.
 * “sky”, “blue”, “weather”, and “hot” are the words with a frequency of 1.
 *
 * The words with a frequency of 2 are the most frequent words and the lexicographically smallest word from the words with a frequency of 1 is “blue”.
 */
import java.util.*;

public class KMostFreqWords {
    public List<String> kMostFrequent(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> {
                if (a.getValue().equals(b.getValue())) {
                    return b.getKey().compareTo(a.getKey());
                }
                return a.getValue() - b.getValue();
            }
        );
        
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        KMostFreqWords k = new KMostFreqWords();
        
        // Test case 1: ["i", "love", "codingninjas", "i", "love", "coding"], k=2
        String[] words1 = {"i", "love", "codingninjas", "i", "love", "coding"};
        List<String> result1 = k.kMostFrequent(words1, 2);
        System.out.println("Test case 1: " + result1);
        
        // Test case 2: ["the", "sky", "is", "blue", "the", "weather", "is", "hot"], k=3
        String[] words2 = {"the", "sky", "is", "blue", "the", "weather", "is", "hot"};
        List<String> result2 = k.kMostFrequent(words2, 3);
        System.out.println("Test case 2: " + result2);
    }
}
