package create_sorted_array_through_instructions;

import java.util.LinkedList;

/**
 * This solution should be correct. But it's too slow to accept.
 *
 * The reason of slowness is that it simulates the creating of the array.
 *
 * Will use an abstract data structure to represent the array in next solution.
 *
 * Created by Zhiyong Pan on 2021-01-10.
 */
public class Solution2 {
    static class Node {
        int num, cnt;

        public Node(int num) {
            this.num = num;
            cnt = 1;
        }
    }

    public int createSortedArray(int[] instructions) {
        LinkedList<Node> nodes = new LinkedList<>();
        long cost = 0;

        for (int num : instructions) {

            // the costs of each of the two approach:
            // 1. shift less numbers left, or
            // 2. shift greater numbers right.
            int cost1 = 0, cost2 = 0;

            Node matchedNode = null;

            for (Node i : nodes) {
                if (i.num < num)
                    cost1 += i.cnt;
                else if (i.num > num)
                    cost2 += i.cnt;
                else
                    matchedNode = i;
            }

            cost += Math.min(cost1, cost2);
            if (matchedNode == null)
                nodes.add(new Node(num));
            else
                ++matchedNode.cnt;
        }

        // "Since the answer may be large, return it modulo 109 + 7"
        return (int) (cost % (1_000_000_000 + 7));
    }

    private int countExpanded(int[] counts, int begin, int end) {
        int n = 0;
        for (int i = begin; i < end; ++i) {
            n += counts[i];
        }
        return n;
    }

}