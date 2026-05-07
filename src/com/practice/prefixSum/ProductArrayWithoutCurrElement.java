package com.practice.prefixSum;

import java.util.Arrays;

class ProductArrayWithoutCurrElement {

    /*Product Array Without Current Element
Given an array of integers, return an array res so that res[i] is equal to the product of all the elements of the input array except nums[i] itself.

Example:
Input: nums = [2, 3, 1, 4, 5]
Output: [60, 40, 120, 30, 24]
Explanation: The output value at index 0 is the product of all numbers except nums[0] (3⋅1⋅4⋅5 = 60). The same logic applies to the rest of the output.
*/

    static void main(String[] args) {
        int[] input = new int[] { 2, 3, 1, 4, 5 };
        int[] output = productArrayWithoutCurrElement(input);
        System.out.println(Arrays.toString(output));
        output = productArrayWithoutCurrElementOptimized(input);
        System.out.println(Arrays.toString(output));
    }

    private static int[] productArrayWithoutCurrElementOptimized(int[] input) {
        int[] output = new int[input.length];
        output[0] = 1;

        for (int i = 1; i < input.length; i++) {
            output[i] = output[i - 1] * input[i - 1];
        }

        int rightProduct = 1;
        for (int i = input.length - 1; i >= 0; i--) {
            output[i] *= rightProduct;
            rightProduct *= input[i];
        }

        return output;
    }

    private static int[] productArrayWithoutCurrElement(int[] input) {
        int[] output = new int[input.length];
        int[] leftProduct = new int[input.length];
        int[] rightProduct = new int[input.length];

        leftProduct[0] = 1;
        rightProduct[input.length - 1] = 1;

        for (int i = 1; i < input.length; i++) {
            leftProduct[i] = leftProduct[i - 1] * input[i - 1];
        }

        for (int i = input.length - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * input[i + 1];
        }

        for (int i = 0; i < input.length; i++) {
            output[i] = leftProduct[i] * rightProduct[i];
        }

        return output;
    }
}
