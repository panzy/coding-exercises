package remove_duplicates_from_sorted_list;

import util.singly_linked_list.ListFactory;
import util.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-05.
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head, // the cursor
                // nodes between [p,q) have the same value, i.e.,
                // they are duplicates if there are more than one nodes in this range
                q;

        while (p != null) {
            q = p.next;
            if (q != null && q.val == p.val) {
                do {
                    q = q.next;
                } while (q != null && q.val == p.val);
                p.next = q;
                p = q;
            } else {
                p = p.next;
            }
        }

        return head;
    }

    @Test
    void example1() {
        ListNode head = ListFactory.fromArray(new int[]{1, 2, 3, 3, 4, 4, 5});
        int[] output = ListFactory.toArray(deleteDuplicates(head));
        int[] expected = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example2() {
        ListNode head = ListFactory.fromArray(new int[]{1, 1, 1, 2, 3});
        int[] output = ListFactory.toArray(deleteDuplicates(head));
        int[] expected = {1, 2, 3};
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example3() {
        ListNode head = null;
        int[] output = ListFactory.toArray(deleteDuplicates(head));
        int[] expected = {};
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example4() {
        ListNode head = new ListNode(1);
        int[] output = ListFactory.toArray(deleteDuplicates(head));
        int[] expected = {1};
        Assertions.assertArrayEquals(expected, output);
    }
}
