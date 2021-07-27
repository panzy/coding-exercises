//
// 108. Convert Sorted Array to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// 
// 31 / 31 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 21.4 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      26/07/2021, 22:41:29
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/528871861/?from=explore&item_id=3827/
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
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        return go(nums, 0, nums.size());  
    }
    
    // process range [a, b)
    TreeNode* go(const vector<int>& nums, int a, int b) {
        int m = a + (b - a) / 2;
        TreeNode* root = new TreeNode(nums[m]);
        if (m > a)
            root->left = go(nums, a, m);
        if (m + 1 < b)
            root->right = go(nums, m + 1, b);
        return root;
    }
};