package com.practice.dynamicProgrammming;

import java.util.*;

/**
 * Painter’s Partition Problem
 * You have k painters and n boards of different lengths. Each painter paints contiguous boards.
 * Find the minimum time to paint all boards if each unit length takes 1 unit of time.
 * */
public class PaintersPartition {

    public static void main(String[] args) {
        int[] boards = {10, 20, 30, 40};
        int k = 2;

        System.out.println("Boards: " + Arrays.toString(boards));
        System.out.println("Painters: " + k);
        int result = minTime(boards, k);
        System.out.println("Minimum time to paint all boards = " + result);
    }

    // Function to check if boards can be painted within maxTime using k painters
    /*
    * MinTime(boards, k):
        low = max(boards)
        high = sum(boards)
        result = high

        while low <= high:
            mid = (low + high) / 2
            if IsPossible(boards, k, mid):
                result = mid
                high = mid - 1
            else:
                low = mid + 1

        return result

    IsPossible(boards, k, maxTime):
        painters = 1
        currSum = 0
        for each board in boards:
            if board > maxTime:
                return false
            if currSum + board > maxTime:
                painters++
                currSum = board
                if painters > k:
                    return false
            else:
                currSum += board
        return true

    * */
    private static boolean isPossible(int[] boards, int k, int maxTime) {
        int painters = 1;
        int currSum = 0;

        for (int length : boards) {
            if (length > maxTime) return false; // single board exceeds maxTime
            if (currSum + length > maxTime) {
                painters++;
                currSum = length;
                if (painters > k) return false;
            } else {
                currSum += length;
            }
        }
        return true;
    }

    // Function to find minimum time
    public static int minTime(int[] boards, int k) {
        int low = Arrays.stream(boards).max().getAsInt(); // largest board
        int high = Arrays.stream(boards).sum();           // sum of all boards
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(boards, k, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }


}

