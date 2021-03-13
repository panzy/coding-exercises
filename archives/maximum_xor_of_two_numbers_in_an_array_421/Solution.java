/*
 * 421. Maximum XOR of Two Numbers in an Array
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/ 
 *
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
 * Follow up: Could you do this in O(n) runtime?
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 * Example 2:
 * 
 * Input: nums = [0]
 * Output: 0
 * Example 3:
 * 
 * Input: nums = [2,4]
 * Output: 6
 * Example 4:
 * 
 * Input: nums = [8,10,2]
 * Output: 10
 * Example 5:
 * 
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1*
 *
 * --
 * Zhiyong
 * 2021-03-13
 */

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

class Solution {
    public int findMaximumXOR(int[] nums) {
        BinaryTrieTree tree = new BinaryTrieTree(32);
        
        for (int i : nums) {
            tree.add(i);
        }
        
        int ans = 0;
        for (int i : nums) {
            int j = (int)tree.findClosest(~i);
            ans = Math.max(ans, i ^ j);
        }
        
        return ans;
    }
}
