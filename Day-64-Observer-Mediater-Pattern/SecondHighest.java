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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
class TreeNode {
    Integer val;
    TreeNode left, right;

    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class SecondHighest {
    private final PriorityQueue<Integer> maxHeap;
    public SecondHighest(){
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    private void dfs(TreeNode root){
        if(root == null) return;
        
        if(root.left != null && root.right != null){
            root.val = Math.max(root.left.val,root.right.val);
        }
        maxHeap.offer(root.val);
        dfs(root.left);
        dfs(root.right);
    }
    private int secondHighest(TreeNode root) {
       dfs(root);
       
       int firstMax = maxHeap.poll();
       int secondMax = firstMax;
       while(!maxHeap.isEmpty() && secondMax == firstMax){
           secondMax = maxHeap.poll();
       }
       
       return (secondMax == firstMax) ? -2 : secondMax;
    }

    
    private TreeNode buildTree(List<Integer> data){
        if(data.isEmpty() || data.get(0) == -1){
            return null;
        }
        
        Queue<TreeNode> qu = new LinkedList<>();
        TreeNode root = new TreeNode(data.get(0));
        qu.offer(root);
        int n = data.size();
        int i = 1;
        while(i < n){
            TreeNode curr = qu.poll();
            if(i < n && data.get(i) != -1){
                curr.left = new TreeNode(data.get(i));
                qu.offer(curr.left);
            }
            i++;
            
            if(i < n && data.get(i) != -1){
                curr.right = new TreeNode(data.get(i));
                qu.offer(curr.right);
            }
            i++;
        }
        
        return root;
    }
    public static void main(String[] args) {
        List<Integer> data;
        try(Scanner sc = new Scanner(System.in)){
            data = Arrays.stream(sc.nextLine().split(" ")).map(Integer :: parseInt).collect(Collectors.toList());
        }
        SecondHighest obj = new SecondHighest();
        TreeNode root = obj.buildTree(data);
        int secondTop = obj.secondHighest(root);
        System.out.println(secondTop);
    }
}
