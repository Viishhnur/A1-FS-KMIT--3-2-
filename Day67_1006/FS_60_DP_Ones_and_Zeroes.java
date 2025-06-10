/*
 * You are managing a fleet of exploratory spacecraft, each carrying containers 
composed of two types of critical resources: 
fuel units (represented by '0') and oxygen tanks (represented by '1'). 
You're given a list 'containers', where each container is represented by a 
binary string indicating its resource composition, 
along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
'oxygenLimit' (maximum allowed oxygen tanks).

Your goal is to select the largest possible subset of containers such that 
the total number of fuel units does not exceed 'fuelLimit' and the total number 
of oxygen tanks does not exceed 'oxygenLimit'.

Input format:
-------------
Line 1: Space seperated strings, represents the container strings
Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

Output format:
--------------
An integer, largest possible subset of containers.


Sample Input-1:
---------------
10 0001 111001 1 0
5 3

Sample Output-1:
----------------
4

Explanation: 
The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
- Total fuel units = 5 (within limit)
- Total oxygen tanks = 3 (within limit)
Container "111001" cannot be included as it exceeds the oxygen tank limit.


Sample Input-2:
---------------
10 0 1
1 1

Sample Output-2:
----------------
2

Explanation: The largest subset meeting the constraints is {"0", "1"}.
- Total fuel units = 1
- Total oxygen tanks = 1


Constraints:
- 1 <= containers.length <= 600
- 1 <= containers[i].length <= 100
- 'containers[i]' consists only of digits '0' and '1'.
- 1 <= fuelLimit, oxygenLimit <= 100
*/

import java.util.Arrays;
import java.util.Scanner;

public class  FS_60_DP_Ones_and_Zeroes{
    
    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in)){
            String[] strings = sc.nextLine().split("\\s+");
            int[] cap = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int res = solution(strings, cap);
            System.out.println(res);
        }
    }
    static int solution(String[] strings, int[] capacity){
        int[][] values=new int[strings.length][2];
        int n=strings.length;
        for(int i=0;i<n;i++){
            for(char c:strings[i].toCharArray()){
                if(c=='0') values[i][0]++;
                else values[i][1]++;
            }
        }
        return solve(values, capacity,n);
    }
    static int solve(int[][]values, int[] capacity, int n){
        if(n==0 || capacity[0]==0 || capacity[1]==0) return 0;
    }
}