/**
 * Zhiyong Pan, 2020-12-22.
 */
package balanced_binary_tree;

import __lib_btree.TreeFactory;
import __lib_btree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SolutionTest {
    Solution3 solution = new Solution3();

    @Test
    void getHeight() {
        TreeNode root = TreeFactory.fromArray(new Integer[]{0, 1, 2, 3, null, null, 6, 7, 8, 13, 14});
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
        Assertions.assertEquals(true, solution.isBalanced(TreeFactory.fromArray(new Integer[0])));
    }

    @Test
    void isBalanced_singleNode() {
        Assertions.assertEquals(true, solution.isBalanced(TreeFactory.fromArray(new Integer[]{0})));
    }

    @Test
    void isBalanced_hasLeftChild() {
        Assertions.assertEquals(true, solution.isBalanced(TreeFactory.fromArray(new Integer[]{0, 1})));
    }

    @Test
    void isBalanced_4layersNotFull() {
        Assertions.assertEquals(true, solution.isBalanced(TreeFactory.fromArray(new Integer[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, null, null, 11, null, 13, 14
        })));
    }

    @Test
    void isBalanced_4layersNotBalanced() {
        Assertions.assertEquals(false, solution.isBalanced(TreeFactory.fromArray(new Integer[]{
                0, 1, 2, 3, null, null, 6, 7, 8, 9, 10, 11, 12, 13, 14
        })));
    }

    @Test
    void isBalanced_example1() {
        Assertions.assertEquals(true, solution.isBalanced(TreeFactory.fromArray(new Integer[]{
                3,9,20,null,null,15,7
        })));
    }

    @Test
    void isBalanced_example2() {
        Assertions.assertEquals(false, solution.isBalanced(TreeFactory.fromArray(new Integer[]{
                1,2,2,3,3,null,null,4,4
        })));
    }

    @Test
    void isBalanced_largeN() {
        Integer[] values = new Integer[5000];
        Arrays.fill(values, 0);
        long start = System.nanoTime();
        Assertions.assertEquals(true, solution.isBalanced(TreeFactory.fromArray(values)));
        System.out.printf("Elapsed time when processing %d nodes: %d nanosecond(s).",
                values.length, System.nanoTime() - start);
    }
}
