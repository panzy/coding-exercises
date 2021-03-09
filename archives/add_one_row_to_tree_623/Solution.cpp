/* 623. Add One Row to Tree
 * https://leetcode.com/problems/add-one-row-to-tree/
 * --
 * Zhiyong
 * 2021-03-09
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
public:
    TreeNode* addOneRow(TreeNode* root, int v, int d) {        
        if (d == 1) {
            return new TreeNode(v, root, nullptr);
        }
        
        if (d == 2) {
            root->left = new TreeNode(v, root->left, nullptr);
            root->right = new TreeNode(v, nullptr, root->right);
            return root;
        }
        
        if (root->left)
            root->left = addOneRow(root->left, v, d - 1);
        if (root->right)
            root->right = addOneRow(root->right, v, d - 1);
        return root;
    }
};
