import java.util.Scanner;

/*
 * Amogh is an Antiquarian, The person who collects antiques.
He found a rear keyboard which has following keys,
Keys are 'N', 'S', 'C' and 'P'

1st Key - 'N': Print one character 'N' on Console.
2nd Key - 'S': Select the whole Console.
3rd Key - 'C': Copy selected content to buffer.
4th Key - 'P': Print the buffer on Console, and append it after what has 
already been printed.

Now, your task is to find out maximum numbers of 'N's you can print
after K keystrokes . 

Input Format:
-------------
An integer K

Output Format:
--------------
Print an integer, maximum numbers of 'N's you can print.


Sample Input-1:
-------------------
3

Sample Output-1:
-------------------- 
3

Explanation: 
---------------
We can print at most get 3 N's on console by pressing following key sequence:
N, N, N



Sample Input-2:
-------------------
7

Sample Output-2:
---------------------
9

Explanation: 
---------------
We can print at most get 9 N's on console by pressing following key sequence:
N, N, N, S, C, P, P

 */

public class FS_59_DP_4KeysKeyboard {
    // Function to find out maximum number of 'A's that can be produced by
    // pressing keys in a certain order, given a limit of key presses
    static int maxA(int n) {
        // Initialize an array to store the subproblem results
        int[] dp = new int[n + 1];
        // Base case: For i presses, you can at most get i 'A's by pressing 'A' i times
        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }
        // Start filling dp table for each number of presses (index)
        for (int i = 3; i <= n; ++i) {
            // Explore the effect of using Ctrl-V after pressing Ctrl-A and Ctrl-C
            // starting from j=2 as you need at least one 'A' for Ctrl-A and Ctrl-C to make
            // sense
            for (int j = 2; j < i - 1; ++j) {
                // Calculate the maximum of either just pressing 'A'
                // or using a combination of Ctrl-A, Ctrl-C, and Ctrl-V
                // dp[j - 1] represents the number of 'A's on the screen before copying,
                // (i - j) represents the remaining number of presses after copying which is
                // used solely for pasting
                // the product of those two numbers represent the total 'A's after utilizing the
                // copy-paste operation
                dp[i] = Math.max(dp[i], dp[j - 1] * (i - j));
            }
        }
        // The last element of dp array contains the answer for n key presses
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(maxA(n));
        sc.close();
    }
}
