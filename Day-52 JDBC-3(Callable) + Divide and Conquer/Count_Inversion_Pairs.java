/*
You are given an array of integers. An inversion is defined as a pair of indices (i, j) such that:

i < j, and

A[i] > A[j]

Your task is to count the total number of such inversion pairs in the array.

Inversions indicate how far an array is from being sorted. If the array is completely sorted in 
ascending order, the number of inversions is 0. If it's sorted in descending order, the number of inversions 
is at its maximum.

Input Format:
-------------
Line-1: An integer n — the number of elements in the array
Line-2: A list of n space-separated integers A[0], A[1], ..., A[n-1]

Output Format:
-------------
Line-1: A single integer — the total number of inversions in the array

Sample Input-1:
-------------
6
3 -1 0 -2 2 1

Sample Output-1:
-------------
8
#Inversions are: (3, -1)(3, 0)(3, -2)(3, 2)(3, 1)(-1, -2)(0, -2)(2, 1)

Sample Input-2:
-------------
4
4 3 2 1

Sample Output-2:
-------------
6
#Inversions: All pairs where left > right — total = 6

Sample Input-3:
-------------
6
1 2 3 4 5 6

Sample Output-3:
-------------
0

Constraints:
-------------
-> 1 ≤ n ≤ 1000
-> -10^9 ≤ A[i] ≤ 10^9
-> Elements can be positive, negative, or zero
-> Input array may be partially or completely unsorted
-> Time complexity should be O(n log n) 
 */

import java.util.Scanner;

public class Count_Inversion_Pairs{
    // Function to count inversions in the array.
    private int cnt;
    
    public Count_Inversion_Pairs(){
        this.cnt = 0;
    }
    
    private void merge(int low,int mid,int high,int[] arr){
        
        // Now one array is from low to mid another from mid + 1 to high
        // We should merge these two sorted arrays
        int left = low , right = mid + 1;
        int[] temp = new int[high - low + 1];
        int idx = 0;
        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                temp[idx++] = arr[left++];
            }else{
                cnt += mid - left + 1; // increment the inversion count here 
                temp[idx++] = arr[right++];
            }
        }
        
        while(left <= mid){
            temp[idx++] = arr[left++];
        }
        
        while(right <= high){
            temp[idx++] = arr[right++];
        }
        // Now copy the temp back into arr
        for(int i = low ; i <= high ; i++){
            arr[i] = temp[i - low];
        }
    }
    private void mergeSort(int low,int high,int[] arr){
        if(low >= high) return;
        
        int mid = low + (high - low)/2;
        
        mergeSort(low,mid,arr);
        mergeSort(mid+1,high,arr);
        merge(low,mid,high,arr);
        
    }
    int inversionCount(int arr[],int n) {
        // Optimal Approach using Merge Sort find the inversion pairs T.C:- O(nlogn + n) , S.C:- O(n)
        mergeSort(0,n-1,arr);
        return cnt;
        
    }
    public static void main(String... viishhnu){
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();
            int[] arr = new int[n];
            
            for(int i = 0 ; i < n ; i++){
                arr[i] = sc.nextInt();
            }
            
            System.out.println(new Count_Inversion_Pairs().inversionCount(arr,n));
            
        }
    }
}