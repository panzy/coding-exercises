package data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Stores integers in a trie tree.
 *
 * It stores integers and can find the closest value for a given one.
 *
 * To see how this data structure can be useful, check out this problem:
 * https://www.hackerrank.com/challenges/maximum-xor/problem
 *
 * Created by Zhiyong Pan on 2021-02-06.
 */
public class BinaryTrieTree {
    static class Node {
        Node zero; // if next bit is zero, follow this pointer.
        Node one; // if next bit is one, follow this pointer.

        public Node ensureZeroBranch() {
            if (zero == null)
                zero = new Node();
            return zero;
        }

        public Node ensureOneBranch() {
            if (one == null)
                one = new Node();
            return one;
        }
    }

    // Root's height is zero.
    private int maxDepth;

    // Root node doesn't store a bit.
    private Node root = new Node();

    public BinaryTrieTree(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    // Add numbers.
    public void add(long... nums) {
        for (long num : nums) {
            Node p = root;
            for (long mask = 1L << (maxDepth - 1); mask != 0; mask >>= 1) {
                if ((num & mask) == 0) {
                    p = p.ensureZeroBranch();
                } else {
                    p = p.ensureOneBranch();
                }
            }
        }
    }

    // Find the closest number.
    public long findClosest(long num) {
        long val = 0;
        Node p = root;
        for (long mask = 1L << (maxDepth - 1); mask != 0 && p != null; mask >>= 1) {
            Node next;
            if ((num & mask) == 0) {
                next = p.zero != null ? p.zero : p.one;
            } else {
                next = p.one != null ? p.one : p.zero;
            }
            if (next == p.one && p.one != null)
                val += mask;
            p = next;
        }

        return val;
    }
}

class TestBinaryTireTree {
    @Test
    void storeIntegers() {
        BinaryTrieTree tree = new BinaryTrieTree(8);
        tree.add(3, 7, 10, 15);
        Assertions.assertEquals(15, tree.findClosest(~3));
    }

    @Test
    void storeIntegersJustEnoughDepth() {
        BinaryTrieTree tree = new BinaryTrieTree(4);
        tree.add(3, 7, 10, 15);
        Assertions.assertEquals(15, tree.findClosest(~3));
    }

    @Test
    void storeIntegers32bits() {
        BinaryTrieTree tree = new BinaryTrieTree(32);
        tree.add(3, 7, 10, 15);
        Assertions.assertEquals(15, tree.findClosest(~3));
    }

    @Test
    void storeIntegers2() {
        BinaryTrieTree tree = new BinaryTrieTree(8);
        tree.add(5, 1, 7, 4, 3);
        Assertions.assertEquals(5, tree.findClosest(~2));
        Assertions.assertEquals(7, tree.findClosest(~0));
    }

    @Test
    void findNonExist() {
        BinaryTrieTree tree = new BinaryTrieTree(4);
        Assertions.assertEquals(0, tree.findClosest(12));
        Assertions.assertEquals(7, 2 ^ 5);
    }
}

/**
 * An application of this trie tree implementation.
 *
 * Problem came from: https://www.hackerrank.com/challenges/maximum-xor/problem
 */
class MaximumXor {
    static int[] maxXor(int[] arr, int[] queries) {
        // solve here
        BinaryTrieTree tree = new BinaryTrieTree(32);

        for (int num : arr)
            tree.add(num);

        int[] res = new int[queries.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] =  (queries[i] ^ (int) tree.findClosest(~queries[i]));
        }

        return res;
    }

    @Test
    void test0() {
        Assertions.assertArrayEquals(new int[]{3, 7, 3}, maxXor(new int[]{0, 1, 2}, new int[]{3, 7, 2}));
    }
}