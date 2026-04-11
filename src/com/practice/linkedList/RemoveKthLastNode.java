package com.practice.linkedList;

public class RemoveKthLastNode {
    
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        
        System.out.println("Original linked list:");
        printList(head);
        
        // Test two-pointer approach
        Node result1 = removeKthLastTwoPointer(head, 2);
        System.out.println("\nAfter removing 2nd last node (two-pointer approach):");
        printList(result1);
        
        // Rebuild list for next test
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        
        // Test two-pass approach
        Node result2 = removeKthLastTwoPass(head, 3);
        System.out.println("\nAfter removing 3rd last node (two-pass approach):");
        printList(result2);
        
        // Test edge cases
        System.out.println("\n--- Edge Cases ---");
        
        // Remove first node (k = length)
        head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        Node result3 = removeKthLastTwoPointer(head, 3);
        System.out.println("After removing 3rd last node (first node):");
        printList(result3);
        
        // Single node
        Node single = new Node(100);
        Node result4 = removeKthLastTwoPointer(single, 1);
        System.out.println("After removing 1st last node from single node list:");
        printList(result4);
    }
    
    // Two-pointer approach (fast and slow pointers)
    // Time Complexity: O(n) - single pass through the list
    // Space Complexity: O(1) - only using two extra pointers
    public static Node removeKthLastTwoPointer(Node head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        
        Node dummy = new Node(0); // Dummy node to handle head removal
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;
        
        // Move fast pointer k steps ahead
        for (int i = 0; i <= k; i++) {
            if (fast == null) {
                return head; // k is larger than list length
            }
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // Remove the kth last node
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    // Two-pass approach (first pass to find length, second to remove)
    // Time Complexity: O(n) - two passes through the list
    // Space Complexity: O(1) - only using a few extra variables
    public static Node removeKthLastTwoPass(Node head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        
        // First pass: find the length of the list
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        
        // Check if k is valid
        if (k > length) {
            return head;
        }
        
        // Second pass: remove the (length - k + 1)th node from start
        Node dummy = new Node(0);
        dummy.next = head;
        current = dummy;
        
        for (int i = 0; i < length - k; i++) {
            current = current.next;
        }
        
        // Remove the target node
        current.next = current.next.next;
        
        return dummy.next;
    }
    
    // Helper method to print linked list
    // Time Complexity: O(n) - visit each node exactly once
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
