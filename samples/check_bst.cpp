/* Is This a Binary Search Tree? */

struct Node {
	int data;
	Node* left;
	Node* right;
};

bool checkBST(Node* root) {
	return checkBST(root, 0, 10000);
}

bool checkBST(Node* root, int lowBound, int upBound) {
	if (root == nullptr) return true;
	if (root->data < lowBound || root->data > upBound) return false;
	if (!checkBST(root->left, lowBound, root->data - 1)) return false;
	if (!checkBST(root->right, root->data + 1, upBound)) return false;
	return true;
}
