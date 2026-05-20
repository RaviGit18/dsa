package com.practice.onlineTest.hacker;

import java.util.*;

class SafeIntegerSum {

    /*java program to calculate the sum of a list of integers, the partial integer sum could overflow, but the sum is guaranteed to be able to fit into a 32bit int type.
    This is a classic overflow-safe summation problem.

Problem

You need to sum integers where:

intermediate (partial) sum may overflow
but final answer is guaranteed to fit in int

Example:

[2147483647, -2147483648, 10, -9]

Partial sums may overflow if using int.

Correct Approach

Use a wider datatype during calculation: long sum
Then safely cast back to int.

Why This Works

int range: −2^31 to 2^31 −1

long range is much larger: −2^63 to 2^63 −1

So intermediate overflow is avoided.

Time Complexity
O(N)
Space Complexity
O(1)
*/
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                10,
                -9
        );

        int result = safeSum(numbers);

        System.out.println(
                "Safe Sum = " + result);

        int result1 = (int) numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println(
                "Safe Sum = " + result1);
    }

    public static int safeSum(List<Integer> numbers) {

        long sum = 0;

        for (int num : numbers) {
            sum += num;
        }

        // Final result guaranteed to fit in int
        return (int) sum;
    }


}
