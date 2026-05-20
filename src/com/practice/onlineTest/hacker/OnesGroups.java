package com.practice.onlineTest.hacker;

import java.util.*;

public class OnesGroups {

    /*# Problem Statement — Connected Groups of 1s in a Grid

In a square grid, two cells are considered connected if:

* they both contain value `1`
* they share a common edge:

  * up
  * down
  * left
  * right

Diagonal cells are **not** connected.

You are given:

1. A square matrix (`grid`) containing only `0`s and `1`s
2. An array of queries

Your task is to:

* determine the size of every connected group of `1`s
* for each query, return how many groups exist with that exact size.

---

# Example

Input matrix:

```text id="jlwmd8"
1 1 1 1 1 1
1 1 0 0 0 0
0 0 0 1 1 1
0 0 0 1 1 1
0 0 1 0 0 0
1 0 0 0 0 0
```

Queries:

```text id="jlwmo1"
[6, 1, 8, 2]
```

---

# Explanation

Connected groups of `1`s are:

1. One group of size `8`
2. One group of size `6`
3. Two groups of size `1`

So:

| Query | Number of Groups |
| ----- | ---------------- |
| 6     | 1                |
| 1     | 2                |
| 8     | 1                |
| 2     | 0                |

---

# Output

```text id="jlwma5"
[1, 2, 1, 0]
```

---

# Function Signature

```java id="jlwmb6"
int[] onesGroups(int[][] grid, int[] queries)
```

---

# Parameters

| Parameter | Description                       |
| --------- | --------------------------------- |
| `grid`    | square 2D matrix of `0`s and `1`s |
| `queries` | array containing query sizes      |

---

# Return

Return an integer array where:

```text id="jlwmn7"
result[i]
```

represents the number of connected groups having size:

```text id="jlwmq5"
queries[i]
```

---

# Constraints

Typical constraints:

```text id="jlwmb5"
1 <= n <= 1000
```

Grid contains only:

```text id="jlwme6"
0 or 1
```

---

# Expected Approach

Use:

* DFS
  OR
* BFS

to find connected components and compute their sizes efficiently.
*/

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) {

        int[][] grid = {

                {1,1,1,1,1,1},
                {1,1,0,0,0,0},
                {0,0,0,1,1,1},
                {0,0,0,1,1,1},
                {0,0,1,0,0,0},
                {1,0,0,0,0,0}
        };

        int[] queries = {6,1,8,2};

        int[] result =
                onesGroups(grid, queries);

        System.out.println(
                Arrays.toString(result));
    }

    public static int[] onesGroups(int[][] grid,
                                   int[] queries) {

        int n = grid.length;

        boolean[][] visited =
                new boolean[n][n];

        // size -> number of groups
        Map<Integer, Integer> sizeCount =
                new HashMap<>();

        // Find all connected groups
        for (int r = 0; r < n; r++) {

            for (int c = 0; c < n; c++) {

                if (grid[r][c] == 1 &&
                        !visited[r][c]) {

                    int size =
                            dfs(grid,
                                    visited,
                                    r,
                                    c);

                    sizeCount.put(
                            size,
                            sizeCount.getOrDefault(size, 0) + 1
                    );
                }
            }
        }

        // Answer queries
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            result[i] =
                    sizeCount.getOrDefault(
                            queries[i],
                            0);
        }

        return result;
    }

    private static int dfs(int[][] grid,
                           boolean[][] visited,
                           int r,
                           int c) {

        int n = grid.length;

        // Boundary check
        if (r < 0 || c < 0 ||
                r >= n || c >= n ||
                visited[r][c] ||
                grid[r][c] == 0) {

            return 0;
        }

        visited[r][c] = true;

        int size = 1;

        // Explore 4 directions
        for (int i = 0; i < 4; i++) {

            size += dfs(grid,
                    visited,
                    r + dr[i],
                    c + dc[i]);
        }

        return size;
    }


}
