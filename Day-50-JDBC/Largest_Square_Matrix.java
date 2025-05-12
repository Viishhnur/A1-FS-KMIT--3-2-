/*
You are a software engineer at TechNet Solutions, a company specializing in network infrastructure analysis 
for large-scale communication systems. The company is developing a fault-tolerant network simulation tool to
identify robust communication clusters within a network grid. The network is represented as an M × N binary 
matrix, where each cell is either active (1) or inactive (0). 

Your task is to process the binary matrix and determine the size of the largest square submatrix of 1's, 
representing a robust communication cluster, while adhering to the following constraints:
1. The cluster must not include nodes on the network's perimeter (i.e., the first or last row or column of 
the matrix) to avoid external interference.
2. The cluster must include at least one "critical node," defined as an active node (1) surrounded by at 
least three inactive nodes (0's) in its four adjacent cells (up, down, left, right), as these nodes are 
pivotal for maintaining connectivity.
3. If multiple clusters of the same size exist, prioritize the one located closer to the bottom of the 
matrix (highest sum of row indices) to optimize signal propagation.

Constraints:
-------------
1. 3 ≤ M, N ≤ 1000 (to allow non-perimeter clusters).
2. Matrix elements are either 0 (inactive) or 1 (active).
3. At least one critical node exists in the matrix.

Input Format:
-------------

Line-1: Two integers: M (number of rows) and N (number of columns).
Line-2 to M: The next M lines each contain N integers (0 or 1), representing the binary network grid.

Output Format:
---------------

Line-1: An integer, The size of the largest square submatrix of 1’s is <size> other wise if no valid 
cluster exists print 0.

Sample Input-1:
---------------
6 6
0 0 1 0 1 1
0 1 1 1 0 0
0 0 1 1 1 1
1 1 1 0 1 1
1 0 1 1 1 1
1 1 1 1 1 0

Sample Output-1:
---------------- 
2

Explanation
------------
The network grid contains a 2 × 2 cluster of active nodes starting at position (3, 2):
1 1
1 1

Sample Input-2:
---------------
1 1
0

Sample Output-2:
---------------- 
0
 */


import java.util.*;

public class Largest_Square_Matrix {

    private static final int[] dx = {0,-1,0,1};
    private static final int[] dy = {1,0,-1,0};
    
    
    private static void dfs(int x,int y,int[][] grid,boolean[][] visited,int[] temp,final int m,final int n){
        visited[x][y] =  true;
        temp[2]++; // this is for component size 
        if(x == 0 || x == m - 1 || y == 0 || y == n - 1){
            temp[0]++; // this is for boundary check
        }
        // check if this can be a critical node
        int adjZerosCnt = 0;
        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && nx < m && ny >= 0 && ny < n){
                if(!visited[nx][ny] && grid[nx][ny] == 1){
                    
                    dfs(nx,ny,grid,visited,temp,m,n);
                }else if(grid[nx][ny] == 0){
                    adjZerosCnt++;
                }
            }
        }
        
        if(adjZerosCnt >= 3){
            temp[1]++; // yes there is a critical node
        }
    }
    private static int getLargestSquareSize(int[][] grid,final int m,final int n){
        
        // first start a dfs bro
        int maxSize = 0;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    int[] temp = new int[]{0,0,0};
                    dfs(i,j,grid,visited,temp,m,n);
                    
                    // first check for borders
                    if(temp[0] == 0 && temp[1] >= 1 && temp[2] > maxSize && Math.sqrt(temp[2]) == temp[2]){
                        maxSize = temp[2];
                        
                    }
                }
            }
        }
        
        return maxSize;
    }
    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in)){
            int m = sc.nextInt();
            int n = sc.nextInt();
            sc.nextLine();
            int[][] grid = new int[m][n];
            for(int i = 0 ; i < m ; i++){
                for(int j = 0 ; j < n ; j++){
                    grid[i][j] = sc.nextInt();
                }
            }
            
            System.out.println(getLargestSquareSize(grid,m,n));
        }
    }
}