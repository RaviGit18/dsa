package com.practice.onlineTest;

import java.util.*;

class ModuloMagicGame {

    /*simple and interesting Modulo Magic Game in Java based on array indexes.

Game Idea

You have:

an integer array
a starting index
a number of moves

Rule:

At every step:
jump to next index using:

nextIndex=(currentIndex+arr[currentIndex])modn

collect score from visited elements
stop if:
moves finish
loop detected
Example

Input:

arr = [2, 3, 1, 2, 4]
start = 0
moves = 5

Traversal:

0 → 2 → 3 → 0 (loop)

Collected values:

2 + 1 + 2 = 5


*/

    public static void main(String[] args) {

        int[] arr = {2, 3, 1, 2, 4};

        int start = 0;
        int moves = 5;

        int score =
                playGame(arr, start, moves);

        System.out.println(
                "Final Score = " + score);
    }

    public static int playGame(int[] arr,
                               int start,
                               int moves) {

        int n = arr.length;

        boolean[] visited = new boolean[n];

        int current = start;

        int score = 0;

        for (int step = 0; step < moves; step++) {

            // Loop detection
            if (visited[current]) {

                System.out.println(
                        "Loop detected at index: "
                                + current);

                break;
            }

            visited[current] = true;

            // Collect score
            score += arr[current];

            System.out.println(
                    "Visited index "
                            + current
                            + " value="
                            + arr[current]);

            // Modulo magic jump
            current =
                    (current + arr[current]) % n;
        }

        return score;
    }


}
