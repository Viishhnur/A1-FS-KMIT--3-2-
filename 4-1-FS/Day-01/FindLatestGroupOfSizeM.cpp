//  Leetcode -  1562
#include<iostream>
#include<vector>
using namespace std;

class DisjointSet{
    int n;
    void init(){
        for(int i = 0 ; i < n ; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public:
        vector<int> parent,size;
        DisjointSet(int n){
            this-> n = n;
            this->parent.resize(n);
            this->size.resize(n);
            init();
        }

        int findUParent(int node){
            if(node == parent[node]){
                return node;
            }

            return parent[node] = findUParent(parent[node]);
        }

        void unionBySize(int u,int v){
            int pu = findUParent(u);
            int pv = findUParent(v);

            if(pu == pv) return;

            if(size[pu] <= size[pv]){
                parent[pu] = pv;
                size[pv] += size[pu];
            }else{
                parent[pv] = pu;
                size[pu] += size[pv];
            }
        }
};
class FindLatestGroupOfSizeM {
    bool check(string s,int m){
        int cnt = 0;
        for(int i = 0 ; i < s.size() ; i++){
            if(s[i] == '1'){
                cnt++;
            }else{
                if(cnt == m) return true;
                cnt = 0;
            }
        }

        return cnt == m;
    }
public:
    int findLatestStep(vector<int>& arr, int m) {
        int n = arr.size();

        // Approach-i) Brute force , TLE
        // string s = "";
        // for(int i = 0 ; i < n ; i++){
        //     s += "0";
        // }

        // int latest = -1;
        // for(int i = 0 ; i < n ; i++){
        //     s[arr[i]-1] = '1';
        //     // now check if there exist m sized substring of all one's 
        //     if(check(s,m)){
        //         latest = i+1;
        //     }
        // }

        // return latest;


        // Approach-ii) Use DSU 
        DisjointSet* dsu = new DisjointSet(n);
        vector<bool>visited(n,false);
        int latest = -1 , m_size = 0; // m_size tells the number of m_sized comps
        for(int i = 0 ; i < n ; i++){
            int idx = arr[i] - 1;

            // now check for prev index if it is visited
            if(idx >= 1 && visited[idx-1]){
                // get the ultimate parent of this idx - 1
                int pu = dsu->findUParent(idx-1);

                if(dsu->size[pu] == m){
                    m_size--; // since we are merging this is no more a m-sized component , so decrement bro
                }

                dsu->unionBySize(idx,idx-1);
            }


            // now check for next index if it is visited
            if(idx + 1 < n && visited[idx+1]){
                // get the ultimate parent of this idx - 1
                int pu = dsu->findUParent(idx+1);

                if(dsu->size[pu] == m){
                    m_size--; // since we are merging this is no more a m-sized component , so decrement bro
                }

                dsu->unionBySize(idx,idx+1);
            }

            if(dsu->size[dsu->findUParent(idx)] == m){
                m_size++;
            }

            if(m_size > 0){
                latest = i + 1;
            }


            visited[idx] = true;
        }

        return latest;

    }
};

int main(){
    int n;
    cin >> n;
    
    vector<int> arr(n,0);
    for(int i = 0 ; i < n ; i++) cin >> arr[i];
    
    int m;
    cin >> m;
    
    FindLatestGroupOfSizeM obj;
    cout << obj.findLatestStep(arr,m);
}