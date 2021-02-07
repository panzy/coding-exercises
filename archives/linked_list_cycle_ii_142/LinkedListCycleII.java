package linked_list_cycle_ii_142;

import util.singly_linked_list.ListFactory;
import util.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class LinkedListCycleII {
    Solution solution = new Solution();

    @Test
    void example1() {
        ListNode head = ListFactory.fromArray(3, 2, 0, -4);
        head.next.next.next.next = head.next;
        Assertions.assertEquals(2, solution.detectCycle(head).val);
    }

    @Test
    void example2() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = head;
        Assertions.assertEquals(head, solution.detectCycle(head));
    }

    @Test
    void example3() {
        ListNode head = new ListNode(1);
        Assertions.assertEquals(null, solution.detectCycle(head));
    }

    @Test
    void example4() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        Assertions.assertEquals(null, solution.detectCycle(head));
    }
}
