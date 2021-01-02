package house_robber_iii;

import _lib.btree.TreeFactory;
import _lib.btree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Recursion with cache.
 *
 * Created by Zhiyong Pan on 2021-01-02.
 */
public class Solution {
    HashMap<TreeNode, Integer> cache = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;

        if (cache.containsKey(root))
            return cache.get(root);

        // Plan A: rob the root.
        int planA = root.val;

        // Plan B: not rob the root.
        int planB = 0;

        if (root.left != null) {
            planA += rob(root.left.left);
            planA += rob(root.left.right);
            planB += rob(root.left);
        }

        if (root.right != null) {
            planA += rob(root.right.left);
            planA += rob(root.right.right);
            planB += rob(root.right);
        }

        int ans = Math.max(planA, planB);
        cache.put(root, ans);
        return ans;
    }
}

class HouseRobberIII {
    Solution solution = new Solution();

    @Test
    void example1() {
        TreeNode root = TreeFactory.fromList(3, 2, 3, null, 3, null, 1);
        int expected = 7;
        Assertions.assertEquals(expected, solution.rob(root));
    }

    @Test
    void example2() {
        TreeNode root = TreeFactory.fromList(3, 4, 5, 1, 3, null, 1);
        int expected = 9;
        Assertions.assertEquals(expected, solution.rob(root));
    }
}