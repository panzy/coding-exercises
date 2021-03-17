// Merge two sorted linked lists
// https://www.hackerrank.com/challenges/merge-two-sorted-linked-lists/problem
// --
// Zhiyong
// 2021-03-17
//
// Complete the mergeLists function below.

/*
 * For your reference:
 *
 * SinglyLinkedListNode {
 *     int data;
 *     SinglyLinkedListNode* next;
 * };
 *
 */
SinglyLinkedListNode* mergeLists(SinglyLinkedListNode* head1, SinglyLinkedListNode* head2) {
    SinglyLinkedListNode* head = nullptr, *tail = nullptr;
    
    while (head1 != nullptr || head2 != nullptr) {
        SinglyLinkedListNode* newTail;
        if (head1 != nullptr && (head2 == nullptr || head1->data <= head2->data)) {
            newTail = head1;
            head1 = head1->next;
        } else {
            newTail = head2;
            head2 = head2->next;
        }
        if (head == nullptr) {
            head = tail = newTail;
        } else {
            tail->next = newTail;
            tail = newTail;
        }
    }

    return head;
}
