package com.practice.stack;

import java.util.HashMap;
import java.util.Stack;

class ValidParanthesisExpression {

    /*Valid Parenthesis Expression
Given a string representing an expression of parentheses containing the characters '(', ')', '[', ']', '{', or '}', determine if the expression forms a valid sequence of parentheses.

A sequence of parentheses is valid if every opening parenthesis has a corresponding closing parenthesis, and no closing parenthesis appears before its matching opening parenthesis.

Example 1:
Input: s = '([]{})'
Output: True
Example 2:
Input: s = '([]{)}'
Output: False
Explanation: The '(' parenthesis is closed before its nested '{' parenthesis is closed.*/

    static void main(String[] args) {
        String str1 = "([]{})";
        String str2 = "([]{)}";
        System.out.println("Is valid (str1): " + isValid(str1));
        System.out.println("Is valid (str2): " + isValid(str2));
    }

    private static boolean isValid(String str) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (map.containsValue(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
