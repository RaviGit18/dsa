package com.practice.LL;

import java.util.Objects;

public class LL<T> {
    static void main() {

        ListNode<Integer> head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(7);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        printLL(head);

        head = reverseLL(head);

        printLL(head);

        removeKthNodeFromEnd(head, 2);

        printLL(head);

        ListNode<Integer> head1 = new ListNode(11);
        head1.next = new ListNode(12);
        head1.next.next = new ListNode(13);
        head1.next.next.next = new ListNode(14);
        head1.next.next.next.next = head;


        ListNode<Integer> head2 = new ListNode(21);
        head2.next = new ListNode(22);
        head2.next.next = new ListNode(23);
        head2.next.next.next = new ListNode(24);
        head2.next.next.next.next = head;

        System.out.print("head1: ");
        printLL(head1);
        System.out.print("head2: ");
        printLL(head2);

        ListNode<Integer> intersection = getIntersectionNode(head1, head2);
        System.out.println("Intersection: " + intersection.val);

        ListNode<Integer> palindromicHead = new ListNode(1);
        palindromicHead.next = new ListNode(2);
        palindromicHead.next.next = new ListNode(3);
        palindromicHead.next.next.next = new ListNode(2);
        palindromicHead.next.next.next.next = new ListNode(1);

        printLL(palindromicHead);
        System.out.println("Is palindromic: " + isPalindromic(palindromicHead));

        ListNode<Integer> nonPalindromicHead = new ListNode(1);
        nonPalindromicHead.next = new ListNode(2);
        nonPalindromicHead.next.next = new ListNode(1);
        nonPalindromicHead.next.next.next = new ListNode(2);

        printLL(nonPalindromicHead);
        System.out.println("Is palindromic: " + isPalindromic(nonPalindromicHead));

        ListNode<Integer> loopHead = new ListNode(1);
        loopHead.next = new ListNode(2);
        loopHead.next.next = new ListNode(3);
        loopHead.next.next.next = new ListNode(4);
        loopHead.next.next.next.next = new ListNode(5);
        loopHead.next.next.next.next.next = loopHead.next.next;

        System.out.println("Is loop detected: " + detectLoop(loopHead));
    }

    private static boolean detectLoop (ListNode<Integer> head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }

        }

        return false;
    }

    private static boolean isPalindromic(ListNode<Integer> node) {
        ListNode<Integer> mid = findMid(node);
        System.out.println("Mid: " + mid.val);

        ListNode<Integer> secondHead = reverseLL(mid);
        System.out.println("secondHead: " + secondHead.val);

        System.out.print("secondhalf: ");
        printLL(secondHead);

        ListNode<Integer> ptr1 = node;
        ListNode<Integer> ptr2 = secondHead;

        boolean flag = true;
        while (ptr2 != null) {

            if (!ptr1.val.equals(ptr2.val)) {
                flag = false;
                break;
            }

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return  flag;
    }

    private static ListNode<Integer> findMid(ListNode<Integer> node) {

        ListNode<Integer> slow = node;
        ListNode<Integer> fast = node;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static void printLL(ListNode<Integer> head) {

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    private static ListNode<Integer> reverseLL(ListNode<Integer> head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode<Integer> prev = null;
        ListNode<Integer> curr = head;

        while (curr != null) {

            ListNode<Integer> next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;

        }

        return prev;

    }

    private static ListNode<Integer> removeKthNodeFromEnd(ListNode<Integer> head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode<Integer> dummy = new ListNode(-1);
        dummy.next = head;

        ListNode<Integer> trailer = dummy;
        ListNode<Integer> leader = dummy;

        while (k > 0) {
            if (leader == null) {
                return head;
            }

            leader = leader.next;
            k--;
        }

        while (leader != null && leader.next != null) {
            trailer = trailer.next;
            leader = leader.next;
        }

        trailer.next = trailer.next.next;

        return dummy.next;
    }

    private static ListNode<Integer> getIntersectionNode(ListNode<Integer> head1, ListNode<Integer> head2) {
        ListNode<Integer> curr1 = head1;
        ListNode<Integer> curr2 = head2;

        while(curr1 != curr2) {
            curr1 = (curr1 != null) ? curr1.next : head2;
            curr2 = (curr2 != null) ? curr2.next : head1;
        }

        return curr1;
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