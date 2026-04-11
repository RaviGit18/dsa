package com.practice.linkedList;

public class LinkedListIntersection {
    
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    static void main(String[] args) {
        // Create two linked lists that intersect
        // List1: 1 -> 2 -> 3 -> 4 -> 5
        // List2: 9 -> 8 -> 4 -> 5 (intersection at node 4)
        
        Node common = new Node(4);
        common.next = new Node(5);
        
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = common;
        
        Node head2 = new Node(9);
        head2.next = new Node(8);
        head2.next.next = common;
        
        System.out.println("List1:");
        printList(head1);
        System.out.println("List2:");
        printList(head2);
        
        // Test two-pointer approach
        Node intersection1 = getIntersectionTwoPointer(head1, head2);
        System.out.println("\nIntersection point (two-pointer approach): " + 
            (intersection1 != null ? intersection1.data : "No intersection"));
        
        // Test length difference approach
        Node intersection2 = getIntersectionLengthDiff(head1, head2);
        System.out.println("Intersection point (length difference approach): " + 
            (intersection2 != null ? intersection2.data : "No intersection"));
        
        // Test with no intersection
        Node noIntersect1 = new Node(1);
        noIntersect1.next = new Node(2);
        
        Node noIntersect2 = new Node(3);
        noIntersect2.next = new Node(4);
        
        System.out.println("\n--- Testing with no intersection ---");
        System.out.println("List1:");
        printList(noIntersect1);
        System.out.println("List2:");
        printList(noIntersect2);
        
        Node intersection3 = getIntersectionTwoPointer(noIntersect1, noIntersect2);
        System.out.println("Intersection point: " + 
            (intersection3 != null ? intersection3.data : "No intersection"));
        
        // Test with one empty list
        System.out.println("\n--- Testing with empty list ---");
        Node intersection4 = getIntersectionTwoPointer(null, head1);
        System.out.println("Intersection point (one empty list): " + 
            (intersection4 != null ? intersection4.data : "No intersection"));
    }
    
    // Two-pointer approach (optimal solution)
    // Time Complexity: O(n + m) - where n and m are lengths of the two lists
    // Space Complexity: O(1) - only using two extra pointers
    public static Node getIntersectionTwoPointer(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        
        Node pointer1 = head1;
        Node pointer2 = head2;
        
        // Continue until both pointers meet or both become null
        while (pointer1 != pointer2) {
            // Move each pointer one step
            pointer1 = pointer1 == null ? head2 : pointer1.next;
            pointer2 = pointer2 == null ? head1 : pointer2.next;
        }
        
        // Either both meet at intersection or both become null
        return pointer1;
    }
    
    // Length difference approach
    // Time Complexity: O(n + m) - two passes to find lengths, one pass to find intersection
    // Space Complexity: O(1) - only using a few extra variables
    public static Node getIntersectionLengthDiff(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        
        // Find lengths of both lists
        int length1 = getLength(head1);
        int length2 = getLength(head2);
        
        // Determine which list is longer
        Node longer = length1 >= length2 ? head1 : head2;
        Node shorter = length1 >= length2 ? head2 : head1;
        int diff = Math.abs(length1 - length2);
        
        // Move the longer list pointer ahead by the difference
        for (int i = 0; i < diff; i++) {
            longer = longer.next;
        }
        
        // Move both pointers until they meet
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        
        return longer; // Could be null if no intersection
    }
    
    // Helper method to calculate the length of a linked list
    // Time Complexity: O(n) - visit each node exactly once
    // Space Complexity: O(1) - only using a counter variable
    private static int getLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
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
