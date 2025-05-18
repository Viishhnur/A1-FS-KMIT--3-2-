/*
You are a software developer at AdventureTech, a company that designs algorithmic solutions for interactive
 treasure hunt games. 
The game is set on an M × N grid representing a maze, where each cell has a non-negative cost associated with it,
 indicating the energy (in points) required to traverse that cell. 
Players must navigate from the starting point at cell (0, 0) to the treasure located at cell (M-1, N-1) while 
expending exactly a given amount of energy. 
Due to the maze's structure, players can only move one step right (to (i, j+1)) or one step down (to (i+1, j))
 from any cell.
Your task is to create a Java application that calculates the number of distinct paths a player can take from the
 start to the treasure while achieving the exact target energy cost, using a dynamic programming approach.

Your task is to process the maze grid (M × N integer matrix) and count the number of paths from cell (0, 0) to 
cell (M-1, N-1) that have a total cost equal to a given target value. 
Movements are restricted to right or down steps only, and the solution must use dynamic programming to compute 
the result efficiently.


Input Format:
-------------
Line-1: Three integers: M (number of rows), N (number of columns), and C (target energy cost).
Line-2 to M: The next M lines each contain N non-negative integers, representing the energy cost of traversing 
each cell in the maze.

Output Format:
--------------
Line-1: An integer, number of paths, If no valid paths exists print 0

Constraints
------------
1 ≤ M, N ≤ 100
0 ≤ Matrix[i][j] ≤ 100 (energy cost of each cell)
0 ≤ C ≤ 10^4 (target energy cost)

Sample Input-1:
---------------
4 4 25
1 3 4 2
2 3 3 4
3 2 1 3
4 1 2 5

Sample Output-1:
----------------
2

Explanation:
-------------
The 4 × 4 maze grid represents energy costs, and the target energy cost is 25. Two paths from (0, 0) to (3, 3) 
have a total cost of 25:
Path 1: (0,0) → (0,1) → (0,2) → (1,2) → (2,2) → (3,2) → (3,3)
Costs: 1 + 3 + 4 + 3 + 1 + 3 = 25

Path 2: (0,0) → (0,1) → (1,1) → (2,1) → (2,2) → (3,2) → (3,3)
Costs: 1 + 3 + 3 + 2 + 1 + 3 = 25


Thus, the output is Number of paths with cost 25 is 2.

Sample Input-2:
---------------
2 2 7
1 2
2 2

Sample Output-2:
----------------
0
 */

import java.util.Scanner;

public class No_Of_Paths {

    private int solveRec(int i, int j, int cost, int[][] grid, boolean[][] visited, final int m, final int n) {
        if (j >= n || i >= m || cost < 0) {
            return 0;
        }

        cost -= grid[i][j];
        if (i == m - 1 && j == n - 1) {
            return cost == 0 ? 1 : 0;
        }

        int rightWays = solveRec(i, j + 1, cost , grid, visited, m, n);
        int downWays = solveRec(i + 1, j, cost, grid, visited, m, n);

        return rightWays + downWays;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int cost = sc.nextInt();
            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            System.out.println(new No_Of_Paths().solveRec(0, 0, cost, grid, new boolean[m][n], m, n));
        }
    }
}