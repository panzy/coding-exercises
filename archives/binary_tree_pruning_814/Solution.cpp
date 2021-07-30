//
// 814. Binary Tree Pruning
// https://leetcode.com/problems/binary-tree-pruning/
// 
// 30 / 30 test cases passed.	Status: Accepted
// Runtime: 4 ms
// Memory Usage: 9.4 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      30/07/2021, 18:21:26
// LeetCode submit time: 21 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/530811241//
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
    TreeNode* pruneTree(TreeNode* root) {
        if (!root) return nullptr;
        root->left = pruneTree(root->left);
        root->right = pruneTree(root->right);
        if (!root->left && !root->right && root->val == 0)
            return nullptr;
        else
            return root;
    }
};