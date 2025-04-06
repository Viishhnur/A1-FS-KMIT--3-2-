
/*
Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

I	f(I)
-------
0	""
1	"0"
2	"1"
3	"00"
4	"01"
5	"10"
6	"11"
7	"000"

You are given an integer value I, where I is positive number.
Your task is to find BBC representation of  the given number I.

Input Format:
-------------
Line-1: An integer I

Output Format:
--------------
Line-2: Print the BBC representation of I.


Sample Input-1:
---------------
23

Sample Output-1:
----------------
1000


Sample Input-2:
---------------
45

Sample Output-2:
----------------
01110


 */
import java.util.*;

public class EncodeString {

    public String encode(int num) {
        num++;

        StringBuilder sb = new StringBuilder();

        while(num > 1){ // skip the leading one
            sb.append(num & 1);
            num >>= 1;
        }

        return sb.reverse().toString();


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        // add 1 to num and convert to its binary representations and remove the highest
        // set bit from left

        System.out.println(Integer.toBinaryString(num + 1).substring(1));
        sc.close();
    }
}
