//
// 201. Bitwise AND of Numbers Range
// https://leetcode.com/problems/bitwise-and-of-numbers-range/
// 
// 8267 / 8267 test cases passed.	Status: Accepted
// Runtime: 12 ms
// Memory Usage: 5.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      29/05/2021, 10:38:39
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/499884180//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int rangeBitwiseAnd(int left, int right) {
        // Until left == right, there is always at least one number between them
        // that its right-most bit is zero.
        
        int cnt = 0; // zero bits from right
        while (left != right) {
            left >>= 1;
            right >>= 1;
            ++cnt;
        }
        return left << cnt;
    }
};