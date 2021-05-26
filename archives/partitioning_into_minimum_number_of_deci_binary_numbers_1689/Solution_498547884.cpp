//
// 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
// https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
// 
// 94 / 94 test cases passed.	Status: Accepted
// Runtime: 36 ms
// Memory Usage: 13.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      26/05/2021, 09:42:16
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/498547884/?from=explore&item_id=3756/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int minPartitions(string n) {
        // Key observation: suppose we're subtracting one
        // deci-binary number from n each time, then we never
        // want borrowing happens because that will generate
        // another big digit.
        // So each digit of n can be solved independently.
        return *max_element(cbegin(n), cend(n)) - '0';
    }
};