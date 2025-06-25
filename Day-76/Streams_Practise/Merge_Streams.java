/*
Given two arrays a of size m and b of size n, 
merge them into a single sorted array and print it.

Input Format:
-------------
Line-1: two space separated integers m and n, sizes of two arrays
Line-2: m space separated integers, a₁ a₂ … a_m
Line-3: n space separated integers, b₁ b₂ … b_n

Output Format:
--------------
Sorted merged array in one line, space‐separated.

Sample Input:
-------------
4 4
4 2 7 1
8 3 9 5

Sample Output:
--------------
1 2 3 4 5 7 8 9
 */
import java.util.*;
import java.util.stream.*;
public class Merge_Streams{
    public static void main(String... helloAll){
        int m,n;
        int[] arr1 , arr2;
        try(Scanner sc = new Scanner(System.in)){
            m = sc.nextInt();
            n = sc.nextInt();
            
            arr1 = new int[m];
            for(int i = 0 ; i < m ; i++) arr1[i] = sc.nextInt();
            
            arr2 = new int[n];
            for(int i = 0 ; i < n ; i++) arr2[i] = sc.nextInt();
        }
        
        int[] res = IntStream.concat(Arrays.stream(arr1),Arrays.stream(arr2)).sorted().toArray();
        for(int num : res){
            System.out.print(num + " ");
        }
    }
}