package house_robber_iii;

import _lib.bintree.TreeFactory;
import _lib.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-02.
 */
class HouseRobberIII {
    Solution2 solution = new Solution2();

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
