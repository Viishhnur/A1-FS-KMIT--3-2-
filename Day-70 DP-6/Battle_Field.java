
/*
You are controlling a battlefield represented by an m x n grid. 
Each cell on this grid can be one of the following:

- A reinforced barrier ('B'), blocking your laser.
- An enemy drone ('D'), your target.
- An open cell ('0'), where you can position your robot to fire.

When your robot fires its powerful laser from an open cell, 
the beam destroys all enemy drones in the same row and column 
until the beam hits a barrier ('B'). The barrier completely stops 
the laser, protecting anything behind it.

Your goal is to identify the best position (open cell) to place 
your robot so that firing the laser destroys the maximum number 
of enemy drones in a single shot. Return this maximum number.

Input format:
-------------
Line 1: Two space separated integers, represents m & n
Next M lines: each row of battlefield


Output format:
--------------
An integer, maximum number of enemy drones destroyed in a single shot.

Sample Input-1:
---------------
3 4
0 D 0 0
D 0 B D
0 D 0 0

Sample Output-1:
----------------
3

Explanation: placing robot at battlefield[1][1] destroys 3 enemy drones in a single shot.

Sample Input-2:
---------------
3 3
B B B
0 0 0
D D D

Sample Output-2:
----------------
1


Constraints:
- 1 <= m, n <= 500
- battlefield[i][j] is either 'B', 'D', or '0'.
 */
import java.util.*;

@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}

public class Battle_Field {

    private int helper1(char[][] grid, final int m, final int n) {
        int maxi = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    int cnt = 0;
                    // go up till u find 'B' and count 'D'

                    int ptr = i;
                    while (ptr >= 0 && grid[ptr][j] != 'B') {
                        if (grid[ptr--][j] == 'D') {
                            cnt++;
                        }

                    }

                    // go down till u find 'B' and count 'D'

                    ptr = i;
                    while (ptr < m && grid[ptr][j] != 'B') {
                        if (grid[ptr++][j] == 'D') {
                            cnt++;
                        }

                    }

                    // go left till u find 'B' and count 'D'

                    ptr = j;
                    while (ptr >= 0 && grid[i][ptr] != 'B') {
                        if (grid[i][ptr--] == 'D') {
                            cnt++;
                        }

                    }

                    // go right till u find 'B' and count 'D'

                    ptr = j;
                    while (ptr < n && grid[i][ptr] != 'B') {
                        if (grid[i][ptr++] == 'D') {
                            cnt++;
                        }

                    }

                    maxi = Math.max(maxi, cnt);
                }
            }
        }

        return maxi;
    }

    private int helper2(char[][] grid, final int m, final int n) {
        int[][] killCount = new int[m][n];

        // Row-wise left to right
        for (int i = 0; i < m; i++) {
            int rowCnt = 0;
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 'B' -> rowCnt = 0;
                    case 'D' -> rowCnt++;
                    case '0' -> killCount[i][j] += rowCnt;
                    default -> {
                    }
                }
            }
            // Row-wise right to left
            rowCnt = 0;
            for (int j = n - 1; j >= 0; j--) {
                switch (grid[i][j]) {
                    case 'B' -> rowCnt = 0;
                    case 'D' -> rowCnt++;
                    case '0' -> killCount[i][j] += rowCnt;
                    default -> {
                    }
                }
            }
        }

        // Column-wise top to bottom
        for (int j = 0; j < n; j++) {
            int colCnt = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 'B') {
                    colCnt = 0;
                } else if (grid[i][j] == 'D') {
                    colCnt++;
                } else if (grid[i][j] == '0') {
                    killCount[i][j] += colCnt;
                }
            }
            // Column-wise bottom to top
            colCnt = 0;
            for (int i = m - 1; i >= 0; i--) {
                if (grid[i][j] == 'B') {
                    colCnt = 0;
                } else if (grid[i][j] == 'D') {
                    colCnt++;
                } else if (grid[i][j] == '0') {
                    killCount[i][j] += colCnt;
                }
            }
        }

        // Get max kill count from open cells
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, killCount[i][j]);
                }
            }
        }
        return max;
    }

    TriFunction<char[][], Integer, Integer, Integer> getMaxEnemiesDestroyedInSingleShot = (grid, m, n) -> {

        // Approach-i) Brute force (TLE)
        // return helper1(grid,m,n);

        // Approach-ii) Optimized
        return helper2(grid, m, n);
    };

    public static void main(String[] args) {
        int m, n;
        char[][] grid;
        try (Scanner sc = new Scanner(System.in)) {
            m = sc.nextInt();
            n = sc.nextInt();
            grid = new char[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.next().charAt(0);
                }
            }
        }

        System.out.println(new Battle_Field().getMaxEnemiesDestroyedInSingleShot.apply(grid, m, n));
    }
}
