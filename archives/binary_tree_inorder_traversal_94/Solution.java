package binary_tree_inorder_traversal_94;

import _lib.bintree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {
    List<Integer> ans;

    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        if (root != null)
            inorder(root);
        return ans;
    }

    private void inorder(TreeNode root) {
        assert root != null;
        if (root.left != null)
            inorder(root.left);
        ans.add(root.val);
        if (root.right != null)
            inorder(root.right);
    }
}
