package binary_tree_maximum_path_sum_124;

import _lib.bintree.TreeFactory;
import _lib.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        // Because the path can't be empty, and Node.val can be negative,
        // we can't init the ans to zero.
        int ans = root.val;

        // We have constraint -1000 <= Node.val <= 1000
        // Don't init them to MIN_VALUE, otherwise overflow would happen if we add negative values to them.
        int leftSum = -1000;
        int rightSum = -1000;

        if (root.left != null) {
            // A path reside in the left tree.
            ans = Math.max(ans, maxPathSum(root.left));
            // A path starting from the left child and going down.
            leftSum = maxPathSumStartFrom(root.left);
            // A path starting from root and going left.
            ans = Math.max(ans, leftSum + (root.val > 0 ? root.val : 0));
        }

        if (root.right != null) {
            ans = Math.max(ans, maxPathSum(root.right));
            rightSum = maxPathSumStartFrom(root.right);
            ans = Math.max(ans, rightSum + (root.val > 0 ? root.val : 0));
        }

        // A path pass the root node.
        if (leftSum > 0 && rightSum > 0)
            ans = Math.max(ans, leftSum + root.val + rightSum);

        return ans;
    }

    private int maxPathSumStartFrom(TreeNode root) {
        assert root != null;

        int ans = root.val;

        if (root.left != null)
            ans = Math.max(ans, root.val + maxPathSumStartFrom(root.left));
        if (root.right != null)
            ans = Math.max(ans, root.val + maxPathSumStartFrom(root.right));

        return ans;
    }

    @Test
    void example1() {
        Assertions.assertEquals(6, maxPathSum(TreeFactory.fromArray(new Integer[]{1, 2, 3})));
    }

    @Test
    void example2() {
        Assertions.assertEquals(42, maxPathSum(TreeFactory.fromArray(new Integer[]{-10, 9, 20, null, null, 15, 7})));
    }

    @Test
    void example3() {
        Assertions.assertEquals(54, maxPathSum(TreeFactory.fromArray(new Integer[]{
                10, 9, 20, null, null, 15, 7})));
    }

    @Test
    void example4() {
        Assertions.assertEquals(45, maxPathSum(TreeFactory.fromArray(new Integer[]{
                10, -9, 20, null, null, 15, 7})));
    }

    @Test
    void testCase73() {
        Assertions.assertEquals(-3, maxPathSum(new TreeNode(-3)));
    }

    @Test
    void testCase92() {
        Assertions.assertEquals(16, maxPathSum(TreeFactory.fromArray(new Integer[]{
                9, 6, -3, null, null, -6, 2, null, null, 2, null, -6, -6, -6
        })));
    }
}
