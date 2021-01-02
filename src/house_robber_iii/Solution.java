package house_robber_iii;

import _lib.btree.TreeNode;

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
            // TODO: optimizable code.
            //      rob(root.left) will call rob(root.left.*) again in its own recursion.
            //      So, normally, a node will be passed to the rob() function more than once.
            //      That's why we have used a cache.
            //      If we can eliminate the redundant callings, then a cache of O(N) memory usage is not necessary.
            //      The optimized code is provided in the next solution.
        }

        if (root.right != null) {
            planA += rob(root.right.left);
            planA += rob(root.right.right);
            planB += rob(root.right);
        }

        int ans = Math.max(planA, planB);
        cache.put(root, ans);
        // TODO: it's a waste of computation to discard one of the plan's result.
        return ans;
    }
}