package com.practice.stack;

import java.util.Stack;

class EvaluateExpression {

    /*Evaluate Expression
Given a string representing a mathematical expression containing integers, parentheses, addition, and subtraction operators, evaluate and return the result of the expression.

Example:
Input: s = '18-(7+(2-4))'
Output: 13*/

    static void main(String[] args) {
        String str = "18-(7+(2-4))";
        System.out.println("Result: " + evaluateExpression(str));
    }

    private static int evaluateExpression(String str) {

        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0, result = 0, sign = 1;

        for (char c : str.toCharArray()) {

            if (Character.isDigit(c)) {

                currentNumber = currentNumber * 10 + (c - '0');

            } else if (c == '+' || c == '-') {

                result += currentNumber * sign;
                sign = c == '+' ? 1 : -1;
                currentNumber = 0;

            } else if (c == '(') {

                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;

            } else if (c == ')') {

                result += currentNumber * sign;

                result = result * stack.pop();

                result += stack.pop();

                currentNumber = 0;

            }
        }

        return result + currentNumber * sign;
    }
}
