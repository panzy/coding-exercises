package two_sum_iv_input_is_a_bst_653.two_sum_ii_input_array_is_sorted_167;

import _lib.bintree.TreeFactory;
import _lib.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Traverse the tree and try to find the paired node for the current one.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    TreeNode root;
    int k;

    public boolean findTarget(TreeNode root, int k) {
        this.root = root;
        this.k = k;
        return findPairedNode(root);
    }

    public boolean findPairedNode(TreeNode node) {
        // Since a node can't be used twice, if k - node.val == node.val,
        // we require that val to present at least twice in the tree.
        if (findNodeByValue(root, k - node.val) > (k - node.val == node.val ? 1 : 0))
            return true;

        if (node.left != null && findPairedNode(node.left))
            return true;

        if (node.right != null && findPairedNode(node.right))
            return true;

        return false;
    }

    /**
     * Find out how many nodes have the given value.
     */
    private static int findNodeByValue(TreeNode root, int val) {
        if (root == null) return 0;
        int n = root.val == val ? 1 : 0;

        if (val <= root.val)
            n += findNodeByValue(root.left, val);

        if (val >= root.val)
            n += findNodeByValue(root.right, val);

        return n;
    }

    @Test
    void example1() {
        Assertions.assertTrue(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{5, 3, 6, 2, 4, null, 7}), 9));
    }

    @Test
    void example2() {
        Assertions.assertFalse(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{5, 3, 6, 2, 4, null, 7}), 28));
    }

    @Test
    void example3() {
        Assertions.assertTrue(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{2, 1, 3}), 4));
    }

    @Test
    void example4() {
        Assertions.assertFalse(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{2, 1, 3}), 1));
    }

    @Test
    void example5() {
        Assertions.assertTrue(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{2, 1, 3}), 5));
    }

    @Test
    void example6() {
        Assertions.assertTrue(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{1, 1}), 2));
        Assertions.assertFalse(
                findTarget(TreeFactory.fromArray(
                        new Integer[]{1, 2}), 2));
    }
}
