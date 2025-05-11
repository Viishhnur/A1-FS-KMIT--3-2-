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
import java.util.*;
public class ConnectedComponentsBFS {
    private static List<List<Integer>> adjLst;

    private static void buildAdjLst(int n,int[][] edges){
        adjLst = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adjLst.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adjLst.get(u).add(v);
            adjLst.get(v).add(u);
        }
    }

    private static void bfs(int start,boolean[] visited){

        Queue<Integer> qu = new LinkedList<>();

        qu.offer(start);
        visited[start] = true;

        while(!qu.isEmpty()){
            int node = qu.poll();

            for(int nbr : adjLst.get(node)){
                if(!visited[nbr]){
                    qu.offer(nbr);
                    visited[nbr] = true;
                }
            }
        }
    }
    private static int countComponents(int n, int[][] edges) {
        // Write your code here and return an integer

        buildAdjLst(n,edges);
        int cnt = 0;

        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i++){
            if(!visited[i]){
                bfs(i,visited);
                cnt++;
            }
        }

        return cnt;
    }

    // Any supporting functinalities, write here
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int[][] edges = new int[e][2];
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < 2; j++) {
                edges[i][j] = sc.nextInt();
            }
        }
        System.out.println(countComponents(n, edges));

        sc.close();
    }
}

