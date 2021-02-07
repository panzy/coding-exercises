package merge_k_sorted_lists_23;

import util.singly_linked_list.ListFactory;
import util.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * Same as the previous solution, except that a min heap
 * is used to speedup finding the smallest head among the k lists.
 *
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0); // dummy
        ListNode tail = head;

        PriorityQueue<ListNode> heap = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        for (ListNode l : lists)
            if (l != null)
                heap.add(l);

        while (!heap.isEmpty()) {
            ListNode minHead = heap.poll();

            // Append the minimal head to the merged list.
            tail = tail.next = minHead;

            // Put the rest of the selected list back to the heap.
            if (minHead.next != null)
                heap.add(minHead.next);
        }

        return head.next;
    }

    @Test
    void testMergeK() {
        Assertions.assertArrayEquals(
                new int[]{},
                ListFactory.toArray(mergeKLists(new ListNode[]{ }))
        );
        Assertions.assertArrayEquals(
                new int[]{},
                ListFactory.toArray(mergeKLists(new ListNode[]{null}))
        );
        Assertions.assertArrayEquals(
                new int[]{1},
                ListFactory.toArray(mergeKLists(new ListNode[]{new ListNode(1)}))
        );
        Assertions.assertArrayEquals(
                new int[]{1, 2},
                ListFactory.toArray(mergeKLists(new ListNode[]{
                        new ListNode(2),
                        new ListNode(1)}))
        );
        Assertions.assertArrayEquals(
                new int[]{1, 1, 2, 3, 4, 4, 5, 6},
                ListFactory.toArray(mergeKLists(new ListNode[]{
                        ListFactory.fromArray(new int[]{1, 4, 5}),
                        ListFactory.fromArray(new int[]{1, 3, 4}),
                        ListFactory.fromArray(new int[]{2, 6}),
                }))
        );
        Assertions.assertArrayEquals(
                new int[]{1, 1, 2, 3, 4, 4, 5, 6},
                ListFactory.toArray(mergeKLists(new ListNode[]{
                        ListFactory.fromArray(new int[]{1, 3, 4, 4, 5, 6}),
                        ListFactory.fromArray(new int[]{1}),
                        ListFactory.fromArray(new int[]{2}),
                }))
        );
    }
}
