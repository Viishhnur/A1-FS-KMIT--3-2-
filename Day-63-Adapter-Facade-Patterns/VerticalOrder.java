/*
Imagine you are a librarian organizing books on vertical shelves in a grand library. 
The books are currently scattered across a tree-like structure, 
where each book (node) has a position determined by its shelf number (column) 
and row number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged from left to right, just as they appear in the original scattered arrangement.

Sample Input-1:
---------------
3 9 20 -1 -1 15 7

Sample Output-1:
----------------
[[9],[3,15],[20],[7]]

Explanation:
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]

Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]

Library Organization Rules:
1. Each column represents a shelf from left to right.
2. Books on the same shelf are arranged from top to bottom.
3. If books share the same position, they are arranged left to right in order of appearance.
 */
import java.util.*;
import java.util.stream.Collectors;
class TreeNode{
    TreeNode left,right;
    int val;
    
    public TreeNode(int val){
        this.val = val;
    }
}
class Solution
{
	public List<List<Integer>> verticalOrder(TreeNode root) {
		// We need two queues here 
		Queue<TreeNode> nodes = new LinkedList<>();
		Queue<Integer> shelves = new LinkedList<>();
		TreeMap<Integer,List<Integer>> mp = new TreeMap<>();
		nodes.offer(root);
		shelves.offer(0);
		
		while(!nodes.isEmpty() && !shelves.isEmpty()){
		    TreeNode node = nodes.poll();
		    int shelfNo = shelves.poll();
		    
		    mp.computeIfAbsent(shelfNo, k -> new ArrayList<>()).add(node.val);
		    
		    //  go to nbrs
		    if(node.left != null){
		        nodes.offer(node.left);
		        shelves.offer(shelfNo-1);
		    }
		    
		    if(node.right != null){
		        nodes.offer(node.right);
		        shelves.offer(shelfNo+1);
		    }
		}
		
		List<List<Integer>> ans = new ArrayList<>();
		mp.forEach((key,lst)->{
		   ans.add(lst); 
		});
		
		return ans;
	}
}
public class VerticalOrder {
    
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
        
        System.out.println(new Solution().verticalOrder(root));
    }
}
