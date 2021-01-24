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
        int[] ans = helper(root);
        return ans[0];
    }

    /**
     * Returns two values:
     * [0] is the primary answer.
     * [1] is an auxiliary answer. It's the sum of a path with the root being one of its end.
     */
    public int[] helper(TreeNode root) {
        assert root != null;

        // The primary answer.
        // Because the path can't be empty, and Node.val can be negative,
        // we can't init the ans to zero.
        int ans = root.val;

        // One of the following will be the auxiliary answer.
        int leftSum = root.val;
        int rightSum = root.val;

        if (root.left != null) {
            int[] left = helper(root.left);
            // Update the answer with the max path reside in the left tree.
            ans = Math.max(ans, left[0]);
            if (left[1] > 0)
                leftSum += left[1];
        }

        if (root.right != null) {
            int[] right = helper(root.right);
            ans = Math.max(ans, right[0]);
            if (right[1] > 0)
                rightSum += right[1];
        }

        // Update the answer with the max path passing the root node.
        ans = Math.max(ans, leftSum + rightSum - root.val);

        return new int[]{ans, Math.max(leftSum, rightSum)};
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
