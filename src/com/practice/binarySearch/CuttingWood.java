package com.practice.binarySearch;

class CuttingWood {

    /*Cutting Wood
You are given an array representing the heights of trees, and an integer k representing the total length of wood that needs to be cut.

For this task, a woodcutting machine is set to a certain height, H. The machine cuts off the top part of all trees taller than H, while trees shorter than H remain untouched. Determine the highest possible setting of the woodcutter (H) so that it cuts at least k meters of wood.

Assume the woodcutter cannot be set higher than the height of the tallest tree in the array.

Input: heights = [2, 6, 3, 8], k = 7
Output: 3

Explanation: The highest possible height setting that yields at least k = 7 meters of wood is 3, which yields 8 meters of wood. Any height setting higher than this will yield less than 7 meters of wood.

Constraints:
It's always possible to attain at least k meters of wood.
There's at least one tree.
*/

    static void main(String[] args) {
        int[] heights = {2, 6, 3, 8};
        int k = 7;
        System.out.println("Maximum height: " + maxHeight(heights, k));
    }

    private static int maxHeight(int[] heights, int k) {
        int left = 0;
        int right = max(heights);

        while (left < right) {

            int mid = (left + (right - left) / 2) + 1;

            if (cutsEnoughWood(heights, mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private static boolean cutsEnoughWood(int[] heights, int mid, int k) {
        int total = 0;
        for (int height : heights) {
            total += Math.max(0, height - mid);
        }
        return total >= k;
    }

    private static int max(int[] heights) {
        int max = 0;
        for (int height : heights) {
            max = Math.max(max, height);
        }
        return max;
    }
}
