package com.practice.graph;

class MergingCommunities {

    /*Merging Communities
There are n people numbered from 0 to n - 1 , with each person initially belonging to a separate community. When two people from different communities connect, their communities merge into a single community.

Your goal is to write two functions:

connect(x: int, y: int) -> None: Connects person x with person y and merges their communities.
get_community_size(x: int) -> int: Returns the size of the community which person x belongs to.
Example:
Image represents a visualization of a graph's evolution and community size calculations.  The image is divided into five sections. The first two sections show the `connect(x, y)` function calls, visually represented by numbered nodes (0-4) within circles.  Lines connecting the nodes indicate the creation of edges between them.  `connect(0, 1)` shows a line connecting node 0 and node 1. `connect(1, 2)` adds a line connecting node 1 and node 2. The third section displays the result of `get_community_size(3)`, which returns 1, indicating node 3 is in a community of size one. The fourth section shows the result of `get_community_size(0)`, returning 3, indicating node 0 belongs to a community of size three (nodes 0, 1, and 2). The fifth section shows `connect(3, 4)`, adding a connection between node 3 and node 4. Finally, `get_community_size(4)` returns 2, showing that nodes 3 and 4 now form a community of size two.  The arrangement demonstrates how adding connections (`connect`) affects the community sizes (`get_community_size`), illustrating a fundamental concept in graph theory and network analysis.
Input: n = 5,
       [
         connect(0, 1),
         connect(1, 2),
         get_community_size(3),
         get_community_size(0),
         connect(3, 4),
         get_community_size(4),
       ]
Output: [1, 3, 2]
*/

    private static int[] parent;
    private static int[] size;

    static void main(String[] args) {
        int n = 5;
        initialize(n);

        connect(0, 1);
        connect(1, 2);
        System.out.println(getCommunitySize(3));
        System.out.println(getCommunitySize(0));
        connect(3, 4);
        System.out.println(getCommunitySize(4));
    }

    private static void initialize(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private static int getCommunitySize(int x) {
        return size[find(x)];
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        parent[x] = find(parent[x]);

        return parent[x];
    }

    private static void connect(int x, int y) {
        int representativeX = find(x);
        int representativeY = find(y);

        if (representativeX != representativeY) {

            if (size[representativeX] > size[representativeY]) {
                parent[representativeY] = representativeX;
                size[representativeX] += size[representativeY];
            } else {
                parent[representativeX] = representativeY;
                size[representativeY] += size[representativeX];
            }
        }
    }
}
