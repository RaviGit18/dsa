package com.practice.LL;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

    /*
        LRU Cache
            Design and implement a data structure for the Least Recently Used (LRU) cache that supports the following operations:

            LRUCache(capacity: int): Initialize an LRU cache with the specified capacity.
            get(key: int) -> int: Return the value associated with a key. Return -1 if the key doesn't exist.
            put(key: int, value: int) -> None: Add a key and its value to the cache. If adding the key would result in the cache exceeding its size capacity, evict the least recently used element. If the key already exists in the cache, update its value.
            Example:
            Input: [
              put(1, 100),
              put(2, 250),
              get(2),
              put(4, 300),
              put(3, 200),
              get(4),
              get(1),
            ],
              capacity = 3
            Output: [250, 300, -1]
            Explanation:

            put(1, 100)  # cache is[1: 100]
            put(2, 250)  # cache is[1: 100, 2: 250]
            get(2)       # return 250
            put(4, 300)  # cache is[1: 100, 2: 250, 4: 300]t
            put(3, 200)  # cache is[2: 250, 4: 300, 3: 200]
            get(4)       # return 300
            get(1)       # key 1 was evicted when adding key 3 due to the capacity
                         # limit: return -1
            Constraints:
            All keys and values are positive integers.

            The cache capacity is positive.
    */

    static void main() {
        System.out.println("Testing LRU Cache with capacity = 3");
        System.out.println("Operations: put(1, 100), put(2, 250), get(2), put(4, 300), put(3, 200), get(4), get(1)");
        System.out.println("Expected output: [250, 300, -1]");
        System.out.println();

        LruCache cache = new LruCache(3);

        cache.put(1, 100);
        System.out.println("put(1, 100) - Cache: [1:100]");

        cache.put(2, 250);
        System.out.println("put(2, 250) - Cache: [1:100, 2:250]");

        int result1 = cache.get(2);
        System.out.println("get(2) -> " + result1);

        cache.put(4, 300);
        System.out.println("put(4, 300) - Cache: [1:100, 2:250, 4:300]");

        cache.put(3, 200);
        System.out.println("put(3, 200) - Cache: [2:250, 4:300, 3:200] (key 1 evicted)");

        int result2 = cache.get(4);
        System.out.println("get(4) -> " + result2);

        int result3 = cache.get(1);
        System.out.println("get(1) -> " + result3 + " (key 1 was evicted)");

        System.out.println();
        System.out.println("Actual output: [" + result1 + ", " + result2 + ", " + result3 + "]");
        System.out.println("Test passed: " + (result1 == 250 && result2 == 300 && result3 == -1));
    }

    private int capacity;
    private Map<Integer, DoublyLinkedList> hashMap;
    private DoublyLinkedList head;
    private DoublyLinkedList tail;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.head = new DoublyLinkedList(-1, -1);
        this.tail = new DoublyLinkedList(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private int get(int key) {
        if (!hashMap.containsKey(key)) {
            return -1;
        }

        DoublyLinkedList node = hashMap.get(key);
        removeNode(node);
        addToTail(node);
        return node.value;
    }

    private void addToTail(DoublyLinkedList node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeNode(DoublyLinkedList node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void put(int key, int val) {
        if (hashMap.containsKey(key)) {
            removeNode(hashMap.get(key));
        }

        DoublyLinkedList node = new DoublyLinkedList(key, val);
        hashMap.put(key, node);

        if (hashMap.size() > capacity) {
            hashMap.remove(head.next.key);
            removeNode(head.next);
        }

        addToTail(node);
    }
}

class DoublyLinkedList {
    int key;
    int value;
    DoublyLinkedList prev;
    DoublyLinkedList next;

    public DoublyLinkedList(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}