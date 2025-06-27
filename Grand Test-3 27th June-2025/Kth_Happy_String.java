/* Leetcode - 1415 The Kth Lexicographical String of All Happy Strings of Length n
A happy string is a string that:

    consists only of letters of the set ['a', 'b', 'c'].
    s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).

For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.

Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.

Return the kth string of this list or return an empty string if there are less than k happy strings of length n.

 

Example 1:

Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".

Example 2:

Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.

Example 3:

Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. 
You will find the 9th string = "cab"

 

Constraints:

    1 <= n <= 10
    1 <= k <= 100
 */

import java.util.ArrayList;
import java.util.List;
public class Kth_Happy_String {
    private final List<String> happy ;
    public Kth_Happy_String(){
        this.happy = new ArrayList<>();
    }

    private void backtrack(StringBuilder sb,int n,int k){
        if(sb.length() == n){
            happy.add(sb.toString());
            return;
        }
        
        for(char ch = 'a' ; ch <= 'c' ; ch++){
            // take this only if 
            if(sb.isEmpty() || sb.charAt(sb.length()-1) != ch){
                sb.append(ch);
                backtrack(sb,n,k);
                sb.setLength(sb.length()-1);
            }

        }
    }
    public String getHappyString(int n, int k) {
        backtrack(new StringBuilder(),n,k);

        if(happy.size() < k){
            return "";
        }

        // But sorting is not needed while generating them we are already generating in lexicographical order
        // Collections.sort(happy); // this will sort
        return happy.get(k-1);
    }
}