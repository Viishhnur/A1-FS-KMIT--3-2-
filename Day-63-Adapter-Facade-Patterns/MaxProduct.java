/*
Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
    in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100
 */
import java.util.*;
import java.util.stream.Collectors;

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}
class Solution {
    private long totalSum;
    private long ans;
    private final int MOD; 
    
    public Solution() {
        this.totalSum = 0;
        this.ans = 0;
        this.MOD = 1_000_000_007;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        totalSum += root.val;
        dfs(root.left);
        dfs(root.right);
    }

    private long solve(TreeNode root) {
        if (root == null) return 0;

        long leftSubSum = solve(root.left);
        long rightSubSum = solve(root.right);

        long currentSubtreeSum = root.val + leftSubSum + rightSubSum;
        long complementSum = totalSum - currentSubtreeSum;

        ans = Math.max(ans, currentSubtreeSum * complementSum);
        return currentSubtreeSum;
    }

    public int maxProduct(TreeNode root) {
        dfs(root);              // Get total sum of tree
        solve(root);            // Find max product
        return (int)(ans % MOD);
    }
}
public class MaxProduct {
    
    public static TreeNode buildTree(List<Integer> data) {
        if (data.isEmpty() || data.get(0) == -1) return null;
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(data.get(0));
        queue.offer(root);
        
        int i = 1;
        while (i < data.size()) {
            TreeNode current = queue.poll();
            
            if (i < data.size() && data.get(i) != -1) {
                current.left = new TreeNode(data.get(i));
                queue.offer(current.left);
            }
            i++;
            
            if (i < data.size() && data.get(i) != -1) {
                current.right = new TreeNode(data.get(i));
                queue.offer(current.right);
            }
            i++;
        }
        
        return root;
    }
   
    public static void main(String[] args) {
        List<Integer> inp;
        try(Scanner sc = new Scanner(System.in)){

            inp = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        }
        
        TreeNode root = buildTree(inp);
        
        System.out.println(new Solution().maxProduct(root));
    }
}
