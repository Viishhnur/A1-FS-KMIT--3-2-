/*
Given a rod of length n and an array of prices price[] where price[i-1] represents the price of a rod of length i (for 1 <= i <= n), 
determine the optimal way to cut the rod into smaller pieces to maximize the total profit. 
Each piece must have an integer length, and the sum of the lengths of the pieces must equal n. 

The goal is to find the combination of cuts that yields the highest total revenue based on the given price array.

Input Format:
-------------
Line-1:A positive integer n, representing the total length of the rod.
Line-2: A positive integer, number of prices (k, 1 <= k <= 1000):
Line-3: An array price[] of non-negative integers where price[i-1] is the price of a rod of length length[i-1].

Output Format:
--------------
Line-1: An integer, The maximum profit achievable by cutting the rod into smaller pieces of integer lengths.

Constraints:
--------------
1 <= n <= 1000 (rod length).
1 <= length[i] <= n for all i in length[].
0 <= price[i] <= 10^5 for all prices in price[].
The length of length[] and price[] arrays is at most n.

Sample Input-1:
---------------
4
8
1 5 8 9 10 17 17 20

Sample Output-1:
----------------
10

Explanation:
------------
The possible ways to cut a rod of length 4 and their profits are:
No cut (length 4): 9
Cut into 1 + 3: 1 + 8 = 9
Cut into 2 + 2: 5 + 5 = 10
Cut into 3 + 1: 8 + 1 = 9
Cut into 1 + 1 + 2: 1 + 1 + 5 = 7
Cut into 1 + 2 + 1: 1 + 5 + 1 = 7
Cut into 2 + 1 + 1: 5 + 1 + 1 = 7
Cut into 1 + 1 + 1 + 1: 1 + 1 + 1 + 1 = 4

The maximum profit is 10, achieved by cutting the rod into two pieces of length 2 each.

Sample Input-2:
---------------
5
3
2 5 7

Sample Output-2:
----------------
12
 */