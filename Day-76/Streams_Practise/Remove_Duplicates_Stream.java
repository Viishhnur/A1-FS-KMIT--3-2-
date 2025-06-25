/*
Given a list of n strings, remove duplicate entries, 
preserving only the first occurrence, and output the deduplicated list.

Input Format:
-------------
Line-1: A integer n, number of strings
Line-2: n space separated strings

Output Format:
--------------
Space‐separated list of unique strings in original order.

Sample Input:
-------------
6
Java Python C# Java Kotlin Python

Sample Output:
--------------
Java Python C# Kotlin
 */
import java.util.*;
import java.util.stream.*;
public class Remove_Duplicates_Stream{
    public static void main(String... hello){
        int n;
        List<String> words = new ArrayList<>();
        
        try(Scanner sc = new Scanner(System.in)){
            n = sc.nextInt();
            for(int i = 0 ; i < n ; i++){
                words.add(sc.next());
            }
        }
        
        // Approach-i) Use LinkedHashMap + streams
        // Map<String,Integer> mp = new LinkedHashMap<>();
        // words.stream()
        //     .forEach(word -> mp.put(word,mp.getOrDefault(word,0)+1));
        
        // mp.keySet().stream()
        //         .forEach(word -> System.out.print(word + " "));
        
        // Approach-ii) Using distinct() as a intermediate method
        words.stream()
                .distinct()
                .forEach(word -> System.out.print(word + " "));
        
        
    }
}
