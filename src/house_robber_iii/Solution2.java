package house_robber_iii;

import _lib.btree.TreeNode;

/**
 * Improvement on the previous solution.
 *
 * In the previous solution, each recursion computes two results, for plan A and B respectively, but it returns only
 * one of them and discard the other. It's a waste of computation to discard a result because it is needed when some
 * other "robbing" plans are evaluated.
 *
 * In this solution, the core idea is to let the recursion function return all the results of its hard work. Instead
 * of returning the better plan, it returns both plans. It's similar to the optimization in
 * {@link balanced_binary_tree.Solution3}, where we let the recursion function returns whether it's balanced AS WELL AS
 * its height.
 *
 * Created by Zhiyong Pan on 2021-01-02.
 */
public class Solution2 {
    public int rob(TreeNode root) {
        int[] plans = rob_(root);
        return Math.max(plans[0], plans[1]);
    }

    public int[] rob_(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] leftChildPlans = rob_(root.left),
                rightChildPlans = rob_(root.right);

        // Plan A: rob the root.
        int planA = root.val + leftChildPlans[1] + rightChildPlans[1];

        // Plan B: not rob the root.
        int planB = Math.max(leftChildPlans[0], leftChildPlans[1])
                + Math.max(rightChildPlans[0], rightChildPlans[1]);

        return new int[]{planA, planB};
    }
}

