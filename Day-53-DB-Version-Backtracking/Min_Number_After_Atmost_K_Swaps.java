/*
You are given a string S representing a positive integer and an integer k. Your task is to find 
the smallest possible number that can be formed by performing at most k swaps between any two digits 
of the string.
If k == 0, return the original number.
If the number is already the smallest possible permutation, return it as-is.
If the input is null or empty, return it unchanged.

A swap operation means exchanging the positions of any two digits (not necessarily adjacent). Each swap 
counts as one operation. The goal is to minimize the number.

Input Format:
-----------
A string S of digits (1 ≤ S.length ≤ 10)
An integer k (0 ≤ k ≤ 10) representing the number of allowed swaps.

Output Format:
------------
A string representing the smallest number possible after performing at most k swaps.

Constraints:
--------------
1 ≤ length(S) ≤ 10
0 ≤ k ≤ 10
Digits are in the range '0' to '9'
No leading zeros in input unless the number is exactly "0"
Each swap counts as 1 operation, regardless of position

Sample Input-1:
------------
934651
2

Sample Output-1:
----------------
134569

Sample Input-2:
-------------
11111
3
Sample Output-2:
--------------
11111
 */

import java.util.*;
public class Min_Number_After_Atmost_K_Swaps {
    private String ans;
    public Min_Number_After_Atmost_K_Swaps(String s){
        this.ans = s;
    }
    private static void swap(StringBuilder sb,int i,int j){
        char temp = sb.charAt(i);

        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }
    private void backtrack(int idx,StringBuilder sb,final int n,int k){
        if(sb.toString().compareTo(ans) < 0){
            ans = sb.toString();
            // System.out.print(sb.toString() + " ");
        }


        for(int i = idx; i < n ; i++){
            if(k > 0){
                if(sb.charAt(idx) == sb.charAt(i)){
                    // If characters are same no need to swap and decrement k
                    backtrack(idx+1,sb,n,k);
                }else{
                    swap(sb,idx,i);
                    backtrack(idx+1,sb,n,k-1);
                    swap(sb,idx,i);
                }
            }
        }
    }
    private String getMinNumberAfterKSwaps(String s,final int k){
        //  greedily fails use backtracking and generate all possible swaps and get the minimized string 
        StringBuilder sb = new StringBuilder(s);
        backtrack(0,sb,s.length(),k);

        return ans;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String s = sc.next();
            int k = sc.nextInt();

            System.out.println(new Min_Number_After_Atmost_K_Swaps(s).getMinNumberAfterKSwaps(s, k));
        }
    }
}