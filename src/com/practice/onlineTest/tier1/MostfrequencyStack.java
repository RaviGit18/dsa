package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Implement the given class FrequencyStack consisting of three functions :
 *
 * FrequencyStack() : Creates a new FrequencyStack.
 *
 * void push(int element) : Pushes the element onto the top of the stack.
 *
 * int pop() : Returns and remove the most frequent element in the stack. If there are multiple elements with the same maximum frequency then return the integer which is closest to the stack top.
 * You will be given ‘q’ queries consisting of push and pop operation. In each query input is of following type :
 *
 * 0 : It means we have to pop the element with maximum frequency and return this element.
 *
 * 1 ‘element’ : It means we have to push ‘element’ onto the top of the stack.
 *
 * Note: If a pop operation is used in an empty stack nothing happens to the stack, but you have to return  -1.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 50
 * 0 <= q , element <= 5000
 * 0 <= id <= 1
 *
 * where 'id' denotes the type of query which is either 0 or 1.
 *
 * Time Limit : 1 sec
 * Sample Input 1 :
 * 1
 * 7
 * 0
 * 1 3
 * 1 3
 * 1 2
 * 1 2
 * 0
 * 0
 * Sample Output 1 :
 * -1
 *  2
 *  3
 * Explanation Of Sample Input 1 :
 * For the First test case :
 *
 * Initially, stack is empty : {}
 *
 * For the first query : pop operation :
 * Since the stack is empty return -1.
 *
 * For the second query: push operation:
 * The stack is now : {3}
 *
 * For the third query: push operation:
 * The stack is now : {3,3}
 *
 * For the fourth query : push operation :
 * Stack is now : {2,3,3}
 *
 * For the fifth query : push operation :
 * The stack is now : {2,2,3,3}
 *
 * For the sixth query : pop operation :
 * Both 2 and 3 have the same frequency but 2 is
 * nearer to the top. Hence 2 is popped
 * Stack is now : {2,3,3}
 *
 * For the seventh query : pop operation :
 * 3 has a maximum frequency. Hence 3 is popped.
 * The stack is now : {2,3}
 * Sample Input 2 :
 * 1
 * 3
 * 1 1
 * 0
 * 0
 * Sample Output 2 :
 * 1
 * -1
 */
import java.util.*;

public class MostfrequencyStack {
    private Map<Integer, Integer> freqMap;
    private Map<Integer, Stack<Integer>> freqStackMap;
    private int maxFreq;

    public MostfrequencyStack() {
        freqMap = new HashMap<>();
        freqStackMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int element) {
        int freq = freqMap.getOrDefault(element, 0) + 1;
        freqMap.put(element, freq);
        maxFreq = Math.max(maxFreq, freq);
        
        freqStackMap.computeIfAbsent(freq, k -> new Stack<>()).push(element);
    }

    public int pop() {
        if (maxFreq == 0) {
            return -1;
        }
        
        Stack<Integer> stack = freqStackMap.get(maxFreq);
        int element = stack.pop();
        
        freqMap.put(element, freqMap.get(element) - 1);
        
        if (stack.isEmpty()) {
            freqStackMap.remove(maxFreq);
            maxFreq--;
        }
        
        return element;
    }

    public static void main(String[] args) {
        MostfrequencyStack fs = new MostfrequencyStack();
        
        // Test case from sample
        System.out.println("Pop (empty): " + fs.pop()); // -1
        fs.push(3);
        fs.push(3);
        fs.push(2);
        fs.push(2);
        System.out.println("Pop: " + fs.pop()); // 2
        System.out.println("Pop: " + fs.pop()); // 3
        
        // Test case 2
        MostfrequencyStack fs2 = new MostfrequencyStack();
        fs2.push(1);
        System.out.println("\nTest case 2:");
        System.out.println("Pop: " + fs2.pop()); // 1
        System.out.println("Pop (empty): " + fs2.pop()); // -1
    }
}
