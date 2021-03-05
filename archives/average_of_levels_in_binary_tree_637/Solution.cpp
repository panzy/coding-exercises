/* 637. Average of Levels in Binary Tree
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/ 
 * --
 * Zhiyong
 * 2021-03-05
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
    vector<double> averageOfLevels(TreeNode* root) {
        queue<pair<TreeNode*, int>> Q; // Q[i] = {node, level}
        vector<double> res;

        if (root != nullptr) {
            Q.push({root, 0});
        }

        int currLevel = 0;
        double sum = 0;
        int cnt = 0;

        while (Q.size()) {
            auto [node, level] = Q.front();
            Q.pop();

            if (level == currLevel) {
                sum += node->val;
                ++cnt;
            } else {
                res.push_back(sum / cnt);
                ++currLevel;
                cnt = 1;
                sum = node->val;
            }

            if (node->left) {
                Q.push({node->left, level + 1});
            }
            if (node->right) {
                Q.push({node->right, level + 1});
            }
        }

        if (cnt > 0) {
            res.push_back(sum / cnt);
        }

        return res;
    }
};
