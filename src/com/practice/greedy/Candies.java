package com.practice.greedy;

class Candies {
    /*Candies
You teach a class of children sitting in a row, each of whom has a rating based on their performance. You want to distribute candies to the children while abiding by the following rules:

Each child must receive at least one candy.
If two children sit next to each other, the child with the higher rating must receive more candies.
Determine the minimum number of candies you need to distribute to satisfy these conditions.

Example 1:
Input: ratings = [4, 3, 2, 4, 5, 1]
Output: 12
Explanation: You can distribute candies to each child as follows: [3, 2, 1, 2, 3, 1].

Example 2:
Input: ratings = [1, 3, 3]
Output: 4
Explanation: You can distribute candies to each child as follows: [1, 2, 1].
*/

    static void main(String[] args) {
        int[] ratings = {4, 3, 2, 4, 5, 1};
        System.out.println("Minimum candies: " + minCandies(ratings));
    }

    private static int minCandies(int[] ratings) {

        int[] candies = new int[ratings.length];

        for (int i = 0; i < ratings.length; i++) {
            candies[i] = 1;
        }

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int totalCandies = 0;

        for (int candy : candies) {
            totalCandies += candy;
        }

        return  totalCandies;
    }
}
