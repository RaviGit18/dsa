# LRU Cache Implementation

## Overview
This is an implementation of an LRU (Least Recently Used) cache using a combination of:
- **Doubly Linked List**: Maintains the order of usage (most recent to least recent)
- **HashMap**: Provides O(1) access to cache items

## Data Structure Design

### Node Class
```java
class Node {
    int key;
    int value;
    Node prev;
    Node next;
}
```

### Cache Structure
- **Head dummy node**: Points to the most recently used (MRU) item
- **Tail dummy node**: Points to the least recently used (LRU) item
- **HashMap**: Maps keys to Node objects for O(1) lookup

## Time and Space Complexity

### Time Complexity
- **get(key)**: O(1)
  - HashMap lookup: O(1)
  - Moving node to front: O(1)
- **put(key, value)**: O(1)
  - HashMap operations: O(1)
  - List manipulations: O(1)

### Space Complexity
- **O(capacity)**: We store at most `capacity` nodes in both HashMap and Doubly Linked List

## Key Operations

### get(key)
1. Check if key exists in HashMap
2. If found, move the corresponding node to the front (MRU position)
3. Return the value, or -1 if not found

### put(key, value)
1. If key exists:
   - Update the value
   - Move node to front (MRU position)
2. If key doesn't exist:
   - Create new node
   - Add to HashMap and front of list
   - If capacity exceeded, remove LRU node (tail.prev)

## Usage Example

```java
// Create cache with capacity 3
LRUCache cache = new LRUCache(3);

// Add items
cache.put(1, 100);
cache.put(2, 200);
cache.put(3, 300);

// Get item (moves to front)
int value = cache.get(2); // returns 200

// Add item beyond capacity (evicts LRU)
cache.put(4, 400); // evicts key 1
```

## Files
- `LRUCache.java`: Main implementation
- `LRUCacheTest.java`: Test class demonstrating functionality
- `README.md`: This documentation file

## Compilation and Execution
```bash
# Compile
javac -cp src src/com/practice/cache/LRUCache.java src/com/practice/cache/LRUCache.java

# Run test
java -cp src com.practice.cache.LRUCache
```
