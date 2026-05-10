package com.practice.backtracking;

import java.util.HashSet;

class NQueen {
    /*N Queens
There is a chessboard of size n x n. Your goal is to place n queens on the board such that no two queens attack each other. Return the number of distinct configurations where this is possible.

Example:
Image represents a before-and-after visualization of a simple puzzle or algorithm, likely illustrating a sorting or rearrangement process.  The image consists of two identical 3x3 grids, each cell shaded alternately light and dark gray.  Each grid contains four identical black crown symbols, each with a horizontal line beneath it. The left grid shows the crowns arranged in a seemingly random pattern: one in the top left, one in the bottom left, one in the middle right, and one in the bottom right. The right grid shows the same four crowns, but now they are arranged in a different, more organized pattern: one in the top right, one in the middle left, one in the bottom left, and one in the bottom right. The grids are side-by-side, clearly indicating a transformation from the initial state (left) to a final state (right).  No text, URLs, or parameters are present; the visual representation alone conveys the change in arrangement.
Input: n = 4
Output: 2
*/

    static void main(String[] args) {
        int n = 4;
        System.out.println("Number of distinct configurations: " + nQueen(n));
        System.out.println("Number of distinct configurations another appraoch: " + nQueen1(n));
        System.out.println("Number of distinct configurations recursion-only: " + nQueen2(n));
    }

    private static int nQueen1(int n) {
        int[] result = new int[1];

        backtrack1(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), result);
        
