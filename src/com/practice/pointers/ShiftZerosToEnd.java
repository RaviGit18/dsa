package com.practice.pointers;

class ShiftZerosToEnd {

    /*
    Shift Zeros to the End
Given an array of integers, modify the array in place to move all zeros to the end
while maintaining the relative order of non-zero elements.

Example:
Input: nums = [0, 1, 0, 3, 2]
Output: [1, 3, 2, 0, 0]
*/

    static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        shiftZerosToEndBruteforce(nums);
        System.out.println();
        shiftZerosToEnd(nums);
        System.out.println();
        shiftZerosToEnd1(nums);
    }

    private static void shiftZerosToEnd(int[] nums) {
        int left = 0, right = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                right++;
                continue;
            }

            if (nums[right] != 0) {

                if (nums[right] != nums[left]) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }

                left++;
                right++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            System.out.print(nums[j] + " ");
        }
    }

    private static void shiftZerosToEnd1(int[] nums) {
        int left = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] != 0) {

                if (nums[right] != nums[left]) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }

                left++;
            }
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void shiftZerosToEndBruteforce(int[] nums) {
        int[] result = new int[nums.length];

        int i = 0;
        for (int num : nums) {
            if (num != 0) {
                result[i] = num;
                i++;
            }
        }

        while (i < nums.length) {
            result[i] = 0;
            i++;
        }

        for (int j = 0; j < nums.length; j++) {
            nums[j] = result[j];
            System.out.print(nums[j] + " ");
        }
    }
}
