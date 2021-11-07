//
// 43. Multiply Strings
// https://leetcode.com/problems/multiply-strings/
// 
// 311 / 311 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 6.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      07/11/2021, 12:25:00
// LeetCode submit time: 2 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/583515299//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    string multiply(string num1, string num2) {
        int m = num1.size();
        int n = num2.size();

        // temporary representation of the answer
        // (sum[i] = v) means there is (v * 10^p) in the answer
        vector<int> sum(m + n);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char d1 = num1[i];
                char d2 = num2[j];
                sum[1 + i + j] += (d1 - '0') * (d2 - '0');
            }
        }

        for (int k = m + n - 1; k > 0; --k) {
            sum[k - 1] += sum[k] / 10;
            sum[k] %= 10;
        }

        auto firstNonZero = find_if(sum.begin(), sum.end(), [](int i) -> bool { return i != 0; });
        if (firstNonZero == sum.end()) {
            return "0";
        } else {
            string ans(m + n - (firstNonZero - sum.begin()), '\0');
            transform(firstNonZero, sum.end(), ans.begin(),
                    [](int i) -> char { return '0' + i; });
            return ans;
        }
    }
};