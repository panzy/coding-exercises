/* 1302. Deepest Leaves Sum
 * https://leetcode.com/problems/deepest-leaves-sum/
 * --
 * Zhiyong 2021-04-11
 */

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
    // sums[depth] = sum of nodes of that depth
    map<int, int> sums;
    int maxDepth = 0;
    
    void traverse(TreeNode* root, int depth) {
        if (root == nullptr) return;
        if (maxDepth < depth) maxDepth = depth;
        
        sums[depth] += root->val;
        traverse(root->left, depth + 1);
        traverse(root->right, depth + 1);
    }
public:
    int deepestLeavesSum(TreeNode* root) {
        traverse(root, 0);
        return sums[maxDepth];
    }
};
