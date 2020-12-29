/**
 * Zhiyong Pan, 2020-12-22.
 */
package __lib_btree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeFactory {

    /**
     * Build a binary tree from its breadth-first traverse result.
     * @param values The values of ALL nodes. A value of null means the node is absent.
     * @return the root.
     */
    public static TreeNode fromArray(Integer[] values) {
        return fromArray(values, 0, 0, 1);
    }

    static TreeNode fromArray(Integer[] values, int nodePosition, int currLayerPosition, int currLayerSize) {
        if (nodePosition >= values.length) return null;
        if (values[nodePosition] == null) return null;
        int leftChildPosition = currLayerPosition + currLayerSize + 2 * (nodePosition - currLayerPosition);
        return new TreeNode(values[nodePosition],
                fromArray(values, leftChildPosition,
                        currLayerPosition + currLayerSize, currLayerSize * 2),
                fromArray(values, leftChildPosition + 1,
                        currLayerPosition + currLayerSize, currLayerSize * 2));
    }

    @Test
    void fromArray_empty() {
        Assertions.assertNull(fromArray(new Integer[0]));
    }

    @Test
    void fromArray_singleNode() {
        TreeNode root = fromArray(new Integer[]{0});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNull(root.left);
        Assertions.assertNull(root.right);
    }

    @Test
    void fromArray_hasLeftChild() {
        TreeNode root = fromArray(new Integer[]{0, 1});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNotNull(root.left);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertNull(root.right);
    }

    @Test
    void fromArray_hasRightChild() {
        TreeNode root = fromArray(new Integer[]{0, null, 2});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNull(root.left);
        Assertions.assertNotNull(root.right);
        Assertions.assertEquals(2, root.right.val);
    }

    @Test
    void fromArray_hasBothChild() {
        TreeNode root = fromArray(new Integer[]{0, 1, 2});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(0, root.val);
        Assertions.assertNotNull(root.left);
        Assertions.assertEquals(1, root.left.val);
        Assertions.assertNotNull(root.right);
        Assertions.assertEquals(2, root.right.val);
    }

    @Test
    void fromArray_3layersFull() {
        TreeNode root = fromArray(new Integer[]{0, 1, 2, 3, 4, 5, 6});
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
    void fromArray_4layersFull() {
        TreeNode root = fromArray(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
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
    void fromArray_4layersNotFull() {
        TreeNode root = fromArray(new Integer[]{0, 1, 2, null, 4, 5, 6, 7, 8, 9, 10, 11, null, 13, 14});
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
    void fromArray_4layersNotBalanced() {
        TreeNode root = fromArray(new Integer[]{0, 1, 2, 3, null, null, 6, 7, 8, 9, 10, 11, 12, 13, 14});
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
}
