/* 1721. Swapping Nodes in a Linked List
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 *
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node
 * from the beginning and the kth node from the end (the list is 1-indexed).
 *
 * --
 * Zhiyong
 * 2021-03-14
 */


/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* swapNodes(ListNode* head, int k) {
        int n = 0;
        for (ListNode* p = head; p != nullptr; p = p->next)
            ++n;

        if (n % 2 == 1 && n / 2 + 1 == k)
            return head;


        // Ensure k-th is before the k-th from the end
        if (k > n + 1 - k)
            k = n + 1 - k;

        // Locate a and b, whose nexts will be swapped.
        ListNode sentinel(0, head);
        ListNode* p = &sentinel;
        ListNode* a, *b;
        for (int i = 1; i <= n; ++i) {
            if (i == k) {
                a = p;
            }
            if (i == n + 1 - k) {
                b = p;
                break;
            }
            p = p->next;
        }
        //cout << "n=" << n << " a=" << a->val << " b=" << b->val << endl;

        // We're going to swap NODES, not VALUES.
        // Swapping VALUES would be easier.
        // Swap c and d.
        // before: a->c ... b->d->e
        // after:  a->d ... b->c->e
        ListNode* c = a->next, *d = b->next, *e = d->next;
        a->next = d;
        b->next = c;
        d->next = c->next;
        c->next = e;

        return sentinel.next;
    }
};
