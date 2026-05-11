package com.practice.onlineTest;

import java.util.*;

public class LeagueTableSort {

    /*
        java program Sort the table of leagues scores based on their scores.
Problem Example

Input:

Team        Score
India       12
Australia   18
England     15
Pakistan    10

Sorted Output (Descending by score):

Australia   18
England     15
India       12
Pakistan    10

Output
League Table:
Australia -> 18
England -> 15
India -> 12
Pakistan -> 10

Better Comparator (Avoid Integer Overflow)

Instead of:

t2.score - t1.score

Prefer:

Integer.compare(t2.score, t1.score)

Updated sorting:

teams.sort((t1, t2) ->
        Integer.compare(t2.score, t1.score));
Java 8 Stream Version
teams.stream()
     .sorted((t1, t2) ->
         Integer.compare(t2.score, t1.score))
     .forEach(System.out::println);
Multi-Level Sorting

Very common interview follow-up:

Sort by:

Higher score first
If scores equal → sort by name
teams.sort((t1, t2) -> {

    if (t1.score != t2.score) {
        return Integer.compare(t2.score, t1.score);
    }

    return t1.name.compareTo(t2.name);
});
Time Complexity

Sorting:

O(N log N)

Where N = number of teams.
    * */
    public static void main(String[] args) {

        List<Team> teams = new ArrayList<>();

        teams.add(new Team("India", 12));
        teams.add(new Team("Australia", 18));
        teams.add(new Team("England", 15));
        teams.add(new Team("Pakistan", 10));

        // Sort by score descending
        teams.sort((t1, t2) -> t2.score - t1.score);

        System.out.println("League Table:");

        for (Team team : teams) {
            System.out.println(team);
        }
    }
}

class Team {

    String name;
    int score;

    Team(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " -> " + score;
    }
}