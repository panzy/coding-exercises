/* 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * --
 * Zhiyong 2021-04-08
 */

const string groups[]{ "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

class Solution {
public:
    vector<string> helper(string& digits, int start) {
        if (start >= digits.length()) return {};

        vector<string> res;

        vector<string> rest;
        if (start + 1 < digits.length())
            rest = helper(digits, start + 1);

        for (char a : groups[digits[start] - '2']) {

            if (rest.size() > 0) {
                for (auto&& s : rest) {
                    res.push_back(a + s);
                }
            } else {
                res.push_back(string(1, a));
            }
        }

        return res;
    }

    vector<string> letterCombinations(string digits) {
        return helper(digits, 0);
    }
};

