package com.practice.onlineTest;

import java.util.*;

/**
 * CodingScoreLevels - Classifies users into coding score levels
 * 
 * Problem: Categorize users into 5 coding score levels (Poor, Fair, Good, Excellent, Elite)
 * 
 * Algorithm: Single pass counting + custom sorting by count then level priority
 * 
 * Time Complexity: O(N) where N = number of scores
 * Space Complexity: O(1) - fixed 5 categories
 */
class CodingScoreLevels {

    /*Problem Statement

The marketing team at CodeSignal wants to know how many users fall into each Coding Score range.

You are given a list of coding scores where each score is between:

300 and 850

The score levels are defined as:

Level	Score Range
Poor	300–599
Fair	600–699
Good	700–749
Excellent	750–799
Elite	800+
Task

Calculate how many users belong to each level.

Return a list of strings formatted as:

"LevelName - count"
Sorting Rules
Sort by:
descending order of user count
If two levels have same count:
higher level appears first
Omit levels with:
zero users
Example

Input:

[330, 723, 730, 825]

Counts:

Level	Count
Poor	1
Good	2
Elite	1

Sorted result:

[
 "Good - 2",
 "Elite - 1",
 "Poor - 1"
]

Because:

Good has highest count
Elite and Poor both have count 1
Elite is higher level than Poor

Time Complexity

Counting:

O(N)

Sorting:

only 5 levels
O(1)

Overall:

O(N)
Space Complexity
O(1)

Since maximum 5 categories exist.
*/
    public static void main(String[] args) {

        int[] scores =
                {330, 723, 730, 825};

        List<String> result =
                classifyScores(scores);

        System.out.println(result);
    }

    static class Level {

        String name;
        int count;
        int rank;

        Level(String name,
              int count,
              int rank) {

            this.name = name;
            this.count = count;
            this.rank = rank;
        }
    }

    public static List<String> classifyScores(
            int[] scores) {

        Map<String, Integer> map =
                new HashMap<>();

        // Count users in each level
        for (int score : scores) {

            String level;

            if (score >= 800) {
                level = "Elite";
            }
            else if (score >= 750) {
                level = "Excellent";
            }
            else if (score >= 700) {
                level = "Good";
            }
            else if (score >= 600) {
                level = "Fair";
            }
            else {
                level = "Poor";
            }

            map.put(level,
                    map.getOrDefault(level, 0) + 1);
        }

        // Higher rank => stronger level
        Map<String, Integer> rankMap =
                new HashMap<>();

        rankMap.put("Poor", 1);
        rankMap.put("Fair", 2);
        rankMap.put("Good", 3);
        rankMap.put("Excellent", 4);
        rankMap.put("Elite", 5);

        List<Level> levels =
                new ArrayList<>();

        // Build objects
        for (String key : map.keySet()) {

            levels.add(
                    new Level(
                            key,
                            map.get(key),
                            rankMap.get(key)
                    )
            );
        }

        // Sort:
        // 1. Desc count
        // 2. Higher level first
        levels.sort((a, b) -> {

            if (b.count != a.count) {
                return b.count - a.count;
            }

            return b.rank - a.rank;
        });

        List<String> result =
                new ArrayList<>();

        for (Level l : levels) {

            result.add(
                    l.name + " - " + l.count
            );
        }

        return result;
    }


}