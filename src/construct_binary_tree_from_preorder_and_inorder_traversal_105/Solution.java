package construct_binary_tree_from_preorder_and_inorder_traversal_105;

import _lib.IntArrays;
import _lib.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {

    // The return value of the recursion functions.
    static class Tree {
        final static Tree EMPTY = new Tree(null, 0);

        TreeNode root;
        int size; // number of nodes

        public Tree(TreeNode root, int size) {
            this.root = root;
            this.size = size;
        }

        public Tree(int rootVal, Tree leftChild, Tree rightChild) {
            root = new TreeNode(rootVal);
            root.left = leftChild.root;
            root.right = rightChild.root;
            size = leftChild.size + 1 + rightChild.size;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        assert preorder.length == inorder.length;
        return helper(
                preorder, 0,
                inorder, 0, inorder.length).root;
    }

    public Tree helper(int[] preorder, int preorderBegin,
                           int[] inorder, int inorderBegin, int inorderEnd) {

        // Find the end of the left child in the inorder array.
        int leftEnd = inorderBegin;
        while (leftEnd < inorderEnd && inorder[leftEnd] != preorder[preorderBegin])
            ++leftEnd;

        Tree leftChild = Tree.EMPTY;
        Tree rightChild = Tree.EMPTY;

        if (inorderBegin < leftEnd && leftEnd < inorderEnd) {
            leftChild = helper(
                    preorder, preorderBegin + 1,
                    inorder, inorderBegin, leftEnd);
        }

        if (leftEnd + 1 < inorderEnd) {
             rightChild = helper(
                    preorder, preorderBegin + 1 + leftChild.size,
                    inorder, leftEnd + 1, inorderEnd);
        }

        return new Tree(preorder[preorderBegin], leftChild, rightChild);
    }

    @Test
    void testBuildTree() {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode tree = buildTree(preorder, inorder);

        Assertions.assertArrayEquals(preorder,
                IntArrays.fromList(new binary_tree_preorder_traversal_144.Solution().preorderTraversal(tree)));
        Assertions.assertArrayEquals(inorder,
                IntArrays.fromList(new binary_tree_inorder_traversal_94.Solution().inorderTraversal(tree)));
    }

    @Test
    void testBuildTree2() {
        int[] preorder = new int[]{1, 2, 4, 3, 5};
        int[] inorder = new int[]{2, 4, 1, 3, 5};
        TreeNode tree = buildTree(preorder, inorder);

        Assertions.assertArrayEquals(preorder,
                IntArrays.fromList(new binary_tree_preorder_traversal_144.Solution().preorderTraversal(tree)));
        Assertions.assertArrayEquals(inorder,
                IntArrays.fromList(new binary_tree_inorder_traversal_94.Solution().inorderTraversal(tree)));
    }

    @Test
    void testBuildTree3() {
        int[] preorder = new int[]{1, 2, 3};
        int[] inorder = new int[]{3, 2, 1};
        TreeNode tree = buildTree(preorder, inorder);

        Assertions.assertArrayEquals(preorder,
                IntArrays.fromList(new binary_tree_preorder_traversal_144.Solution().preorderTraversal(tree)));
        Assertions.assertArrayEquals(inorder,
                IntArrays.fromList(new binary_tree_inorder_traversal_94.Solution().inorderTraversal(tree)));
    }
}
