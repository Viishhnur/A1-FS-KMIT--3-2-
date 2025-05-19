/*
A financial analytics platform is monitoring short-term stock price changes
to identify patterns of investor sentiment.

Each price movement is recorded as an integer:
A positive number means the price increased (bullish sentiment).
A negative number means the price decreased (bearish sentiment).

Analysts are interested in finding the longest continuous period where stock sentiment alternates
—i.e., gains are followed by losses and vice versa—without interruption.
This helps flag volatile periods for closer examination.

Your task is to identify the longest contiguous subarray of alternating stock price changes.
The pattern must be strictly alternating in sign, and the subarray must occupy consecutive positions
in the original array.


Constraints:
--------------
2 ≤ N ≤ 100,000 (length of the array).
All values are non-zero integers.
At least one alternating pair exists in the array.

Input Format:
-------------
Line-1: An integer N: number of price changes.
Line-2: N space-separated integers representing changes.

Output Format:
---------------
Line-1: List of sub array elements

Sample Input-1:
---------------
9
-5 6 -3 2 -1 4 4 -6 7

Sample Output-1:
-----------------
[-5, 6, -3, 2, -1, 4]


Explanation:
-------------
The subarray [-5, 6, -3, 2, -1, 4] alternates in sign and is the longest such sequence.
The sequence ends when the next element 4 repeats the positive sign.


Sample Input-2:
-------------
5
-5 -4 -3 -2 -1

Sample Output-2:
----------------
[-5]
*/

#include <iostream>
#include <bits/stdc++.h>
using namespace std;
vector<int> ans;
void printVectorAsList(const vector<int>& vec) {
    cout << "[";
    for (size_t i = 0; i < vec.size(); ++i) {
        cout << vec[i];
        if (i != vec.size() - 1) cout << ",";
    }
    cout << "]";
}


int solveRec(int idx, int prev, int arr[], vector<int>& temp, const int n) {
    if (idx == n) {
        if (temp.size() > ans.size()) {
            ans = temp;
        }
        return 0;
    }


    int take = 0;
    if (prev == -1 || (prev == idx - 1 && arr[prev] >= 0 && arr[idx] < 0) || (prev == idx - 1 && arr[prev] < 0 && arr[idx] >= 0)) {
        temp.push_back(arr[idx]);
        take = 1 + solveRec(idx + 1, idx, arr, temp, n);
        temp.pop_back();
    }

    int notTake = solveRec(idx + 1, prev, arr, temp, n);

    return max(take, notTake);
}
int solveMemo(int idx, int prev, int arr[], vector<int>& temp, const int n, vector<vector<int>>& dp) {
    if (idx == n) {
        if (temp.size() > ans.size()) {
            ans = temp;
        }
        return 0;
    }

    if (dp[idx][prev + 1] != -1) {
        return dp[idx][prev + 1];
    }

    int take = 0;
    if (prev == -1 || (prev == idx - 1 && arr[prev] >= 0 && arr[idx] < 0) || (prev == idx - 1 && arr[prev] < 0 && arr[idx] >= 0)) {
        temp.push_back(arr[idx]);
        take = 1 + solveMemo(idx + 1, idx, arr, temp, n, dp);
        temp.pop_back();
    }

    int notTake = solveMemo(idx + 1, prev, arr, temp, n, dp);

    return dp[idx][prev + 1] = max(take, notTake);
}
int main()
{
    int n;
    cin >> n;

    int arr[n];

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    vector<int> temp;
    
    // Approach-i) Recursion
    // int maxLen = solveRec(0,-1, arr, temp, n);
    
    // Approach-ii) Memoization 
    vector<vector<int>>dp(n,vector<int>(n+1,-1));
    int maxLen = solveMemo(0,-1,arr,temp,n,dp);
    
    // Approach-iii) Greedy + LIS variant (think later)
    // temp.push_back(arr[0]);
    // for(int i = 1 ; i < n ; i++){
    //     if((temp.back() >= 0 && arr[i] < 0) || (temp.back() < 0 && arr[i] >= 0)){
    //         temp.push_back(arr[i]);
    //     }else{
    //         if(temp.size() > ans.size()){
                
    //             ans = temp;
    //         }
    //         temp.clear();
    //         temp.push_back(arr[i]);
    //     }
    // }
    
    
    printVectorAsList(ans);
}