        return result[0];
    }

    private static void backtrack1(int n, int row, HashSet<Object> columns, HashSet<Object> diagonals, HashSet<Object> antiDiagonals, int[] result) {

        if (row == n) {
            result[0]++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int currentDiagonal = row - col;
            int currentAntiDiagonal = row + col;

            if (columns.contains(col) || diagonals.contains(currentDiagonal) || antiDiagonals.contains(currentAntiDiagonal)) {
                continue;
            }

            columns.add(col);
            diagonals.add(currentDiagonal);
            antiDiagonals.add(currentAntiDiagonal);

            backtrack1(n, row + 1, columns, diagonals, antiDiagonals, result);

            columns.remove(col);
            diagonals.remove(currentDiagonal);
            antiDiagonals.remove(currentAntiDiagonal);

        }
    }

    private static int nQueen2(int n) {
        int[] result = new int[1];
        backtrack2(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), result);
        return result[0];
    }

    private static void backtrack2(int n, int row, HashSet<Object> columns, HashSet<Object> diagonals, HashSet<Object> antiDiagonals, int[] result) {
        if (row == n) {
            result[0]++;
            return;
        }

        if (row < 0 || row >= n) {
            return;
        }
        
        // Try placing queen in column 0
        backtrack2Helper(n, row, 0, columns, diagonals, antiDiagonals, result);
    }

    private static void backtrack2Helper(int n, int row, int col, HashSet<Object> columns, HashSet<Object> diagonals, HashSet<Object> antiDiagonals, int[] result) {
        if (col >= n) {
            return;
        }
        
        int currentDiagonal = row - col;
        int currentAntiDiagonal = row + col;

        if (!columns.contains(col) && !diagonals.contains(currentDiagonal) && !antiDiagonals.contains(currentAntiDiagonal)) {
            // Place queen
            columns.add(col);
            diagonals.add(currentDiagonal);
            antiDiagonals.add(currentAntiDiagonal);

            backtrack2(n, row + 1, columns, diagonals, antiDiagonals, result);

            // Backtrack
            columns.remove(col);
            diagonals.remove(currentDiagonal);
            antiDiagonals.remove(currentAntiDiagonal);
        }
        
        // Try next column
        backtrack2Helper(n, row, col + 1, columns, diagonals, antiDiagonals, result);
    }

    private static int nQueen(int n) {
        int[] result = new int[1];
        boolean[] columns = new boolean[n];
        boolean[] diagonal1 = new boolean[2 * n - 1]; // \ diagonals (row + col)
        boolean[] diagonal2 = new boolean[2 * n - 1]; // / antiDiagonals (row - col + n - 1)
        
        backtrack(n, 0, columns, diagonal1, diagonal2, result);
        return result[0];
    }

    /*Whiteboard: NQueen.backtrack() Method
Input: n = 4 (4x4 board, 4 queens)

Method Signature:

java
backtrack(n, row, columns, diagonal1, diagonal2, result)
Algorithm: Place queens row by row, checking column and diagonal conflicts.

Iteration Walkthrough:
Initial call: backtrack(4, 0, [F,F,F,F], [F*7], [F*7], [0])
Level 0 (row=0):

Try col=0: (0,0) is SAFE
├── columns = [T,F,F,F]
├── diagonal1[0] = T (0+0)
├── diagonal2[3] = T (0-0+3)
├── backtrack(4, 1, [T,F,F,F], [T,F*6], [F*3,T,F*3], [0])

Try col=1: (0,1) is SAFE
├── columns = [F,T,F,F]
├── diagonal1[1] = T (0+1)
├── diagonal2[2] = T (0-1+3)
├── backtrack(4, 1, [F,T,F,F], [F,T,F*6], [F*2,T,F*2], [0])

Try col=2: (0,2) is SAFE
├── columns = [F,F,T,F]
├── diagonal1[2] = T (0+2)
├── diagonal2[1] = T (0-2+3)
├── backtrack(4, 1, [F,F,T,F], [F*2,T,F*5], [F,T,F,T,F*2], [0])

Try col=3: (0,3) is SAFE
├── columns = [F,F,F,T]
├── diagonal1[3] = T (0+3)
├── diagonal2[0] = T (0-3+3)
├── backtrack(4, 1, [F,F,F,T], [F*3,T,F*3], [T,F*6], [0])
Level 1 (row=1) - from (0,0):

From (0,0) with columns=[T,F,F,F]:
├── Try col=0: CONFLICT (same column) ❌
├── Try col=1: CONFLICT (diag1[1]=T) ❌
├── Try col=2: SAFE (1,2)
│   ├── columns = [T,F,T,F]
│   ├── diagonal1[3] = T (1+2)
│   ├── diagonal2[2] = T (1-2+3)
│   └── backtrack(4, 2, [T,F,T,F], [T,F,T,F*4], [F*2,T,T,F*2], [0])
└── Try col=3: CONFLICT (diag1[4]=T) ❌
Level 2 (row=2) - from path (0,0) → (1,2):

From (0,0) → (1,2) with columns=[T,F,T,F]:
├── Try col=0: CONFLICT (same column) ❌
├── Try col=1: SAFE (2,1)
│   ├── columns = [T,T,T,F]
│   ├── diagonal1[3] already T ❌ (same diagonal as (1,2))
├── Try col=2: CONFLICT (same column) ❌
└── Try col=3: SAFE (2,3)
    ├── columns = [T,F,T,T]
    ├── diagonal1[5] = T (2+3)
    ├── diagonal2[2] already T ❌ (same diagonal as (1,2))
Successful Path - First Solution:

Path: (0,1) → (1,3) → (2,0) → (3,2)

Row 0: Place queen at (0,1)
├── columns = [F,T,F,F], diag1[1]=T, diag2[2]=T

Row 1: Place queen at (1,3)
├── columns = [F,T,F,T], diag1[4]=T, diag2[0]=T

Row 2: Place queen at (2,0)
├── columns = [T,T,F,T], diag1[2]=T, diag2[1]=T

Row 3: Place queen at (3,2)
├── columns = [T,T,T,T], diag1[5]=T, diag2[1] already T ❌

CORRECTED: Path (0,1) → (1,3) → (2,0) → (3,2) WORKS!
Complete Solution Trace:

Solution 1:
Row 0: [ . Q . . ]
Row 1: [ . . . Q ]
Row 2: [ Q . . . ]
Row 3: [ . . Q . ]
→ result[0] = 1

Solution 2:
Row 0: [ . . Q . ]
Row 1: [ Q . . . ]
Row 2: [ . . . Q ]
Row 3: [ . Q . . ]
→ result[0] = 2
Diagonal Conflict Detection:

\\ Diagonal (row + col):
(0,1) → diag1[1]
(1,3) → diag1[4]
(2,0) → diag1[2]
(3,2) → diag1[5]

/ Diagonal (row - col + n - 1):
(0,1) → diag2[2]
(1,3) → diag2[0]
(2,0) → diag2[1]
(3,2) → diag2[1] ❌ CONFLICT!
Key Concepts:
Row-by-Row Placement: Process one row at a time
Conflict Detection: Check columns and both diagonal types
Backtracking: Remove queen when path fails, try next column
Base Case: When row == n, all queens placed successfully
Diagonal Indexing:
\ diagonal: row + col
/ diagonal: row - col + n - 1
Time Complexity: O(n!) - exponential search space
Space Complexity: O(n) - arrays + recursion stack
The method systematically explores all valid queen placements using backtracking with efficient conflict detection.
*/
    private static void backtrack(int n, int row, boolean[] columns, boolean[] diagonals, boolean[] antiDiagonals, int[] result) {
        // Base case: all queens placed successfully
        if (row == n) {
            result[0]++;
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            int diagonalIndex = row + col; // \ diagonal index
            int antiDiagonalIndex = row - col + n - 1; // / diagonal index
            
            // Check if position is safe
            if (!columns[col] && !diagonals[diagonalIndex] && !antiDiagonals[antiDiagonalIndex]) {
                // Place queen
                columns[col] = true;
                diagonals[diagonalIndex] = true;
                antiDiagonals[antiDiagonalIndex] = true;
                
                // Recurse to next row
                backtrack(n, row + 1, columns, diagonals, antiDiagonals, result);
                
                // Backtrack: remove queen
                columns[col] = false;
                diagonals[diagonalIndex] = false;
                antiDiagonals[antiDiagonalIndex] = false;
            }
        }
    }
}
