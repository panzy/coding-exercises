package binary_tree_postorder_traversal_145;

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

    public List<Integer> postorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        if (root != null)
            postorder(root);
        return ans;
    }

    private void postorder(TreeNode root) {
        assert root != null;
        if (root.left != null)
            postorder(root.left);
        if (root.right != null)
            postorder(root.right);
        ans.add(root.val);
    }

    @Test
    void testPostorder() {
        Assertions.assertArrayEquals(new Integer[]{}, postorderTraversal(TreeFactory.fromArray(new Integer[]{
        })).toArray(new Integer[0]));

        Assertions.assertArrayEquals(new Integer[]{1}, postorderTraversal(TreeFactory.fromArray(new Integer[]{
                1
        })).toArray(new Integer[1]));

        Assertions.assertArrayEquals(new Integer[]{3, 2, 1}, postorderTraversal(TreeFactory.fromArray(new Integer[]{
                1, null, 2, 3
        })).toArray(new Integer[3]));
    }
}
