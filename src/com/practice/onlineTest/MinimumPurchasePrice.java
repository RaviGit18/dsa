package com.practice.onlineTest;

import java.util.*;

public class MinimumPurchasePrice {

    /*java program for A store offers n items, each with a price specified in the array price[i]. A customer has m discount coupons. When x coupons are applied to the ith item, its price is reduced to floor(price[i]/2).

Determine the minimum total cost to purchase all items by optimally allocating at most m discount coupons.

Example

Consider n = 2, price = [2, 4], m = 2.

The optimum solution:

Purchase item 1 for 2.

Use 2 coupons on item 2. so the discounted price is 4/22 = 4 /4=1.

The amount required = 2+1=3.

Function Description

Complete the function findMinimumPrice in the editor with the following parameters:

int price[n]: the original prices of the items

int m: the number of discount coupons

Returns

int: the minimum amount of money needed to buy all n items
*/

    /*Key Observation

Applying one coupon to an item reduces its price as:

newPrice=[price/2]

Applying coupons repeatedly:

price -> floor(price/2)

Example:

10 -> 5 -> 2 -> 1 -> 0
Goal

Use at most m coupons to minimize total cost.

At every step:

apply coupon to item giving maximum reduction.

This is a classic:

Greedy + Max Heap

problem.

Greedy Insight

Reduction obtained:

reduction=price−[price/2]

Always choose the current largest price because:

it gives maximum immediate reduction.
Example

Input:

price = [2,4]
m = 2

Initial sum:

6

Apply coupon on 4:

4 -> 2
sum = 4

Apply coupon again on 2:

2 -> 1
sum = 3

Answer:

3

Another Example

Input:

price = [10,20,7]
m = 3

Operations:

20 -> 10
10 -> 5
10 -> 5

Final prices:

5,5,7

Total:

17
Time Complexity

Let:

n = number of items

Heap operations:

O(log n)

Performed m times:

O(m log n)
Space Complexity

Heap stores all items:

O(n)
Why Greedy Works

Each coupon independently reduces price.

Largest current price always provides maximum reduction:
x − [x/2] ≥ y − [y/2] for x≥y
Thus choosing maximum price at every step is optimal.

*/
    public static void main(String[] args) {

        int[] price = {2, 4};

        int m = 2;

        int result =
                findMinimumPrice(price, m);

        System.out.println(
                "Minimum Total Price = "
                        + result);
    }

    public static int findMinimumPrice(
            int[] price,
            int m) {

        // Max Heap
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(
                        Collections.reverseOrder());

        int total = 0;

        // Add all prices
        for (int p : price) {

            maxHeap.offer(p);
            total += p;
        }

        // Apply coupons greedily
        while (m > 0 && !maxHeap.isEmpty()) {

            int current = maxHeap.poll();

            int discounted =
                    current / 2;

            // Reduction
            total -= (current - discounted);

            // Add updated price back
            maxHeap.offer(discounted);

            m--;
        }

        return total;
    }


}