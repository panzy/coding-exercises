package pseduo_palindromic_paths_in_a_binary_tree;

import __lib_btree.TreeFactory;
import __lib_btree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Implement postorder depth-first search with backtracking.
 *
 * Here, backtracking is easier to use than inorder DFS because the stack is also the path.
 * --
 * Zhiyong Pan, 2020-12-29
 */
class Solution_dfs_postorder {
    public int pseudoPalindromicPaths (TreeNode root) {
        int cnt = 0;

        // depth-first traverse the tree

        Deque<TreeNode> path = new ArrayDeque<>();
        // The most recently popped node.
        // Init it to a random so we won't confuse it with an absent child node.
        TreeNode poppedNode = new TreeNode();
        // the frequency of each digit in the current path
        int[] digits = new int[10];
        Arrays.fill(digits, 0);

        // push the root
        path.push(root);
        digits[root.val] = 1;

        TreeNode top = path.peek();
        while (top != null) {
            if (top.left != null && top.left != poppedNode && top.right != poppedNode) {
                // go left
                path.push(top.left);
                ++digits[top.left.val];
                top = top.left;
            } else if (top.right != null && top.right != poppedNode) {
                // go right
                path.push(top.right);
                ++digits[top.right.val];
                top = top.right;
            } else {
                if (top.left == null && top.right == null && hasPalindromicPermutation(digits))
                    ++cnt;
                // go back
                poppedNode = path.pop();
                --digits[top.val];
                top = path.peek();
            }
        }
        return cnt;
    }

    /**
     * Judge whether a set of digits can be arranged in a palindromic way.
     */
    private boolean hasPalindromicPermutation(int[] digitFrequencies) {
        // found a digit with an odd frequency?
        boolean foundOdd = false;
        for (int i : digitFrequencies) {
            if ((i & 1) == 1) {
                if (foundOdd)
                    return false;
                else
                    foundOdd = true;
            }
        }
        return true;
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
        Integer[] treeValues = new Integer[]{
                8,
                8,null,
                7,7,null,null,
                null,null,2,4,null,null,null,null,
                null,null,null,null,null,8,null,7,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,1
        };
        // TODO simplify the treeValues to [8,8,null,7,7,null,null,2,4,null,8,null,7,null,1]
        Assertions.assertEquals(2, pseudoPalindromicPaths(TreeFactory.fromArray(treeValues)));
    }
}