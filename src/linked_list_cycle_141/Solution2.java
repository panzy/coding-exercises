package linked_list_cycle_141;

import _lib.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * Use two pointers. Both of them start from the head and go along the list but at different speeds.
 *
 * If there is a cycle then SOONER OR LATER the faster pointer will meet the slower.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution2 {
    public boolean hasCycle(ListNode head) {
        ListNode L = new ListNode(); // sentinel
        L.next = head;

        ListNode p = L;
        ListNode q = L;

        while (q != null && q.next != null) {
            q = q.next.next;
            p = p.next;
            if (p == q)
                return true;
        }

        return false;
    }
}
