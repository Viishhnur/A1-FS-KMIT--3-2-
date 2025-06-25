/*
Given n strings, compute how many times each string appears, 
and output a frequency map in lexicographic order of the string.

Input Format:
-------------
Line-1: A integer n, number of strings
Line-2: n space separated strings

Output Format:
--------------
<string>:<count> 
One line per unique string

Sample Input:
-------------
8
Pen Eraser Notebook Pen Pencil Stapler Notebook Pencil

Sample Output:
--------------
Eraser:1
Notebook:2
Pen:2
Pencil:2
Stapler:1
 */
import java.util.*;
import java.util.stream.*;
public class Frequency_Stream{
    public static void main(String... june25th){
        int n;
        List<String> words = new ArrayList<>();
        
        try(Scanner sc = new Scanner(System.in)){
            n = sc.nextInt();
            for(int i = 0 ; i < n ; i++){
                words.add(sc.next());
            }
        }
        
        Map<String,Long> freq = words.stream()
                    .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()    
                    ));
        
        freq.keySet().stream()
            .sorted() // sorts the keys in lexicographical order
            .forEach(word ->{
                System.out.println(word + " : " + freq.get(word));
            });
    }
}
