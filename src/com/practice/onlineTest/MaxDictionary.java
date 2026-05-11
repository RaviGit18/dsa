package com.practice.onlineTest;

import java.util.*;

class MaxDictionary {

    /*java program to Find max element in a dictionary at any time
    * If the requirement is:

Maintain a dictionary/map and efficiently find the maximum element at any time.

Then the best data structure in Java is:

TreeMap

Because:

keys remain sorted
max element retrieval is efficient
Example

Operations:

put("A", 10)
put("B", 50)
put("C", 30)

Maximum value:

50
Approach 1 — Using TreeMap + HashMap

We maintain:

HashMap
key → value
TreeMap
value → frequency

This supports:

insert
update
delete
max retrieval

efficiently.
*
* Time Complexity
Operation	Complexity
put	O(log N)
remove	O(log N)
getMax	O(log N)

Because TreeMap uses:

Red-Black Tree
Alternative Approaches
1. HashMap + PriorityQueue

Good for:

frequent max queries

But:

deletion/update becomes tricky
requires lazy deletion
2. TreeSet

If values unique.

3. Max Heap

Efficient max retrieval:
    * */

    public static void main(String[] args) {

        MaxDictionary dict =
                new MaxDictionary();

        dict.put("A", 10);
        dict.put("B", 50);
        dict.put("C", 30);

        System.out.println(
                "Max = " + dict.getMax());

        dict.put("A", 100);

        System.out.println(
                "Max = " + dict.getMax());

        dict.remove("A");

        System.out.println(
                "Max = " + dict.getMax());
    }

    // Key -> Value
    private Map<String, Integer> map;

    // Value -> Frequency
    private TreeMap<Integer, Integer> freqMap;

    public MaxDictionary() {

        map = new HashMap<>();

        freqMap = new TreeMap<>();
    }

    // Insert or update
    public void put(String key, int value) {

        // If key already exists
        if (map.containsKey(key)) {

            int oldValue = map.get(key);

            removeFrequency(oldValue);
        }

        map.put(key, value);

        freqMap.put(value,
                freqMap.getOrDefault(value, 0) + 1);
    }

    // Remove key
    public void remove(String key) {

        if (!map.containsKey(key)) {
            return;
        }

        int value = map.remove(key);

        removeFrequency(value);
    }

    // Get max value
    public int getMax() {

        if (freqMap.isEmpty()) {

            throw new RuntimeException(
                    "Dictionary is empty");
        }

        return freqMap.lastKey();
    }

    // Helper
    private void removeFrequency(int value) {

        int count = freqMap.get(value);

        if (count == 1) {
            freqMap.remove(value);
        }
        else {
            freqMap.put(value, count - 1);
        }
    }


}