package com.practice.heap;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianOfStream {

    /*Median of an Integer Stream
Design a data structure that supports adding integers from a data stream and retrieving the median of all elements received at any point.

add(num: int) -> None: adds an integer to the data structure.
get_median() -> float: returns the median of all integers so far.
Example:
Input: [add(3), add(6), get_median(), add(1), get_median()]
Output: [4.5, 3.0]
Explanation:

add(3)        # data structure contains [3] when sorted
add(6)        # data structure contains [3, 6] when sorted
get_median()  # median is (3 + 6) / 2 = 4.5
add(1)        # data structure contains [1, 3, 6] when sorted
get_median()  # median is 3.0
Constraints:
At least one value will have been added before get_median is called.
*/

    private PriorityQueue<Integer> leftHeap;
    private PriorityQueue<Integer> rightHeap;

    public MedianOfStream() {
        leftHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightHeap = new PriorityQueue<>();
    }

    static void main(String[] args) {

        MedianOfStream m = new MedianOfStream();
        m.add(3);
        m.add(6);
        System.out.println(m.get_median());
        m.add(1);
        System.out.println(m.get_median());
    }

    private double get_median() {
        if (leftHeap.size() == rightHeap.size()) {

            return (leftHeap.peek() + rightHeap.peek()) / 2.0;

        }

        return (double)leftHeap.peek();
    }

    private void add(int num) {

        if (leftHeap.isEmpty() || num <= leftHeap.peek()) {

            leftHeap.offer(num);

            if (leftHeap.size() > rightHeap.size() + 1) {
                rightHeap.offer(leftHeap.poll());
            }
        } else  {

            rightHeap.offer(num);

            if (rightHeap.size() > leftHeap.size()) {
                leftHeap.offer(rightHeap.poll());
            }
        }

    }
}
