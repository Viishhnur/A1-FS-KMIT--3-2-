/*
Imagine you are designing a network of secret corridors in an ancient castle. Each chamber in the castle leads to at most two other chambers through hidden passageways, forming a branching layout. The castle’s "longest secret route" is defined as the maximum number of corridors you must traverse to get from one chamber to another (without repeating the corridor). This route may or may not pass through the main entry hall.

Your task is to compute the length of longest secret route between two chambers which is represented by the number of corridors between them.

Example 1
input=
1 2 3 4 5 
output=
3

Structure:
       1
      / \
     2   3
    / \
   4   5

Explanation:
The longest secret route from chamber 4 to chamber 3 (alternatively, from chamber 5 to chamber 3) along the route:
4 → 2 → 1 → 3
This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

Example 2:
input=
1 -1 2 3 4
output=
2

Structure:
   1
    \
     2
    / \
   3   4

Explanation:
The longest secret route from chamber 3 to chamber 4 (alternatively, from chamber 1 to chamber 4) along the route:
3 → 2 → 4
This path has 2 corridors (3–2, 2–4), so the length is 2.


Constraints:
The castle can have between 1 and 10,000 chambers. 
Each chamber is assigned a value (which could be used for other purposes in the design) ranging from -100 to 100.
 */
import java.util.*;
import java.util.stream.Collectors;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { 
        this.val = x; 
    }
}

class Solution {
    private int diameter;
    public Solution(){
        this.diameter = 0;
    }
    private int helper(TreeNode root){
        if(root == null){
            return 0;
        }

        int lh = helper(root.left);
        int rh = helper(root.right);
        diameter = Math.max(diameter,lh + rh);
        return 1 + Math.max(lh,rh);
    }
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return diameter;
    }
}

public class BTDiameter {
    private static TreeNode buildTree(List<Integer> nodes) {
        if (nodes.isEmpty() || nodes.get(0) == -1) {
            return null;
        }
        
        TreeNode root = new TreeNode(nodes.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        
        while (index < nodes.size()) {
            TreeNode current = queue.poll();
            if (current == null) continue;
            
            // Process left child
            if (index < nodes.size()) {
                int leftVal = nodes.get(index++);
                if (leftVal != -1) {
                    current.left = new TreeNode(leftVal);
                    queue.offer(current.left);
                }
            }
            
            // Process right child
            if (index < nodes.size()) {
                int rightVal = nodes.get(index++);
                if (rightVal != -1) {
                    current.right = new TreeNode(rightVal);
                    queue.offer(current.right);
                }
            }
        }
        
        return root;
    }
    public static void main(String[] args) {
        List<Integer> inp;
        try(Scanner sc = new Scanner(System.in)){

            inp = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        }
        
        TreeNode root = buildTree(inp);
        // Generate the string representation
        System.out.println(new Solution().diameterOfBinaryTree(root));
        
    }
    
    
    
}
