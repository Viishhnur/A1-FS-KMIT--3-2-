/*
Given a rod of length n and an array of prices price[] where price[i-1] represents the price of a rod of length i (for 1 <= i <= n), 
determine the optimal way to cut the rod into smaller pieces to maximize the total profit. 
Each piece must have an integer length, and the sum of the lengths of the pieces must equal n. 

The goal is to find the combination of cuts that yields the highest total revenue based on the given price array.

Input Format:
-------------
Line-1:A positive integer n, representing the total length of the rod.
Line-2: A positive integer, number of prices (k, 1 <= k <= 1000):
Line-3: An array price[] of non-negative integers where price[i-1] is the price of a rod of length length[i-1].

Output Format:
--------------
Line-1: An integer, The maximum profit achievable by cutting the rod into smaller pieces of integer lengths.

Constraints:
--------------
1 <= n <= 1000 (rod length).
1 <= length[i] <= n for all i in length[].
0 <= price[i] <= 10^5 for all prices in price[].
The length of length[] and price[] arrays is at most n.

Sample Input-1:
---------------
4
8
1 5 8 9 10 17 17 20

Sample Output-1:
----------------
10

Explanation:
------------
The possible ways to cut a rod of length 4 and their profits are:
No cut (length 4): 9
Cut into 1 + 3: 1 + 8 = 9
Cut into 2 + 2: 5 + 5 = 10
Cut into 3 + 1: 8 + 1 = 9
Cut into 1 + 1 + 2: 1 + 1 + 5 = 7
Cut into 1 + 2 + 1: 1 + 5 + 1 = 7
Cut into 2 + 1 + 1: 5 + 1 + 1 = 7
Cut into 1 + 1 + 1 + 1: 1 + 1 + 1 + 1 = 4

The maximum profit is 10, achieved by cutting the rod into two pieces of length 2 each.

Sample Input-2:
---------------
5
3
2 5 7

Sample Output-2:
----------------
12
 */
import java.util.*;
public class Rod_Cutting_Problem {
    private int solveRec(int idx,int target,int[] price){
        // Our target is to maximise the revenue by cutting the rod optimally
        // it means the sum of all cuts should be equal to n
        if(target == 0){ 
            // it means mera rod length is 0 , usko banane ka price bhi 0 hai
            return 0;
        }

        if(idx == 0){
            // Because f(0) tells how much profit i can make by making a rod of length = target 
            // by combining target number of rods of length = 1 I can form target and profit = target * price[0]
            return target * price[0]; 
        }

       
        // donot take this 
        int notTakeRevenue = 0 + solveRec(idx - 1, target ,price);

        // May be I'll take same rod length
        int takeRevenue = Integer.MIN_VALUE;
        if(idx + 1 <= target){ // (idx + 1 is the current rod length)

            takeRevenue = price[idx] + solveRec(idx, target - (idx + 1) ,price);
        }


        return Math.max(takeRevenue,notTakeRevenue);
    }

    private int solveMemo(int idx,int target,int[] price,int[][] dp){
        // Our target is to maximise the revenue by cutting the rod optimally
        // it means the sum of all cuts should be equal to n
        if(target == 0){ 
            // it means mera rod length is 0 , usko banane ka price bhi 0 hai
            return 0;
        }

        if(idx == 0){
            // Because f(0) tells how much profit i can make by making a rod of length = target 
            // by combining target number of rods of length = 1 I can form target and profit = target * price[0]
            return target * price[0]; 
        }


        // Pruning
        if(dp[idx][target] != -1){
            return dp[idx][target];
        } 
       
        // donot take this 
        int notTakeRevenue = 0 + solveMemo(idx - 1, target ,price,dp);

        // May be I'll take same rod length
        int takeRevenue = Integer.MIN_VALUE;
        if(idx + 1 <= target){ // (idx + 1 is the current rod length)

            takeRevenue = price[idx] + solveMemo(idx, target - (idx + 1) ,price,dp);
        }


        return dp[idx][target] = Math.max(takeRevenue,notTakeRevenue);
    }

    private int solveTab(int[] price,int rodLen){
        int[][] dp = new int[price.length][rodLen+1];

        // First copy the base case 
        for(int idx = 0 ; idx < price.length ; idx++){
            dp[idx][0] = 0; // idx kuch bhi ho agar mera rodLen 0 hai toh revenue bhi 0 hai
        }

        for(int len = 0 ; len <= rodLen ; len++){
            dp[0][len] = price[0] * len;
        }

        // Now write nested for loops
        for(int idx = 1 ; idx < price.length ; idx++){
            for(int len = 1 ; len <= rodLen ; len++){
                // donot take this 
                int notTakeRevenue = 0 + dp[idx - 1][len];

                // May be I'll take same rod length
                int takeRevenue = Integer.MIN_VALUE;
                if(idx + 1 <= len){ // (idx + 1 is the current rod length)

                    takeRevenue = price[idx] + dp[idx][len - (idx + 1)];
                }


                dp[idx][len] = Math.max(takeRevenue,notTakeRevenue);
            }
        }

        return dp[price.length-1][rodLen];
    }

    private int solveSpaceOptimzed(int[] price,int rodLen){
        int[] prev = new int[rodLen+1];

        // Copy the base case
        for(int len = 0 ; len <= rodLen ; len++){
            prev[len] = price[0] * len;
        }

        // Now write nested for loops
        for(int idx = 1 ; idx < price.length ; idx++){
            int[] curr = new int[rodLen+1];
            for(int len = 1 ; len <= rodLen ; len++){
                // donot take this 
                int notTakeRevenue = 0 + prev[len];

                // May be I'll take same rod length
                int takeRevenue = Integer.MIN_VALUE;
                if(idx + 1 <= len){ // (idx + 1 is the current rod length)

                    takeRevenue = price[idx] + curr[len - (idx + 1)];
                }


                curr[len] = Math.max(takeRevenue,notTakeRevenue);
            }

            prev = curr;
        }

        return prev[rodLen];
    }
    
    public int RodCutting(int price[], int rodLen) {

        // Approach-i) Using Recursion
        return solveRec(Math.min(price.length , rodLen) - 1 , rodLen , price); // min is used because apko just rodLen ko banana but with available rod lengths only

        // Approach-ii) Memoization
        // int[][] dp = new int[price.length][rodLen+1];
        // for(int[] row : dp){
        //     Arrays.fill(row,-1);
        // }
        // return solveMemo(Math.min(price.length , rodLen) -1,rodLen,price,dp);


        // Approach-iii) Tabulation 
        // return solveTab(price,rodLen);

        // Approach-iv) Space Optimized approach
        // return solveSpaceOptimzed(price,rodLen);
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int totalRodLen = sc.nextInt();

            int noOfRods = sc.nextInt();

            int[] price = new int[noOfRods];
            for(int i = 0 ; i < noOfRods ; i++){
                price[i] = sc.nextInt();
            }

            System.out.println(new Rod_Cutting_Problem().RodCutting(price, totalRodLen));
        }
    }
}
