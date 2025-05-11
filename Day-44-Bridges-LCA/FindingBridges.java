/*
Given an undirected graph of V vertices and E edges. 
Our task is to find all the bridges in the given undirected graph. 

A bridge in any graph is defined as an edge which, when removed, makes the graph disconnected.

Sample Input-1:
---------------
4 //no of vertices
3 //no of edges
0 1
0 2
1 3

Sample Output-1:
----------------
1 3
0 1
0 2

Sample Input-2:
---------------
5 
5
1 0
1 2
0 2
3 0
3 4

Sample Output-2:
---------------
3 4
0 3

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

    private void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low) {
        visited[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        // Now go to adjacent elements , update low greedily for each nbr of node except
        // parent
        for (int nbr : adjLst.get(node)) {
            if (nbr == parent)
                continue; // skip parent

            if (!visited[nbr]) {
                // may be do a dfs from here
                dfs(nbr, node, visited, tin, low);

                // Once dfs is done updated the low greedily
                low[node] = Math.min(low[nbr], low[node]);

                // Check if node --- nbr can be a bridge
                if (low[nbr] > tin[node]) {
                    /*
                     * It means that , my nbr no way is able to reach the parent node since lowest
                     * time of insertion of
                     * nbr is greater than step count for parent , so i can't reach parent via any
                     * other path other than this
                     * hence a bridge
                     */
                    System.out.println(node + " " + nbr);
                }
            } else {
                // Just greedily update the nbr
                low[node] = Math.min(low[nbr], low[node]);
            }
        }
    }

    public void printBridges() {
        // May be i should be writing a dfs code
        // I need two arrays
        int[] tin = new int[n]; // time of insertion
        int[] low = new int[n]; // lowest time of insertion
        boolean[] visited = new boolean[n];
        dfs(0, -1, visited, tin, low);
    }
}

public class FindingBridges {
    public static void main(String args[]) {

        try (Scanner sc = new Scanner(System.in)) {
            int v = sc.nextInt();
            int e = sc.nextInt();

            int[][] edges = new int[e][2];
            for (int i = 0; i < e; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            Graph g = new Graph(v, e, edges);

            g.printBridges();
        }
    }
}
