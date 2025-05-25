/*
You are provided with a sequence of integers representing various measurements. 
A position in this sequence is termed a balance point if the sum of all measurements before it 
equals the sum of all measurements after it. 
Your task is to identify all such balance points within the sequence.

Input Format:
--------------
Line-1: An integer N, representing the number of measurements in the sequence.
Line-2: A line with N space-separated integers, representing the measurements.

Output Format:
--------------
Line-1: Print all the balance point indices as space-separated integers. If no balance point exists, print -1.

Sample Input-1:
---------------
7
-7 1 5 2 -4 3 0

Sample Output-1:
----------------
3 6

Explanation:
------------
At index 3: Sum of elements before = -7 + 1 + 5 = -1, Sum of elements after = -4 + 3 + 0 = -1.
At index 6: Sum of elements before = -7 + 1 + 5 + 2 + (-4) + 3 = 0, Sum of elements after = 0.

Sample Input-2:
---------------
3
1 2 3

Sample Output-2:
----------------
-1

Explanation:
-------------
No index satisfies the balance point condition.

Sample Input-3:
---------------
5
2 3 5 3 2

Sample Output-3:
----------------
2

Explanation:
------------
At index 2: Sum before = 2 + 3 = 5, Sum after = 3 + 2 = 5.

Constraints:
-------------
1 ≤ N ≤ 100,000
-1,000,000 ≤ measurement[i] ≤ 1,000,000
 */

import java.util.Scanner;
public class BalancePoint{
    private static void printBalancedPoints(int[] arr,final int n){
        // First calc the prefix sum and totalSum
        int totalSum = arr[0];
        int[] prefSum = new int[n];
        prefSum[0] = arr[0];
        for(int i = 1 ; i < n ; i++){
            prefSum[i] = prefSum[i-1] + arr[i];
            totalSum += arr[i];
        }
        
        boolean foundBalancedPoint = false;
        for(int i = 0 ; i < n ; i++){
            int prefix_sum = prefSum[i] - arr[i]; 
            int suffix_sum = totalSum - prefSum[i]; 
            if(prefix_sum == suffix_sum){
                System.out.print(i + " ");
                foundBalancedPoint = true;
            }
        }
        
        if(!foundBalancedPoint){
            System.out.print(-1);
            
        }
        
    } 
    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0 ; i < n ; i++){
                arr[i] = sc.nextInt();
            }
            
            printBalancedPoints(arr,n);
        }
    }
}