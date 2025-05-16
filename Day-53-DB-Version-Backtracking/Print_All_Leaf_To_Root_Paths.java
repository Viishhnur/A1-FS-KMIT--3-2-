
/*
You're given a binary tree represented in level-order (with -1 denoting nulls). Your task is 
to print all paths from each leaf node to the root, preserving the order from leaf → parent → … → root. Each 
path should be printed on a new line, with node values joined by " —> ".
If the current node is null, return.
If the current node is a leaf (no left or right children), print the current path.

Input Format:
------------
Line-1: A single line containing space-separated integers representing the binary tree in level-order.
Use -1 to represent a null (no child).

Output Format:
--------------
Each line represents one path from a leaf node to the root.
Node values must be joined using " —> ".
Order of output paths may vary based on traversal, but all valid paths must be included

Constraints:
-------------
1 ≤ number of nodes ≤ 10^4
Node values are integers in the range [-10^9, 10^9]
Input tree is binary (each node has at most two children)
Input is provided in level-order, and -1 denotes nulls

Sample Input-1:
------------------
1 2 3 4 5 6 7 -1 -1 -1 -1 8 9 -1 -1

Sample Output-1:
--------------
4 —> 2 —> 1  
5 —> 2 —> 1  
8 —> 6 —> 3 —> 1  
9 —> 6 —> 3 —> 1  
7 —> 3 —> 1

Sample Input-2:
-------------
1 -1 2 -1 3 -1 4

Sample Output-2:
-------------------
4 —> 3 —> 2 —> 1
 */
import java.util.*;

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int data) {
        this.left = null;
        this.right = null;
        this.val = data;
    }
}

class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void buildTree() {
        try(Scanner sc = new Scanner(System.in)){

            int[] arr = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer :: parseInt).toArray();
            root = buildTreeFromLevelOrder(arr);
        }
        
    }

    private TreeNode buildTreeFromLevelOrder(int[] arr) {
        if (arr.length == 0 || arr[0] == -1)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < arr.length && arr[i] != -1) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < arr.length && arr[i] != -1) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    public TreeNode getRoot() {
        return root;
    }
}

public class Print_All_Leaf_To_Root_Paths {
    private List<String> ans;

    public Print_All_Leaf_To_Root_Paths() {
        this.ans = new ArrayList<>();
    }

    private TreeNode dfs(TreeNode root, List<String> temp) {
        if (root == null) return null;

        temp.add(root.val + "");
        TreeNode left = dfs(root.left, temp);
        TreeNode right = dfs(root.right, temp);

        if (left == null && right == null) {
            // Leaf node: reverse the path and store
            List<String> rev = new ArrayList<>(temp); // directly do not reverse because our temp gets affected
            Collections.reverse(rev);
            ans.add(String.join(" —> ", rev));
        }

        temp.remove(temp.size() - 1); // Backtrack
        return root;
    }

    public void printBinaryTreePaths(TreeNode root) {
        dfs(root, new ArrayList<>());
        ans.forEach(System.out::println); // print all paths
    }

    public static void main(String[] args) {
        
        BinaryTree tree = new BinaryTree();
        tree.buildTree(); // builds tree from level-order input

        TreeNode root = tree.getRoot();
        new Print_All_Leaf_To_Root_Paths().printBinaryTreePaths(root);
    }
}
