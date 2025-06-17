
/*
 * You are navigating a spaceship through a galaxy represented as an m x n space 
map.The spaceship starts from the top-left sector (sector[0][0]) and its 
mission is to safely reach the bottom-right sector (sector[m-1][n-1]).

Each sector on the map can either be clear (0) or blocked by an 
asteroid field (1).  
The spaceship can only move right or downward at any moment.  
It cannot pass through sectors with asteroid fields.

Return the total number of distinct safe routes the spaceship can take to 
reach its destination at the bottom-right corner.


Input format:
-------------
2 space seperated integers, m & n
next m lines of representing the sector 

Output format:
--------------
An integer, the total number of distinct safe routes



Example 1:
----------
Input:
3 3
0 0 0
0 1 0
0 0 0

Output:
2

Explanation:  
There’s one asteroid field blocking the center of the 3×3 map.  
Two possible safe navigation paths:
- Move → Move → Down → Down
- Down → Down → Move → Move

Example 2:
---------
Input:
2 2
0 1
0 0

Output:
1


Constraints:
- m == sectorMap.length
- n == sectorMap[i].length
- 1 <= m, n <= 100
- sectorMap[i][j] is either 0 (clear) or 1 (asteroid field)

 */

import java.util.Scanner;

public class FS_57_DP_Unique_Paths_II {
    static int solution(int[][] obstacleGrid) {
        // Get the dimensions of the grid
        int numRows = obstacleGrid.length;
        int numCols = obstacleGrid[0].length;
        // Initialize a DP table with dimensions equivalent to the obstacle grid
        int[][] dp = new int[numRows][numCols];
        // Set up the first column of the DP table. If there is an obstacle,
        // paths beyond that point are not possible, so the loop will break.
        for (int row = 0; row < numRows && obstacleGrid[row][0] == 0; ++row) {
            dp[row][0] = 1;
        }
        // Set up the first row of the DP table. If there is an obstacle,
        // paths beyond that point are not possible, so the loop will break.
        for (int col = 0; col < numCols && obstacleGrid[0][col] == 0; ++col) {
            dp[0][col] = 1;
        }
        // Iterate over the grid starting from cell (1, 1) to calculate the
        // number of unique paths to each cell, considering the obstacles.
        for (int row = 1; row < numRows; ++row) {
            for (int col = 1; col < numCols; ++col) {
                // If the current cell is not an obstacle
                if (obstacleGrid[row][col] == 0) {
                    // Number of paths to current cell is the sum of paths to the
                    // cell above it and the cell to the left of it.
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                }
                // If the current cell is an obstacle, dp[row][col] remains 0
            }
        }
        // Return the number of unique paths to the bottom-right corner of the grid
        return dp[numRows - 1][numCols - 1];
    }

    static int sol(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // can't reach this cell
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // add left cell if not obstacle
                }
                // else: j == 0 (first column), nothing to add
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = sc.nextInt();
        System.out.println(solution(mat));
        sc.close();
    }
}