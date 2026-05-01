package com.practice.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache implementation using Doubly Linked List and HashMap
 * 
 * Time Complexity:
 * - get(key): O(1) - HashMap lookup and moving node to front of list
 * - put(key, value): O(1) - HashMap operations and list manipulations
 * 
 * Space Complexity: O(capacity) - We store at most capacity nodes in both HashMap and Doubly Linked List
 */
class LRUCache {

    public static void main(String[] args) {
        System.out.println("=== LRU Cache Test ===\n");

        // Create LRU Cache with capacity 3
        LRUCache lruCache = new LRUCache(3);

        // Test 1: Put operations
        System.out.println("Test 1: Adding elements (1:100, 2:200, 3:300)");
        lruCache.put(1, 100);
        lruCache.put(2, 200);
        lruCache.put(3, 300);
        lruCache.display();
        System.out.println("Cache size: " + lruCache.size());
        System.out.println();

        // Test 2: Get operations
        System.out.println("Test 2: Getting key 2 (should move to front)");
        System.out.println("Value for key 2: " + lruCache.get(2));
        lruCache.display();
        System.out.println();

        // Test 3: Add element exceeding capacity
        System.out.println("Test 3: Adding key 4 (should evict key 1 - LRU)");
        lruCache.put(4, 400);
        lruCache.display();
        System.out.println("Cache size: " + lruCache.size());
        System.out.println();

        // Test 4: Access existing key and update
        System.out.println("Test 4: Getting key 1 (should return -1 as it was evicted)");
        System.out.println("Value for key 1: " + lruCache.get(1));
        System.out.println();

        // Test 5: Update existing key
        System.out.println("Test 5: Updating key 3 to 350");
        lruCache.put(3, 350);
        lruCache.display();
        System.out.println("Value for key 3: " + lruCache.get(3));
        System.out.println();

        // Test 6: Add more elements to test eviction
        System.out.println("Test 6: Adding key 5 (should evict key 4)");
        lruCache.put(5, 500);
        lruCache.display();
        System.out.println();

        // Test 7: Final state verification
        System.out.println("Test 7: Final cache state verification");
        System.out.println("Value for key 2: " + lruCache.get(2));
        System.out.println("Value for key 3: " + lruCache.get(3));
        System.out.println("Value for key 4: " + lruCache.get(4)); // Should be -1
        System.out.println("Value for key 5: " + lruCache.get(5));
        lruCache.display();
    }
    
    // Node class for Doubly Linked List
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, -1); // Dummy head
        this.tail = new Node(-1, -1); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }
    
    /**
     * Get the value of the key if the key exists in the cache.
     * Also moves the accessed node to the front (most recently used).
     * 
     * @param key the key to retrieve
     * @return the value associated with the key, or -1 if not found
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        Node node = cache.get(key);
        // Move to front (most recently used)
        removeNode(node);
        addToFront(node);
        return node.value;
    }
    
    /**
     * Put or update the value of the key.
     * If the key is new, adds it to the front.
     * If capacity is exceeded, removes the least recently used item.
     * 
     * @param key the key to insert/update
     * @param value the value to associate with the key
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            removeNode(cache.get(key));
        }
        // Add new node
        Node newNode = new Node(key, value);
        cache.put(key, newNode);

        // Check capacity and remove LRU if needed
        if (cache.size() > capacity) {
            Node lruNode = tail.prev;
            cache.remove(lruNode.key);
            removeNode(lruNode);
        }

        addToFront(newNode);
    }
    
    /**
     * Helper method to add a node to the front of the list
     */
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * Helper method to remove a node from the list
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * Helper method to display the current cache state (for debugging)
     */
    public void display() {
        Node current = head.next;
        System.out.print("Cache state (MRU -> LRU): ");
        while (current != tail) {
            System.out.print("[" + current.key + ":" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Helper method to get current cache size
     */
    public int size() {
        return cache.size();
    }
}
