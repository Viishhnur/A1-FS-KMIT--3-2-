/*
Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can construct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6

Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false
 */
import java.util.Arrays;
import java.util.Scanner;
public class Blocks_To_Square{

    private boolean backtrack(int[] nums, boolean[] used, int k, int start, int currSum, int target) {
        if (k == 1) return true;
        if (currSum == target)
            return backtrack(nums, used, k - 1, 0, 0, target);

        for (int i = start; i < nums.length; i++) {
            if (used[i] || currSum + nums[i] > target) continue;

            used[i] = true;
            if (backtrack(nums, used, k, i + 1, currSum + nums[i], target))
                return true;
            used[i] = false;
        }
        return false;
    }
    public boolean makesquare(int[] matchsticks,final int n) {

        int sum = Arrays.stream(matchsticks).sum();
        if(sum % 4 != 0) return false;

        Arrays.sort(matchsticks);

        int target = sum / 4;
        if (matchsticks[n - 1] > target) return false;

        return backtrack(matchsticks, new boolean[n], 4, 0, 0, target);
    }
    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0 ; i < n ; i++){
                arr[i] = sc.nextInt();
            }
            
            System.out.println(new Blocks_To_Square().makesquare(arr,n));
        }
    }
}