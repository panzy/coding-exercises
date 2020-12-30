/**
 * Zhiyong Pan, 2020-12-22.
 *
 * Performance:
 * Elapsed time when processing 5000 nodes: 1805700 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 2838600 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 2277100 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 2361500 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 2066600 nanosecond(s).
 */
package balanced_binary_tree;

import _lib.btree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution2 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    static int getHeight(TreeNode n) {
        if (n == null) return 0;
        return Math.max(
                n.left == null ? 1 : getHeight(n.left) + 1,
                n.right == null ? 1 : getHeight(n.right) + 1
        );
    }
}
