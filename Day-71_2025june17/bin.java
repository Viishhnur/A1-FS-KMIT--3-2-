/*
Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

 

Example 1:

Input: s = "00110110", k = 2
Output: true
Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.

Example 2:

Input: s = "0110", k = 1
Output: true
Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 

Example 3:

Input: s = "0110", k = 2
Output: false
Explanation: The binary code "00" is of length 2 and does not exist in the array.

 
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class bin {
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String s=sc.next();
            int k=sc.nextInt();
            boolean res=hasAllCodes(s,k);
            System.out.println(res);
        }
    }
    


    public boolean hasAllCodes1(String s, int k) {
        Set<String> ss = new HashSet<>();
        int totalCodes=1<<k;
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ss.add(s.substring(i, i + k));
            if(ss.size()==totalCodes) return true;
        }
        return ss.size() == totalCodes;
    }



    static boolean hasAllCodes(String s, int k) {
        int totalCodes = 1 << k; // 2^k
        int bitmask = totalCodes - 1;
        int n = s.length();

        if (n < k || totalCodes > n - k + 1)
            return false;

        boolean[] seen = new boolean[totalCodes];
        int current = 0, count = 0;

        for (int i = 0; i < n; i++) {
            current = ((current << 1) & bitmask) | (s.charAt(i) - '0');
            if (i >= k - 1 && !seen[current]) {
                seen[current] = true;
                count++;
                if (count == totalCodes)
                    return true;
            }
        }
        return false;
    }
}
