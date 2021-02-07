/**
 * Straightforward linked list operations, no complex algorithm involved.
 *
 * Zhiyong Pan, 2020-12-24
 */
package swap_nodes_in_pairs;

import util.singly_linked_list.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;

        // this node will be the return value.
        ListNode newHead = head.next;

        ListNode prev = null;
        ListNode A = head;
        while (A != null) {
            swap(A, prev);
            prev = A;
            A = A.next;
        }

        return newHead;
    }

    /**
     * Swap ListNode A and its next.
     * @param A
     * @param prev the node before A.
     */
    static void swap(ListNode A, ListNode prev) {
        if (A.next == null) return;

        ListNode B = A.next;

        if (prev != null)
            prev.next = B;

        A.next = B.next;
        B.next = A;
    }
}
