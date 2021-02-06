package pseduo_palindromic_paths_in_a_binary_tree;

import _lib.bintree.Traversal;
import _lib.bintree.TreeFactory;
import _lib.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Refactor {@link Solution_backtracking} using {@link Traversal#preorder}.
 *
 * --
 * Zhiyong Pan, 2020-12-30
 */
public class Solution_dfs_preorder_api {
    public int pseudoPalindromicPaths (TreeNode root) {
        return Traversal.preorder(root, 0, new Traversal.XDataListener<>() {
            int cnt = 0;

            @Override
            public Integer onNode(TreeNode node, Integer parentXData) {
                if (node.left == null && node.right == null) {
                    int digits = parentXData ^ (1 << node.val);
                    if ((digits & (digits - 1)) == 0)
                        ++cnt;
                }
                return parentXData ^ (1 << node.val);
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