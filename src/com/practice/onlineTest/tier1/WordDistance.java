package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * You are given a document as an Array/List 'ARR' of words of length ‘N’. You have to perform Q queries. In each query, you are given two words. Your task is to find the smallest distance between these two words in a document and return it.
 *
 * Distance between words is defined as the difference between their indexes.
 *
 * For example:
 *
 * ARR=[‘hot’, ‘a’, ‘b’, ‘dog’] and query = (‘hot’, ‘dog’)
 *
 * The answer, in this case, is 3 as the minimum distance between ‘hot’ and ‘dog’ in the given document is 3.
 * Note:
 *
 * If any one of the words is not present in the document then your program must return ‘N’ which is the length of the document.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 5
 * 1 <= N <= 10^4
 * 1 <= Q <=100
 *
 * Where, ‘N’ and ‘Q’, are the length of the ARR, number of queries, respectively.
 *
 * All the strings in ARR contain only lowercase English letters.
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 5 2
 * a b c d a
 * a d
 * a z
 * 4 1
 * cat rat hat bat
 * cat bat
 * Sample Output 1:
 * 1
 * 5
 * 3
 * Explanation of Sample Input 1:
 * For the first test case, the minimum distance between “a” and “d” is 1,
 * ARR[5] = “a”, ARR[4]= “d” , 5-4 = 1
 *
 * For the second query answer is 5 as “z” is not present in the ARR.
 *
 * For the only query of the second test, the case answer is 3 as the minimum distance between “cat” and “bat” is 3.
 * Sample Input 2:
 * 2
 * 2 3
 * a b
 * a b
 * a b
 * a b
 * 6 1
 * ab bb cd ra wf bb
 * bb ra
 * Sample Output 2:
 * 1
 * 1
 * 1
 * 2
 */
/**
 * Algorithm:
 * - For each query, perform a single pass through the document array
 * - Track the most recent index where each of the two words was found
 * - When both words have been seen at least once, calculate the distance
 * - Keep track of the minimum distance encountered
 * - If either word is never found, return N (document length)
 *
 * Time Complexity: O(Q * N)
 * - Q = number of queries
 * - N = length of document
 * - For each query, we scan the entire document once
 *
 * Space Complexity: O(1)
 * - Only storing indices and minimum distance
 */
import java.util.*;

public class WordDistance {

    public static int findMinDistance(String[] arr, String word1, String word2) {
        int n = arr.length;
        int index1 = -1;
        int index2 = -1;
        int minDistance = n;

        for (int i = 0; i < n; i++) {
            if (arr[i].equals(word1)) {
                index1 = i;
            }
            if (arr[i].equals(word2)) {
                index2 = i;
            }

            if (index1 != -1 && index2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(index1 - index2));
            }
        }

        if (index1 == -1 || index2 == -1) {
            return n;
        }

        return minDistance;
    }

    public static void main(String[] args) {
        // Test case 1
        String[] arr1 = {"a", "b", "c", "d", "a"};
        System.out.println("Test 1: " + findMinDistance(arr1, "a", "d"));
        System.out.println("Test 2: " + findMinDistance(arr1, "a", "z"));

        // Test case 2
        String[] arr2 = {"cat", "rat", "hat", "bat"};
        System.out.println("Test 3: " + findMinDistance(arr2, "cat", "bat"));

        // Test case 3
        String[] arr3 = {"a", "b"};
        System.out.println("Test 4: " + findMinDistance(arr3, "a", "b"));
        System.out.println("Test 5: " + findMinDistance(arr3, "a", "b"));
        System.out.println("Test 6: " + findMinDistance(arr3, "a", "b"));

        // Test case 4
        String[] arr4 = {"ab", "bb", "cd", "ra", "wf", "bb"};
        System.out.println("Test 7: " + findMinDistance(arr4, "bb", "ra"));
    }
}
