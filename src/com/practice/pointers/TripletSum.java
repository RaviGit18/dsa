package com.practice.pointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class TripletSum {
    public static void main(String[] args) {
        /*
            Triplet Sum
            Given an array of integers, return all triplets [a, b, c] such that a + b + c = 0 .
            The solution must not contain duplicate triplets (e.g., [1, 2, 3] and [2, 3, 1] are considered duplicates).
            If no such triplets are found, return an empty array.

            Each triplet can be arranged in any order, and the output can be returned in any order.

            Input: nums = [0, -1, 2, -3, 1]
            Output: [[-3, 1, 2], [-1, 0, 1]]
        */
        ArrayList<Integer> nums = new ArrayList<>(List.of(0, -1, 2, -3, 1));
        System.out.println(tripletSumBruteForce(nums));
        System.out.println(tripletSum(nums));
    }

    private static List<List<Integer>> tripletSum(List<Integer> nums) {
        int n = nums.size();
        List<List<Integer>> triplets = new ArrayList<>();
        Collections.sort(nums);

        for (int i = 0; i < n; i++) {

            if (nums.get(i) > 0) {
                break;
            }

            if (i > 0 && nums.get(i).equals(nums.get(i - 1))) {
                continue;
            }

            List<List<Integer>> pairs = pairSum(nums, i + 1, -nums.get(i));

            for (List<Integer> pair : pairs) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums.get(i));
                triplet.addAll(pair);

                triplets.add(triplet);
            }
        }

        return triplets;
    }

    private static List<List<Integer>> pairSum(List<Integer> nums, int start, int target) {
        List<List<Integer>> pairs = new ArrayList<>();

        int left = start, right = nums.size() - 1;

        while (left < right) {

            int sum = nums.get(left) + nums.get(right);

            if (sum == target) {
                List<Integer> pair = new ArrayList<>();
                pair.add(nums.get(left));
                pair.add(nums.get(right));
                pairs.add(pair);

                left++;

                while (left < right && nums.get(left).equals(nums.get(left - 1))) {
                    left++;
                }

            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return pairs;
    }

    private static List<List<Integer>> tripletSumBruteForce(List<Integer> nums) {
        int n = nums.size();
        HashSet<List<Integer>> triplets = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums.get(i) + nums.get(j) + nums.get(k) == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums.get(i));
                        triplet.add(nums.get(j));
                        triplet.add(nums.get(k));

                        Collections.sort(triplet);
                        triplets.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(triplets);
    }
}
