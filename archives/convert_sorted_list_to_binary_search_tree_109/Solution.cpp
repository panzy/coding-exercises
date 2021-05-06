/* 109. Convert Sorted List to Binary Search Tree
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * --
 * Zhiyong 2021-05-06
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
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if (head == nullptr)
            return nullptr;
        
        // dump list
        //for (ListNode* p = head; p; p = p->next) {
        //    cout << p->val << ' ';
        //}
        //cout << endl;
        
        // The mid node will be the root,
        // the premid node will be the tail of the left half list.
        ListNode* mid = head, *tail = head, *premid = nullptr;
        while (tail != nullptr && tail->next != nullptr) {
            premid = mid;
            mid = mid->next;
            tail = tail->next->next;
        }
        
        if (premid != nullptr) {
            premid->next = nullptr; // cut the list before mid
        }
        return new TreeNode(mid->val,
                            sortedListToBST(head != mid ? head : nullptr),
                            sortedListToBST(mid->next));
    }
};
