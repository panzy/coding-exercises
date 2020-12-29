/**
 * Zhiyong Pan, 2020-12-22.
 *
 * Performance:
 * Elapsed time when processing 5000 nodes: 3189300 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 6330600 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 2747200 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 4012700 nanosecond(s).
 */
package balanced_binary_tree;

import __lib_btree.TreeNode;

import java.util.LinkedList;

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
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        // scan the tree to check heights
        boolean balanced = true;
        LinkedList<TreeNode> layer = new LinkedList<>();
        layer.add(root);
        while (!layer.isEmpty()) {
            TreeNode n = layer.removeFirst();
            int leftHeight = getHeight(n.left);
            int rightHeight = getHeight(n.right);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                balanced = false;
                break;
            }
            if (n.left != null) layer.add(n.left);
            if (n.right != null) layer.add(n.right);
        }

        return balanced;
    }

    static int getHeight(TreeNode n) {
        if (n == null) return 0;
        return Math.max(
                n.left == null ? 1 : getHeight(n.left) + 1,
                n.right == null ? 1 : getHeight(n.right) + 1
        );
    }
}
