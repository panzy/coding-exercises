package _lib.singly_linked_list;

import java.util.LinkedList;

/**
 * Created by Zhiyong Pan on 2021-01-05.
 */
public class ListFactory {
    public static ListNode fromArray(int[] arr) {
        ListNode dummyHead = new ListNode(), tail = dummyHead;
        for (int i : arr) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        return dummyHead.next;
    }

    public static int[] toArray(ListNode head) {
        LinkedList<Integer> lst = new LinkedList<>();
        for (ListNode p = head; p != null; p = p.next) {
            lst.add(p.val);
        }
        return lst.stream().mapToInt(i -> i).toArray();
    }
}
