package pseduo_palindromic_paths_in_a_binary_tree;

import _lib.btree.Traversal;
import _lib.btree.TreeFactory;
import _lib.btree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Refactor {@link Solution_backtracking} using {@link Traversal#backtrack(TreeNode, Traversal.BacktrackingListener)}.
 *
 * --
 * Zhiyong Pan, 2020-12-30
 */
public class Solution_backtracking_api {
    public int pseudoPalindromicPaths (TreeNode root) {
        return Traversal.backtrack(root, new Traversal.BacktrackingListener<>() {
            int cnt = 0;

            // The odd/even flag of the frequency of each digit in the current path.
            // If the n-th bit is 1, then n has occurred an odd times.
            // The bit index starts from the right end of the int.
            int digits = 0;

            @Override
            public void onPathNodeEnter(TreeNode node) {
                digits ^= (1 << node.val);
            }

            @Override
            public void onPathNodeExit(TreeNode node) {
                digits ^= (1 << node.val);
            }

            @Override
            public boolean onNode(TreeNode node) {
                // Explanation of why express (digits & (digits - 1)) == 0 determines whether there is
                // at least one palindromic permutation:
                // (1) If there's at most one bit set to one, then there can be palindromic permutations.
                // (2) If the bits is a power of two, then there's at most one bit set to one.
                // (3) If x & (x - 1) == 0, then x is a power of two.
                // See also https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/solution/
                if (node.left == null && node.right == null) {
                    // Notice that Traversal.backtrack() doesn't put leaves into their paths.
                    digits ^= (1 << node.val);
                    if ((digits & (digits - 1)) == 0)
                        ++cnt;
                    digits ^= (1 << node.val);
                }
                return false;
            }

            @Override
            public Integer onDone() {
                return cnt;
            }
        });
    }

    @Test
    void pseudoPalindromicPaths_example1() {
        Integer[] treeValues = new Integer[]{2, 3, 1, 3, 1, null, 1};
        Assertions.assertEquals(2, pseudoPalindromicPaths(TreeFactory.fromArray(treeValues)));
    }

    @Test
    void pseudoPalindromicPaths_example2() {
        Integer[] treeValues = new Integer[]{2, 1, 1, 1, 3, null, null, null, null, null, 1};
        Assertions.assertEquals(1, pseudoPalindromicPaths(TreeFactory.fromArray(treeValues)));
    }

    @Test
    void pseudoPalindromicPaths_example3() {
        Integer[] treeValues = new Integer[]{9};
        Assertions.assertEquals(1, pseudoPalindromicPaths(TreeFactory.fromArray(treeValues)));
    }

    @Test
    void pseudoPalindromicPaths_case1() {
        Integer[] treeValues = new Integer[]{8,8,null,7,7,null,null,2,4,null,8,null,7,null,1};
        Assertions.assertEquals(2, pseudoPalindromicPaths(TreeFactory.fromArray(treeValues)));
    }
}