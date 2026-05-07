package com.practice.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConnectTheDots {

    /*Connect the Dots
Given a set of points on a plane, determine the minimum cost to connect all these points.

The cost of connecting two points is equal to the Manhattan distance between them, which is calculated as |x1 - x2| + |y1 - y2| for two points (x1, y1) and (x2, y2).

Example:
Image represents a transformation of a set of points in a Cartesian coordinate system.  The left side shows a scatter plot with five points located at approximately (1,1), (3,2), (4,3), (2,6), and (7,1).  These points are unconnected. A large, bold arrow points to the right, indicating a transformation. The right side displays the same five points, but now connected to a central point located at approximately (3,2).  The lengths of the lines connecting the central point to each of the other points are labeled with numerical values: 3, 5, 2, and 5.  The x and y axes are labeled on both sides, ranging from 0 to 7. The transformation visually demonstrates a change from an unorganized set of points to a structured arrangement radiating from a central node, with the connecting lines representing distances or weights between the points.
Input: points = [[1, 1], [2, 6], [3, 2], [4, 3], [7, 1]]
Output: 15
Constraints:
There will be at least 2 points on the plane.
*/

    static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 6}, {3, 2}, {4, 3}, {7, 1}};
        System.out.println("Minimum cost to connect all points: " + connectTheDots(points));
    }

    private static int connectTheDots(int[][] points) {

        int n = points.length;

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                int[] edge = new int[] {i, j, cost};
                edges.add(edge);
            }
        }

        edges.sort((a, b) -> Integer.compare(a[2], b[2]));

        int totalCost = 0, edgesAdded = 0;

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {

            if (uf.union(edge[0], edge[1])) {

                totalCost += edge[2];
                edgesAdded += 1;

                if (edgesAdded == n - 1) {
                    return totalCost;
                }

            }
        }

        return  totalCost;
    }
}


class UnionFind {

    private int[] parent;
    private  int[] size;

    UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public  int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        parent[x] = find(parent[x]);

        return parent[x];
    }

    public  boolean union(int x, int y) {

        int repX = find(x), repY = find(y);

        if (repX != repY) {

            if (size[repX] > size[repY]) {
                parent[repY] = repX;
                size[repX] += size[repY];
            } else {
                parent[repX] = repY;
                size[repY] += size[repX];
            }

            return true;
        }

        return  false;
    }

}