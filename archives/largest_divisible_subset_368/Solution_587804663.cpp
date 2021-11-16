//
// 368. Largest Divisible Subset
// https://leetcode.com/problems/largest-divisible-subset/
// 
// 48 / 48 test cases passed.	Status: Accepted
// Runtime: 36 ms
// Memory Usage: 8.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      15/11/2021, 20:17:51
// LeetCode submit time: 2 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/587804663//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// There is a directed graph:
//
// 4 -- 8 -- 32 -- 64
//  \     `---.
//   `- 12 -- 24 -- 48 ---.
// 3 --' |      `---.     |
//        `-- 36 -- 72 -- 144      
//
// We want to find a longest path.
        
// Describe a path.
// We remember its size to compare with others to determine who's longer.
// We remember its previous node to build the answer in the end.
struct Path {
    int size;
    int prev; // index of the greatest number
};

class Solution {
public:
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        int n = nums.size();
        
        // Paths ending at each number.
        // A longer path always nests shorter paths.
        vector<Path> paths(n, {1, -1});
        
        sort(nums.begin(), nums.end());
        
        // build all paths
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[j] % nums[i] == 0 && paths[j].size < paths[i].size + 1) {
                    paths[j].size = paths[i].size + 1;
                    paths[j].prev = i;
                }
            }
        }
        
        // find the longest path
        int si = 0;
        for (int i = 1; i < n; ++i) {
            if (paths[si].size < paths[i].size)
                si = i;
        }
        
        // build the answer
        vector<int> ans(paths[si].size);
        for (int i = si, k = ans.size(); i >= 0; i = paths[i].prev) {
            ans[--k] = nums[i];
        }
        return ans;
    }
};