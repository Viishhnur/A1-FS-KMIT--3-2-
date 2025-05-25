/*
Mr. Ram works in a manufacturing industry where items of different weights are produced. 
His task is to find pairs of items whose weight difference is equal to a given value k. 
You are asked to write a program to help Mr. Ram achieve this without using extra space.

Input Format:
-------------
Line-1: An integer n, the number of items (weights).
Line-2: n space-separated positive integers representing the weights of the items.
Line3: An integer k, the desired difference between the weights of the paired items.

Output Format:
--------------
Line-1 to n: Print the pairs of integers where the difference between the two numbers in each pair is exactly k.
Each pair should be printed on a new line.

Constraints:
------------
The input list will contain distinct integers.
You are not allowed to use extra space, so you must solve the problem in-place or by using a fixed amount of space.


Sample Input-1:
---------------
8
1 2 3 4 5 6 7 8
3

Sample Output-1:
----------------
1 4
2 5
3 6
4 7
5 8

Sample Input-2:
---------------
10
5 10 15 20 25 30 35 40 45 50
5

Sample Output-2:
----------------
5 10
10 15
15 20
20 25
25 30
30 35
35 40
40 45
45 50
 */

import java.util.Arrays;
import java.util.Scanner;

public class PairItems {
    private void printPairsWithDiff(int[] nums, final int n, final int k) {
        Arrays.sort(nums);
        int left = 0, right = 1;

        while (right < n) {
            if (left == right || nums[right] - nums[left] < k) {
                right++;
            } else if (nums[right] - nums[left] > k) {
                left++;
            } else {
                System.out.println(nums[left] + " " + nums[right]);
                int currLeft = nums[left];
                int currRight = nums[right];

                while (left < n && currLeft == nums[left])
                    left++;
                while (right < n && currRight == nums[right])
                    right++;

                if (left >= right) {
                    right = left + 1; // ensure right is always ahead of left
                }
            }
        }
    }

    public static void main(String[] argsss) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int k = sc.nextInt();

            new PairItems().printPairsWithDiff(arr, n, k);
        }
    }
}
