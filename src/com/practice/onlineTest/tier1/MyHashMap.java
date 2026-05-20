package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Design a data structure that stores a mapping of a key to a given value and supports the following operations in constant time.
 *
 * 1. INSERT(key, value): Inserts an integer value to the data structure against a string type key if not already present. If already present, it updates the value of the key with the new one. This function will not return anything.
 *
 * 2. DELETE(key): Removes the key from the data structure if present. It doesn't return anything.
 *
 * 3. SEARCH(key): It searches for the key in the data structure. In case it is present, return true. Otherwise, return false.
 *
 * 4. GET(key): It returns the integer value stored against the given key. If the key is not present, return -1.
 *
 * 5. GET_SIZE(): It returns an integer value denoting the size of the data structure.
 *
 * 6. IS_EMPTY(): It returns a boolean value, denoting whether the data structure is empty or not.
 * Note :
 * 1. Key is always a string value.
 * 2. Value can never be -1.
 * Operations Performed :
 * First(Denoted by integer value 1):  Insertion to the Data Structure. It is done in a pair of (key, value).
 *
 * Second(Denoted by integer value 2):  Deletion of a key from the Data Structure.
 *
 * Third(Denoted by integer value 3): Search a given key in the Data Structure.
 *
 * Fourth(Denoted by integer value 4): Retrieve the value for a given key from the Data Structure.
 *
 * Fifth(Denoted by integer value 5): Retrieve the size of the Data Structure.
 *
 * Sixth(Denoted by integer value 6): Retrieve whether the Data Structure is empty or not.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= N <= 10 ^ 5
 * 1 <= T <= 3
 * 1 <= V <= 10 ^ 5
 *
 * Where 'T' is the type of operation and 'V' is the value of the operand.
 *
 * Time Limit: 3 sec
 * Sample Input 1 :
 * 3
 * 1 qwerty 35
 * 1 qwerty 50
 * 5
 * Sample Output 1 :
 * 1
 * Explanation Of Sample Input 1 :
 * 1 operation: We need to insert 'qwerty' with a value of 35.
 *
 * 2 operation: We need to insert 'qwerty' with a value of 50.
 *
 * 3 operation: We need to return the size of HashMap. So, We will return 1.
 * Sample Input 2 :
 * 6
 * 1 code 9
 * 3 code
 * 2 code
 * 3 code
 * 5
 * 6
 * Sample Output 2 :
 * true
 * false
 * 0
 * true
 */
public class MyHashMap
{
    private static final int DEFAULT_CAPACITY = 10007;
    private Entry[] buckets;
    private int size;

    private class Entry {
        String key;
        int value;
        Entry next;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    static void main(String[] args) {
        MyHashMap map = new MyHashMap();

        // Test Sample Input 1
        System.out.println("=== Sample Input 1 ===");
        map.insert("qwerty", 35);
        map.insert("qwerty", 50);
        System.out.println("Size: " + map.getSize()); // Expected: 1

        // Test Sample Input 2
        System.out.println("\n=== Sample Input 2 ===");
        MyHashMap map2 = new MyHashMap();
        map2.insert("code", 9);
        System.out.println("Search 'code': " + map2.search("code")); // Expected: true
        map2.remove("code");
        System.out.println("Search 'code' after remove: " + map2.search("code")); // Expected: false
        System.out.println("Size: " + map2.getSize()); // Expected: 0
        System.out.println("Is empty: " + map2.isEmpty()); // Expected: true

        // Additional tests
        System.out.println("\n=== Additional Tests ===");
        MyHashMap map3 = new MyHashMap();
        map3.insert("apple", 10);
        map3.insert("banana", 20);
        map3.insert("cherry", 30);
        System.out.println("Get 'apple': " + map3.get("apple")); // Expected: 10
        System.out.println("Get 'banana': " + map3.get("banana")); // Expected: 20
        System.out.println("Get 'nonexistent': " + map3.get("nonexistent")); // Expected: -1
        System.out.println("Size: " + map3.getSize()); // Expected: 3
        System.out.println("Is empty: " + map3.isEmpty()); // Expected: false
        map3.remove("banana");
        System.out.println("Size after remove: " + map3.getSize()); // Expected: 2
        System.out.println("Search 'banana': " + map3.search("banana")); // Expected: false
    }

    public MyHashMap()
    {
        buckets = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % DEFAULT_CAPACITY;
    }

    public void insert(String key, int value)
    {
        int index = hash(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    public int get(String key)
    {
        int index = hash(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return -1;
    }

    public void remove(String key)
    {
        int index = hash(key);
        Entry current = buckets[index];
        Entry prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean search(String key)
    {
        int index = hash(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

}
