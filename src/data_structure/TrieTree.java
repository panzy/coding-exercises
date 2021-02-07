package data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Stores strings in a trie tree and can count strings that share a prefix.
 *
 * To see how this data structure can be useful, check out
 * https://www.hackerrank.com/challenges/contacts/problem
 *
 * Created by Zhiyong Pan on 2021-02-06.
 */
public class TrieTree {
    public static class Node {
        char val;
        int cnt = 0;
        HashMap<Character, Node> children = new HashMap<>();

        Node(char val) {
            this.val = val;
        }

        public Node getOrCreate(char c) {
            Node node = children.get(c);
            if (node == null) {
                node = new Node(c);
                children.put(c, node);
            }
            return node;
        }

        public Node get(char c) {
            return children.get(c);
        }
    }

    private Node root = new Node('/');

    public int count(String chars) {
        Node p = root;
        for (int i = 0, n = chars.length(); i < n && p != null; ++i) {
            char c = chars.charAt(i);
            p = p.get(c);
        }
        return p == null ? 0 : p.cnt;
    }

    /**
     * Return the furthest node the given path leads to.
     */
    public Node search(String path) {
        Node p = root;
        for (int i = 0, n = path.length(); i < n; ++i) {
            char c = path.charAt(i);
            Node next = p.get(c);
            if (next == null)
                break;
            p = next;
        }
        return p;
    }

    public TrieTree add(String chars) {
        Node p = root;
        for (int i = 0, n = chars.length(); i < n; ++i) {
            char c = chars.charAt(i);
            p = p.getOrCreate(c);
            ++p.cnt;
        }
        return this;
    }

    public TrieTree add(int bits) {
        Node p = root;
        for (long mask = (1L << 31); mask != 0; mask >>= 1) {
            char c = (bits & mask) == 0 ? '0' : '1';
            p = p.getOrCreate(c);
            ++p.cnt;
        }
        return this;
    }
}

class TestTireTree {
    @Test
    void storeIntegers() {
        TrieTree tree = new TrieTree();
        tree.add(3).add(7).add(10).add(14).add(15);
        Assertions.assertEquals(3, tree.count("00000000000000000000000000001")); // search by 8 as binary
        Assertions.assertEquals(1, tree.count("0000000000000000000000000000101")); // search by 10 as binary
        Assertions.assertEquals(2, tree.count("0000000000000000000000000000111")); // search by 12 as binary
        Assertions.assertEquals(1, tree.count("00000000000000000000000000001111")); // search by 15 as binary
        Assertions.assertEquals(2, tree.search("00000000000000000000000000001100").cnt); // search by 12 as binary
    }
}