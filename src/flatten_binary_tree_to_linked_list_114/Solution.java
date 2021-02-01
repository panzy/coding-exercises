package flatten_binary_tree_to_linked_list_114;

import _lib.bintree.TreeFactory;
import _lib.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-01.
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root != null)
            helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root.left == null) {
            if (root.right == null) {
                return root;
            } else {
                return helper(root.right);
            }
        } else {
            TreeNode rightBak = root.right;
            root.right = root.left;
            root.left = null;
            TreeNode tail = helper(root.right);

            if (rightBak != null) {
                tail.right = rightBak;
                tail = helper(rightBak);
            }
            return tail;
        }
    }

    @Test
    void example1() {
        TreeNode root = TreeFactory.fromArray(new Integer[]{1, 2, 5, 3, 4, null, 6});
        flatten(root);
        Assertions.assertEquals(1, root.val);
        Assertions.assertEquals(2, root.right.val);
        Assertions.assertEquals(3, root.right.right.val);
        Assertions.assertEquals(4, root.right.right.right.val);
        Assertions.assertEquals(5, root.right.right.right.right.val);
        Assertions.assertEquals(6, root.right.right.right.right.right.val);
        Assertions.assertEquals(null, root.left);
        Assertions.assertEquals(null, root.right.left);
        Assertions.assertEquals(null, root.right.right.left);
        Assertions.assertEquals(null, root.right.right.right.left);
        Assertions.assertEquals(null, root.right.right.right.right.left);
        Assertions.assertEquals(null, root.right.right.right.right.right.left);
    }

    @Test
    void example2() {
        TreeNode root = TreeFactory.fromArray(new Integer[]{});
        flatten(root);
        Assertions.assertEquals(null, root);
    }

    @Test
    void example3() {
        TreeNode root = TreeFactory.fromArray(new Integer[]{0});
        flatten(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertEquals(null, root.left);
        Assertions.assertEquals(null, root.right);
    }
}
