/*
You are organizing a grand parade where 'N' marching bands will move in a straight line. 
Each band must wear uniforms of exactly one color chosen from 'K' available colors. 
To keep the parade visually appealing and avoid monotony, 
you must follow this important guideline:
- No more than 'two consecutive bands' can wear 'uniforms of the same color'.

Given the total number of bands N and the number of uniform color choices K, 
determine the total number of valid ways you can assign uniform colors to all bands 
so that the above rule is not violated.

Input Format:
-------------
Two integers N and K.

Output Format:
--------------
Print an integer, Number of ways.

Sample Input-1:  
---------------
3 2

Sample Output-1:
----------------
6  

Explanation:
------------
Bands	band-1	band-2	band-3
----- 	----- 	----- 	-----
1		c1 		c1		c2
2		c1 		c2 		c1
3		c1 		c2 		c2
4		c2 		c1 		c1
5		c2 		c1 		c2
6		c2 		c2 		c1

Sample Input-2:  
--------------- 
1 1

Sample Output-2:
---------------- 
1


Constraints:  
- 1 <= n <= 50  
- 1 <= k <= 10^5 
- The result will always be within the range of a 32-bit signed integer.
 */

import java.util.Arrays;
import java.util.Scanner;
public class Paint_Dots{
    private int solveRec(int noOfBands,int prev_color,int cnt,int k){
        if(noOfBands == 0){ // all bands are given colors so one way is found
            return 1;
        }
        
        int total = 0;
        // At each band iterate through all k colors and check which can suit best
        for(int color = 0 ; color < k ; color++){
            if(color == prev_color){
                if(cnt < 2){
                    total += solveRec(noOfBands-1,color,cnt+1,k);
                }
            }else{
                total += solveRec(noOfBands-1,color,1,k);
;            }
        }
        
        return total;
    }
    
    private int solveMemo(int noOfBands,int prev_color,int cnt,int k,int[][][] dp){
        if(noOfBands == 0){ // all bands are given colors so one way is found
            return 1;
        }
        
        if(dp[noOfBands][prev_color+1][cnt] != -1){
            return dp[noOfBands][prev_color+1][cnt];
        }
        int total = 0;
        // At each band iterate through all k colors and check which can suit best
        for(int color = 0 ; color < k ; color++){
            if(color == prev_color){
                if(cnt < 2){
                    total += solveMemo(noOfBands-1,color,cnt+1,k,dp);
                }
            }else{
                total += solveMemo(noOfBands-1,color,1,k,dp);
;            }
        }
        
        return dp[noOfBands][prev_color+1][cnt] = total;
    }
    private int totalValidWays(int n,int k){
        // return solveRec(n,-1,0,k);
        
        int[][][] dp = new int[n+1][k+1][3];
        for(int[][] mat : dp){
            for(int[] row : mat){
                Arrays.fill(row,-1);
            }
        }
        return solveMemo(n,-1,0,k,dp);
    }
    public static void main(String[] aegs){
        int n,k;
        try(Scanner sc = new Scanner(System.in)){
            n = sc.nextInt();
            k = sc.nextInt();
        }
        
        System.out.println(new Paint_Dots().totalValidWays(n,k));
    }
}