package com.practice.onlineTest.hacker;

class MostFrequentDigit {

    /*Most Frequent Digit in a Number
Example

Input:

99887766559

Digit frequencies:

9 -> 3
8 -> 2
7 -> 2
6 -> 2
5 -> 2

Output: 9

Time Complexity
O(number of digits)
*/
    public static void main(String[] args) {

        long number = 99887766559L;

        int result = mostFrequentDigit(number);

        System.out.println("Most Frequent Digit = " + result);
    }

    public static int mostFrequentDigit(long number) {

        int[] freq = new int[10];

        // Count digit frequencies
        while (number > 0) {

            int digit = (int)(number % 10);

            freq[digit]++;

            number /= 10;
        }

        int maxFreq = 0;
        int result = 0;

        // Find most frequent digit
        for (int i = 0; i <= 9; i++) {

            if (freq[i] > maxFreq) {

                maxFreq = freq[i];
                result = i;
            }
        }

        return result;
    }


}
