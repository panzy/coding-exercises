/**
 * Zhiyong Pan, 2020-12-22.
 *
 * This version is better than Solution2 because it eliminates the need to calculate a subtree's height from scratch.
 *
 * It archives this by combining these two functions in Solution2,
 *      boolean isBalanced(TreeNode), and
 *      int getHeight(TreeNode),
 * into
 *      boolean isBalanced(TreeNode, AtomicInteger height).
 *
 * The purpose of AtomicInteger class here is to pass height by reference so that isBalanced() can modify it.
 *
 * It is also possible to design the signature of isBalanced() as
 *      int isBalanced(TreeNode)
 * and introduce a global boolean indicating whether at least one unbalanced subtree has been detected. In this case,
 * the return value becomes the subtree height.
 *
 * Or, let isBalanced() return a structure containing a boolean and an int.
 *
 * Another application of this idea can be seen in {@link house_robber_iii.Solution2}.
 *
 * Performance:
 * Elapsed time when processing 5000 nodes: 1913800 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 2872300 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 1500200 nanosecond(s).
 * Elapsed time when processing 5000 nodes: 1586000 nanosecond(s).
 */
package balanced_binary_tree;

import _lib.btree.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

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
public class Solution3 {
    public boolean isBalanced(TreeNode root) {
        return isBalanced(root, new AtomicInteger());
    }

    /**
     * Determine whether a tree is balanced, and output the height of it.
     *
     * The idea of calculating a subtree's height in an accumulative manner --
     * based on its children's heights -- came from this article:
     * https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
     *
     * @param root
     * @param height an output parameter to receive the tree height.
     * @return balanced or not?
     */
    public boolean isBalanced(TreeNode root, AtomicInteger height) {
        if (root == null) {
            height.set(0);
            return true;
        }

        AtomicInteger leftHeight = new AtomicInteger();
        boolean isLeftChildBalanced = isBalanced(root.left, leftHeight);
        if (!isLeftChildBalanced) return false;

        AtomicInteger rightHeight = new AtomicInteger();
        boolean isRightChildBalanced = isBalanced(root.right, rightHeight);
        if (!isRightChildBalanced) return false;

        // note we don't need another recursive call (the getHeight(Node) function in previous solutions)
        // to calculate the children heights.
        if (Math.abs(leftHeight.get() - rightHeight.get()) > 1) return false;

        height.set(1 + Math.max(leftHeight.get(), rightHeight.get()));
        return true;
    }
}
