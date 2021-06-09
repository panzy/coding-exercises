//
// 105. Construct Binary Tree from Preorder and Inorder Traversal
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// 
// 202 / 202 test cases passed.	Status: Accepted
// Runtime: 28 ms
// Memory Usage: 26.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      08/06/2021, 23:20:44
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/505216067/?from=explore&item_id=3772/
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
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        return go(preorder.cbegin(), preorder.cend(), inorder.cbegin(), inorder.cend());
    }
private:
    TreeNode* go(
        vector<int>::const_iterator preorderBegin,
        vector<int>::const_iterator preorderEnd,
        vector<int>::const_iterator inorderBegin,
        vector<int>::const_iterator inorderEnd) {
        
        if (preorderBegin == preorderEnd)
            return nullptr;
        
        // Sample input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        
        int rootVal = *preorderBegin;
        int leftCnt = find(inorderBegin, inorderEnd, rootVal) - inorderBegin; // 1
        
        return new TreeNode(
            rootVal,
            go(preorderBegin + 1, preorderBegin + 1 + leftCnt, // [9]
               inorderBegin, inorderBegin + leftCnt), // [9]
            go(preorderBegin + 1 + leftCnt, preorderEnd, // [20,15,7]
               inorderBegin + leftCnt + 1, inorderEnd) // [15,20,7]
        );
    }
};