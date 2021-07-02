//
// 143. Reorder List
// https://leetcode.com/problems/reorder-list/
// 
// 12 / 12 test cases passed.	Status: Accepted
// Runtime: 32 ms
// Memory Usage: 18.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      02/07/2021, 00:34:50
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/516146863//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
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
    void reorderList(ListNode* head) {
        stack<ListNode*> nodes; // for accessing the right half of the nodes backwards
        
        for (auto p = head; p != nullptr; p = p->next)
            nodes.push(p);
        
        int n = nodes.size(), moves = n / 2;
        auto p = head;
        
        for (int i = 0; i < moves; ++i) {
            // move stack top to after p
            auto q = nodes.top();
            nodes.pop();
            // it's possible that q is already p->next, but that doesn't matter.
            q->next = p->next;
            p->next = q;
            p = q->next;
        }
        
        p->next = nullptr;
    }
};