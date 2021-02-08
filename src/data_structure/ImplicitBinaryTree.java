package data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This program demonstrates how to manipulate an implicit binary tree, that is, a binary tree represented by an array.
 *
 * It:
 *  - performs in-order traversal.
 *  - swap children of some nodes as instructed by given rules.
 *
 * There are two format of arrays representing a binary tree:
 *
 *  A. a 2-D array, int[][] indexes.
 *
 *      indexes[i] = {index of the (i + 1)-th node's left child, index of the i-th node's right child}.
 *               Node index starts from 1. So for the root node, its index is 1, and its two children are
 *               indexes[0].
 *
 *      The array doesn't hold node values (something like TreeNode.val), it just defines the shape of the binary tree.
 *      Each tree node is denoted by its index, with the root's index being 1. But, of course, node values can be
 *      stored separately in a hash map, as this program does.
 *
 *  B. serialized node values, Integer[] values.
 *
 *      values[i] == null denotes an absent node.
 *
 *      This is how LeetCode describes a tree.
 *
 *      I don't know how to perform recursions on this kind of array, but have long ago build an explicit tree from
 *      this array, see also {@link util.bintree.TreeFactory#fromArray(Integer[])}.
 *
 *      Also, {@link #buildIndexesFromValueArray(Integer[])} was developed to convert this array to the first kind.
 *
 * See unit test comments below for examples of both formats.
 *
 * Created by Zhiyong Pan on 2021-02-08.
 */
public class ImplicitBinaryTree {
    /**
     * In-order traversal.
     * @param indexes
     * @param rootIndex 1 == root
     * @param output should be of the same length as indexes.
     * @param outputProgress start from 0.
     * @return updated outputProgress.
     */
    public static int inorder(int[][] indexes, int rootIndex, int[] output, int outputProgress) {
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

    /**
     * Given a tree presented by its serialized node values, generates the followings:
     *  1. the indexes array which exactly describe the tree's shape, and
     *  2. a hash map mapping node indexes to their values.
     *
     * @param nodeValues
     * @return
     */
    public static Pair<int[][], HashMap<Integer, Integer>> buildIndexesFromValueArray(Integer[] nodeValues) {
        LinkedList<int[]> indexes = new LinkedList<>();
        HashMap<Integer, Integer> values = new HashMap<>();
        int nodeIdx = 1;

        // Put root value
        values.put(1, nodeValues[0]);

        for (int i = 1; i < nodeValues.length; i += 2) {
            int leftChildIdx = -1, rightChildIdx = -1;
            if (nodeValues[i] != null) {
                leftChildIdx = ++nodeIdx;
                values.put(leftChildIdx, nodeValues[i]);
            }
            if (nodeValues[i + 1] != null) {
                rightChildIdx = ++nodeIdx;
                values.put(rightChildIdx, nodeValues[i + 1]);
            }
            indexes.add(new int[]{leftChildIdx, rightChildIdx});
        }
        return new Pair<>(indexes.toArray(new int[indexes.size()][]), values);
    }

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
    void testBuildIndexesFromValueArray() {
        Pair<int[][], HashMap<Integer, Integer>> res = buildIndexesFromValueArray(new Integer[]{
                101, 102, 103, 104, null, 105, null, 106, null, 107, 108, null, 109, null, null, 110, 111,
                null, null, null, null, null, null
        });

        int[][] indexes = res.getKey();
        HashMap<Integer, Integer> values = res.getValue();

        int[] output = new int[indexes.length];
        inorder(indexes, 1, output, 0);

        // check traversal
        Assertions.assertArrayEquals(new int[]{6, 9, 4, 2, 1, 7, 5, 10, 8, 11, 3}, output);

        // check index->value map
        Assertions.assertArrayEquals(new int[]{106, 109, 104, 102, 101, 107, 105, 110, 108, 111, 103},
                Arrays.stream(output).map(nodeIdx -> values.get(nodeIdx)).toArray());
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
