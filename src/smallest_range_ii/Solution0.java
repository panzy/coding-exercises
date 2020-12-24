/**
 * Zhiyong Pan, 2020-12-21.
 *
 * This solution is terribly slow -- O(2^n).
 *
 * The maximum N it can handle on my laptop is 1000.
 *
 * I tried to implement an O(n*logN) algorithm -- sort and do a linear scan,
 * but back then I lacked insight into this question.
 */
package smallest_range_ii;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Solution0 {
    public int smallestRangeII(int[] A, int K) {
        return bruteForce(A, K);
    }

    private static class Node {
        int index;
        int value; // this attribute is only for backtracking the final array B.
        int min;
        int max;
        Node prev;

        Node(int index_, int value_, int min_, int max_, Node prev_) {
            index = index_;
            value = value_;
            min = min_;
            max = max_;
            prev = prev_;
        }
    }

    private static Node createNode(Node prev, int value) {
        if (prev == null) {
            return new Node(0, value, value, value, null);
        } else {
            return new Node(
                    prev.index + 1,
                    value,
                    Math.min(prev.min, value),
                    Math.max(prev.max, value),
                    prev);
        }
    }

    public static int bruteForce(int[] A, int K) {
        // find range threshold
        int threshold = 0;
        {
            int min = A[0];
            int max = A[0];
            for (int i = 1; i < A.length; ++i) {
                if (min > A[i]) min = A[i];
                if (max < A[i]) max = A[i];
            }
            threshold = max - min;
        }

        LinkedList<Node> layer = new LinkedList<>();

        // for each layer of the tree
        for (int i = 0; i < A.length; ++i) {
            HashSet<Integer> set = new HashSet<>();

            if (layer.isEmpty()) {
                layer.add(createNode(null, A[0] - K));
                layer.add(createNode(null, A[0] + K));
            } else {
                while (layer.getFirst().index < i) {
                    Node head = layer.removeFirst();

                    Node left = createNode(head, A[i] - K);
                    int key = left.max * 10000 + left.min;
                    if (left.max - left.min < threshold && !set.contains(key)) {
                        layer.add(left);
                        set.add(key);
                    }

                    Node right = createNode(head, A[i] + K);
                    key = right.max * 10000 + right.min;
                    if (right.max - right.min < threshold && !set.contains(key)) {
                        layer.add(right);
                        set.add(key);
                    }

                    if (layer.isEmpty()) break;
                }
            }
        }

        // for each leaf node of the tree
        Node theLeaf = null;
        for (Node leaf : layer) {
            if (theLeaf == null || leaf.max - leaf.min < theLeaf.max - theLeaf.min)
                theLeaf = leaf;
        }

        // dump
        if (true) {
            System.out.println("A = [" + Arrays.stream(A).mapToObj(Integer::toString).collect(Collectors.joining(",")) + "]");
            System.out.println("K = " + K);
            System.out.println("N = " + A.length);
            if (theLeaf != null) {
                int[] B = new int[A.length];
                Node l = theLeaf;
                for (int i = A.length - 1; i >= 0; --i) {
                    B[i] = l.value;
                    l = l.prev;
                }
                System.out.println("B = [" + Arrays.stream(B).mapToObj(Integer::toString).collect(Collectors.joining(",")) + "]");
                System.out.println("Smallest range = " + (theLeaf.max - theLeaf.min));
                System.out.println("Threshold = " + threshold);
                System.out.printf("Number of leaf nodes = %d (%d%%)",
                        layer.size(),
                        Math.round(layer.size() * 100 / Math.pow(2, A.length)));
            }
        }

        return theLeaf != null ? theLeaf.max - theLeaf.min : threshold;
    }
}
