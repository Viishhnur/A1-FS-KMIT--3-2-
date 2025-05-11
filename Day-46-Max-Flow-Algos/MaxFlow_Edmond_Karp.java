/*
Given a graph which represents a flow network where every edge has a capacity. 
Also, given two vertices source 's' and sink 't' in the graph, find the maximum possible flow from s to t with the following constraints:
	- Flow on an edge doesn't exceed the given capacity of the edge.
	- Incoming flow is equal to outgoing flow for every vertex except s and t.

Input Format:
------------------
Line-1: An integer V, number of vertices in graph
Next V lines: V space separated integers, graph[][], 
                       the adjacency matrix of the grpah.
Last Line: Two space separated integers, s and t, source and sink.

input=
Enter number of vertices 
6
Enter the adjacency matrix of the directed graph
0 16 13  0   0   0
0  0  10 12  0   0
0  4   0   0  14  0
0  0   9   0   0  20
0  0   0   7   0  4
0  0   0   0   0  0
Enter source and sink 
0 5
Output =23

Example 2:
input=Enter number of vertices 
6
Enter the adjacency matrix of the directed graph

0 7 4 0 0 0
0 0 0 5 3 0
0 3 0 0 2 0
0 0 0 0 0 8
0 0 0 3 0 5
0 0 0 0 0 0
Enter source and sink
0 5
output=10
*/

/*
The following is simple idea of Ford-Fulkerson algorithm:

1) Start with initial flow as 0.

2) While there exists an augmenting path from the source to the sink:  
	a) Find an augmenting path using any path-finding algorithm, such as 
		breadth-first search or depth-first search.

	b) Determine the amount of flow that can be sent along the augmenting path, 
		which is the minimum residual capacity along the edges of the path.

	c) Increase the flow along the augmenting path by the determined amount.

3) Return the maximum flow.
*/

import java.util.*;
class MaxFlowEK
{
    static int V; // number of vertices in the graph
   
	// method to find the maximum flow in a flow network using the Edmonds-Karp algorithm
    static int findMaxFlow(int[][] graph, int source, int sink) 
    {
        int[][] residualGraph = new int[V][V];
        for (int i = 0; i < V; i++) 
		{
            for (int j = 0; j < V; j++) 
			{
                residualGraph[i][j] = graph[i][j];
            }
        }
		int[] parent = new int[V];
		int maxFlow = 0;     
        while (bfs(residualGraph, source, sink, parent)) 
		{
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) 
			{
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
				System.out.println("u " + u + " v " + v + " pathFlow " + pathFlow);
            }
			System.out.println("Minimum path flow " + pathFlow);

			for (int v = sink; v != source; v = parent[v]) 
			{
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }        
        return maxFlow;
    }

	// helper method to find the shortest augmenting path in the residual graph using BFS
	static boolean bfs(int[][] residualGraph, int source, int sink, int[] parent) 
	{
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;        
        while (!queue.isEmpty()) 
		{
			System.out.print("Queue " + queue);

            int u = queue.poll();
			System.out.println(" u " + u);

            for (int v = 0; v < V; v++) 
			{
                if (!visited[v] && residualGraph[u][v] > 0) 
				{
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
					System.out.println("parent " + u + " child " + v + " residualGraph " + residualGraph[u][v] + " visited[v] " + visited[v]);
                }
            }
        }
        return visited[sink];
    }  
	
    public static void main(String[] args) 
    {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter number of vertices");
		V=s.nextInt();
		int[][] graph = new int[V][V]; 
		System.out.println("Enter the adjacency matrix of the directed graph");
		for(int i=0;i<V;i++)
			for(int j=0;j<V;j++)
				graph[i][j]=s.nextInt();

		System.out.println("Enter source and sink");
		int source = s.nextInt();
		int sink = s.nextInt();
		int maxFlow = findMaxFlow(graph, source, sink);
		System.out.println(maxFlow);
    }
}

/*
test cases
case =1
input=5
0 1 1 0 0
0 0 4 5 6
0 0 0 0 2
0 0 0 0 5
0 0 0 0 0
0 4
output=2
case =2
input=6
0 10 10 0 0 0
0 0 2 4 8 0
0 0 0 0 9 0
0 0 0 0 0 10
0 1 0 6 0 10
0 0 0 0 0 0
0 4
output=17

case =3
input=4
0 20 5 0
10 0 2 7
0 0 0 8
0 0 0 0
0 3
output= 14

case=4
input=6
0 8 10 0 0 0
0 0 0 3 7 0
0 3 0 0 12 0
0 0 0 0 0 10
0 0 0 4 0 8
0 0 0 0 0 0
0 5
output=15

case =5
input=3
0 10 20
0 0 30
0 0 0
0 2
output=30

case =6
input=6
0 11 12 0 0 0
0 0 12 0 0 0
0 1 0 0 11 0
0 0 0 0 0 19
0 0 0 7 0 4
0 0 0 0 0 0
0 5
output=11
*/