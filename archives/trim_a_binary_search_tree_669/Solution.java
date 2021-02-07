package trim_a_binary_search_tree_669;

import util.bintree.TreeNode;

/**
 * Created by Zhiyong Pan on 2021-02-02.
 */
public class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        } else if (low <= root.val && root.val <= high) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        } else if (root.val < low) {
            return trimBST(root.right, low, high);
        } else {
            return trimBST(root.left, low, high);
        }
    }
}
