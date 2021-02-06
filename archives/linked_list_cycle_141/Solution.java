package linked_list_cycle_141;

import _lib.singly_linked_list.ListNode;

import java.util.HashSet;

/**
 * Use a set to record all met nodes.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        for (ListNode p = head; p != null; p = p.next) {
            if (visited.contains(p))
                return true;
            visited.add(p);
        }

        return false;
    }
}
