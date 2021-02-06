package linked_list_in_binary_tree;

import _lib.bintree.TreeNode;
import _lib.singly_linked_list.ListNode;

/**
 * Created by Zhiyong Pan on 2021-01-05.
 */
public class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (isSubPathFromRoot(head, root))
            return true;
        else
            return (root.left != null && isSubPath(head, root.left))
                    || (root.right != null && isSubPath(head, root.right));

    }

    public boolean isSubPathFromRoot(ListNode head, TreeNode root) {
        if (head == null)
            return true;

        if (root == null)
            return false;

        if (head.val == root.val) {
            if (head.next == null) {
                return true;
            } else {
                return isSubPathFromRoot(head.next, root.left) || isSubPathFromRoot(head.next, root.right);
            }
        } else {
            return false;
        }
    }
}
