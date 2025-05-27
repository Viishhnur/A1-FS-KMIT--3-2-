/*
You are given a maze represented by a binary matrix of size R × C, where each cell contains either 1 (open) or 0 (blocked).

You must find the length of the shortest path from a given source cell to a given destination cell. 
You may move one step at a time in any of the four cardinal directions (up, down, left, right), and you may only traverse cells with value 1. 

If no valid path exists, return -1.

Input Format:
--------------
Line-1: Two space-separated integers, represents R, C
Next R lines: Each line contains C space-separated integers 0 or 1, describing one row of the maze.
Next line: Two space-separated integers, represents (sr, sc), zero-based coordinates of the source cell.
Next line: Two space-separated integers,represents (dr, dc), zero-based coordinates of the destination cell.

It is guaranteed that 0 ≤ sr, dr < R and 0 ≤ sc, dc < C, and that both the source and destination cells contain 1.

Output Format:
--------------
Line-1: A single integer, the length of the shortest path (number of steps) from the source to the destination, or -1 if no such path exists.


Sample Input-1:
---------------
10 10
1 1 1 1 1 0 0 1 1 1
0 0 1 0 1 1 0 1 0 1
0 0 1 0 1 1 1 0 0 1
1 0 1 1 1 0 1 1 0 1
0 0 0 1 0 0 0 1 0 1
1 0 1 1 1 0 0 1 1 0
0 0 0 0 1 0 0 1 0 1
0 1 1 1 1 1 1 1 0 0
1 1 1 1 1 0 0 1 1 1
0 0 1 0 0 1 1 0 0 1
0 0
5 7

Sample Output-1:
----------------
12

Explanation: 
------------
The shortest path from (0, 0) to (5, 7) has length 12. The shortest path is marked below with x.

[
	[x, x, x, x, x, 0, 0, 1, 1, 1],
	[0, 0, 1, 0, x, 1, 0, 1, 0, 1],
	[0, 0, 1, 0, x, x, x, 0, 0, 1],
	[1, 0, 1, 1, 1, 0, x, x, 0, 1],
	[0, 0, 0, 1, 0, 0, 0, x, 0, 1],
	[1, 0, 1, 1, 1, 0, 0, x, 1, 0],
	[0, 0, 0, 0, 1, 0, 0, 1, 0, 1],
	[0, 1, 1, 1, 1, 1, 1, 1, 0, 0],
	[1, 1, 1, 1, 1, 0, 0, 1, 1, 1],
	[0, 0, 1, 0, 0, 1, 1, 0, 0, 1]
]
 */

import java.util.Scanner;

public class Maze_Shortest_Path {
    private final int MAX = Integer.MAX_VALUE;
    private final int[] dx = {0,-1,0,1};
    private final int[] dy = {1,0,-1,0};
    
    private int solve(int x,int y,int steps,int[][] grid,int dest_x,int dest_y,boolean[][] visited,final int m,final int n){
        if(x == dest_x && y == dest_y){
            return steps;
        }
        visited[x][y] = true;
        int ans = MAX;
        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == 1){
                ans = Math.min(ans,solve(nx, ny, steps + 1, grid, dest_x, dest_y, visited, m, n));
            }
        }

        return ans;


    }

    private int getLengthOfShortestPath(int[][] grid,int src_x,int src_y,int dest_x,int dest_y,final int m,final int n){
        if(grid[src_x][src_y] == 0 || grid[dest_x][dest_y] == 0) return -1;
        
        if(src_x == dest_x && src_y == dest_y) return 0;
        int res =  solve(src_x,src_y,0,grid,dest_x,dest_y,new boolean[m][n],m,n);
        
        return res == MAX ? -1 : res;
    }
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int m = sc.nextInt();
            int n = sc.nextInt();
    
            int[][] grid = new int[m][n];

            for(int i = 0 ; i < m ; i++){
                for(int j = 0 ; j < n ; j++){
                    grid[i][j] = sc.nextInt();
                }
            }

            int src_x = sc.nextInt();
            int src_y = sc.nextInt();

            int dest_x = sc.nextInt();
            int dest_y = sc.nextInt();
            
            int shortestLength = new Maze_Shortest_Path().getLengthOfShortestPath(grid,src_x,src_y,dest_x,dest_y,m,n);
            System.out.println(shortestLength);


        }
    }
}
