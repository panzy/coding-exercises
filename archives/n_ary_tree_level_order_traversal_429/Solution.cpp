/* 
 * 429. N-ary Tree Level Order Traversal
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * --
 * Zhiyong, 2021-02-17
 */


/*
// Definition for a Node.
class Node {
public:
int val;
vector<Node*> children;

Node() {}

Node(int _val) {
val = _val;
}

Node(int _val, vector<Node*> _children) {
val = _val;
children = _children;
}
};
*/

class Solution {
	public:
		vector<vector<int>> levelOrder(Node* root) {
			vector<vector<int>> res;
			vector<Node*> layer;

			if (root != nullptr)
				layer.push_back(root);

			while (!layer.empty()) {

				res.push_back(vector<int>{});
				vector<int>& v = res.back();
				for (Node* n : layer)
					v.push_back(n->val);            

				vector<Node*> layer2;
				for (Node* n : layer)
					layer2.insert(layer2.end(), n->children.begin(), n->children.end());
				layer.clear();
				move(layer2.begin(), layer2.end(), back_inserter(layer));
			}

			return res;
		}
};
