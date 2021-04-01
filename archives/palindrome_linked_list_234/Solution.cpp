/* 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 * --
 * Zhiyong 
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
    bool isPalindrome(ListNode* head) {
        int n = 0;
        for (ListNode* p = head; p != nullptr; p = p->next) {
            ++n;
        }

        if (n == 1) return true;

        // Reverse the first half
        ListNode* p = head, *q = head->next;
        p->next = nullptr;
        for (int i = 0; i < n / 2 - 1; ++i) {
            ListNode* t = q->next;
            q->next = p;
            p = q;
            q = t;
        }

        // if q is exactly in the middle, step right
        if (n % 2 == 1) q = q->next;

        // now we have two linked list, ...<-p, and q->...
        while (p) {
            if (p->val != q->val)
                return false;
            p = p->next;
            q = q->next;
        }

        return true;
    }
};
