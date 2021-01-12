package add_two_numbers;

import _lib.singly_linked_list.ListFactory;
import _lib.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-12.
 */
public class AddTwoNumbers {
    Solution solution = new Solution();

    @Test
    void example1() {
        ListNode l1 = ListFactory.fromArray(2, 4, 3);
        ListNode l2 = ListFactory.fromArray(5, 6, 4);
        ListNode ans = solution.addTwoNumbers(l1, l2);
        int[] expected = {7, 0, 8};
        Assertions.assertArrayEquals(expected, ListFactory.toArray(ans));
    }

    @Test
    void example2() {
        ListNode l1 = ListFactory.fromArray(0);
        ListNode l2 = ListFactory.fromArray(0);
        ListNode ans = solution.addTwoNumbers(l1, l2);
        int[] expected = {0};
        Assertions.assertArrayEquals(expected, ListFactory.toArray(ans));
    }

    @Test
    void example3() {
        ListNode l1 = ListFactory.fromArray(9, 9, 9, 9, 9, 9, 9);
        ListNode l2 = ListFactory.fromArray(9, 9, 9, 9);
        ListNode ans = solution.addTwoNumbers(l1, l2);
        int[] expected = {8, 9, 9, 9, 0, 0, 0, 1};
        Assertions.assertArrayEquals(expected, ListFactory.toArray(ans));
    }

    @Test
    void example4() {
        ListNode l1 = ListFactory.fromArray(9, 9, 9, 9);
        ListNode l2 = ListFactory.fromArray(9, 9, 9, 9, 9, 9, 9);
        ListNode ans = solution.addTwoNumbers(l1, l2);
        int[] expected = {8, 9, 9, 9, 0, 0, 0, 1};
        Assertions.assertArrayEquals(expected, ListFactory.toArray(ans));
    }
}
