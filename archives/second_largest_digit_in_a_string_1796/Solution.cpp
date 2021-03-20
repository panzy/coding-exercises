/* 1796. Second Largest Digit in a String
 * https://leetcode.com/contest/biweekly-contest-48/problems/second-largest-digit-in-a-string/
 * --
 * Zhiyong
 * 2021-03-20
 */

/*
Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.

An alphanumeric string is a string consisting of lowercase English letters and digits.

 

Example 1:

Input: s = "dfa12321afd"
Output: 2
Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
Example 2:

Input: s = "abc1111"
Output: -1
Explanation: The digits that appear in s are [1]. There is no second largest digit. 
 

Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters and/or digits.
*/

class Solution {
public:
    int secondHighest(string s) {
        bool d[10]{};
        for (char c : s) {
            if (c >= '0' && c <= '9') {
                d[c - '0'] = true;
            }
        }
        
        for (int i = 9, cnt = 0; i >= 0; --i) {
            if (d[i] && ++cnt == 2) {
                return i;
            }
        }
        return -1;
    }
};
