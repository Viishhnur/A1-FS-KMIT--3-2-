/*
Two brothers are playing a game based on two sorted lists of numerical elements and a target sum.

The first brother provides:
-> Two sorted lists of integers.
-> A target sum.

The second brother's task is to find and return the closest pair of elements (one from each list) 
whose sum is closest to the given target.

Rules:
------
Each pair must consist of one element from each list.
If multiple pairs have the same closest sum, return any one valid pair.
The input lists are already sorted in ascending order.
The result must be printed as a comma-separated pair.

Input Format:
-------------
Line 1: An integer N1, representing the size of the first list.
Line 2: N1 space-separated integers, representing elements of the first sorted list.
Line 3: An integer N2, representing the size of the second list.
Line 4: N2 space-separated integers, representing elements of the second sorted list.
Line 5: An integer X, representing the target sum.

Output Format:
--------------
Line-1: Print a comma-separated pair (a, b), where a is from list_1 and b is from list_2, such that their sum is closest to the target sum.

Sample Input-1:
---------------
4
1 4 5 7
4
10 20 30 40
32

Sample Output-1:
----------------
1,30

Explanation:
------------
The closest pair to 32 is (1,30) → 1 + 30 = 31, which is the closest sum to 32.

Sample Input-2:
---------------
3
2 4 6
4
5 7 11 13
15

Sample Output-2:
----------------
2,13

Explanation:
------------
The closest pair to 15 is (2,13) → 2 + 13 = 15, which is an exact match.
 */

import java.util.*;

public class ClosestPair {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for first sorted list
        int l1 = sc.nextInt();
        int[] arr1 = new int[l1];
        for (int i = 0; i < l1; i++) {
            arr1[i] = sc.nextInt();
        }

        // Input for second sorted list
        int l2 = sc.nextInt();
        int[] arr2 = new int[l2];
        for (int i = 0; i < l2; i++) {
            arr2[i] = sc.nextInt();
        }

        // Input for target sum
        int target = sc.nextInt();

        // Array to store result pair
        int[] result = new int[2];

        // Find closest pair
        findPair(arr1, arr2, target, result);

        // Output result
        System.out.println(result[0] + "," + result[1]);

        sc.close();
    }

    // Function to find the closest pair with sum closest to the target
    public static void findPair(int[] arr1, int[] arr2, int target, int[] result) {
        int i = 0;                // Pointer for arr1 (start)
        int j = arr2.length - 1; // Pointer for arr2 (end)
        int minDiff = Integer.MAX_VALUE;

        while (i < arr1.length && j >= 0) {
            int sum = arr1[i] + arr2[j];
            int diff = Math.abs(sum - target);

            if (diff < minDiff) {
                minDiff = diff;
                result[0] = arr1[i];
                result[1] = arr2[j];
            }

            if (sum == target) {
                return; // Found exact match, exit early
            } else if (sum > target) {
                j--; // Try a smaller number from arr2
            } else {
                i++; // Try a larger number from arr1
            }
        }
    }
}
