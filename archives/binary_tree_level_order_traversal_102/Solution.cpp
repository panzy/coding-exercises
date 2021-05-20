/**
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/submissions/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-20, 12:17:19 p.m.
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
        vector<vector<int>> levelOrder(TreeNode* root) {
            vector<vector<int>> res;

            if (!root) return res;

            vector<TreeNode*> layer{root};

            while (!layer.empty()) {
                vector<int> layerVals;
                layerVals.reserve(layer.size());

                vector<TreeNode*> nextLayer;
                nextLayer.reserve(layer.size() * 2);

                for (auto node : layer) {
                    layerVals.push_back(node->val);
                    if (node->left)
                        nextLayer.push_back(node->left);
                    if (node->right)
                        nextLayer.push_back(node->right);
                }

                res.push_back(move(layerVals));
                layer = move(nextLayer);
            }

            return res;
        }
};
