package swap_nodes_in_pairs;

import util.singly_linked_list.ListNode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution s = new Solution();

    @Test
    void swap_noPrev() {
        ListNode D = new ListNode(4);
        ListNode C = new ListNode(3, D);
        ListNode B = new ListNode(2, C);
        ListNode A = new ListNode(1, B);

        Solution.swap(A, null);
        Assertions.assertEquals(A, B.next);
        Assertions.assertEquals(C, A.next);
        Assertions.assertEquals(D, C.next);
    }

    @Test
    void swap_withPrev() {
        ListNode D = new ListNode(4);
        ListNode C = new ListNode(3, D);
        ListNode B = new ListNode(2, C);
        ListNode A = new ListNode(1, B);

        Solution.swap(B, A);
        Assertions.assertEquals(C, A.next);
        Assertions.assertEquals(B, C.next);
        Assertions.assertEquals(D, B.next);
    }

    @Test
    void swapPairs() {
        ListNode D = new ListNode(4);
        ListNode C = new ListNode(3, D);
        ListNode B = new ListNode(2, C);
        ListNode A = new ListNode(1, B);

        ListNode A1 = s.swapPairs(A);
        Assertions.assertEquals(B, A1);
        Assertions.assertEquals(A, B.next);
        Assertions.assertEquals(D, A.next);
        Assertions.assertEquals(C, D.next);
        Assertions.assertEquals(null, C.next);
    }

    @Test
    void swapPairs_empty() {
        ListNode head = s.swapPairs(null);
        Assertions.assertEquals(null, head);
    }

    @Test
    void swapPairs_singleNode() {
        ListNode A = new ListNode(1);

        ListNode A1 = s.swapPairs(A);
        Assertions.assertEquals(A, A1);
        Assertions.assertEquals(null, A.next);
    }

    @Test
    void swapPairs_oddNumbers() {
        ListNode C = new ListNode(3, null);
        ListNode B = new ListNode(2, C);
        ListNode A = new ListNode(1, B);

        ListNode A1 = s.swapPairs(A);
        Assertions.assertEquals(B, A1);
        Assertions.assertEquals(A, B.next);
        Assertions.assertEquals(C, A.next);
        Assertions.assertEquals(null, C.next);
    }
}
