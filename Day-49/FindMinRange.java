/*
A surveillance system company is deploying scanner nodes in three separate zones of a large restricted facility. 
Each zone has a sorted list of time slots (in minutes) when a scanner captures footage. 
Due to asynchronous setups, these slots vary in both count and value across zones.

To analyze incidents effectively, the system must identify the smallest possible continuous time window (range) that includes at least one scanning time from each zone. 
Your job is to help the company optimize the incident response time by computing this minimum effective window.

Given three sorted arrays (each representing scanning times in minutes for a zone), find the minimum range (i.e., [start, end]) that contains at least one value from each of the three arrays.

Constraints:
------------
-> Each array is already sorted in ascending order.
-> Arrays may be of different lengths.
-> Array elements are positive integers.

Input Format:
--------------
Line-1: Three integers separated by space, length1, length2, length3, the size of each array
Line-2: length1 integers separated by space
Line-3: length2 integers separated by space
Line-4: length3 integers separated by space

Output Format:
--------------
Line-1: The minimum range: [start, end]

Sample Input-1:
---------------
6 3 4
2 3 4 8 10 15
1 5 12
7 8 15 16

Sample Output-1:
----------------
[4, 7]

Sample Input-2:
---------------
6 3 4
3 6 8 10 15
1 5 12
4 8 15 16

Sample Output-2:
----------------
[3, 5]
 */
import java.util.*;

class Pair {
    private final int first;
    private final int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public String toString() {
        return "[" + first + ", " + second + "]";
    }
}

public class FindMinRange {
    public static Pair findMinRange(int[] a, int[] b, int[] c) {
        //WRITE YOUR CODE HERE and return a Pair pbject
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //System.out.println("Enter sizes of the three arrays:");
        int n1 = sc.nextInt(), n2 = sc.nextInt(), n3 = sc.nextInt();

        int[] a = new int[n1];
        int[] b = new int[n2];
        int[] c = new int[n3];

        //System.out.println("Enter elements of array 1 (sorted):");
        for (int i = 0; i < n1; i++) a[i] = sc.nextInt();

        //System.out.println("Enter elements of array 2 (sorted):");
        for (int i = 0; i < n2; i++) b[i] = sc.nextInt();

        //System.out.println("Enter elements of array 3 (sorted):");
        for (int i = 0; i < n3; i++) c[i] = sc.nextInt();

        Pair result = findMinRange(a, b, c);
        System.out.println("The minimum range is " + result);
    }
}

