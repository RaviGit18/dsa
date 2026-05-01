package com.practice.linkedList;

/**
 * Class to determine if a linked list is a palindrome
 * 
 * Time Complexity: O(n) - We traverse the list once to find middle, once to reverse, and once to compare
 * Space Complexity: O(1) - We only use a few pointers, no extra space proportional to input size
 */
public class LinkedListPalindrome {
    
    // Node class for singly linked list
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    
    public LinkedListPalindrome() {
        this.head = null;
    }
    
    /**
     * Add a node to the end of the linked list
     */
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    
    /**
     * Check if the linked list is a palindrome
     * Uses the approach of finding middle, reversing second half, and comparing
     * 
     * @return true if the linked list is a palindrome, false otherwise
     */
    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true; // Empty list or single node is always a palindrome
        }
        
        // Step 1: Find the middle of the linked list using slow and fast pointers
        Node slow = head;
        Node fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse the second half of the linked list
        Node secondHalf = reverseList(slow);
        
        // Step 3: Compare the first half with the reversed second half
        Node firstHalf = head;
        Node tempSecondHalf = secondHalf; // Store to restore the list later
        boolean result = true;
        
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                result = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        // Step 4: Restore the original linked list (optional)
        reverseList(tempSecondHalf);
        
        return result;
    }
    
    /**
     * Reverse a linked list
     * 
     * @param head head of the list to reverse
     * @return new head of the reversed list
     */
    private Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Alternative approach: Convert to array and check palindrome
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Uses extra space for array
     * 
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindromeUsingArray() {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Count the number of nodes
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        
        // Store values in array
        int[] values = new int[count];
        current = head;
        for (int i = 0; i < count; i++) {
            values[i] = current.data;
            current = current.next;
        }
        
        // Check palindrome using two pointers
        int left = 0;
        int right = count - 1;
        while (left < right) {
            if (values[left] != values[right]) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Display the linked list
     */
    public void display() {
        Node current = head;
        System.out.print("Linked List: ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * Main method to test the palindrome functionality
     */
    public static void main(String[] args) {
        System.out.println("=== Linked List Palindrome Test ===\n");
        
        // Test 1: Palindrome list with even length
        System.out.println("Test 1: Even length palindrome (1->2->2->1)");
        LinkedListPalindrome list1 = new LinkedListPalindrome();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(1);
        list1.display();
        System.out.println("Is palindrome: " + list1.isPalindrome());
        System.out.println("Using array method: " + list1.isPalindromeUsingArray());
        System.out.println();
        
        // Test 2: Palindrome list with odd length
        System.out.println("Test 2: Odd length palindrome (1->2->3->2->1)");
        LinkedListPalindrome list2 = new LinkedListPalindrome();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(2);
        list2.add(1);
        list2.display();
        System.out.println("Is palindrome: " + list2.isPalindrome());
        System.out.println("Using array method: " + list2.isPalindromeUsingArray());
        System.out.println();
        
        // Test 3: Non-palindrome list
        System.out.println("Test 3: Non-palindrome list (1->2->3->4->5)");
        LinkedListPalindrome list3 = new LinkedListPalindrome();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(4);
        list3.add(5);
        list3.display();
        System.out.println("Is palindrome: " + list3.isPalindrome());
        System.out.println("Using array method: " + list3.isPalindromeUsingArray());
        System.out.println();
        
        // Test 4: Single element
        System.out.println("Test 4: Single element (7)");
        LinkedListPalindrome list4 = new LinkedListPalindrome();
        list4.add(7);
        list4.display();
        System.out.println("Is palindrome: " + list4.isPalindrome());
        System.out.println("Using array method: " + list4.isPalindromeUsingArray());
        System.out.println();
        
        // Test 5: Empty list
        System.out.println("Test 5: Empty list");
        LinkedListPalindrome list5 = new LinkedListPalindrome();
        list5.display();
        System.out.println("Is palindrome: " + list5.isPalindrome());
        System.out.println("Using array method: " + list5.isPalindromeUsingArray());
        System.out.println();
        
        // Test 6: Two elements palindrome
        System.out.println("Test 6: Two elements palindrome (5->5)");
        LinkedListPalindrome list6 = new LinkedListPalindrome();
        list6.add(5);
        list6.add(5);
        list6.display();
        System.out.println("Is palindrome: " + list6.isPalindrome());
        System.out.println("Using array method: " + list6.isPalindromeUsingArray());
        System.out.println();
    }
}
