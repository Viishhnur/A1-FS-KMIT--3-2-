/*
In your computer lab, network switches are connected using Ethernet cables to form a LAN. 
However, a new hardware technician inadvertently connected switches in a way that created a cycle,
resulting in a network loop. 
As a consequence, the network has become unstable. You need to identify and remove the specific cable that 
is causing the loop. 
If there are multiple cables contributing to the loop, you should remove the one that was most recently added

Sample Input-1:
---------------
3
0 1
0 2
1 2

Sample Output-1:
----------------
[1 2]

Sample Input-2:
---------------
5
0 1
1 2
2 3
0 3
0 4

Sample Output-2:
----------------
[0,3]
 

Constraints:
------------
-> n == edges.length
-> 3 <= n <= 1000
-> edges[i].length == 2
-> 1 <= ai < bi <= edges.length
-> ai != bi
-> There are no repeated edges.
-> The given graph is connected.
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

public class LAN {
    private static int u, v;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        DisjointSet dsu = new DisjointSet(n);
        boolean ans = false;
        for (int i = 0; i < n; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            ans = dsu.unionByRank(source, destination);
            if (ans) {
                u = source;
                v = destination;
            }
        }

        System.out.println(Arrays.toString(new int[] { u, v }));

        sc.close();
    }
}
