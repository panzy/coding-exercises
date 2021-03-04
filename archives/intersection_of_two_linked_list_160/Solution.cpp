/* 
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/ 
 * --
 * Zhiyong
 * 2021-03-04
 */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
	public:
		ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
			ListNode *a = headA, *b = headB;
			bool switchedA = false, switchedB = false;

			while (a != nullptr && b != nullptr) {
				if (a == b) {
					return a;
				}

				a = a->next;
				if (a == nullptr && !switchedA) {
					a = headB;
					switchedA = true;
				}

				b = b->next;
				if (b == nullptr && !switchedB) {
					b = headA;
					switchedB = true;
				}            
			}

			return nullptr;
		}
};
