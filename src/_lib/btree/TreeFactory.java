/**
 * Zhiyong Pan, 2020-12-22.
 */
package _lib.btree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeFactory {

    /**
     * Build a binary tree from its breadth-first traverse result.
     * @param values The values of all nodes except those whose parent is absent or
     *               who would be one of the tailing nulls in the array.
     *               A value of null means the node is absent.
     * @return the root.
     */
    public static TreeNode fromArray(Integer[] values) {
        if (values.length == 0) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> layer = new ArrayDeque<>();
        layer.add(root);

        for (int i = 1; i < values.length; i += 2) {
            TreeNode parent = layer.remove();
            if (values[i] != null) {
                parent.left = new TreeNode(values[i]);
                layer.add(parent.left);
            }
            if (i + 1 < values.length && values[i + 1] != null) {
                parent.right = new TreeNode(values[i + 1]);
                layer.add(parent.right);
            }
        }

        return root;
    }

    public static TreeNode fromList(Integer... values) {
        return fromArray(values);
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
        TreeNode root = fromArray(new Integer[]{0, 1, 2, null, 4, 5, 6, 9, 10, 11, null, 13, 14});
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
        TreeNode root = fromArray(new Integer[]{0, 1, 2, 3, null, null, 6, 7, 8, 13, 14});
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

    @Test
    void fromArray_6layersNotBalanced() {
        TreeNode root = fromArray(new Integer[]{8,8,null,7,7,null,null,2,4,null,8,null,7,null,1});
        Assertions.assertNotNull(root);
        Assertions.assertEquals(8, root.val);

        Assertions.assertEquals(8, root.val);

        Assertions.assertEquals(8, root.left.val);
        Assertions.assertNull(root.right);

        Assertions.assertEquals(7, root.left.left.val);
        Assertions.assertEquals(7, root.left.right.val);

        Assertions.assertEquals(2, root.left.right.left.val);
        Assertions.assertEquals(4, root.left.right.right.val);

        Assertions.assertEquals(8, root.left.right.left.right.val);
        Assertions.assertEquals(7, root.left.right.right.right.val);

        Assertions.assertEquals(1, root.left.right.left.right.right.val);
    }

    @Test
    void fromList_example() {
        TreeNode root = fromList(1, null, 3);
        Assertions.assertNotNull(root);
        Assertions.assertEquals(1, root.val);
        Assertions.assertNull(root.left);
        Assertions.assertEquals(3, root.right.val);
    }
}
