package binary_tree_preorder_traversal_144;

import util.bintree.TreeFactory;
import util.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {
    List<Integer> ans;

    public List<Integer> preorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        if (root != null)
            preorder(root);
        return ans;
    }

    private void preorder(TreeNode root) {
        assert root != null;
        ans.add(root.val);
        if (root.left != null)
            preorder(root.left);
        if (root.right != null)
            preorder(root.right);
    }

    @Test
    void testPreorder() {
        Assertions.assertArrayEquals(new Integer[]{}, preorderTraversal(TreeFactory.fromArray(new Integer[]{
        })).toArray(new Integer[0]));

        Assertions.assertArrayEquals(new Integer[]{1}, preorderTraversal(TreeFactory.fromArray(new Integer[]{
                1
        })).toArray(new Integer[1]));

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, preorderTraversal(TreeFactory.fromArray(new Integer[]{
                1, null, 2, 3
        })).toArray(new Integer[3]));
    }
}
