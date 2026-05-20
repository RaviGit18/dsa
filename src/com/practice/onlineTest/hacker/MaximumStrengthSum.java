package com.practice.onlineTest.hacker;

/*
* Given an array of `n` integers,
you can perform the following operation any number of times:

    1. Choose any index `i` (`0 ≤ i < n - 1`) and swap `arr[i]` and `arr[i + 1]`.
    2. Each element can be swapped at most once during the process.

The strength of an index `i` is defined as: [ arr[i] * (i + 1) ] using 0-based indexing.

Find the maximum possible sum of the strength of all indices after optimal swaps:
        summation of [ arr[i] * (i + 1) ] where i = 0 to n-1

---

# Example
    n = 4
    arr = [2, 1, 4, 3]

    Optimal swaps:
        * Swap `arr[2]` and `arr[3]`
        * Swap `arr[0]` and `arr[1]`

    Final array: [1, 2, 3, 4]

    Sum of strengths: (1 * 1) + (2 * 2) + (3 * 3) + (4 * 4) = 30
    So, the maximum possible value is: 30

* */
public class MaximumStrengthSum {

    public static void main(String[] args) {

        int[] arr = {2, 1, 4, 3};

        long result = maximumStrengthSum(arr);

        System.out.println("Maximum Strength Sum = " + result);

        System.out.print("Final Array = ");

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
        * Swap Decision

            For adjacent elements: arr[i], arr[i+1]

            Current contribution: arr[i](i+1)+arr[i+1](i+2)

            After swap: arr[i+1](i+1)+arr[i](i+2)

            Difference: =arr[i]−arr[i+1]

            So swap only if: arr[i]<arr[i+1]
        *  Time Complexity
            O(n)
            Space Complexity
            O(n)
    * */
    public static long maximumStrengthSum(int[] arr) {

        int n = arr.length;

        /*
         If we swap adjacent elements:

         Before:
         arr[i] * (i+1) + arr[i+1] * (i+2)

         After swap:
         arr[i+1] * (i+1) + arr[i] * (i+2)

         Gain after swap:
         = arr[i] - arr[i+1]

         Swap is beneficial when:
         arr[i] < arr[i+1]
        */

        boolean[] used = new boolean[n];

        for (int i = 0; i < n - 1; i++) {

            // both elements must not be swapped before
            if (!used[i] && !used[i + 1]
                    && arr[i] < arr[i + 1]) {

                // swap
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;

                // mark both as used
                used[i] = true;
                used[i + 1] = true;
            }
        }

        // calculate final strength sum
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (long) arr[i] * (i + 1);
        }

        return sum;
    }


}
