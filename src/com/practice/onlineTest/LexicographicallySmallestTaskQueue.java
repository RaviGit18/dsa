package com.practice.onlineTest;

class LexicographicallySmallestTaskQueue {

    /*java program for
A system manages several tasks. There is a string taskQueue containing n tasks, where each task has a priority level represented as follows: '1' for low, '2' for medium, and '3' for high. To optimize the system, the following operations can be performed any number of times:

Swap adjacent tasks with values '1' and '2' (or vice versa).

Swap adjacent tasks with values '2' and '3' (or vice versa).

Determine the lexicographically smallest order of the task Queue.

Note: A task order a is lexicographically smaller than a task order b if and only if in the first position where a and b differ, the task order a has a lower priority task than b.
*/
    /*Key Observation

Allowed swaps:

1 ↔ 2
2 ↔ 3

But:

1 and 3 cannot swap directly

This means:

relative order of 1 and 3 is fixed
2 can move around both
Important Insight

To get lexicographically smallest string:

move all possible 1s left
move all possible 3s right
while preserving relative order between 1 and 3

Effectively:

all 2s can be inserted optimally between them.
Example

Input:

"321"

Possible transformations:

321
231
213
123

Minimum:

123
Another Example

Input:

"31312"

1 and 3 relative order:

3 1 3 1

Now insert all 2s optimally:

13132
Simplified Rule

The lexicographically smallest arrangement is obtained by:

Keep relative order of only 1 and 3
Insert all 2s:
after all movable 1s
before remaining 3s

Why This Works

Because:

2 can swap with both 1 and 3
so 2s are fully movable
1s can always move before 2s
3s can always move after 2s

Thus smallest lexicographical order becomes:

1...1 2...2 3...3

Time Complexity

Single traversal:

O(N)
Space Complexity
O(N)
*/
    public static void main(String[] args) {

        String taskQueue = "31312";

        String result =
                smallestTaskQueue(taskQueue);

        System.out.println(result);
    }

    public static String smallestTaskQueue(
            String taskQueue) {

        StringBuilder ones = new StringBuilder();
        StringBuilder threes = new StringBuilder();

        int countTwos = 0;

        // Separate tasks
        for (char ch : taskQueue.toCharArray()) {

            if (ch == '1') {
                ones.append(ch);
            }
            else if (ch == '2') {
                countTwos++;
            }
            else {
                threes.append(ch);
            }
        }

        // Build lexicographically smallest result
        StringBuilder result =
                new StringBuilder();

        result.append(ones);

        // Add all 2s
        for (int i = 0; i < countTwos; i++) {
            result.append('2');
        }

        result.append(threes);

        return result.toString();
    }


}
