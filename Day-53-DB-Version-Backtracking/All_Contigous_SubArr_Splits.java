/*
Given a string str of length n, your task is to find all possible ways to split it into non-empty, contiguous, and 
non-overlapping substrings while preserving the original character order.If the input string is empty, return an empty list [].

Each split must use all characters in the original string exactly once, with substrings formed from consecutive characters only.

Input Format:
--------------
Line-1: A single string str containing only uppercase or lowercase English letters.

Output Format:
--------------
Line-1: A list of lists containing all possible combinations of non-overlapping, contiguous substrings.
Each inner list represents one valid way to split the string.

Constraints:
------------
-> 1 ≤ str.length() ≤ 100
-> The string can contain only uppercase or lowercase English letters.
-> Each substring in a combination must be contiguous (no skipping characters).
-> If the input is an empty string, output an empty list.s

Sample  Input-1:
----------------
ABC

Sample Output-1:
----------------
[["A", "B", "C"], ["A", "BC"], ["AB", "C"], ["ABC"]]

Sample  Input-2:
---------------
ABCD

Sample Output-2:
----------------
[["A", "B", "C", "D"], ["A", "B", "CD"], ["A", "BC", "D"], ["A", "BCD"], ["AB", "C", "D"], ["AB", "CD"], ["ABC", "D"], ["ABCD"]]
 */
import java.util.*;
public class All_Contigous_SubArr_Splits {
    private static List<List<String>> ans;

    static{
        All_Contigous_SubArr_Splits.ans = new ArrayList<>(); // initializing my static data members here
    }


    private static void backtrack(int idx,List<String> temp,String s,final int n){

        if(idx == n){
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i = idx ; i < n ; i++){
            
            String sub = s.substring(idx,i+1);
            temp.add(sub);
            backtrack(i+1, temp, s, n);
            temp.removeLast();
            
        }
    }
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String s = sc.next();

            backtrack(0,new ArrayList<>(),s,s.length());
            System.out.println(ans);
        }
    }
}
