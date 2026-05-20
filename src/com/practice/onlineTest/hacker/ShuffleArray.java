package com.practice.onlineTest.hacker;

import java.util.*;

class ShuffleArray {


    /*Shuffle an Array in Java

The best way to shuffle an array uniformly is using the:

Fisher–Yates Shuffle Algorithm

It produces a truly random permutation in:

O(N)

time.

How It Works

Starting from the last index:

Pick a random index from 0 to i
Swap elements

Core step:

swap(arr[i], arr[random(0,i)])


Time Complexity
O(N)
Space Complexity
O(1)
Why Fisher–Yates Is Correct

Every permutation gets equal probability:

P(each permutation)= 1 / n!

This makes it unbiased.

Common Wrong Approach

Many people do:

for (int i = 0; i < n; i++) {
    swap(arr[i], arr[randomIndex]);
}

This is:

biased
not uniformly random

Interviewers often ask this.
*/

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        System.out.println(
                "Before Shuffle: "
                        + Arrays.toString(arr));

        shuffle(arr);

        System.out.println(
                "After Shuffle: "
                        + Arrays.toString(arr));



        //Java Collections Version- For List<Integer>
        //Internally, Java uses a Fisher–Yates style algorithm.
        List<Integer> list =
                Arrays.asList(1,2,3,4,5);

        Collections.shuffle(list);

        System.out.println(list);
    }

    public static void shuffle(int[] arr) {

        Random random = new Random();

        for (int i = arr.length - 1; i > 0; i--) {

            // Random index from 0 to i
            int j = random.nextInt(i + 1);

            // Swap
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


}
