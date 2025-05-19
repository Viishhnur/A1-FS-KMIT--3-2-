
/*
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:
-> i + 1 where: i + 1 < arr.length.
-> i - 1 where: i - 1 >= 0.
-> j where: arr[i] == arr[j] and i != j.
-> Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Sample Input-1:
---------------
100 -23 -23 404 100 23 23 23 3 404

Sample Output-1:
---------------
3

Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of
the array.

Sample Input-2:
---------------
1
7

Sample Output-2: 
----------------
0

Explanation: Start index is the last index. You do not need to jump.

Sample Input-3:
---------------
8
7 6 9 6 9 6 9 7

Sample Output-3: 
----------------
1

Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 
Constraints:
------------
-> 1 <= arr.length <= 5 * 10^4
-> -10^8 <= arr[i] <= 10^8
 */

import java.util.*;
class MinJumps {
	private final int MAX = Integer.MAX_VALUE;

	private int solveRec(int idx, int jumps ,boolean[] visited, int[] arr, final int n) {
		
		if(idx >= n - 1){
			return jumps;
		}

		visited[idx] = true;

		int minJumps = MAX;
		int nextIdx = idx + 1;
		if(nextIdx < n && !visited[nextIdx]){
			minJumps = Math.min(minJumps,solveRec(nextIdx, jumps+1,visited,arr,n));
		}

		int prevIdx = idx - 1;
		if(prevIdx >= 0 && !visited[prevIdx]){
			minJumps = Math.min(minJumps,solveRec(idx-1, jumps+1, visited, arr, n));
		}

		for(int j = 0 ; j < n ; j++){
			if(idx != j && !visited[j] && arr[idx] == arr[j]){
				minJumps = Math.min(minJumps,solveRec(j, jumps+1, visited, arr, n));
			}
		}

		visited[idx] = false;
		return minJumps;

	}

	private int solveMemo(int idx, int jumps ,boolean[] visited, int[] arr, final int n,Integer[][] memo) {
		
		if(idx >= n - 1){
			return jumps;
		}

		if(memo[idx][jumps] != null){
			return memo[idx][jumps];
		}
		visited[idx] = true;

		int minJumps = MAX;
		int nextIdx = idx + 1;
		if(nextIdx < n && !visited[nextIdx]){
			minJumps = Math.min(minJumps,solveMemo(nextIdx, jumps+1,visited,arr,n,memo));
		}

		int prevIdx = idx - 1;
		if(prevIdx >= 0 && !visited[prevIdx]){
			minJumps = Math.min(minJumps,solveMemo(idx-1, jumps+1, visited, arr, n,memo));
		}

		for(int j = 0 ; j < n ; j++){
			if(idx != j && !visited[j] && arr[idx] == arr[j]){
				minJumps = Math.min(minJumps,solveMemo(j, jumps+1, visited, arr, n,memo));
			}
		}

		visited[idx] = false;
		return memo[idx][jumps] = minJumps;

	}

	public int minJumps(int[] arr, final int n) {
		// Write your code here and return

		// Approach-i) Recursion
		// return solveRec(0, 0,new boolean[n], arr, n);

		// Approach-ii) Memoization
		return solveMemo(0,0,new boolean[n],arr,n,new Integer[n][n]);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		System.out.println(new MinJumps().minJumps(arr, n));
	}
}
