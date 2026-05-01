package com.practice.pointers;

public class LargestContainer {

    /*
        Largest Container
You are given an array of numbers, each representing the height of a vertical line on a graph.
A container can be formed with any pair of these lines, along with the x-axis of the graph.
Return the amount of water which the largest container can hold.

Example:
Image represents a bar chart or histogram with a horizontal x-axis ranging from 0 to 5 and a vertical y-axis ranging from 0 to 8.  The y-axis represents frequency or count, while the x-axis likely represents categories or data points.  The chart displays several vertical bars of varying heights. A bar at x=0 has a height of 2.  Bars at x=1, x=2, x=4, and x=5 all reach a height of 6, forming a plateau of light-blue filled area.  A shorter bar at x=3 reaches a height of 3.  The bars are represented by vertical black lines extending from the x-axis to their respective heights on the y-axis.  The area under the bars from y=0 to y=6 between x=1 and x=5 is filled with light blue, visually highlighting the consistent frequency across those data points.
Input: heights = [2, 7, 8, 3, 7, 6]
Output: 24
    * */

    static void main(String[] args) {
        int[] heights = {2, 7, 8, 3, 7, 6};
        System.out.println(largestContainerBruteForce(heights));
        System.out.println(largestContainer(heights));
    }

    private static int largestContainer(int[] heights) {
        int left = 0, right = heights.length -1, max_water = 0;

        while (left < right) {
            int height = Math.min(heights[left], heights[right]);
            int width = right - left;
            int water = height * width;
            max_water = Math.max(max_water, water);

            if (heights[left] < heights[right]) {
                left++;
            } else if (heights[left] > heights[right]) {
                right--;
            } else  {
                left++;
                right--;
            }
        }

        return max_water;
    }

    private static int largestContainerBruteForce(int[] heights) {
        int n = heights.length;

        int max_water = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int height = Math.min(heights[i], heights[j]);
                int width = j - i;
                int water = height * width;
                max_water = Math.max(max_water, water);
            }
        }

        return max_water;
    }
}
