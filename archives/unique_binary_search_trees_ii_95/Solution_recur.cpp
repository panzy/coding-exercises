//
// 95. Unique Binary Search Trees II
// https://leetcode.com/problems/unique-binary-search-trees-ii/
// 
// 8 / 8 test cases passed.	Status: Accepted
// Runtime: 16 ms
// Memory Usage: 15.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      08/11/2021, 18:44:16
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/584196205//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<TreeNode*> generateTrees(int n) {
        return generateTrees(n, 1);
    }
    
private:
    // start - the lest node value
    vector<TreeNode*> generateTrees(int n, int start) {
        if (n == 0)
            return {nullptr};
        if (n == 1)
            return {new TreeNode(start)};
        
        vector<TreeNode*> results;
        
        for (int i = 0; i < n; ++i) {
            auto leftCandidates = generateTrees(i, start);
            auto rightCandidates = generateTrees(n - i - 1, start + i + 1);
            for (auto le : leftCandidates) {
                for (auto ri : rightCandidates) {
                    results.push_back(new TreeNode(start + i, le, ri));
                }
            }
        }
        
        return results;
    }
};