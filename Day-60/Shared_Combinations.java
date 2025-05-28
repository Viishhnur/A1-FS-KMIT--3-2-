
/*
You are given a crystal with an energy level n. Your goal is to discover all the different ways this crystal could have been created by combining smaller shards.

Each combination must:
- Use only shards with energy values between 2 and n - 1.
- Be represented as a list of shard values whose product equals n.
- Use any number of shards (minimum 2), and the order is ascending order.

Your task is to return all unique shard combinations that can multiply together to recreate the original crystal.

Input Format:
-------------
Line-1: An integer

Output Format:
--------------
Line-1: List of all unique shard combinations

Sample Input-1:
---------------
28

Sample Output-1:
----------------
[[2, 14], [2, 2, 7], [4, 7]]

Sample Input-2:
---------------
23

Sample Output-2:
----------------
[]


Constraints:
- 1 <= n <= 10^4
- Only shards with energy between 2 and n - 1 can be used.

*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Shared_Combinations {
    private static HashSet<List<String>> ans;
    private static List<List<Integer>> res;
    static {
        ans = new HashSet<>();
    }

    public Shared_Combinations() {
        this.res = new ArrayList<>();
    }

    private void backtrack(int n, int pro, List<String> temp) {
        if (pro > n)
            return;
        if (pro == n && temp.size() > 1) {
            List<String> entry = new ArrayList<>(temp);
            Collections.sort(entry);
            ans.add(new ArrayList<>(entry));
            return;
        }

        for (int i = 2; i * pro <= n; i++) {
            temp.add(i + "");
            // System.out.print(i + " ");
            backtrack(n, i * pro, temp);
            temp.remove(temp.size() - 1);

        }
    }

    private void backtrack2(int n,int start,int pro,List<Integer> temp){
        if(pro > n) return;

        if(pro == n && temp.size() > 1){
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i = start ; i * pro <= n ; i++){
            // Take into consideration only if it is a factor
            if(n % i == 0){

                temp.add(i);
                backtrack2(n, i , i * pro, temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String... hi) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            
            // Approach-i) Using hashset
            // new Shared_Combinations().backtrack(n, 1, new ArrayList<>());
            // for (List<String> temp : ans) {
            //     Collections.sort(temp, (s1, s2) -> Integer.compare(Integer.parseInt(s1), Integer.valueOf(s2))); // sort
            //                                                                                                     // by
            //                                                                                                     // value
            //                                                                                                     // of
            //                                                                                                     // those
            //                                                                                                     // integers
            // }
            // System.out.println(ans);

            // Approach-ii) No hashmap
             new Shared_Combinations().backtrack2(n,2, 1, new ArrayList<>());
            System.out.println(res);
        }
    }
}