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
        if (head == null) return false;
        if (head.next == head) return true;

        for (ListNode f = head.next, s = head; f != null && f.next != null; f = f.next.next, s = s.next) {
            if (f == s) return true;
        }

        return false;
    }
}
