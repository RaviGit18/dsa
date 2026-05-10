package com.practice.sortAndSearch;

import java.util.List;

class SortLinkedList {

    /*Sort Linked List
Given the head of a singly linked list, sort the linked list in ascending order.

Example:
Image represents two linear sequences of numbered nodes (3, 2, 4, 5, 1) and (1, 2, 3, 4, 5) connected by unidirectional arrows indicating a flow from left to right.  The top sequence uses black-bordered circles containing numbers 3, 2, 4, 5, and 1 respectively, linked sequentially by black arrows.  The bottom sequence uses orange-bordered circles with the same numbers (1, 2, 3, 4, 5) arranged sequentially and connected by black arrows. A downward-pointing grey arrow connects the node '4' in the top sequence to the node '3' in the bottom sequence, suggesting a transformation or transition between the two sequences.  The overall arrangement visually depicts a before-and-after scenario, or a process that reorders the sequence of nodes.
*/

    static void main(String[] args) {
        ListNode<Integer> head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(1);

        printLL(head);

        head = sortList(head);

        printLL(head);
    }

    private static ListNode<Integer> sortList(ListNode<Integer> head) {

        if (head == null || head.next == null) {return head;}

        ListNode<Integer> secondHead = splitHead(head);

        ListNode<Integer> leftHalfSorted = sortList(head);
        ListNode<Integer> rightHalfSorted = sortList(secondHead);

        return merge(leftHalfSorted, rightHalfSorted);

    }

    private static ListNode<Integer> merge(ListNode<Integer> left, ListNode<Integer> right) {

        ListNode<Integer> dummy = new ListNode(-1);
        ListNode<Integer> tail = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else  {
                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        tail.next = left != null ? left : right;

        return dummy.next;
    }

    private static ListNode<Integer> splitHead(ListNode<Integer> head) {

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode<Integer> secondHead =  slow.next;
        slow.next = null;
        return secondHead;
    }

    private static void printLL(ListNode<Integer> head) {

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

class ListNode<T> {
    T val;
    ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
        this.next = null;
    }
}
