/* Extension of Isomorphic 
A secret agent receives a list of encrypted codewords. 
Each codeword must follow the same symbol sequence as a given prototype code. 
Your mission is to find which codewords follow the same symbol arrangement.

NOTE:
Matching is not about the actual characters, but how they repeat.
For example, “moon” = m o o n → pattern: first letter, two repeated letters, and a unique last letter.

Input Format:
-------------
Line-1: A space-separated list of words 
Line-2: A string representing the reference pattern

Output Format:
---------------------
Line-1: A list of words that follow the same pattern, if not found print -1


Sample Input-1:
---------------
leet abcd loot geek cool for peer dear seed meet noon mess loss
moon

Sample Output-1:
----------------
leet loot geek cool peer seed meet


Sample Input-2:
----------------
leet abcd loot geek cool for peer dear
lamp

Sample Output-2:
----------------
abcd dear
 */

import java.util.*;

public class Pattern {
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
            String[] words = sc.nextLine().split(" ");
            String pattern = sc.next();

            Pattern obj = new Pattern();
            List<String> lst = new ArrayList<>();
            for(String word : words){
                // check if they are isomorphic and just print them
                if(obj.isIsomorphic2(word, pattern)){
                    lst.add(word);
                }
            }

            System.out.println((lst.isEmpty()) ? -1 : String.join(" ",lst));


        }
    }
}
