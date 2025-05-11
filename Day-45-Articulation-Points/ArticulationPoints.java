/*
A vertex is said to be an articulation point in a graph 
if removal of the vertex and associated edges disconnects the graph.

So, the removal of articulation points increases the number of connected components in a graph.

The main aim here is to find out all the articulations points in a graph.


Sample Input-1:
---------------
5
5
1 0
0 2
2 1
0 3
3 4

Sample Output-1:
----------------
[0, 3]

Sample Input-2:
---------------
4
3
0 1
1 2
2 3

Sample Output-2:
----------------
[1, 2]


Sample Input-3:
---------------
7
8
0 1 
1 2
2 0 
1 3
1 4
1 6
3 5
4 5

Sample Output-3:
----------------
[1]

Sample Input-4:
---------------
5
4
0 1
1 2
2 3
3 4

Sample Output-4:
----------------
[1, 2, 3]
 */

import java.util.*;

class Graph {
    private final int n;
    private final int e;
    private final List<List<Integer>> adjLst;
    private int timer;

    private void buildAdjLst(int[][] edges) {
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjLst.get(u).add(v);
            adjLst.get(v).add(u);
        }
    }

    private void init(int[][] edges) {
        for (int i = 0; i < n; i++) {
            adjLst.add(new ArrayList<>());
        }
        buildAdjLst(edges);
    }

    public Graph(int n, int e, int[][] edges) {
        this.timer = 1; // step count during dfs
        this.n = n;
        this.e = e;
        this.adjLst = new ArrayList<>();
        init(edges);
    }

    private void dfs(int node, int parent, int[] tin, int[] low, boolean[] visited, boolean[] isAP) {
        visited[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        int child = 0; // this is a check for root node
        for (int nbr : adjLst.get(node)) {

            if (nbr == parent)
                continue;

            if (!visited[nbr]) {
                dfs(nbr, node, tin, low, visited, isAP);

                // Update the low
                low[node] = Math.min(low[node], low[nbr]);

                // Check if this can be AP
                if (low[nbr] >= tin[node] && parent != -1) {
                    isAP[node] = true;
                }

                child++;
            } else {
                // Update the low , but not with the visited low , but tin since we should not
                // take visited again
                low[node] = Math.min(low[node], tin[nbr]);
            }
        }

        // For root node to be AP it should have atleast 2 children
        if (child >= 2 && parent == -1) {
            isAP[node] = true;
        }
    }

    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> articulationPoints(int V) {
        // Code here

        // May be we required few data strctures
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        boolean[] isAP = new boolean[V];

        // Start a dfs from each component
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, tin, low, visited, isAP);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isAP[i]) {
                res.add(i);
            }
        }

        if (res.isEmpty()) {
            res.add(-1); // indicating no AP found
        }

        return res;

    }

}

public class ArticulationPoints {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[][] edges = new int[e][2];
        for (int i = 0; i < e; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        Graph g = new Graph(v, e, edges);
        System.out.println(g.articulationPoints(v));
    }

}
