package validate_binary_search_tree_98;

import util.bintree.TreeFactory;
import util.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {
    static class Info {
        boolean isValid;
        int minVal;
        int maxVal;

        public Info(boolean isValid, int minVal, int maxVal) {
            this.isValid = isValid;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return helper(root).isValid;
    }

    Info helper(TreeNode root) {
        Info leftInfo = null, rightInfo = null;

        if (root.left != null) {
            leftInfo = helper(root.left);
            if (!leftInfo.isValid || leftInfo.maxVal >= root.val)
                return new Info(false, 0, 0);
        }

        if (root.right != null) {
            rightInfo = helper(root.right);
            if (!rightInfo.isValid || rightInfo.minVal <= root.val)
                return new Info(false, 0, 0);
        }

        Info ans = new Info(true,
                leftInfo == null ? root.val : leftInfo.minVal,
                rightInfo == null ? root.val : rightInfo.maxVal);

        return ans;
    }

    @Test
    void testValidBST() {
        Assertions.assertTrue(isValidBST(TreeFactory.fromArray(new Integer[]{2, 1, 3})));
        Assertions.assertFalse(isValidBST(TreeFactory.fromArray(new Integer[]{5, 1, 4, null, null, 3, 6})));
        Assertions.assertFalse(isValidBST(TreeFactory.fromArray(new Integer[]{5, 4, 6, null, null, 3, 7})));
        Assertions.assertFalse(isValidBST(TreeFactory.fromArray(new Integer[]{32, 26, 47, 19, null, null, 56, null, 27})));
    }
}
