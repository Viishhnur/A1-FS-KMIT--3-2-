#include<iostream>
#include<bits/stdc++.h>
using namespace std;

void printPairs(const int n,int arr[] , const int k){
    unordered_map<int,int> seen;
    set<pair<int,int>> st;
    for(int i = 0 ; i < n ; i++){
        // check if u saw this num as sum somewhere
        if(seen.find(arr[i] - k) != seen.end()){
            st.insert({arr[i],arr[i] - k});
            // cout << "(" << arr[i] << "," << arr[seen[arr[i]]] << ")" << endl;
        }
        if(seen.find(arr[i] + k) != seen.end()){
            st.insert({arr[i] + k , arr[i]});
        }
        seen[arr[i]] = i;
    }
    
    // check if
    for(auto p : st){
        cout << "(" << p.first << "," << p.second << ")" << endl;
    }
    
}
int main(){
    int n;
    cin >> n;
    int arr[n];
    for(int i = 0 ; i < n ; i++){
        cin >> arr[i];
    }
    int k ;
    cin >> k;
    
    printPairs(n,arr,k);
}