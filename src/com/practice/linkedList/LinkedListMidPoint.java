package com.practice.linkedList;

import java.util.LinkedList;

class LinkedListMidPoint {
    static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        //linkedList.add(5);
        System.out.println(getMidPoint(linkedList));
    }

    private static int getMidPoint(LinkedList<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        
        int slowIndex = 0;
        int fastIndex = 0;
        
        // Use two-pointer technique with indices
        while (fastIndex < list.size() && fastIndex + 1 < list.size() /*&& fastIndex + 2 < list.size()*/) {
            slowIndex++;
            fastIndex += 2;
        }
        
        return list.get(slowIndex);
    }
}
