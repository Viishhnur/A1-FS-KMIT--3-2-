
/* Jump Game-ii) Leetcode - 45
You are participating in a futuristic space exploration mission where you must navigate through a series of planets aligned in a straight line.
The planets are numbered from 0 to n-1, and you start your journey on planet 0.

You are given a 0-indexed array planets, where each element planets[i] 
represents the maximum number of planets you can move forward from planet i. 
In simpler terms, standing on planet i, you can move to any planet from i+1 to 
i+planets[i], as long as you don't exceed the last planet.

Your goal is to reach the final planet (planet n-1) in the fewest number of 
moves possible.

It is guaranteed that a path to the final planet always exists.

Return the minimum number of moves needed to reach planet n-1.

Example 1
----------
Input:
2 3 1 0 4
Output:
2

Explanation:
- Move from planet 0 to planet 1.
- Move from planet 1 to planet 4 (last planet).
 */
import java.util.Arrays;
import java.util.Scanner;

public class Jump_Game {
    private int solveRec(int idx, int jumps, int[] nums, final int n) {
        if (idx >= n - 1) {
            return jumps;
        }

        int maxJump = nums[idx];
        int minJumps = Integer.MAX_VALUE;
        for (int i = idx; i < idx + maxJump && i < n; i++) {
            minJumps = Math.min(minJumps, solveRec(i + 1, jumps + 1, nums, n));
        }

        return minJumps;
    }

    private int solveRec2(int idx, int[] nums, final int n) {
        if (idx >= n - 1) {
            return 0;
        }

        int maxJump = nums[idx];
        int minJumps = Integer.MAX_VALUE;
        for (int i = idx; i < idx + maxJump && i < n; i++) {
            int steps = solveRec2(i + 1, nums, n);
            if (steps != Integer.MAX_VALUE) {
                minJumps = Math.min(minJumps, 1 + steps);
            }
        }

        return minJumps;
    }

    private int solveMemo(int idx, int[] nums, final int n, int[] dp) {
        if (idx >= n - 1) {
            return 0;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }
        int maxJump = nums[idx];
        int minJumps = Integer.MAX_VALUE;
        for (int i = idx; i < idx + maxJump && i < n; i++) {
            int steps = solveMemo(i + 1, nums, n, dp);
            if (steps != Integer.MAX_VALUE) {

                minJumps = Math.min(minJumps, 1 + steps);
            }
        }

        return dp[idx] = minJumps;
    }

    private int solveTab(int[] nums, final int n) {
        int[] dp = new int[n];

        dp[n - 1] = 0; // base case

        for (int idx = n - 2; idx >= 0; idx--) {
            int maxJump = nums[idx];
            int minJumps = Integer.MAX_VALUE;
            for (int i = idx; i < idx + maxJump && i + 1 < n; i++) {
                int steps = dp[i + 1];
                if (steps != Integer.MAX_VALUE) {

                    minJumps = Math.min(minJumps, 1 + steps);
                }
            }

            dp[idx] = minJumps;
        }

        return dp[0];
    }

    public int minJumps(int[] nums) {
        int n = nums.length;

        // Approach-i) Recursion(TLE)
        // return solveRec(0,0,nums,n);

        // Approach-ii) Recursion another variant do not pass jumps as argument
        // return solveRec2(0,nums,n);

        // Approach-iii) Memoization
        // int[] dp = new int[n];
        // Arrays.fill(dp,-1);
        // return solveMemo(0,nums,n,dp);

        // Approach-iv) Tabulation
        return solveTab(nums, n);
    }

    public static void main(String[] args) {
        int[] nums;
        try (Scanner sc = new Scanner(System.in)) {
            nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(new Jump_Game().minJumps(nums));
    }
}