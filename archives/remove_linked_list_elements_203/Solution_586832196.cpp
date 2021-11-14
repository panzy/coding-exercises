//
// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/
// 
// 66 / 66 test cases passed.	Status: Accepted
// Runtime: 29 ms
// Memory Usage: 14.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      13/11/2021, 22:47:46
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/586832196//
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
    ListNode* removeElements(ListNode* head, int val) {
        ListNode dummy(0, head);
        for (auto p = &dummy; p && p->next;) {
            if (p->next->val == val) {
                p->next = p->next->next;
            } else {
                p = p->next;
            }
        }
        return dummy.next;
    }
};