/**
 * 86. Partition List
 * https://leetcode.com/problems/partition-list/
 * --
 * Zhiyong 2021-04-14
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
    ListNode* partition(ListNode* head, int x) {
        if (head == nullptr) return head;

        // dummy head and tail
        ListNode* A0 = new ListNode();
        ListNode* A1 = A0;
        
        // dummy head and tail
        ListNode* B0 = new ListNode();
        ListNode* B1 = B0;
        
        for (ListNode* p = head; p != nullptr; p = p->next) {
            if (p->val < x) {
                A1->next = p;
                A1 = p;
            } else {
                B1->next = p;
                B1 = p;
            }
        }
        
        A1->next = B0->next;
        B1->next = nullptr;
        
        ListNode* res = A0->next;
        delete A0;
        delete B0;
        return res;
    }
};
