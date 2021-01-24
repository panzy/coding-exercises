package merge_k_sorted_lists_23;

import _lib.singly_linked_list.ListFactory;
import _lib.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0); // dummy
        ListNode tail = head;

        while (true) {
            // Find the minimal head of the k lists.
            int minPos = -1;
            int exhausted = 0;
            for (int i = 0; i < lists.length; ++i) {
                if (lists[i] == null) {
                    ++exhausted;
                    continue;
                }
                if (minPos == -1 || lists[minPos].val > lists[i].val)
                    minPos = i;
            }

            if (minPos == -1) // done
                break;

            // Append the minimal head to the merged list.
            tail = tail.next = lists[minPos];

            // Consume the minimal head.
            lists[minPos] = lists[minPos].next;

            if (exhausted + 1 == lists.length)
                break;
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
