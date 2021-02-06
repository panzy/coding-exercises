package linked_list_cycle_141;

import _lib.singly_linked_list.ListFactory;
import _lib.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class LinkedListCycle {
    Solution2 solution = new Solution2();

    @Test
    void example1() {
        ListNode head = ListFactory.fromArray(3, 2, 0, -4);
        head.next.next.next.next = head.next;
        Assertions.assertTrue(solution.hasCycle(head));
    }

    @Test
    void example2() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = head;
        Assertions.assertTrue(solution.hasCycle(head));
    }

    @Test
    void example3() {
        ListNode head = new ListNode(1);
        Assertions.assertFalse(solution.hasCycle(head));
    }

    @Test
    void example4() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        Assertions.assertFalse(solution.hasCycle(head));
    }
}
