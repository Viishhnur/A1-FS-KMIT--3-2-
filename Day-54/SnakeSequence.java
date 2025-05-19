
/*
GridFlow Technologies is developing a path optimization tool for data transmission in a grid-based 
communication network, 
represented as an N × N square matrix of integers. 
Each cell contains a numeric value, and the goal is to find the longest "snake sequence" within the grid. 
A snake sequence is a path of numbers where each subsequent number 
is located either to the right or down of the current number and differs from it by exactly + or - 1. 
The task is to compute the maximum length of such a snake sequence and outputs one valid sequence.

Constraints:
------------
1 ≤ N ≤ 100 (matrix dimension)
-1000 ≤ Matrix[i][j] ≤ 1000 (value in each cell)
At least one snake sequence exists in the matrix.

Input Format:
--------------
Line-1: An integer: N (size of the square matrix).
Line-2 to N: The next N lines each contain N integers, representing the values in the matrix.

Output Format:
---------------
Line-1: Maximum length number. If maximum length not exists then write 0.

Sample Input-1:
---------------
3
1 3 5
7 9 11
13 15 17

Sample Output-1:
----------------
0

Explanation:
-------------
No two adjacent elements differ by + or - 1.

Sample Input-2:
---------------
5
7 5 2 3 1
3 4 1 4 4
1 5 6 7 8
3 4 5 8 9
3 2 2 7 6

Sample Output-2:
----------------
7

Explanation:
----------------
The 5 × 5 matrix contains several snake sequences. One maximum length snake sequence is:
- Start at (0,1): 5
- Move down to (1,1): 4 (5-1)
- Move down to (2,1): 5 (4+1)
- Move right to (2,2): 6 (5+1)
- Move right to (2,3): 7 (6+1)
- Move right to (2,4): 8 (7+1)
- Move down to (3,4): 9 (8+1, invalid, but check down to (3,3))
- From (2,3): 7, move down to (3,3): 8 (7+1)
- Move down to (4,3): 7 (8-1)
This sequence (5 → 4 → 5 → 6 → 7 → 8 → 7) has a length of 7 and satisfies the + or - 1 constraint for 
each step, moving only right or down.

 */

import java.util.*;
public class SnakeSequence {

    private int solveRec(int x,int y,int prevx,int prevy,int[][] grid,final int n){

        if(x >= n || y >= n){
            return 0;
        }

        int maxi = 0;
        int rightLen = 0 , downLen = 0;
        // I can take this element into the sequence only
        if(Math.abs(grid[x][y] - grid[prevx][prevy]) == 1){
            rightLen = 1 + solveRec(x, y+1, x, y, grid, n);
            downLen = 1 + solveRec(x+1, y, x, y, grid, n);
            maxi = Math.max(rightLen,downLen);
        }else{
            rightLen = 0 + solveRec(x, y+1, prevx, prevy, grid, n);
            downLen = 0 + solveRec(x+1, y, prevx, prevy, grid, n);
            maxi = Math.max(rightLen,downLen);
        }

        return maxi;
        
        
    }

    private int solveMemo(int x,int y,int prevx,int prevy,int[][] grid,final int n,Integer[][][][] memo){

        if(x >= n || y >= n){
            return 0;
        }

        if(memo[x][y][prevx][prevy] != null){
            return memo[x][y][prevx][prevy];
        }

        int maxi = 0;
        int rightLen = 0 , downLen = 0;
        // I can take this element into the sequence only
        if(Math.abs(grid[x][y] - grid[prevx][prevy]) == 1){
            // Take case
            rightLen = 1 + solveMemo(x, y+1, x, y, grid, n,memo);
            downLen = 1 + solveMemo(x+1, y, x, y, grid, n,memo);
            maxi = Math.max(rightLen,downLen);
        }else{
            // Not take case
            rightLen = 0 + solveMemo(x, y+1, prevx, prevy, grid, n,memo);
            downLen = 0 + solveMemo(x+1, y, prevx, prevy, grid, n,memo);
            maxi = Math.max(rightLen,downLen);
        }

        return memo[x][y][prevx][prevy] = maxi;
        
        
    }

    private int solveTab(int startx,int starty,int[][] grid,final int n){
        int[][][][] dp = new int[n][n][n][n];

        for(int x = n - 1 ; x >= startx ; x--){
            for(int y = n - 1 ; y >= starty ; y--){
                for(int prevx = n - 1 ; prevx >= x ; prevx--){
                    for(int prevy = n - 1 ; prevy >= y ; prevy--){
                        
                        int maxi = 0;
                        int rightLen = 0 , downLen = 0;
                        // I can take this element into the sequence only
                        if(Math.abs(grid[x][y] - grid[prevx][prevy]) == 1){
                            // Take case
                            rightLen = 1 + dp[x][y+1][x][y];
                            downLen = 1 + dp[x+1][y][x][y];
                            maxi = Math.max(rightLen,downLen);
                        }else{
                            // Not take case
                            rightLen = 0 + dp[x][y+1][prevx][prevy];
                            downLen = 0 + dp[x+1][y][prevx][prevy];
                            maxi = Math.max(rightLen,downLen);
                        }

                        dp[x][y][prevx][prevy] = maxi;




                    }
                }
            }
        }

        return dp[startx][starty][startx][starty];
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();

            int[][] grid = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int maxLen = 0;
            SnakeSequence obj = new SnakeSequence();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Appraoch-i) Recursion
                    // maxLen = Math.max(maxLen, obj.solveRec(i,j,i,j,grid,n));

                    // Approach-ii) Memoization
                    // Integer[][][][] memo = new Integer[n][n][n][n];
                    // maxLen = Math.max(maxLen, obj.solveMemo(i,j,i,j,grid,n,memo));

                    // Appraroch-iii) Tabulation
                    maxLen = Math.max(maxLen, obj.solveTab(i,j,grid,n));
                }
            }

            System.out.println(maxLen);
        }
    }
}
