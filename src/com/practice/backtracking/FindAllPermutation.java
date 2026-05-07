package com.practice.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class FindAllPermutation {

    /*Find All Permutations
Return all possible permutations of a given array of unique integers. They can be returned in any order.

Example:
Input: nums = [4, 5, 6]
Output: [[4, 5, 6], [4, 6, 5], [5, 4, 6], [5, 6, 4], [6, 4, 5], [6, 5, 4]]*/

    static void main(String[] args) {
        int[] nums = {4, 5, 6};
        List<List<Integer>> permutations = findAllPermutations(nums);
        System.out.println(permutations);
    }

    private static List<List<Integer>> findAllPermutations(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        //backtracking(nums, new boolean[nums.length], new ArrayList<>(), permutations);

        backtrack(nums, new HashSet<Integer>(), new ArrayList<Integer>(), permutations);

        return permutations;
    }

    private static void backtrack(int[] nums, HashSet<Integer> used, ArrayList<Integer> current, List<List<Integer>> permutations) {
        if (current.size() == nums.length) {
            permutations.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {

            if (!used.contains(num)) {

                current.add(num);
                used.add(num);

                backtrack(nums, used, current, permutations);

                current.remove(current.size() - 1);
                used.remove(num);
            }
        }
    }

    /*Whiteboard: FindAllPermutation.backtracking() Method
Input: nums = [4, 5, 6]

Method Signature:

java
backtracking(nums, used, current, permutations)
Algorithm: For each position, try placing each unused element.

Iteration Walkthrough:
Initial call: backtracking([4,5,6], [F,F,F], [], permutations)
Level 0 (position 0):

Try nums[0] = 4 (unused)
├── current = [4]
├── used = [T,F,F]
├── backtracking([4,5,6], [T,F,F], [4], permutations)

Try nums[1] = 5 (unused)
├── current = [5]
├── used = [F,T,F]
├── backtracking([4,5,6], [F,T,F], [5], permutations)

Try nums[2] = 6 (unused)
├── current = [6]
├── used = [F,F,T]
├── backtracking([4,5,6], [F,F,T], [6], permutations)
Level 1 (position 1) - from [4]:

From [4] with used = [T,F,F]:
├── Try nums[1] = 5 (unused): [4,5] → used = [T,T,F]
└── Try nums[2] = 6 (unused): [4,6] → used = [T,F,T]
Level 2 (position 2) - from [4,5]:

From [4,5] with used = [T,T,F]:
├── Try nums[2] = 6 (unused): [4,5,6] → used = [T,T,T] ✓ BASE CASE
Complete Tree:

       []
      / | \
    4   5   6
   / \   \   \
 45  46  54  56
 |    |   |   |
456  465 546 565  ← BASE CASES
Detailed Trace:

1. [] → [4] → [4,5] → [4,5,6] ✓ ADD [4,5,6]
   Backtrack: [4,5] → [4] → []

2. [] → [4] → [4,6] → [4,6,5] ✓ ADD [4,6,5]
   Backtrack: [4,6] → [4] → []

3. [] → [5] → [5,4] → [5,4,6] ✓ ADD [5,4,6]
   Backtrack: [5,4] → [5] → []

4. [] → [5] → [5,6] → [5,6,4] ✓ ADD [5,6,4]
   Backtrack: [5,6] → [5] → []

5. [] → [6] → [6,4] → [6,4,5] ✓ ADD [6,4,5]
   Backtrack: [6,4] → [6] → []

6. [] → [6] → [6,5] → [6,5,4] ✓ ADD [6,5,4]
   Backtrack: [6,5] → [6] → []
Base Case (current.size() == nums.length): When we have a complete permutation, we add it to permutations.

Final Result:

permutations = [
  [4,5,6], [4,6,5], [5,4,6],
  [5,6,4], [6,4,5], [6,5,4]
]
Key Concepts:
Decision Tree: Each position creates n branches (one for each unused element)
Backtracking: After exploring a branch, we backtrack by removing the element and marking it unused
Used Array: Tracks which elements are already in the current permutation
Base Case: When permutation is complete (current.size() == nums.length)
Time Complexity: O(n!) - n choices for first position, n-1 for second, etc.
Space Complexity: O(n) - recursion stack + used array
The method systematically explores all possible orderings by trying each unused element at each position.
*/
    private static void backtracking(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> permutations) {
        if (current.size() == nums.length) {
            permutations.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (!used[i]) {

                current.add(nums[i]);
                used[i] = true;

                backtracking(nums, used, current, permutations);

                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
}
