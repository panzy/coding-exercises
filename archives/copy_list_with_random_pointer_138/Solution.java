package copy_list_with_random_pointer_138;

import java.util.HashMap;

/**
 * Created by Zhiyong Pan on 2021-02-10.
 */
public class Solution {
    public Node copyRandomList(Node head) {
        // old node --> corresponding new node
        HashMap<Node, Node> copies = new HashMap<>();

        Node newHead = copy(head, copies);
        for (Node p = head, q = newHead; p != null; p = p.next, q = q.next) {
            q.next = copy(p.next, copies);
        }

        // Ensure random pointers.
        for (Node p = head, q = newHead; p != null; p = p.next, q = q.next) {
            if (p.random != null)
                q.random = copies.getOrDefault(p.random, null);
        }

        return newHead;
    }

    private Node copy(Node node, HashMap<Node, Node> copies) {
        if (copies.containsKey(node))
            return copies.get(node);

        Node newNode = new Node(node.val);
        if (node.next != null)
            newNode.next = copies.getOrDefault(node.next, null);
        if (node.random != null)
            newNode.random = copies.getOrDefault(node.random, null);

        copies.put(node, newNode);
        return newNode;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
