package com.practice.stack;

import java.util.Stack;

class RepeatedRemovalAdjacentDuplicates {

    /*Repeated Removal of Adjacent Duplicates
Given a string, continually perform the following operation: remove a pair of adjacent duplicates from the string. Continue performing this operation until the string no longer contains pairs of adjacent duplicates. Return the final string.

Example 1:
Image represents a sequence of data transformations or operations.  The sequence begins with two 'a's connected by a red horizontal line, followed by a 'c'. This is then followed by an 'a' and two 'b's connected by a red horizontal line, followed by another 'a'. A grey arrow indicates a transformation from this first segment to a second segment. The second segment starts with a 'c', followed by two 'a's connected by a red horizontal line, and finally another 'c'. A grey arrow indicates the flow from the first segment to the second, suggesting a transformation or mapping from the input (the first segment) to the output (the second segment). The red lines visually highlight groups of elements undergoing a specific operation or transformation, while the grey arrows represent the overall transformation process between the input and output.  The letters 'a', 'b', and 'c' likely represent data elements or variables.
Input: s = 'aacabba'
Output: 'c'
Example 2:
Image represents a simple data flow diagram illustrating a data transformation or processing step.  The diagram shows two instances of the variable 'a' connected by a thick red horizontal line, suggesting a direct, possibly in-place, modification or operation on 'a'. This is followed by a grey arrow pointing to another instance of 'a', indicating that the modified 'a' (from the red line connection) is then passed on or transformed into a new, potentially different, 'a'. The absence of labels on the connections or the 'a' variables themselves prevents a more precise description of the specific operation or transformation involved, but the visual structure clearly shows a sequential process where data ('a') undergoes a transformation and is then passed along.
Input: s = 'aaa'
Output: 'a'*/

    static void main(String[] args) {
        String str = "aacabba";
        System.out.println("Result: " + removeDuplicates(str));
    }

    private static String removeDuplicates(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
