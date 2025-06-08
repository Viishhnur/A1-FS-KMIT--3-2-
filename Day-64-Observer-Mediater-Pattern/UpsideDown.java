/*
VishnuVardan is working with Decision Trees for AI-based predictions.
To analyze alternative outcomes, Kishore has planned to flip the decision 
tree horizontally to simulate a reverse processing approach.

Rules for Flipping the Decision Tree:
	- The original root node becomes the new rightmost node.
	- The original left child becomes the new root node.
	- The original right child becomes the new left child.
This transformation is applied level by level recursively.

Note:
------
- Each node in the given tree has either 0 or 2 children.
- Every right node in the tree has a left sibling sharing the same parent.
- Every right node has no further children (i.e., they are leaf nodes).

Your task is to help VishnuVardan flip the Decision Tree while following 
the given transformation rules.

Input Format:
-------------
Space separated integers, nodes of the tree.

Output Format:
--------------
Print the list of nodes of flipped tree as described below.


Sample Input-1:
---------------
4 2 3 5 1

Sample Output-1:
----------------
5 1 2 3 4


Sample Input-2:
---------------
4 2 5

Sample Output-2:
----------------
2 5 4
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}
public class UpsideDown {
    
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
    
    //Write your code here 
    private TreeNode upsideDownBinaryTree(TreeNode root){
        // Base case
        if (root == null || root.left == null) return root;

        // Recursively flip the left subtree
        TreeNode newRoot = upsideDownBinaryTree(root.left);

        // Rewire connections
        root.left.left = root.right;  // left child's left → right sibling
        root.left.right = root;       // left child's right → current node

        root.left = null;
        root.right = null;

        return newRoot;
    }

    
    private List<Integer> levelOrderTraversal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        
        while(!qu.isEmpty()){
            int size = qu.size();
            for(int i = 0 ; i < size ; ++i){
                TreeNode node = qu.poll();
                if(node != null){
                    
                    ans.add(node.val);
                    if(node.left != null) qu.offer(node.left);
                    if(node.right != null) qu.offer(node.right);
                }
                
            }
        }
        
        return ans;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        List<Integer> data = new ArrayList<>();
        for(String s:input) {
            data.add(Integer.parseInt(s));
        }
        scanner.close();
        
        TreeNode root = buildTree(data);
        UpsideDown obj = new UpsideDown();
        List<Integer> al = obj.levelOrderTraversal(obj.upsideDownBinaryTree(root));
        for(int x: al)
        System.out.print(x+" ");
    }
}
	  
	  
	  