package com.practice.LL;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class DetectLoopLL {
    static void main() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(2);
        linkedList.add(5);

        // Method 1: Direct print
        System.out.println("LinkedList: " + linkedList);
        
        // Method 2: Using for-each loop
        System.out.print("Elements: ");
        for (Integer element : linkedList) {
            System.out.print(element + " ");
        }
        System.out.println();
        
        // Method 3: Using traditional for loop
        System.out.print("Using index: ");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i) + " ");
        }
        System.out.println();

        System.out.println("Is loop detected: " + detectLoopBruteForce(linkedList));
    }

    private static boolean detectLoopBruteForce(LinkedList<Integer> linkedList) {
        Set<Integer> set = new HashSet<>();

        for (int val : linkedList) {
            if (set.contains(val)) {
                return true;
            }

            set.add(val);
        }

        return false;
    }
}
