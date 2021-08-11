//
// 954. Array of Doubled Pairs
// https://leetcode.com/problems/array-of-doubled-pairs/
// 
// 102 / 102 test cases passed.	Status: Accepted
// Runtime: 108 ms
// Memory Usage: 58 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      11/08/2021, 18:22:21
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/537050730/?from=explore&item_id=3877/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    bool canReorderDoubled(vector<int>& arr) {
        int n = arr.size();
        unordered_map<int, int> freqs; // value => repeat times
        
        for (int v : arr) {
            freqs[v]++;
        }
        
        // order by abs
        sort(begin(arr), end(arr), [](int a, int b) { return abs(a) < abs(b); });
        
        for (int i = 0; i < n; ++i) {
            int v = arr[i];
            if (freqs.find(v) == freqs.end() || freqs[v] == 0) // has been consumed
                continue;
            
            if (v == 0) {
                if (freqs[v] % 2 == 1)
                    return false;
                freqs[v] = 0;
            } else {
                int u = 2 * v;
                if (freqs.find(u) == freqs.end() || freqs[u] < freqs[v])
                    return false;
                freqs[u] -= freqs[v];
                freqs[v] = 0;
            }
        }
        
        return true;
    }
};