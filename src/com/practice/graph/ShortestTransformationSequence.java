package com.practice.graph;

import java.util.*;

class ShortestTransformationSequence {

    /*Shortest Transformation Sequence
Given two words, start and end, and a dictionary containing an array of words, return the length of the shortest transformation sequence to transform start to end. A transformation sequence is a series of words in which:

Each word differs from the preceding word by exactly one letter.
Each word in the sequence exists in the dictionary.
If no such transformation sequence exists, return 0.

Example:
Input: start = 'red', end = 'hit',
       dictionary = [
            'red', 'bed', 'hat', 'rod', 'rad', 'rat', 'hit', 'bad', 'bat'
       ]
Output: 5
Constraints:
All words are the same length.
All words contain only lowercase English letters.
The dictionary contains no duplicate words.
*/

    static void main(String[] args) {
        String start = "red";
        String end = "hit";
        String[] dictionary = {"red", "bed", "hat", "rod", "rad", "rat", "hit", "bad", "bat"};
        System.out.println("Shortest transformation sequence: " + shortestTransformationSequence(start, end, dictionary));
        System.out.println("Shortest transformation sequence bi-directional traversal: " + shortestTransformationSequenceBidirectionalTraversal(start, end, dictionary));
    }

    private static int shortestTransformationSequence(String start, String end, String[] dictionary) {

        Set<String> dictionarySet = new HashSet<>(Arrays.asList(dictionary));

        if (!dictionarySet.contains(start) || !dictionarySet.contains(end)) {
            return 0;
        }

        if (start.equals(end)) {
            return 1;
        }

        String lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int length = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(end)) {
                    return length + 1;
                }

                for (int j = 0; j < current.length(); j++) {

                    for (char ch : lowercaseAlphabet.toCharArray()) {
                        String next = current.substring(0, j) + ch + current.substring(j + 1);

                        if (dictionarySet.contains(next) && !visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }

            length++;
        }

        return 0;
    }

    private static int shortestTransformationSequenceBidirectionalTraversal(String start, String end, String[] dictionary) {

        Set<String> dictionarySet = new HashSet<>(Arrays.asList(dictionary));

        if (!dictionarySet.contains(start) || !dictionarySet.contains(end)) {
            return 0;
        }

        if (start.equals(end)) {
            return 1;
        }

        Queue<String> startQueue = new LinkedList<>();
        Queue<String> endQueue = new LinkedList<>();
        startQueue.offer(start);
        endQueue.offer(end);

        Set<String> startVisited = new HashSet<>();
        Set<String> endVisited = new HashSet<>();
        startVisited.add(start);
        endVisited.add(end);

        int levelStart = 0;
        int levelEnd = 0;

        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {

            levelStart++;

            if (exploreLevel(startQueue, startVisited, endVisited, dictionarySet)) {
                return levelStart + levelEnd + 1;
            }

            levelEnd++;

            if (exploreLevel(endQueue, endVisited, startVisited, dictionarySet)) {
                return levelStart + levelEnd + 1;
            }
        }

        return 0;
    }

    private static boolean exploreLevel(Queue<String> queue, Set<String> visited, Set<String> otherVisited, Set<String> dictionarySet) {

        String lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";

        int size = queue.size();

        for (int i = 0; i < size; i++) {

            String current = queue.poll();

            for (int j = 0; j < Objects.requireNonNull(current).length(); j++) {

                for (char ch : lowercaseAlphabet.toCharArray()) {

                    String next = current.substring(0, j) + ch + current.substring(j + 1);

                    if (otherVisited.contains(next)) {
                        return true;
                    }

                    if (dictionarySet.contains(next) && !visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }

        return false;
    }
}
