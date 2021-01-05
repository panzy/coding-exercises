package linked_list_in_binary_tree;

import _lib.btree.TreeFactory;
import _lib.btree.TreeNode;
import _lib.singly_linked_list.ListFactory;
import _lib.singly_linked_list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-05.
 */
public class LinkedListInBinaryTree {
    Solution solution = new Solution();

    @Test
    void example1() {
        ListNode head = ListFactory.fromArray(new int[]{4, 2, 8});
        TreeNode root = TreeFactory.fromList(1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3);
        Assertions.assertTrue(solution.isSubPath(head, root));
    }

    @Test
    void example2() {
        ListNode head = ListFactory.fromArray(new int[]{1, 4, 2, 6});
        TreeNode root = TreeFactory.fromList(1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3);
        Assertions.assertTrue(solution.isSubPath(head, root));
    }

    @Test
    void example3() {
        ListNode head = ListFactory.fromArray(new int[]{1, 4, 2, 6, 8});
        TreeNode root = TreeFactory.fromList(1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3);
        Assertions.assertFalse(solution.isSubPath(head, root));
    }

    @Test
    void example4() {
        ListNode head = ListFactory.fromArray(new int[]{1, 2, 3});
        TreeNode root = TreeFactory.fromList(
                1,
                2, 1,
                8, 4, 2, 2,
                1, null, null, null, 5, null, null, null,
                2, null, null, null,
                7, 3);
        Assertions.assertTrue(solution.isSubPath(head, root));
    }

    @Test
    void example5() {
        ListNode head = ListFactory.fromArray(new int[]{1, 2, 1, 2});
        TreeNode root = TreeFactory.fromList(
                1,
                2, 1,
                8, 4, 2, 2,
                1, null, null, null, 5, null, null, null,
                2, null, null, null,
                7, 3);
        Assertions.assertFalse(solution.isSubPath(head, root));
    }
}
