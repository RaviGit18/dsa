package com.practice.backtracking;

import java.util.ArrayList;
import java.util.List;

class FindAllSubSets {
    /*Find All Subsets
Return all possible subsets of a given set of unique integers. Each subset can be ordered in any way, and the subsets can be returned in any order.

Example:
Input: nums = [4, 5, 6]
Output: [[], [4], [4, 5], [4, 5, 6], [4, 6], [5], [5, 6], [6]]
*/

    static void main(String[] args) {
        int[] nums = {4, 5, 6};
        List<List<Integer>> subsets = findAllSubsets(nums);
        System.out.println(subsets);
    }

    private static List<List<Integer>> findAllSubsets(int[] nums) {

        List<List<Integer>> subsets = new ArrayList<>();

        backtrack(nums, 0, new ArrayList<>(), subsets);

        return subsets;
    }

    /*
Whiteboard: FindAllSubSets.backtrack() Method
Input: nums = [4, 5, 6]

Method Signature:

java
backtrack(nums, i, currentSubSet, subsets)
Algorithm: For each element, we have two choices - include it or exclude it.

Iteration Walkthrough:
Initial call: backtrack([4,5,6], 0, [], subsets)
Level 0 (i=0, element=4):

Path 1: Include 4
├── currentSubSet = [4]
├── backtrack([4,5,6], 1, [4], subsets)

Path 2: Exclude 4
├── currentSubSet = []
├── backtrack([4,5,6], 1, [], subsets)
Level 1 (i=1, element=5):

From Path 1 ([4]):
├── Include 5: [4,5] → backtrack([4,5,6], 2, [4,5], subsets)
└── Exclude 5: [4]   → backtrack([4,5,6], 2, [4], subsets)

From Path 2 ([]):
├── Include 5: [5]   → backtrack([4,5,6], 2, [5], subsets)
└── Exclude 5: []    → backtrack([4,5,6], 2, [], subsets)
Level 2 (i=2, element=6):

From [4,5]:
├── Include 6: [4,5,6] → backtrack([4,5,6], 3, [4,5,6], subsets) ✓ BASE CASE
└── Exclude 6: [4,5]   → backtrack([4,5,6], 3, [4,5], subsets) ✓ BASE CASE

From [4]:
├── Include 6: [4,6]   → backtrack([4,5,6], 3, [4,6], subsets) ✓ BASE CASE
└── Exclude 6: [4]     → backtrack([4,5,6], 3, [4], subsets) ✓ BASE CASE

From [5]:
├── Include 6: [5,6]   → backtrack([4,5,6], 3, [5,6], subsets) ✓ BASE CASE
└── Exclude 6: [5]     → backtrack([4,5,6], 3, [5], subsets) ✓ BASE CASE

From []:
├── Include 6: [6]     → backtrack([4,5,6], 3, [6], subsets) ✓ BASE CASE
└── Exclude 6: []      → backtrack([4,5,6], 3, [], subsets) ✓ BASE CASE
Base Case (i=3 == nums.length): When i == nums.length, we add currentSubSet to subsets.

Final Result:

subsets = [
  [4,5,6], [4,5], [4,6], [4],
  [5,6], [5], [6], []
]
Key Concepts:
Decision Tree: Each element creates 2 branches (include/exclude)
Backtracking: After exploring include branch, we backtrack by removing the element
Base Case: When we've considered all elements (i == nums.length)
Time Complexity: O(2^n) - 2 choices for each of n elements
Space Complexity: O(n) - recursion stack depth
The method systematically explores all possible combinations by making binary choices at each level of recursion.
*/
    private static void backtrack(int[] nums, int i, ArrayList<Integer> currentSubSet, List<List<Integer>> subsets) {

        if (i == nums.length) {
            subsets.add(new ArrayList<>(currentSubSet));
            return;
        }

        currentSubSet.add(nums[i]);
        backtrack(nums, i + 1, currentSubSet, subsets);

        currentSubSet.remove(currentSubSet.size() - 1);
        backtrack(nums, i + 1, currentSubSet, subsets);

    }
}
