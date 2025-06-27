/* Leetcode - 1284 Minimum Number of Flips To Convert Binary Matrix to Zero Matrix
Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

A binary matrix is a matrix with all cells equal to 0 or 1 only.

A zero matrix is a matrix with all cells equal to 0.

 

Example 1:

Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.

Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We do not need to change it.

Example 3:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix cannot be a zero matrix.

 

Constraints:

    m == mat.length
    n == mat[i].length
    1 <= m, n <= 3
    mat[i][j] is either 0 or 1.

 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Min_Flips_To_Make_Zero_Matrix {
    
    private final int[][] DIRS = {{0,0} , {-1,0} , {0,1} , {1,0} , {0,-1}}; // 5 possible cells are affected

    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Let me store the positions of 1 into a bit mask , so that i can store entire mat info into a single integer bitmask
        int bitmask = 0;

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(mat[i][j] == 1){
                    int pos = i * n + j;
                    bitmask |= (1 << pos); // setting the pos bit to ensure 1 is there at this index in mat
                }
            }
        }


        // Now do a BFS
        Queue<Integer> qu = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); // for storing visited states 

        qu.offer(bitmask);
        visited.add(bitmask);

        int flips = 0;
        while(!qu.isEmpty()){
            int size = qu.size();

            for(int c = 0 ; c < size ; c++){
                int currState = qu.poll();

                if(currState == 0){
                    return flips; // we reached our target , by converting the matrix into all 0s
                }

                // now we try flipping every element of matrix 
                List<Integer> nextStates = getNextStates(currState,mat,m,n);

                for(int nextState : nextStates){
                    if(!visited.contains(nextState)){
                        qu.offer(nextState);
                        visited.add(nextState);
                    }
                }
            }

            flips++;
        }

        return -1; // not possible 
    }

    private List<Integer> getNextStates(int currState,int[][] mat,final int m,final int n){

        // go to each and every cell and try all possible states from here 
        List<Integer> nextStates = new ArrayList<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                int nextState = currState;

                // now flip this cell(i,j) and it's nbr get new state and add it to nextStates
                for(int[] dir : DIRS){
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if(ni >= 0 && ni < m && nj >= 0 && nj < n){
                        int pos = ni * n + nj;
                        nextState ^= (1 << pos); // flipping the pos bit in nextState (this represent state of matrix)
                    }
                }
                nextStates.add(nextState);
            }
        }

        return nextStates;
    }
}
