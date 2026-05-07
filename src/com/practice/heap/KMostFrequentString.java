package com.practice.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class KMostFrequentString {

    /*K Most Frequent Strings
Find the k most frequently occurring strings in an array, and return them sorted by frequency in descending order.
If two strings have the same frequency, sort them in lexicographical order.

Example:
Input: strs = ['go', 'coding', 'byte', 'byte', 'go', 'interview', 'go'], k = 2
Output: ['go', 'byte']
Explanation: The strings "go" and "byte" appear the most frequently, with frequencies of 3 and 2, respectively.

Constraints:
k ≤ n, where n denotes the length of the array.
*/
    static void main(String[] args) {
        String[] strs = {"go", "coding", "byte", "byte", "go", "interview", "go"};
        int k = 2;
        String[] output = kMostFrequentStringBruteForce(strs, k);
        System.out.println("K Most Frequent Strings: " + Arrays.toString(output));
        output = kMostFrequentString(strs, k);
        System.out.println("K Most Frequent Strings: " + Arrays.toString(output));
    }

    private static String[] kMostFrequentString(String[] strs, int k) {
        String[] result = new String[k];
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> {
            if (a.count != b.count) {
                return b.count - a.count;
            } else {
                return a.str.compareTo(b.str);
            }
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            maxHeap.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            result[i] = maxHeap.poll().str;
        }

        return result;
    }

    private static String[] kMostFrequentStringBruteForce(String[] strs, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}

class Pair {
    String str;
    int count;

    public Pair(String str, int count) {
        this.str = str;
        this.count = count;
    }
}