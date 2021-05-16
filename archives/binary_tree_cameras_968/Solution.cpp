/**
 * 968. Binary Tree Cameras
 * https://leetcode.com/problems/binary-tree-cameras/
 * 
 * Approach: decide the cameras bottom-up.
 *
 * --
 * Created by Zhiyong 
 * 2021-05-16, 1:48:49 p.m.
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
	// return {number of cameras installed on this substree, coverFlag},
	// coverFlag = -1 if the root is not covered, i.e. no camera is installed on itself or its immediate children;
	//           = 0  if the root is covered by cameras of its children;
	//           = 1 if the root is installed a camera.
	pair<int, int> go(TreeNode* root) {
		if (root == nullptr)
			return {0, 0}; // no camera is installed, root doesn't need cover from its parent

		if (!root->left && !root->right) // root is a leaf
			return {0, -1}; // no camera is installed, root remains uncovered (need cover from its parent)

		auto left = go(root->left);
		auto right = go(root->right);

		// If any of the children is uncovered, a camera must be installed on root
		bool cam = left.second == -1 || right.second == -1;

		return {left.first + right.first + (cam ? 1 : 0),
			cam ? 1 : max(left.second, right.second) - 1};
	}
	public:
	int minCameraCover(TreeNode* root) {
		auto [cams, cover] = go(root);
		return cams + (cover >= 0 ? 0 : 1);
	}
};
