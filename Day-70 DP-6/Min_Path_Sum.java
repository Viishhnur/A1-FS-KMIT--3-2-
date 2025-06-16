/*
Your playing a game, the game contains m*n grid.
Each cell in the grid represent the health points.
You can move one step either downwads or rightwards only.
Whenever you visit a cell in the grid, you will lose the 
same health points of the cell.

You will start at (0,0) cell of the grid and have to reach (m-1)*(n-1) cell.
Your task is to minimize the loss of overall health points.

Input Format:
-------------
Line-1: Two integers M and N.
Next M lines: N space separated integers, health points in each row of the grid.

Output Format:
--------------
Print an integer, minimal loss of overall health points.


Sample Input-1:
---------------
3 3
1 3 1
1 5 1
4 2 1

Sample Output-1:
----------------
7

Explanation:
------------
start at cell(0,0) =>  1→3→1→1→1 minimizes the loss of health points.


Sample Input-2:
---------------
4 4
8 6 9 3
7 6 2 1
5 5 7 9
8 9 6 2

Sample Output-2:
----------------
34

Explanation:
------------
start at cell(0,0) =>  8→6→6→2→1→9→2 minimizes the loss of health points.
 */
    
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class Min_Path_Sum {
    Supplier<Integer> getMax = () -> Integer.MAX_VALUE;
    private int dfs(int x,int y,int[][] grid,final int m,final int n,boolean[][] visited){
        if(x == m - 1 && y == n - 1){
            return grid[m-1][n-1];
        }
        
        if(x >= m || y >= n || visited[x][y]){
            return getMax.get();
        }
        
        visited[x][y] = true;
        int downSum = dfs(x+1,y,grid,m,n,visited);

        int rightSum = dfs(x,y+1,grid,m,n,visited);

        visited[x][y] = false;
        return grid[x][y] + Math.min(downSum,rightSum);
    }
    
    private final int dfsMemo(int x,int y,int[][] grid,final int m,final int n,boolean[][] visited,int[][] dp){
        if(x == m - 1 && y == n - 1){
            return grid[m-1][n-1]; // you reached
        }

        if(x >= m || y >= n || visited[x][y]){
            return Integer.MAX_VALUE;
        }
        
        if(dp[x][y] != -1){
            return dp[x][y];
        }
        visited[x][y] = true;
        int downSum = dfsMemo(x+1,y,grid,m,n,visited,dp);

        int rightSum = dfsMemo(x,y+1,grid,m,n,visited,dp);

        visited[x][y] = false;
        return dp[x][y] = grid[x][y] + Math.min(downSum,rightSum);
    }
    
    private final int solveTab(int[][] grid,final int m,final int n){
        int[][] dp = new int[m][n];

        for(int x = m - 1 ; x >= 0 ; x--){
            for(int y = n - 1 ; y >= 0 ; y--){
                if(x == m - 1 && y == n - 1){
                    dp[x][y] = grid[x][y];
                }else{

                    int downSum = (x + 1 < m) ? dp[x+1][y] : getMax.get();

                    int rightSum = (y + 1 < n) ? dp[x][y+1] : getMax.get();
                    
                    dp[x][y] = grid[x][y] + Math.min(downSum,rightSum);
                }
            }
        }
        
        return dp[0][0];
    }
    Consumer<int[][]> initDpArray = (dp) -> {
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
    };
    Function<int[][],Integer> minPathSum = (grid) -> {
        int m = grid.length;
        int n = grid[0].length;
        
        // Approach-i) Recursion
        // return dfs(0,0,grid,m,n,new boolean[m][n]);
        
        // Approach-ii) Memoization
        // int[][] dp = new int[m][n];
        // initDpArray.accept(dp);
        // return dfsMemo(0,0,grid,m,n,new boolean[m][n],dp);
        
        // Approach-iii) Tabultion
        return solveTab(grid,m,n);
        
    };
    public static void main(String... helloSirIAmBack){
        int m,n;
        int[][] grid;
        try(Scanner sc = new Scanner(System.in)){
            m = sc.nextInt();
            n = sc.nextInt();
            grid = new int[m][n];
            
            for(int i = 0 ; i < m ; i++){
                for(int j = 0 ; j < n ; j++){
                    grid[i][j] = sc.nextInt();
                }
            }
        }
        
        System.out.println(new Min_Path_Sum().minPathSum.apply(grid));
    }
    
}