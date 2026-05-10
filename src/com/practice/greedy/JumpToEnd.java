package com.practice.greedy;

class JumpToEnd {
    /*Jump to the End
You are given an integer array in which you're originally positioned at index 0. Each number in the array represents the maximum jump distance from the current index. Determine if it's possible to reach the end of the array.

Example 1:
Image represents a sequence of numbers, [3, 2, 0, 2, 5], depicted within square brackets.  A green curved arrow originates from the number 3 at the beginning of the sequence and points to the number 2 near the middle.  Another smaller green curved arrow originates from the same initial 2 and points to the number 5 at the end of the sequence. These arrows suggest a connection or flow of information, possibly indicating a relationship or operation between the numbers 3 and 2, and separately between the number 2 and 5. The arrangement implies a linear data structure, possibly an array or list, with the arrows highlighting specific elements and their interactions within the context of a coding pattern, likely illustrating a selection or traversal process.
Input: nums = [3, 2, 0, 2, 5]
Output: True
Example 2:
Image represents two diagrams illustrating different scenarios of data flow or program execution. Each diagram shows a sequence of numbers, [2, 1, 0, 3], enclosed in square brackets.  In both diagrams, a grey curved arrow points from the '0' to the text 'dead end,' indicating a termination point or a path that doesn't continue further.  The key difference lies in the red arrows. In the first diagram, a single red curved arrow points from the '2' to the '0', suggesting a direct transition or flow from '2' to '0'. In the second diagram, two red curved arrows originate from the '2', one pointing to the '1' and the other to the '0', illustrating a branching or conditional flow where '2' can lead to either '1' or '0' before potentially reaching the 'dead end'.  The diagrams likely depict different control flows or execution paths within a program, possibly highlighting the concept of a single path versus multiple paths, or a decision point in the algorithm.
Input: nums = [2, 1, 0, 3]
Output: False
Constraints:
There is at least one element in nums.
All integers in nums are non-negative integers.
*/

    static void main(String[] args) {
        int[] nums = {3, 2, 0, 2, 5};
        System.out.println("Can jump to end: " + canJump(nums));
    }

    private static boolean canJump(int[] nums) {

        int destination = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= destination) {
                destination = i;
            }
        }

        return destination == 0;
    }
}
