package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Design and implement a data structure for Least Recently Used (LRU) cache to support the following operations:
 *
 * 1. get(key) - Return the value of the key if the key exists in the cache, otherwise return -1.
 *
 * 2. put(key, value), Insert the value in the cache if the key is not already present or update the value of the given key if the key is already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting the new item.
 * You will be given ‘Q’ queries. Each query will belong to one of these two types:
 * Type 0: for get(key) operation.
 * Type 1: for put(key, value) operation.
 * Note :
 * 1. The cache is initialized with a capacity (the maximum number of unique keys it can hold at a time).
 *
 * 2. Access to an item or key is defined as a get or a put operation on the key. The least recently used key is the one with the oldest access time.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= C <= 10^4
 * 1 <= Q <= 10^5
 * 1 <= key, value <= 10^9
 *
 * Time Limit: 1 sec
 * Sample Input 1 :
 * 3 11
 * 1 1 1
 * 1 2 2
 * 1 3 3
 * 1 4 5
 * 0 3
 * 0 1
 * 0 4
 * 1 2 3
 * 0 1
 * 0 3
 * 0 2
 * Sample Output 1 :
 * 3
 * -1
 * 5
 * -1
 * 3
 * 3
 * Explanation to Sample Input 1 :
 *
 * Initializing a cache of capacity 3, LRUCache cache = new LRUCache(3);
 * Then each operation is performed as shown in the above figure.
 * cache.put(1,1)
 * cache.put(2,2)
 * cache.put(3,3)
 * cache.put(4,5)
 * cache.get(3)      // returns 3
 * cache.get(1)      // returns -1
 * cache.get(2)      // returns 2
 * cache.put(5,5)
 * cache.get(4)      // returns -1
 * cache.get(3)      // returns 3
 * Sample Input 2 :
 * 2 6
 * 1 1 1
 * 1 2 2
 * 0 2
 * 1 3 3
 * 0 3
 * 0 1
 * Sample Output 2 :
 * 2
 * 3
 * -1
 */
import java.util.*;

public class LRUCache {
    private int capacity;
    private Map<Integer, Integer> cache;
    private LinkedHashSet<Integer> lruOrder;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.lruOrder = new LinkedHashSet<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        lruOrder.remove(key);
        lruOrder.add(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            lruOrder.remove(key);
        } else if (cache.size() >= capacity) {
            int lruKey = lruOrder.iterator().next();
            lruOrder.remove(lruKey);
            cache.remove(lruKey);
        }
        
        cache.put(key, value);
        lruOrder.add(key);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        
        // Test case from sample
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Get 1: " + cache.get(1)); // returns 1
        cache.put(3, 3); // evicts key 2
        System.out.println("Get 2: " + cache.get(2)); // returns -1
        cache.put(4, 4); // evicts key 1
        System.out.println("Get 1: " + cache.get(1)); // returns -1
        System.out.println("Get 3: " + cache.get(3)); // returns 3
        System.out.println("Get 4: " + cache.get(4)); // returns 4
    }
}
