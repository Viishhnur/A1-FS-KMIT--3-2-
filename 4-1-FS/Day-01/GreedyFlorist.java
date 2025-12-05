/* 
K friends want to buy N photo-frames. 

The shop-owner wants to maximize his number of customers and the profit he makes. 
He decides that, he'll multiply the price of each photo-frame,
by the number of frames purchased by that customer's previously and plus 1. 
The first photo-frame price is, (0+1)* original price, 
the next will be (1+1)* original price and so on.

The original prices of the photo-frames are given as an array C[], 
determine the minimum cost to purchase all N photo-frames.

For example, if there are K=3 friends that want to buy N=4, photo-frames that 
cost C=[1,2,3,4], each will buy one of the photo-frames priced [2,3,4] at 
the original price. Having each purchased 1 photo-frame, the price of 
the photo-frame-1 is c[0] , now it will cost (previous purchases+1)*c[0]. 
The total cost will be 4+3+2+2=11.

Input Format
----------------
Line-1 -> contains two space-separated integers N and K, 
	  the number of photo-frames and the number of friends.
Line-2 ->  contains N space-separated positive integers c[i], 
	  the original price of each photo-frame.

Output Format
------------------
Print the minimum cost to buy all the photo-frames.


Sample Input-1:
---------------
3 3
2 5 6

Sample Output-1:
----------------
13

Explanation:
------------
There are n=3 photo-frames with costs c=[2,5,6] and k=3 people in the group. 
If each person buys one photo-frame, the total cost of prices paid is  2+5+6=13 dollars. 


Sample Input-2:
---------------
3 2
2 5 6

Sample Output-2:
------------------
15

Explanation:
-------------
There are n=3 photo-frames with costs c=[2,5,6] and k=2 people in the group. 
We can minimize the total purchase cost like so:
	- The first person purchases 2 photo-frames in order of decreasing price; 
	  this means they buy the more expensive photo-frame (c1=5) first at price 5 dollars 
	  and the less expensive photo-frame (c0=2) second at price (1+1)*2=4 dollars.
	- The second person buys the most expensive photo-frame at price 6 dollars.
We then print the sum of these purchases, which is 5+4+6=15, as our answer.


Sample Input-3:
---------------
5 3
1 3 5 7 9

Sample Output-3:
----------------
29

Explanation:
----------------
The friends buy photo-frames for 9, 7 and 3, 5 and 1 for a cost of 
9+ 7+3*(1+1) + 5+1*(1+1) = 29.
*/

import java.util.*;
// import java.util
public class GreedyFlorist{
    private int getMinCostToBuyAllFrames(int[] prices,final int n,int k) {
        // first sort the prices
        Arrays.sort(prices);
        
        int minCost = 0;
        int cnt = 0;
        int factor = 1;
        for(int i = n - 1 ; i >=0 ; i--){
            minCost += factor * prices[i];
            cnt++;
            
            if(cnt % k == 0){
                factor++;
            }
        }
        
        return minCost;
    }
    public static void main(String... helloFlorist){
        int N,K;
        int[] prices;
        try(Scanner sc = new Scanner(System.in)){
            N = sc.nextInt();
            K = sc.nextInt();
            
            prices = new int[N];
            for(int i = 0 ; i < N ; i++) prices[i] = sc.nextInt();
        }
        
        System.out.println(new GreedyFlorist().getMinCostToBuyAllFrames(prices,N,K));
    }
}