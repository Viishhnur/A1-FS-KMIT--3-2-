/*
A graduate at University of China (UoC) has to follow certain rules. The rules are as follows:

UoC offering K subjects, the subjects are indexed from 0 to K-1.
Some subjets have to follow the conditions, like condition[i]= [Xi, Yi] which specifies you must
take the subject Yi before the subject Xi.

You are given total number of subjects and a list of the condition pairs.
Return the ordering of subjects that a graduate should take to finish all subjects.
	- the result set should follow the given order of conditions.
	- If it is impossible to complete all subjects, return an empty set.

Input Format:
-------------
Line-1: An integer K, number of subjects.
Line-2: An integer C, number of conditions.
Next C lines: Two space separated integers, Xi and Yi.

Output Format:
--------------
Return a list of integers, the ordering of subjects that a graduate should take to finish all subjects


Sample Input-1:
---------------
4
3
1 2
3 0
0 1

Sample Output-1:
----------------
[2, 1, 0, 3]

Explanation-1:
--------------
There are a total of 4 courses to take. 
Subject 1  should be taken after you finished subject 2.
Subject 3  should be taken after you finished subject 0.
Subject 0  should be taken after you finished subject 1.
So the correct subject order is [2, 1, 0, 3].

Sample Input-2:
---------------
5
5
0 1
1 2
2 3
3 4
4 0

Sample Output-2:
----------------
[]

Explanation-2:
-------------
No subject can be completed because they depend on each other.
 */
import java.util.*;
import java.io.*;
class Graph{
    private final int V;
    private final List<List<Integer>> adjLst;
    private final int[] indegree;

    private void build(int V,int[][] edges){
        for(int i = 0 ; i < V ; i++){
            adjLst.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            indegree[v]++;
            adjLst.get(u).add(v);
        }
    }
    public Graph(int V,int[][] edges){
        this.V = V;
        this.adjLst = new ArrayList<>();
        this.indegree = new int[V];
        build(V,edges);
        
    }
    
    public int[] getTopoOrdering(){
        int taken = 0;
        Queue<Integer> qu = new LinkedList<>();
        for(int i = 0 ; i < V ; i++){
            if(indegree[i] == 0){
                qu.offer(i);
            }
        }
        
        
        int[] topo = new int[V];
        while(!qu.isEmpty()){
            int node = qu.poll();
            topo[taken++] = node;
            for(int nbr : adjLst.get(node)){
                if(--indegree[nbr] == 0){
                    qu.offer(nbr);
                }
            }
        }
        
        return (taken != V) ? new int[]{} : topo;
    }

}

class CourseSchedule 
{
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //WRITE YOUR CODE HERE and return the order
        Graph g = new Graph(numCourses,prerequisites);
        
        return g.getTopoOrdering();
    }
	
	public static void main(String args[] ) throws IOException 
	{	
		Scanner sc=new Scanner(System.in);	
		int courses,nprerequisites;
		courses=sc.nextInt();
		nprerequisites=sc.nextInt();
		int[][] prerequisites=new int[nprerequisites][2];
		for(int i=0; i<nprerequisites; i++)
		{
			int a=sc.nextInt(),b=sc.nextInt();
			prerequisites[i][0]=b;
			prerequisites[i][1]=a;
		}  	   
		int[] result=new CourseSchedule().findOrder(courses, prerequisites);
		System.out.println(Arrays.toString(result));
	} 
}