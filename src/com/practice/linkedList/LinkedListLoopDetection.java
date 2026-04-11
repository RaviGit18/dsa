package com.practice.linkedList;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class LinkedListLoopDetection {
    static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(2);
        System.out.println("Duplicate detected (brute force): " + detectLoopBruteForce(linkedList));
        
        // Demonstrate fast and slow pointer algorithm
        demonstrateFastSlowPointer();
    }

    private static boolean detectLoopBruteForce(LinkedList<Integer> list) {
        // Note: Java's built-in LinkedList doesn't support actual loops/cycles
        // This method checks for duplicate values which might indicate a logical loop
        Set<Integer> visited = new HashSet<>();
        
        for (Integer value : list) {
            if (visited.contains(value)) {
                return true; // Duplicate value found
            }
            visited.add(value);
        }
        return false;
    }
    
    // Custom Node class for proper linked list with pointer manipulation
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // Floyd's cycle detection algorithm (fast and slow pointers)
    private static boolean detectLoopFastSlow(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        Node slow = head;
        Node fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2
            
            if (slow == fast) {
                return true; // Loop detected - pointers met
            }
        }
        
        return false; // No loop detected
    }
    
    // Demo method to show fast-slow pointer usage
    private static void demonstrateFastSlowPointer() {
        System.out.println("\n--- Fast and Slow Pointer Demo ---");
        
        // Create linked list with loop
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next; // Create loop: 5 -> 2
        
        System.out.println("Loop detected with fast-slow pointers: " + detectLoopFastSlow(head));
        
        // Create linked list without loop
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        
        System.out.println("Loop detected in list without loop: " + detectLoopFastSlow(head2));
    }
}
