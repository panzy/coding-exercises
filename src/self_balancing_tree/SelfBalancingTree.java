package self_balancing_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-04.
 */
public class SelfBalancingTree {
    /* Class node is defined as : */
    static class Node {
        int val;    //Value
        int ht;        //Height
        Node left;    //Left child
        Node right;    //Right child
    }

    static Node insert(Node root,int val)
    {
        Node newNode = new Node();
        newNode.val = val;
        newNode.ht = 0;

        if (root == null) {
            root = newNode;
        } else if (val < root.val) {
            root.left = insert(root.left, val);

            if (root.left.ht - heightOf(root.right) <= 1) {
                updateHeightOf(root);
            } else {
                // Need to re-balance.
                Node n4; // Will be the new root.

                if (heightOf(root.left.right) > heightOf(root.left.left)) {
                    // This is a Left Right case.
                    Node n3 = root.left; // Will be the new root.left.
                    n4 = n3.right;
                    n3.right = n4.left;
                    updateHeightOf(n3);
                    n4.left = n3;
                    updateHeightOf(n4);
                    root.left = n4;
                    updateHeightOf(root);
                } else {
                    n4 = root.left;
                }
                // Now it's a Left Left case.
                root.left = n4.right;
                updateHeightOf(root);
                n4.right = root;
                updateHeightOf(n4);
                root = n4;
            }
        } else {
            root.right = insert(root.right, val);

            if (root.right.ht - heightOf(root.left) <= 1) {
                updateHeightOf(root);
            } else {
                // Need to re-balance.
                Node n4; // Will be the new root.

                // height of root.right.left.
                int rlHt = root.right.left == null ? -1 : root.right.left.ht;
                // height of root.right.right.
                int rrHt = root.right.right == null ? -1 : root.right.right.ht;

                if (rlHt > rrHt) {
                    // This is a Right Left case.
                    Node n5 = root.right; // Will be the new root.right.
                    n4 = n5.left;
                    n5.left = n4.right;
                    updateHeightOf(n5);
                    n4.right = n5;
                    updateHeightOf(n4);
                    root.right = n4;
                    updateHeightOf(root);
                } else {
                    n4 = root.right;
                }
                // Now it's a Right Right case.
                root.right = n4.left;
                updateHeightOf(root);
                n4.left = root;
                updateHeightOf(n4);
                root = n4;
            }

        }
        return root;
    }

    private static void updateHeightOf(Node root) {
        root.ht = Math.max(heightOf(root.left), heightOf(root.right)) + 1;
    }

    private static int heightOf(Node node) {
        return node == null ? -1 : node.ht;
    }

    private static Node makeNode(int val, Node left, Node right) {
        Node n = new Node();
        n.val = val;
        n.ht = Math.max(left == null ? -1 : left.ht, right == null ? -1 : right.ht) + 1;
        n.left = left;
        n.right = right;
        return n;
    }

    @Test
    void test0() {
        Node n5 = makeNode(5, null, null);
        Node n4 = makeNode(4, null, n5);
        Node n2 = makeNode(2, null, null);
        Node n3 = makeNode(3, n2, n4);

        Node root = insert(n3, 6);

        Assertions.assertEquals(3, root.val);
        Assertions.assertEquals(5, root.right.val);
        Assertions.assertEquals(4, root.right.left.val);
        Assertions.assertEquals(6, root.right.right.val);

        Assertions.assertEquals(0, n2.ht);
        Assertions.assertEquals(2, n3.ht);
        Assertions.assertEquals(0, n4.ht);
        Assertions.assertEquals(1, n5.ht);
        Assertions.assertEquals(0, n5.right.ht);
    }
}
