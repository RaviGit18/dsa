package com.practice.onlineTest.hacker;

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

        System.out.println("Original approach: " + result);
        
        // Test the optimized method
        List<String> optimizedResult =
                classifyScoresOptimized(scores);

        System.out.println("Optimized approach: " + optimizedResult);
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

    /**
     * Improved method: classifyScoresOptimized
     * 
     * Improvements:
     * 1. Uses enum for better type safety and readability
     * 2. Eliminates HashMap lookups with direct array indexing
     * 3. More concise and maintainable code
     * 4. Better performance with O(1) level determination
     * 
     * Time Complexity: O(N) where N = number of scores
     * Space Complexity: O(1) - fixed 5 categories
     */
    public static List<String> classifyScoresOptimized(int[] scores) {
        
        // Use enum for type safety and better code organization
        enum ScoreLevel {
            POOR("Poor", 1, 300, 599),
            FAIR("Fair", 2, 600, 699),
            GOOD("Good", 3, 700, 749),
            EXCELLENT("Excellent", 4, 750, 799),
            ELITE("Elite", 5, 800, Integer.MAX_VALUE);
            
            final String name;
            final int rank;
            final int minScore;
            final int maxScore;
            
            ScoreLevel(String name, int rank, int minScore, int maxScore) {
                this.name = name;
                this.rank = rank;
                this.minScore = minScore;
                this.maxScore = maxScore;
            }
            
            static ScoreLevel getLevel(int score) {
                for (ScoreLevel level : values()) {
                    if (score >= level.minScore && score <= level.maxScore) {
                        return level;
                    }
                }
                return POOR; // Default fallback
            }
        }
        
        // Count array for O(1) access (index corresponds to enum ordinal)
        int[] counts = new int[ScoreLevel.values().length];
        
        // Count users in each level - O(N) time
        for (int score : scores) {
            ScoreLevel level = ScoreLevel.getLevel(score);
            counts[level.ordinal()]++;
        }
        
        // Build list of non-zero levels
        List<ScoreLevelInfo> levels = new ArrayList<>();
        for (ScoreLevel level : ScoreLevel.values()) {
            int count = counts[level.ordinal()];
            if (count > 0) {
                levels.add(new ScoreLevelInfo(level.name, count, level.rank));
            }
        }
        
        // Sort by count (descending), then by rank (descending)
        levels.sort((a, b) -> {
            if (b.count != a.count) {
                return b.count - a.count;
            }
            return b.rank - a.rank;
        });
        
        // Format output
        List<String> result = new ArrayList<>();
        for (ScoreLevelInfo level : levels) {
            result.add(level.name + " - " + level.count);
        }
        
        return result;
    }
    
    /**
     * Helper class for optimized approach
     * More lightweight than original Level class
     */
    private static class ScoreLevelInfo {
        final String name;
        final int count;
        final int rank;
        
        ScoreLevelInfo(String name, int count, int rank) {
            this.name = name;
            this.count = count;
            this.rank = rank;
        }
    }

}