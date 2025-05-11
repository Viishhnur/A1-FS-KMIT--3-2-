/*
In computer networks we have star topology. 
Which is like an undirected graph consisting of n switces labeled from 1 to n. 
A Start topology is a LAN where there is one center node and exactly n-1 connections 
that connect the ceter switch with every other switch.

You will be given connections where each connections[i]=[ui, vi] 
indicates that there is connection between switches ui and vi. 

Return the center node of the star topology.

Input Format:
-------------
Line-1: An integer n, number of edges
Line-2 to n: Space separated two integers, represents edge u v

Sample Output:
--------------
Line-1: An integer, a center node

Sample Input-1:
---------------
3
1 2
2 3
4 2

Sample Output-1:
----------------
2

Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
Example 2:

Sample Input-2:
---------------
4
1 2
5 1
1 3
1 4


Sample Output-2:
----------------
1
 

Constraints:
------------
-> 3 <= n <= 105
-> edges.length == n - 1
-> edges[i].length == 2
-> 1 <= ui, vi <= n
-> ui != vi
-> The given edges represent a valid star graph.
 */
import java.util.Scanner;
public class CenterNode {

    private static int findCenter(int[][] edges) {
        // Write your code here and return integer, the center node
        // find the degree of each node
        if(edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]){
            return edges[0][0];
        }

        return edges[0][1];

    }

    // private static int findCenter2(int[][] edges) {
    //     // Write your code here and return integer, the center node
    //     int nodes = 0;
    //     for(int[] edge : edges){
    //         nodes = Math.max(edge[0],Math.max(edge[1],nodes));
    //     }
    //     int[] degree = new int[nodes];

    //     // now calc the indegree of each and every node
    //     for(int[] edge : edges){
    //         int u = edge[0];
    //         int v = edge[1];
    //         degree[u]++;
    //         degree[v]++;
    //     }
        
    //     int ans = 0;
    //     for(int i = 1 ; i <= nodes ; i++){
    //         if(degree[i] == 2 * (nodes-1)){
    //             ans = i;
    //         }
    //     }
        
    //     return ans;

    // }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            int e = sc.nextInt();
            int[][] ar = new int[e][2];
            for (int i = 0; i < e; i++) {
                ar[i][0] = sc.nextInt();
                ar[i][1] = sc.nextInt();
            }

            System.out.println(findCenter(ar));
        }
    }
}
