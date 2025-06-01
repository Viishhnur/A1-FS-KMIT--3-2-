/*
In the ancient land of Palindoria, wizards write magical spells as strings of lowercase letters. However, for a spell to be effective, each segment of the spell must
read the same forward and backward.

Given a magical spell 'spell', your task is to partition it into sequences of valid magical spell segments. 

Your goal is to help the wizard discover all valid combinations of magical spell segmentations.

Sample Input-1:
---------------
aab
  
Sample Output-1:  
----------------
[[a, a, b], [aa, b]]

Sample Input-2:
--------------- 
a
  
Sample Output-2:  
----------------
[[a]]

Spell Constraints:
------------------
- The length of the spell is between 1 and 16 characters.  
- The spell contains only lowercase English letters.  

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Magical_Spell{
    private static List<List<String>> ans;
    
    public Magical_Spell(){
        Magical_Spell.ans = new ArrayList<>();
    }
    
    private boolean isPalindromic(String s){
        int i = 0 , j = s.length()-1;
        while(i < j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        
        return true;
    }
    private void generateMagicalSpells(int idx,List<String> temp,String s,final int n){
        if(idx == n){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i = idx ; i < n ; i++){
            String sub = s.substring(idx,i+1);
            if(isPalindromic(sub)){
                temp.add(sub);
                generateMagicalSpells(i+1,temp,s,n);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static void main(String[] args){
        String s;
        try(Scanner sc = new Scanner(System.in)){
            s = sc.next();
        }
        
        new Magical_Spell().generateMagicalSpells(0,new ArrayList<>(),s,s.length());
        System.out.println(ans);
    }
}