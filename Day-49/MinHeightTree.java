
/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, 
and an array of n - 1 edges where edges[i] = [ai, bi] indicates 
that there is an undirected edge between the two nodes ai and bi in the tree, 

You can choose any node of the tree as the root. 
When you select a node x as the root, the result tree has height h. 
Among all possible rooted trees, those with minimum height (i.e. min(h))  
are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path 
between the root and a leaf.

Sample Input-1:
---------------
4 3 //no of vertices and no of edges
1 0
1 2
1 3

Sample Output-1:
----------------
[1]

Explanation:The height of the tree is 1 when the root is the node with label 1 
which is the only MHT.

Sample Input-2:
---------------
6 5 //no of vertices and no of edges
3 0
3 1
3 2
3 4
5 4

Sample Output-2:
----------------
[3, 4]

Constraints:
------------
-> 1 <= n <= 2 * 104
-> edges.length == n - 1
-> 0 <= ai, bi < n
-> ai != bi
-> All the pairs (ai, bi) are distinct.
-> The given input is guaranteed to be a tree and there will be no repeated edges.
 */
import java.util.*;
class Graph {
    private final int n;
    private final List<List<Integer>> adjLst;

    private void buildAdjLst(int[][] edges) {

        for (int i = 0; i < n; i++) {
            adjLst.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjLst.get(u).add(v);
            adjLst.get(v).add(u);
        }
    }

    public Graph(int n, int[][] edges) {
        this.n = n;
        this.adjLst = new ArrayList<>();
        buildAdjLst(edges);
    }

    private int dfs(int node, boolean[] visited) {
        visited[node] = true;
        if (adjLst.get(node).isEmpty()) { // Base case is when i reached the leaf node 
            return 0;
        }

        int maxi = 0;
        for (int nbr : adjLst.get(node)) {
            if (!visited[nbr]) {
                maxi = Math.max(maxi, 1 + dfs(nbr, visited));
            }
        }

        return maxi;
    }

    public List<Integer> getMHTsLabels() {
        List<Integer> ans = new ArrayList<>();

        // do dfs from each node
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // (ind,height)
        for (int i = 0; i < n; i++) {
            int height = dfs(i, new boolean[n]);
            pq.offer(new int[] { i, height });
        }

        int[] mht = pq.poll();
        ans.add(mht[0]);
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // (ind,height)

            if (curr[1] == mht[1]) {
                ans.add(curr[0]);
            } else {
                break;
            }
        }

        return ans;

    }
}

public class MinHeightTree {
    private List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Graph g = new Graph(n,edges);

        return g.getMHTsLabels();
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){

            int n = sc.nextInt();
            int m = sc.nextInt();
            int edges[][] = new int[m][2];
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt(), b = sc.nextInt();
                edges[i][0] = a;
                edges[i][1] = b;
            }
            System.out.println(new MinHeightTree().findMinHeightTrees(n, edges));
        }
    }

}
