//
// 22. Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/
// 
// 8 / 8 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/06/2021, 22:27:56
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/509502492//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        // Consider '(' as 0 and ')' as 1, then all combinations (including ill-formed)
        // can be enumerated by increasing an integer from
        //      00000111111 (assume n = 5)
        // to
        //      11111100000
        // or, from 2^n-1 to (2^n-1)<<n,
        // For n <= 8, that's not too much if we check each of them.
        
        int start = pow(2, n) - 1, end = start << n;
        vector<string> res;
        for (int i = start; i <= end; ++i) {
            if (check(i, n))
                res.push_back(format(i, n));
        }
        return res;
    }
    
private:
    // Determine whether a combination of parentheses represented by an integer
    // is well-formed.
    // n is the number of pairs.
    bool check(int i, int n) {
        // count number of open ')', i.e 1, from right to left.
        int ones = 0, opens = 0;
        for (int k = 0; k < 2 * n; ++k) {
            if (i & (1 << k)) {
                ++ones;
                ++opens;
            } else {
                if (opens > 0) {
                    --opens;
                } else {
                    return false;
                }
            }
        }
        
        return ones == n && opens == 0;
    }
    
    // Format a combination of parentheses represented by an integer.
    // n is the number of pairs.
    string format(int i, int n) {
        string s(2 * n, ' ');
        for (int k = 0; k < 2 * n; ++k) {
            s[2 * n - k - 1] = (i & (1 << k)) ? ')' : '(';
        }
        return s;
    }
};