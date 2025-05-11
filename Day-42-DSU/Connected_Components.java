/*
There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 */
import java.util.Scanner;
class DisjointSet {
	private int n;
	private final int[] parent;
	private final int[] rank;
    private int comps;
	public DisjointSet(int n) {
		this.n = n;
		this.parent = new int[n];
		this.rank = new int[n];
        this.comps = n;
		init();
	}

	private void init() {
		for (int i = 0; i < n; i++) {
			this.parent[i] = i;
		}
	}

	public int findUParent(int node) {
		if (parent[node] == node) {
			return node;
		}

		return parent[node] = findUParent(parent[node]);
	}

	public void unionByRank(int u, int v) {
		int pu = findUParent(u);
		int pv = findUParent(v);

		if (pu == pv)
			return; // do not add a edge between them since they are in same component

		if (rank[pu] == rank[pv]) {
			parent[pv] = pu;
			rank[pu]++;
		} else if (rank[pv] < rank[pu]) {
			parent[pv] = pu;
		} else {
			parent[pu] = pv;
		}

        comps--;

	}

	public int getNoOfComponents() {
		return comps;
	}

}
public class Connected_Components {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();
            int e=sc.nextInt();
            
            DisjointSet dsu = new DisjointSet(n);
            for(int i=0;i<e;i++){

                dsu.unionByRank(sc.nextInt(), sc.nextInt());
            }
            
            
            System.out.println(dsu.getNoOfComponents());
        }
    }
}
