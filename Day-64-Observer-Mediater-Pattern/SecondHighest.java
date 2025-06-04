/*
In an Intelligence Agency, each senior officer supervises either two junior officers 
or none. The senior officer is assigned a clearance level equal to the higher clearance 
level of the two junior officers they supervise.

The clearance levels are represented as integer values in the range [1, 50], and multiple 
officers may have the same clearance level.

At the end, all officers (senior and junior) are collectively referred to as agents in the system.

You are provided with a hierarchical clearance level tree where each node represents 
an officer's clearance level. The tree structure follows these rules:
	- If a node has two children, its clearance level is the maximum of the two children's
	  clearance levels.
	- If a node has no children, it's clearance level is same as exists.
	- The value -1 indicates an empty (null) position.
Your task is to find the second highest clearance level among all agents in the agency. 
If no such level exists, return -2.

Input Format:
-------------
A single line of space separated integers, clearance levels of each individual.

Output Format:
--------------
Print an integer, second top agent based on rank.


Sample Input-1:
---------------
5 5 4 -1 -1 2 4

Sample Output-1:
----------------
4


Sample Input-2:
---------------
3 3 3 3 3

Sample Output-2:
----------------
-2
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
    PriorityQueue<Integer>pq;
    public Solution(){
        pq = new PriorityQueue<>(Collections.reverseOrder()); // this is a max heap
    }

    private void dfs(TreeNode root){
        if(root == null){
            return ;
        }
        if(root.left != null && root.right != null){
            root.val = Math.max(root.left.val,root.right.val);
        }
        
        pq.offer(root.val);
        dfs(root.left);
        dfs(root.right);

    }
    public int findSecondMaximumValue(TreeNode root) {
        dfs(root);
        int firstMax = pq.poll();
        int secondMax = firstMax;
        while(!pq.isEmpty() && secondMax == firstMax){
            secondMax = pq.poll();
        }
        return (secondMax == firstMax) ? -2 : secondMax;
    }

    

    public static void main(String[] args) {
        List<Integer> inp;
        try(Scanner sc = new Scanner(System.in)){

            inp = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        }
        
        TreeNode root = buildTree(inp);
        int secondTop = new Solution().findSecondMaximumValue(root);
        System.out.println(secondTop);
    }

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
}
