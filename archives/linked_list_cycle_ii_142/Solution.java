package linked_list_cycle_ii_142;

import util.singly_linked_list.ListNode;

/**
 * Credit goes to https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E5%8F%8C%E6%8C%87%E9%92%88%E6%8A%80%E5%B7%A7.md
 *
 * The principle was explained in detail there.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        if (head.next == head) return head;

        boolean hasCycle = false;
        ListNode f = head, s = head;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                hasCycle = true;
                break;
            };
        }

        if (!hasCycle) return null;

        // Since the faster pointer has moved exactly 2x longer than the slower one,
        // we can prove that the distance from the head to the circle beginning equals
        // the distance from the meeting point to the circle beginning.
        // So, if two pointer start from the head and the meeting point respectively and move along
        // at the same speed, they will meet at the circle beginning.
        s = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }

        return s;
    }
}
