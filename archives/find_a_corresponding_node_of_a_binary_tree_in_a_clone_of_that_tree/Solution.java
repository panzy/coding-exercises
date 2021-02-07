package find_a_corresponding_node_of_a_binary_tree_in_a_clone_of_that_tree;

import util.bintree.Traversal;
import util.bintree.TreeFactory;
import util.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A trivial binary-tree-traversal problem. I insisted on using my Traversal API :)
 *
 * Created by Zhiyong Pan on 2021-01-02.
 */
public class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return Traversal.bfs(cloned, new Traversal.SimpleListener<>() {
            TreeNode ans = null;

            @Override
            public boolean onNode(TreeNode node) {
                if (node.val == target.val) {
                    ans = node;
                    return true;
                }
                return false;
            }

            @Override
            public TreeNode onDone() {
                return ans;
            }
        });
    }
}

class SolutionTest {
    Solution solution = new Solution();

    @Test
    void example1() {
        Integer[] nodes = new Integer[]{7, 4, 3, null, null, 6, 19};
        TreeNode original = TreeFactory.fromArray(nodes);
        TreeNode cloned = TreeFactory.fromArray(nodes);
        TreeNode target = original.right;
        TreeNode expected = cloned.right;
        Assertions.assertEquals(expected, solution.getTargetCopy(original, cloned, target));
    }

    @Test
    void example2() {
        Integer[] nodes = new Integer[]{7};
        TreeNode original = TreeFactory.fromArray(nodes);
        TreeNode cloned = TreeFactory.fromArray(nodes);
        TreeNode target = original;
        TreeNode expected = cloned;
        Assertions.assertEquals(expected, solution.getTargetCopy(original, cloned, target));
    }

    @Test
    void example3() {
        Integer[] nodes = new Integer[]{8, null, 6, null, 5, null, 4, null, 3, null, 2, null, 1};
        TreeNode original = TreeFactory.fromArray(nodes);
        TreeNode cloned = TreeFactory.fromArray(nodes);
        TreeNode target = original.right.right.right;
        TreeNode expected = cloned.right.right.right;
        Assertions.assertEquals(expected, solution.getTargetCopy(original, cloned, target));
    }

    @Test
    void example4() {
        Integer[] nodes = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode original = TreeFactory.fromArray(nodes);
        TreeNode cloned = TreeFactory.fromArray(nodes);
        TreeNode target = original.left.right;
        TreeNode expected = cloned.left.right;
        Assertions.assertEquals(expected, solution.getTargetCopy(original, cloned, target));
    }

    @Test
    void example5() {
        Integer[] nodes = new Integer[]{1, 2, null, 3};
        TreeNode original = TreeFactory.fromArray(nodes);
        TreeNode cloned = TreeFactory.fromArray(nodes);
        TreeNode target = original.left;
        TreeNode expected = cloned.left;
        Assertions.assertEquals(expected, solution.getTargetCopy(original, cloned, target));
    }

}
