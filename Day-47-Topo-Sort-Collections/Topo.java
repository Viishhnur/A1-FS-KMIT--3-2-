/* A Java program to print topological sorting of a graph using indegrees

Sample Input=1:
---------------
6 //no of vertices
6 //no of edges
5 2
5 0
4 0
4 1
2 3
3 1

Sample Output-1:
----------------
4 5 2 0 3 1

Sample Input-2:
---------------
5
5
4 0
4 1
2 3
3 1
2 0

Sample Output-2:
----------------
2 4 3 0 1

*/

import java.util.*;

// Class to represent a graph
class Graph {
	// No. of vertices
	int V;

	// An Array of List which contains references to the Adjacency List of each vertex
	List<Integer> adj[];
	int[] indegree;
	// Constructor
	public Graph(int V)
	{
		this.V = V;
		adj = new ArrayList[V];
    	this.indegree = new int[V];
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<Integer>();
	}

	// Function to add an edge to graph
	public void addEdge(int u, int v)
	{
		adj[u].add(v);
		indegree[v]++;
	}

	public void topologicalSort()
	{
		//Write your code here and print the vertices order after sorting
		Queue<Integer> qu = new LinkedList<>();
		int cnt = 0;
		for(int i = 0 ; i < V ; i++){
		    if(indegree[i] == 0){
		        qu.offer(i);
		        cnt++;
		    }
		}
		
		List<Integer> topo = new ArrayList<>();
		while(!qu.isEmpty()){
		    int node = qu.poll();
		    topo.add(node);
		    
		    for(int nbr : adj[node]){
		        if(--indegree[nbr] == 0){
		            qu.offer(nbr);
		            cnt++;
		        }
		    }
		    
		}
		
		if(cnt == 0){
		    System.out.print(-1);
		    return;
		}
		    
		
		topo.forEach((node)->{
		   System.out.print(node + " ") ;
		});
		
	}
}

class test 
{
	public static void main(String args[])
	{
		// Create a graph 
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Graph g = new Graph(n);
        int e=sc.nextInt();
		for(int i=0;i<e;i++){
			int e1=sc.nextInt();
			int e2=sc.nextInt();
			g.addEdge(e1,e2);
		}

		//System.out.println("Following is a Topological Sort");
		g.topologicalSort();
	}
}


