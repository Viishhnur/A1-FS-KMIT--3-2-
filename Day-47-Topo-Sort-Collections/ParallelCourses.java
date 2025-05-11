
/*
You are given an integer N which denotes the number of courses numbered from 1 to N and a matrix ‘prerequisites’,
in which each row contains exactly two integers ‘A’ and ‘B’ which represents the course ‘A’ 
has to be studied in some semester before studying course ‘B’.

In one semester, you can take at most k courses as long as you have taken all the prerequisites in the 
previous semesters for the courses you are taking.

You are supposed to find the minimum number of semesters required to study all the courses. 
If it is impossible to study all the courses, then return -1.

Input Format:
-------------
The first line represents N, which denotes the number of courses
The second line represents M, which denotes the number of rows of the matrix prerequisites.
The next M lines contain two integers, prerequisites[i][0] and prerequisites[i][1], 
denoting that prerequisites[i][0] has to be studied before prerequisites[i][1].
Last line k, denotes number of courses can be taken in a semester.

Output Format:
---------------
Print the minimum number of semesters required to study all the courses

Sample Input 1
--------------
4
3
2 1
3 1
4 1
2

Sample output 1
---------------
3


Explanation: 
------------
In the first semester, you can take courses 2 and 3.
In the second semester, you can take course 4.
In the third semester, you can take course 1.

Sample input 2
--------------
4
3
1 3
2 3
3 1
3
Sample output 2
----------------
-1

Constraints:
1<= N <= 50
1<=k<=N
1 <= prerequisites[i][0], prerequisites[i][1] <= N
Prerequisites[i][0] != prerequisites[i][1], for any valid i
 */
import java.util.*;
class Graph{
    private final int V;
    private final List<List<Integer>> adjLst;
    private final int[] indegree;

    private void build(int V,int[][] edges){
        for(int i = 0 ; i <= V ; i++){
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
        this.indegree = new int[V+1];
        build(V,edges);
        
    }

    public int minNumberOfSemesters(int k) {

        Queue<Integer> qu = new LinkedList<>();
        // Find the nodes whose indegree is 0
        for(int node = 1 ; node <= V ; node++){
            if(indegree[node] == 0){
                qu.offer(node);
            }
        }


        int semesters = 0;
        int taken = 0;

        while(!qu.isEmpty()){

            int size = qu.size();

            for(int i = 0 ; i < size ; i++){
                int curr_subject = qu.poll();
                taken++;
                for(int dependent_subs : adjLst.get(curr_subject)){
                    if(--indegree[dependent_subs] == 0){
                        qu.offer(dependent_subs);
                    }
                }
            }

            semesters += (size / k + size % k); // indicating the above size number of subjects can be completed in one semester
        }

        if(taken != V){
            return -1;
        }

        return semesters;
    }
}
public class ParallelCourses {
    // public int minNumberOfSemesters(int n, int[][] relations, int k) {
    //     Graph g = new Graph(n, relations);

    //     return g.minNumberOfSemesters(k);
    // }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();

            int e = sc.nextInt();

            int[][] edges = new int[e][2];

            for(int i = 0 ; i < e ; i++){
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            int k = sc.nextInt();
            Graph g = new Graph(n, edges);

            System.out.println(g.minNumberOfSemesters(k));
        }
    }
}

/*
 * import java.util.*;
 * 
 * class ParallelCourses {
 * public int minimumSemesters(int numCourses, int[][] prerequisites, int
 * maxCourses) {
 * // Build graph and in-degree array
 * List<Integer>[] graph = new ArrayList[numCourses + 1];
 * int[] indegree = new int[numCourses + 1];
 * 
 * for (int i = 1; i <= numCourses; i++) {
 * graph[i] = new ArrayList<>();
 * }
 * 
 * for (int[] pre : prerequisites) {
 * int u = pre[0];
 * int v = pre[1];
 * graph[u].add(v);
 * indegree[v]++;
 * }
 * 
 * // Queue for BFS
 * Queue<Integer> queue = new LinkedList<>();
 * 
 * // Initially add all courses with indegree 0 (no prerequisites)
 * for (int i = 1; i <= numCourses; i++) {
 * if (indegree[i] == 0) {
 * queue.offer(i);
 * }
 * }
 * int semesters = 0;
 * int coursesTaken = 0;
 * while (!queue.isEmpty()) {
 * int size = Math.min(queue.size(), maxCourses);
 * List<Integer> temp = new ArrayList<>();
 * for (int i = 0; i < size; i++) {
 * int course = queue.poll();
 * coursesTaken++;
 * for (int neighbor : graph[course]) {
 * indegree[neighbor]--;
 * if (indegree[neighbor] == 0) {
 * temp.add(neighbor);
 * }
 * }
 * }
 * 
 * // Add next set of zero-indegree courses
 * queue.addAll(temp);
 * semesters++;
 * }
 * 
 * // If not all courses taken, there is a cycle
 * return coursesTaken == numCourses ? semesters : -1;
 * }
 * 
 * public static void main(String[] args) {
 * Scanner s = new Scanner(System.in);
 * int numCourses = s.nextInt();
 * int c = s.nextInt();
 * int[][] prerequisites = new int[c][2];
 * for (int i = 0; i < c; i++) {
 * prerequisites[i][0] = s.nextInt();
 * prerequisites[i][1] = s.nextInt();
 * }
 * int maxCourses = s.nextInt();
 * ParallelCourses p = new ParallelCourses();
 * 
 * System.out.println(p.minimumSemesters(numCourses, prerequisites,
 * maxCourses));
 * }
 * }
 */