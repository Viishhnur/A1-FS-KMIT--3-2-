
/*
You are given two strings. Determine whether the first string can be converted to the second 
by replacing each character with a unique character while preserving the order.

Each character from the first string must map to one and only one character in the second string and vice versa.
Note: Two characters in the first string cannot map to the same character in the second.

Explanation:
------------
Two strings are isomorphic if:
•	Each character in the first-string maps to one unique character in the second string.
•	This mapping must be consistent throughout the string.
•	No two different characters from the first-string map to the same character in the second string.


Input Format:
-------------------
Line-1: two space-separated strings

Output Format:
----------------------
Line-1: Boolean value True/False


Sample Input-1:
---------------
ACAB XCXY

Sample Output:
----------------------
True

Explanation:
------------
A → X, C → C, B → Y — all mappings are unique and consistent.


Sample Input:
---------------------
FOO BAR

Sample Output:
----------------------
False
 */

import java.util.*;
public class Isomorphic_String {

    // Approach-i) Using two hashmaps
    public boolean isIsomorphic1(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        HashMap<Character,Character> mp1 = new HashMap<>();
        HashMap<Character,Character> mp2 = new HashMap<>();
        for(int i = 0 ; i < s.length() ; i++){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if(mp1.containsKey(ch1)){

                // check if ch2 is equal to previously mapped value
                if(mp1.get(ch1) != ch2){
                    return false;
                }
            }else{
                // check karo ki jis character se mai ch1 ko map kar raha hu kya vo pehle dusre character se map hua kya
                if(mp2.containsKey(ch2)){
                    return false;
                }
                mp1.put(ch1,ch2); // it means ch1 is mapping to ch2
                mp2.put(ch2,ch1);
            }
        }

        return true;
    }

    // Approach-ii) 
    // Use only 1 hashmap because when a maps to b store that b has already been mapped in the same map by appending dollar at it's end
    // if a maps b then also store that b has been used by "b$" maps a
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<String, String> mp = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            String ch1 = s.charAt(i) + "";
            String ch2 = t.charAt(i) + "";

            if (mp.containsKey(ch1)) {

                // check if ch2 is equal to previously mapped value
                if (!mp.get(ch1).equals(ch2)) {
                    return false;
                }

            } else {
                // check karo ki jis character se mai ch1 ko map kar raha hu kya vo pehle dusre character se map hua kya
                if(mp.containsKey(ch2 + "$")){
                    return false;
                }
                mp.put(ch1, ch2);
                mp.put(ch2+"$",ch1);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String s = sc.next();
            String t = sc.next();

            System.out.println(new Isomorphic_String().isIsomorphic2(s, t) ? "True" : "False");
        }
    }
}
