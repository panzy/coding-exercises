package add_two_numbers;

import _lib.singly_linked_list.ListNode;

/**
 * Created by Zhiyong Pan on 2021-01-12.
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode i = new ListNode(0, l1); // i.next is the curr digit; i itself is never null.
        ListNode j = new ListNode(0, l2); // j.next is the curr digit; j itself is never null.
        int carry = 0; // 0 or 1

        // Add each digit from l2 to l1.
        while (i.next != null) {
            i.next.val += (j.next != null ? j.next.val : 0) + carry;
            if (i.next.val >= 10) {
                i.next.val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            i = i.next;
            if (j.next != null)
                j = j.next;

            // If l2 is longer, move its remain to the end of l1.
            if (i.next == null) {
                i.next = j.next;
                j.next = null; // Move, not copy.
            }
        }

        if (carry > 0)
            i.next = new ListNode(1, null);

        return l1;
    }
}