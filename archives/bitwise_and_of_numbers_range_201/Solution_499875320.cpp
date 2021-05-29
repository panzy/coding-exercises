//
// 201. Bitwise AND of Numbers Range
// https://leetcode.com/problems/bitwise-and-of-numbers-range/
// 
// 8267 / 8267 test cases passed.	Status: Accepted
// Runtime: 16 ms
// Memory Usage: 5.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      29/05/2021, 10:38:25
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/499875320//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int rangeBitwiseAnd(int left, int right) {
        int ans = 0;
        
        for (int i = 0; i < 32; ++i) {
            // If any number between [left, right] as a zero on this bit,
            // the answer also has a zero.
            
            unsigned int mask = 1 << i;
            
            if (mask > left)
                break;
            
            if ((left & mask) == 0 || (right & mask) == 0)
                continue;
            
            // left + mask is the minimal number that has a zero on this bit and is not less than left.
            // right - mask is the maximal number that has a zero on this bit and is not greater than right.
            if ((left + mask) <= right && (right - mask) >= left)
                continue;
            
            ans |= mask;
        }
        
        return ans;
    }
};