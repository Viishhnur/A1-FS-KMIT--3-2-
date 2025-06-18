
/*
 Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 Example 1:

Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.

 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SmallestPositiveMiss {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            sc.nextLine();
            int[] nums = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int res = solve(nums);
            System.out.println(res);
        }
    }

    static int solve(int[] nums) {
        int n = nums.length;
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        for (int i = 1; i <= n; i++) {
            if (!s.contains(i))
                return i;
        }
        return n + 1;
    }
    static int solve1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i]<=0 || nums[i]>n) nums[i]=n+1;            
        }
        
        for(int i=0;i<n;i++){
            int id = Math.abs(nums[i]) - 1;
            if(id>=0 && id<n) nums[id] = -Math.abs(nums[id]);
        }
        
        for (int i = 1; i <= n; i++) {
            if(nums[i-1]>0) return i;
        }

        return n+1;
        /*Steps to Solve "First Missing Positive"
        
            Ignore irrelevant values
            Replace all numbers ≤ 0 or > n with a large dummy value (n + 1) — they can’t be the answer.
        
            Mark existing numbers
            For each valid number x (1 ≤ x ≤ n), mark its position by negating nums[x - 1] (if not already negative).
        
            Find the first missing
            Loop through the array: the first index i where nums[i] > 0 means number i + 1 is missing.
        
            Handle full presence case
            If all positions are marked (i.e., all negative), then numbers 1 to n exist → return n + 1.
        
        This ensures linear time (O(n)) and constant space (O(1)) without extra data structures.
        */
    }
}

