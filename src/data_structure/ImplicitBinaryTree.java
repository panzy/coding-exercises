package data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This program demonstrates how to manipulate an implicit binary tree, that is, a binary tree represented by an array.
 *
 * It:
 *  - performs in-order traversal.
 *  - swap children of some nodes as instructed by given rules.
 *
 *  The tree is presented as a 2-D array, indexes.
 *
 *  indexes[i] = {index of the (i + 1)-th node's left child, index of the i-th node's right child}.
 *               Node index starts from 1. So for the root node, its index is 1, and its two children are
 *               indexes[0].
 *
 * The array doesn't hold node values (something like TreeNode.val), it just defines the shape of the binary tree. Each
 * tree node is denoted by its index, with the root's index being 1.
 *
 * See unit test comments below for examples.
 *
 * Created by Zhiyong Pan on 2021-02-08.
 */
public class ImplicitBinaryTree {
    /**
     * Solved this HackerRank problem:
     *      Swap Nodes [Algo]
     *      https://www.hackerrank.com/challenges/swap-nodes-algo/problem
     * @param indexes presenting the tree.
     * @param queries queries[i] = k, denoting the descent of nodes with a height of multiple of k should be swapped --
     *                left child becomes right, and right becomes left.
     * @return result[i] = in-order traversal after each query is performed.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int[][] res = new int[queries.length][];
        for (int i = 0; i < queries.length; ++i) {
            swapNodes(indexes, 1, 1, queries[i]);
            res[i] = new int[indexes.length];
            inorder(indexes, 1, res[i], 0);
        }
        return res;
    }

    private static void swapNodes(int[][] indexes, int rootIndex, int rootHeight, int k) {
        int[] children = indexes[rootIndex - 1];

        if (children[0] != -1) {
            swapNodes(indexes, children[0], rootHeight + 1, k);
        }

        if (children[1] != -1) {
            swapNodes(indexes, children[1], rootHeight + 1, k);
        }

        if (rootHeight % k == 0) {
            int t = children[0];
            children[0] = children[1];
            children[1] = t;
        }
    }

    private static int inorder(int[][] indexes, int rootIndex, int[] output, int outputProgress) {
        int[] children = indexes[rootIndex - 1];
        if (children[0] != -1) {
            outputProgress = inorder(indexes, children[0], output, outputProgress);
        }

        output[outputProgress++] = rootIndex;

        if (children[1] != -1) {
            outputProgress = inorder(indexes, children[1], output, outputProgress);
        }

        return outputProgress;
    }

    @Test
    void testInorder() {
        /* The int[][] indexes below represent a tree like this:

                 1
                / \
               /   \
              2     3
             /      /
            /      /
           4      5
          /      / \
         /      /   \
        6      7     8
         \          / \
          \        /   \
           9      10   11
         */
        int[][] indexes = {
                {2, 3},
                {4, -1},
                {5, -1},
                {6, -1},
                {7, 8},
                {-1, 9},
                {-1, -1},
                {10, 11},
                {-1, -1},
                {-1, -1},
                {-1, -1}
        };
        int[] output = new int[indexes.length];
        inorder(indexes, 1, output, 0);
        Assertions.assertArrayEquals(new int[]{6, 9, 4, 2, 1, 7, 5, 10, 8, 11, 3}, output);
    }

    @Test
    void testSwapNodesSample0() {
        /* The int[][] indexes below represent a tree like this:
                1   [s]       1    [s]       1
               / \      ->   / \        ->  / \
              2   3 [s]     3   2  [s]     2   3
         */
        int[][] indexes = {
                {2, 3},
                {-1, -1},
                {-1, -1}
        };
        int[] queries = {1, 1};
        int[][] output = swapNodes(indexes, queries);
        Assertions.assertArrayEquals(new int[]{3, 1, 2}, output[0]);
        Assertions.assertArrayEquals(new int[]{2, 1, 3}, output[1]);
    }

    @Test
    void testSwapNodesSample1() {
        /* The int[][] indexes below represent a tree like this:
            1                  1
           / \                / \
          2   3   [s]  ->    2   3
           \   \            /   /
            4   5          4   5
         */
        int[][] indexes = {
                {2, 3},
                {-1, 4},
                {-1, 5},
                {-1, -1},
                {-1, -1}
        };
        int[] queries = {2};
        int[][] output = swapNodes(indexes, queries);
        Assertions.assertArrayEquals(new int[]{4, 2, 1, 5, 3}, output[0]);
    }

    @Test
    void testSwapNodes() {
        /* The int[][] indexes below represent a tree like this:

                 1
                / \
               /   \
              2     3
             /      /
            /      /
           4      5
          /      / \
         /      /   \
        6      7     8
         \          / \
          \        /   \
           9      10   11
         */
        int[][] indexes = {
                {2, 3},
                {4, -1},
                {5, -1},
                {6, -1},
                {7, 8},
                {-1, 9},
                {-1, -1},
                {10, 11},
                {-1, -1},
                {-1, -1},
                {-1, -1}
        };
        int[] queries = {2, 4};
        int[][] output = swapNodes(indexes, queries);
        Assertions.assertArrayEquals(new int[]{2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10}, output[0]);
        Assertions.assertArrayEquals(new int[]{2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11}, output[1]);
    }
}
