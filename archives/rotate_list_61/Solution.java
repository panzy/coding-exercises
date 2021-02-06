package rotate_list_61;

import _lib.singly_linked_list.ListFactory;
import _lib.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-24.
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)
            return head;

        // List length.
        int n = 0;
        for (ListNode i = head; i != null; i = i.next)
            ++n;

        // Because there is a circle.
        k %= n;

        if (k == 0)
            return head;

        // Connect the tail to the head, making the list a circle.
        ListNode tail = head;
        while (tail.next != null)
            tail = tail.next;
        tail.next = head;

        // The result list starts from (head + (n - k)).
        ListNode newTail = head;
        for (int i = 0; i + 1 < n - k; ++i)
            newTail = newTail.next;
        ListNode newHead = newTail.next;

        newTail.next = null; // cut here

        return newHead;
    }

    @Test
    void testRotateList() {
        Assertions.assertArrayEquals(new int[]{}, ListFactory.toArray(rotateRight(ListFactory.fromArray(
                new int[]{}), 4)));

        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ListFactory.toArray(rotateRight(ListFactory.fromArray(
                new int[]{1, 2, 3, 4, 5}), 0)));
        Assertions.assertArrayEquals(new int[]{5, 1, 2, 3, 4}, ListFactory.toArray(rotateRight(ListFactory.fromArray(
                new int[]{1, 2, 3, 4, 5}), 1)));
        Assertions.assertArrayEquals(new int[]{4, 5, 1, 2, 3}, ListFactory.toArray(rotateRight(ListFactory.fromArray(
                new int[]{1, 2, 3, 4, 5}), 2)));
        Assertions.assertArrayEquals(new int[]{4, 5, 1, 2, 3}, ListFactory.toArray(rotateRight(ListFactory.fromArray(
                new int[]{1, 2, 3, 4, 5}), 7)));

        Assertions.assertArrayEquals(new int[]{2, 0, 1}, ListFactory.toArray(rotateRight(ListFactory.fromArray(
                new int[]{0, 1, 2}), 4)));
    }
}
