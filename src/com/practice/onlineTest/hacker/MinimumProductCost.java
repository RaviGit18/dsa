package com.practice.onlineTest.hacker;

import java.util.*;

public class MinimumProductCost {

    /*Problem Understanding

We have:

N products
each product has:
    base price
    zero or more discounts

Discounts may be:

percentage discount
fixed amount discount
buy-X-get-Y
same tag with multiple discount rules

Goal:

Find minimum total purchase cost.
Key Challenge

Multiple discounts may apply to the same product.

Example:

| Product | Base Price | Discounts         |
| ------- | ---------- | ----------------- |
| Laptop  | 1000       | 10% OFF, ₹200 OFF |
| Phone   | 500        | Buy2Get1          |


Some discounts:

can stack
some are mutually exclusive
some apply only if grouped

This becomes:
Optimization Problem

General Strategy

We solve product-by-product:

Generate all valid discount combinations
Compute effective price
Choose minimum valid price

For group discounts:

use DP / graph matching / greedy depending on type

Discount Types & Math
1. Percentage Discount

If:

price = P
discount = d%

Then:

newPrice=P×(1−d/100)

Example:

1000 with 20% OFF

Result:

800
2. Fixed Discount

If:

price = P
fixed discount = f

Then:

newPrice=max(0,P−f)

Example:

1000 - 200

Result:

800
3. Buy-X-Get-Y

Example:

Buy 2 Get 1 Free

Sort prices descending.

For every (X+Y) items:

cheapest Y become free.

Example:

[100,80,50]

Pay:

100 + 80

Free:

50
Important Business Rule

Usually:

discounts with same tag are mutually exclusive

Example:

SUMMER10
SUMMER20

Cannot apply both.

So for each tag:

choose best discount only.
Recommended Approach
Step 1 — Normalize Discounts

Create:

class Discount {
    String tag;
    String type;
    double value;
}
Step 2 — Generate Best Price Per Product

For each product:

evaluate all valid discount combinations
keep minimum achievable price
Step 3 — Handle Group Discounts

Use:

greedy
OR
DP

depending on complexity.


Advanced Optimization

If:

discounts interact globally
shared coupons exist
limited coupon count exists

Then use:

| Scenario             | Technique                  |
| -------------------- | -------------------------- |
| Independent products | Greedy                     |
| Limited coupons      | DP                         |
| Bundle discounts     | Graph / DP                 |
| Complex rules        | Backtracking + Memoization |
| Large-scale commerce | Integer Linear Programming |

Time Complexity

If:

N products
D discounts/product

Simple evaluation:

O(N × D)

If combinations allowed:

O(N × 2^D)

Interview Discussion Points

Strong interview answers mention:

    Discount conflict rules
    Stackable vs exclusive discounts
    Order of applying discounts matters

    Example:

    (P−fixed)×percentage != P×percentage−fixed

    Precision issues
    Coupon optimization
    Real e-commerce scaling
    Real-World Systems

Platforms like:

Amazon
Flipkart
Walmart

typically implement:

pricing engines
rule engines
promotion conflict resolvers
coupon prioritization systems
*/
    public static void main(String[] args) {

        List<Product> products =
                new ArrayList<>();

        products.add(
                new Product(
                        "Laptop",
                        1000,
                        Arrays.asList(
                                new Discount(
                                        "SUMMER",
                                        "PERCENTAGE",
                                        20),

                                new Discount(
                                        "SAVE",
                                        "FIXED",
                                        150)
                        )
                )
        );

        double result =
                minimumCost(products);

        System.out.println(
                "Minimum Total Cost = "
                        + result);
    }

    public static double minimumCost(
            List<Product> products) {

        double total = 0;

        for (Product product : products) {

            double minPrice =
                    product.basePrice;

            for (Discount d :
                    product.discounts) {

                double candidate =
                        applyDiscount(
                                product.basePrice,
                                d);

                minPrice =
                        Math.min(minPrice,
                                candidate);
            }

            total += minPrice;
        }

        return total;
    }

    private static double applyDiscount(
            double price,
            Discount d) {

        switch (d.type) {

            case "PERCENTAGE":

                return price *
                        (1 - d.value / 100.0);

            case "FIXED":

                return Math.max(
                        0,
                        price - d.value);

            default:

                return price;
        }
    }


}

class Discount {

    String tag;
    String type;

    double value;

    Discount(String tag,
             String type,
             double value) {

        this.tag = tag;
        this.type = type;
        this.value = value;
    }
}

class Product {

    String name;
    double basePrice;

    List<Discount> discounts;

    Product(String name,
            double basePrice,
            List<Discount> discounts) {

        this.name = name;
        this.basePrice = basePrice;
        this.discounts = discounts;
    }
}

