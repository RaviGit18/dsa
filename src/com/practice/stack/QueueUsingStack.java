package com.practice.stack;

import java.util.Stack;

class QueueUsingStack {

    /*Implement a Queue using Stacks
Implement a queue using the stack data structure. Include the following functions:

enqueue(x: int) -> None: adds x to the end of the queue.
dequeue() -> int: removes and returns the element from the front of the queue.
peek() -> int: returns the front element of the queue.
You may not use any other data structures to implement the queue.

Example
Input: [enqueue(1), enqueue(2), dequeue(), enqueue(3), peek()]
Output: [1, 2]
Constraints:
The dequeue and peek operations will only be called on a non-empty queue.
*/

    private Stack<Integer> enqueueStack = new Stack<>();
    private Stack<Integer> dequeueStack = new Stack<>();

    private void enqueue(int x) {
        enqueueStack.push(x);
    }

    private int dequeue() {
        transferEnqueueToDequeue();
        return dequeueStack.isEmpty() ? -1 : dequeueStack.pop();
    }

    private int peek() {
        transferEnqueueToDequeue();
        return dequeueStack.isEmpty() ? -1 : dequeueStack.peek();
    }

    private void transferEnqueueToDequeue() {
        while (!enqueueStack.isEmpty()) {
            dequeueStack.push(enqueueStack.pop());
        }
    }

    static void main(String[] args) {
        QueueUsingStack queue = new QueueUsingStack();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println("Dequeued: " + queue.dequeue());
        queue.enqueue(3);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Peek: " + queue.peek());
    }

}
