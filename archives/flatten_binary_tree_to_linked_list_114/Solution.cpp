/**
 * 114. Flatten Binary Tree to Linked List
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/submissions/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-14, 2:05:45 p.m.
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
	// flatten, return the first and last nodes of the list.
	pair<TreeNode*, TreeNode*> go(TreeNode* root) {
		if (root == nullptr)
			return {nullptr, nullptr};

		auto [leftListHead, leftListBack] = go(root->left);
		auto [rightListHead, rightListBack] = go(root->right);

		root->left = nullptr;

		if (leftListHead) {
			root->right = leftListHead;
			leftListBack->right = rightListHead;
		} else {
			root->right = rightListHead;
		}

		auto back = rightListBack;
		if (!back) back = leftListBack;
		if (!back) back = root;

		return {root, back};
	}
	public:
	void flatten(TreeNode* root) {
		go(root);
	}
};
