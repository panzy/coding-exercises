/* 589. N-ary Tree Preorder Traversal
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/ 
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * --
 * Zhiyong 2021-04-20
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
    vector<int> preorder(Node* root) {
        if (root == nullptr) return {};
        
        vector<int> res;
        
        stack<Node*> st;
        st.push(root);

        while (!st.empty()) {
            Node* n = st.top();
            st.pop();
        
            res.push_back(n->val);
            
            if (n->children.size() > 0) {
                for (int i = n->children.size() - 1; i >= 0; --i)    
                    st.push(n->children[i]);
            }
        }
        
        return res;
    }
};
