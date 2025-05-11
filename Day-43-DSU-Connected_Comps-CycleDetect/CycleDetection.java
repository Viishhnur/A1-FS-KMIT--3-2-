
/*
Mr. Balu is interested in solving puzzles. 
One puzzle involves a given number of nodes and undirected paths between these nodes. 

He needs to determine whether he can exit from path loop or not. 
If at least one cycle can be formed with the given paths, then he cannot exit.

Input Format:
-------------
Line-1: Two integers n and e, n, number of nodes and e number of edges
Line-2 to e: e number of integer pairs, space separated

Output Format:
--------------
Line-1: A string, Cycle_found or No_Cycle_Found

Sample Input-1:
---------------
5 5
0 1
1 2
2 3
3 4
4 0

Sample Output-1:
----------------
Cycle_Found

Sample Input-2:
---------------
5 4
0 1
1 2
2 3
3 4

Sample Output-2:
----------------
No_Cycle_Found
 */
import java.util.*;

class DisjointSet {
    private final int n;
    private final int[] rank;
    private final int[] parent;

    public DisjointSet(int n) {
        this.n = n;
        this.rank = new int[n];
        this.parent = new int[n];

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    public int findUParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findUParent(parent[node]);
    }

    public boolean unionByRank(int u, int v) {
        int pu = findUParent(u);
        int pv = findUParent(v);

        if (pu == pv)
            return true;

        if (rank[pu] == rank[pv]) {
            parent[pv] = pu;
            rank[pu]++;
        } else if (rank[pv] < rank[pu]) {
            parent[pv] = pu;
        } else {
            parent[pu] = pv;
        }

        return false;
    }
}

public class CycleDetection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int e = sc.nextInt();

        DisjointSet dsu = new DisjointSet(n);
        boolean ans = false;
        for (int i = 0; i < e; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            ans = ans || dsu.unionByRank(source, destination);
        }

        System.out.println(ans ? "Cycle_Found" : "No_Cycle_Found");

        sc.close();
    }
}
