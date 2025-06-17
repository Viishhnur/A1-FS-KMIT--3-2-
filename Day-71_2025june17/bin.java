/*Each possible K-bit command code (from 000…0 to 111…1) must appear in the log to validate the transmission’s integrity. 
If any K-bit pattern is missing, the message is compromised and the mission fails.

You are given:
	A binary string S (only characters '0' and '1').
	An integer K.

Determine whether every binary code of length K appears at least once as a contiguous substring in S.
 */


 import java.util.*;
public class bin {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String binString=sc.nextLine();
            int k=sc.nextInt();
            boolean res=solve(binString,k);
            System.out.println(res);
        }
    }

    static boolean solve(String binString, int k){
        int len=1<<k, n=binString.length();
        if(n<k || len > n-k+1) return false;
        boolean[] seen=new boolean[len];
        class Solution {
    public boolean hasAllCodes(String s, int k) {
        int totalCodes = 1 << k; // 2^k
        int bitmask = totalCodes - 1;
        int n = s.length();

        if (n < k || totalCodes > n - k + 1) return false;

        boolean[] seen = new boolean[totalCodes];
        int current = 0, count = 0;

        for (int i = 0; i < n; i++) {
            current = ((current << 1) & bitmask) | (s.charAt(i) - '0');
            if (i >= k - 1 && !seen[current]) {
                seen[current] = true;
                count++;
                if (count == totalCodes) return true;
            }
        }
        return false;
    }
}

        int current=0;
        int mask=len-1;
        for(int i=0;i<n-k+1;i++){
            current=((current<<1)&mask)|(binString.charAt(i)-'0');
            if(i>=k-1 && !s.con){
                s.add(current);
                if(s.size()==len) return true;
            }
        }
        return  false;
    }
}
