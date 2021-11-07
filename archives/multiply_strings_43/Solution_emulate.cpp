//
// 43. Multiply Strings
// https://leetcode.com/problems/multiply-strings/
// 
// 311 / 311 test cases passed.	Status: Accepted
// Runtime: 280 ms
// Memory Usage: 80.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      07/11/2021, 12:24:16
// LeetCode submit time: 1 hour, 1 minute ago
// Submission detail page: https://leetcode.com/submissions/detail/583490436//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    string multiply(char num1, char num2) {
        return to_string((num1 - '0') * (num2 - '0'));
    }

   string add(const string& num1, const string& num2) {
        // add digits from right to left
        int m = num1.size(), n = num2.size();
        string res = "";
        int carry = 0;
        for (int i = 0; i < m || i < n; ++i) {
            int d1 = 0, d2 = 0;
            if (i < m)
                d1 = num1[m - 1 - i] - '0';
            if (i < n)
                d2 = num2[n - 1 - i] - '0';
            res += '0' + (d1 + d2 + carry) % 10;
            carry = (d1 + d2 + carry < 10) ? 0 : 1;
        }
        if (carry)
            res += '1';
        return string(res.rbegin(), res.rend());
    }

    string multiply(string num1, char d2) {
        // for each char d1 of num1, do d1 x d2 x 10^i
        int n = num1.size();
        string ans = "0";
        for (int i = 0; i < n; ++i) {
            char d1 = num1[n - 1 - i];
            auto t = multiply(d1, d2);
            if (t != "0")
                ans = add(ans, t + string(i, '0'));
        }
        return ans;
    }

    string multiply(string num1, string num2) {
        // for each char d2 of num2, do num1 x d2 x 10^i
        int n = num2.size();
        string ans = "0";
        for (int i = 0; i < n; ++i) {
            char d2 = num2[n - 1 - i];
            auto t = multiply(num1, d2);
            if (t != "0")
                ans = add(ans, t + string(i, '0'));
        }
        return ans;
    }
};