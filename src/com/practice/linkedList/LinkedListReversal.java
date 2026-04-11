package com.practice.linkedList;

public class LinkedListReversal {
    
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        
        System.out.println("Original linked list:");
        printList(head);
        
        // Test iterative reversal
        Node reversedIterative = reverseIterative(head);
        System.out.println("\nReversed linked list (iterative):");
        printList(reversedIterative);
        
        // Restore original list by reversing again
        Node restored = reverseIterative(reversedIterative);
        
        // Test recursive reversal
        Node reversedRecursive = reverseRecursive(restored);
        System.out.println("\nReversed linked list (recursive):");
        printList(reversedRecursive);
        
        // Test with single node
        Node singleNode = new Node(10);
        System.out.println("\nSingle node list:");
        printList(singleNode);
        Node reversedSingle = reverseIterative(singleNode);
        System.out.println("Reversed single node:");
        printList(reversedSingle);
        
        // Test with empty list
        System.out.println("\nEmpty list:");
        printList(null);
        Node reversedEmpty = reverseRecursive(null);
        System.out.println("Reversed empty list:");
        printList(reversedEmpty);
    }
    
    // Iterative approach to reverse linked list
    // Time Complexity: O(n) - we visit each node exactly once
    // Space Complexity: O(1) - only using three extra pointers regardless of list size
    public static Node reverseIterative(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;    // Store next node
            current.next = prev;    // Reverse the link
            prev = current;          // Move prev forward
            current = next;         // Move current forward
        }
        
        return prev; // prev is the new head
    }
    
    // Recursive approach to reverse linked list
    // Time Complexity: O(n) - we visit each node exactly once
    // Space Complexity: O(n) - recursion stack depth equals the number of nodes
    public static Node reverseRecursive(Node head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        Node newHead = reverseRecursive(head.next);
        
        // Reverse the current node's link
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    // Helper method to print linked list
    // Time Complexity: O(n) - we visit each node exactly once
    // Space Complexity: O(1) - no extra space used except for temporary variable
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        if (head == null) {
            System.out.print("null");
        }
        System.out.println();
    }
}
