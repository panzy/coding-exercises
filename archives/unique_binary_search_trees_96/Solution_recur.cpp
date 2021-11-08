//
// 96. Unique Binary Search Trees
// https://leetcode.com/problems/unique-binary-search-trees/
// 
// 19 / 19 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 5.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      08/11/2021, 18:15:40
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/584186662//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numTrees(int n) {
        if (n <= 1)
            return 1;
        
        // Note that the numbers don't have to be consecutive.
        // numTrees({1,2,3}) == numTrees({7,8,9})
        if (cache[n] > 0)
            return cache[n];
        
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            // Let i be the root.
            ans += numTrees(i - 1) * numTrees(n - i);
        }
        cache[n] = ans;
        return ans;
    }
private:
    const static int MAX_N = 19;
    int cache[MAX_N + 1] = {};
};