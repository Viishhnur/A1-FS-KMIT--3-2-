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