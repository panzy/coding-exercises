/**
 * Zhiyong Pan, 2020-12-22.
 */
package balanced_binary_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SolutionTest {
    Solution solution = new Solution();

    static TreeNode array2tree(Integer[] values) {
        return array2tree(values, 0, 0, 1);
    }

    static TreeNode array2tree(Integer[] values, int nodePosition, int currLayerPosition, int currLayerSize) {
        if (nodePosition >= values.length) return null;
        if (values[nodePosition] == null) return null;
        int leftChildPosition = currLayerPosition + currLayerSize + 2 * (nodePosition - currLayerPosition);
        return new TreeNode(values[nodePosition],
                array2tree(values, leftChildPosition,
                        currLayerPosition + currLayerSize, currLayerSize * 2),
                array2tree(values, leftChildPosition + 1,
                        currLayerPosition + currLayerSize, currLayerSize * 2));
    }

    @Test
    void array2tree_empty() {
        Assertions.assertNull(array2tree(new Integer[0]));
    }

    @Test
    void array2tree_singleNode() {
        TreeNode root = array2tree(new Integer[]{0});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNull(root.left);
        Assertions.assertNull(root.right);
    }

    @Test
    void array2tree_hasLeftChild() {
        TreeNode root = array2tree(new Integer[]{0, 1});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNotNull(root.left);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertNull(root.right);
    }

    @Test
    void array2tree_hasRightChild() {
        TreeNode root = array2tree(new Integer[]{0, null, 2});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNull(root.left);
        Assertions.assertNotNull(root.right);
        Assertions.assertEquals(2, root.right.val);
    }

    @Test
    void array2tree_hasBothChild() {
        TreeNode root = array2tree(new Integer[]{0, 1, 2});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNotNull(root.left);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertNotNull(root.right);
        Assertions.assertEquals(2, root.right.val);
    }

    @Test
    void array2tree_3layersFull() {
        TreeNode root = array2tree(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertEquals(2, root.right.val);
        Assertions.assertEquals(3, root.left.left.val);
        Assertions.assertEquals(4, root.left.right.val);
        Assertions.assertEquals(5, root.right.left.val);
        Assertions.assertEquals(6, root.right.right.val);
    }

    @Test
    void array2tree_4layersFull() {
        TreeNode root = array2tree(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertEquals(2, root.right.val);
        Assertions.assertEquals(3, root.left.left.val);
        Assertions.assertEquals(4, root.left.right.val);
        Assertions.assertEquals(5, root.right.left.val);
        Assertions.assertEquals(6, root.right.right.val);
        Assertions.assertEquals(7, root.left.left.left.val);
        Assertions.assertEquals(8, root.left.left.right.val);
        Assertions.assertEquals(9, root.left.right.left.val);
        Assertions.assertEquals(10, root.left.right.right.val);
        Assertions.assertEquals(11, root.right.left.left.val);
        Assertions.assertEquals(12, root.right.left.right.val);
        Assertions.assertEquals(13, root.right.right.left.val);
        Assertions.assertEquals(14, root.right.right.right.val);
    }

    @Test
    void array2tree_4layersNotFull() {
        TreeNode root = array2tree(new Integer[]{0, 1, 2, null, 4, 5, 6, 7, 8, 9, 10, 11, null, 13, 14});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertEquals(2, root.right.val);
        Assertions.assertEquals(null, root.left.left);
        Assertions.assertEquals(4, root.left.right.val);
        Assertions.assertEquals(5, root.right.left.val);
        Assertions.assertEquals(6, root.right.right.val);
        Assertions.assertEquals(9, root.left.right.left.val);
        Assertions.assertEquals(10, root.left.right.right.val);
        Assertions.assertEquals(11, root.right.left.left.val);
        Assertions.assertEquals(null, root.right.left.right);
        Assertions.assertEquals(13, root.right.right.left.val);
        Assertions.assertEquals(14, root.right.right.right.val);
    }

    @Test
    void array2tree_4layersNotBalanced() {
        TreeNode root = array2tree(new Integer[]{0, 1, 2, 3, null, null, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertEquals(2, root.right.val);
        Assertions.assertEquals(3, root.left.left.val);
        Assertions.assertEquals(null, root.left.right);
        Assertions.assertEquals(null, root.right.left);
        Assertions.assertEquals(6, root.right.right.val);
        Assertions.assertEquals(7, root.left.left.left.val);
        Assertions.assertEquals(8, root.left.left.right.val);
        Assertions.assertEquals(13, root.right.right.left.val);
        Assertions.assertEquals(14, root.right.right.right.val);
    }

    @Test
    void getHeight() {
        TreeNode root = array2tree(new Integer[]{0, 1, 2, 3, null, null, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        Assertions.assertEquals(4, Solution.getHeight(root));
        Assertions.assertEquals(3, Solution.getHeight(root.left));
        Assertions.assertEquals(3, Solution.getHeight(root.right));
        Assertions.assertEquals(2, Solution.getHeight(root.left.left));
        Assertions.assertEquals(2, Solution.getHeight(root.right.right));
        Assertions.assertEquals(1, Solution.getHeight(root.left.left.left));
        Assertions.assertEquals(1, Solution.getHeight(root.left.left.right));
        Assertions.assertEquals(1, Solution.getHeight(root.right.right.left));
        Assertions.assertEquals(1, Solution.getHeight(root.right.right.right));
    }

    @Test
    void isBalanced_empty() {
        Assertions.assertEquals(true, solution.isBalanced(array2tree(new Integer[0])));
    }

    @Test
    void isBalanced_singleNode() {
        Assertions.assertEquals(true, solution.isBalanced(array2tree(new Integer[]{0})));
    }

    @Test
    void isBalanced_hasLeftChild() {
        Assertions.assertEquals(true, solution.isBalanced(array2tree(new Integer[]{0, 1})));
    }

    @Test
    void isBalanced_4layersNotFull() {
        Assertions.assertEquals(true, solution.isBalanced(array2tree(new Integer[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, null, null, 11, null, 13, 14
        })));
    }

    @Test
    void isBalanced_4layersNotBalanced() {
        Assertions.assertEquals(false, solution.isBalanced(array2tree(new Integer[]{
                0, 1, 2, 3, null, null, 6, 7, 8, 9, 10, 11, 12, 13, 14
        })));
    }

    @Test
    void isBalanced_example1() {
        Assertions.assertEquals(true, solution.isBalanced(array2tree(new Integer[]{
                3,9,20,null,null,15,7
        })));
    }

    @Test
    void isBalanced_example2() {
        Assertions.assertEquals(false, solution.isBalanced(array2tree(new Integer[]{
                1,2,2,3,3,null,null,4,4
        })));
    }

    @Test
    void isBalanced_largeN() {
        Integer[] values = new Integer[5000];
        Arrays.fill(values, 0);
        long start = System.nanoTime();
        Assertions.assertEquals(true, solution.isBalanced(array2tree(values)));
        System.out.printf("Elapsed time when processing %d nodes: %d nanosecond(s).",
                values.length, System.nanoTime() - start);
    }
}
