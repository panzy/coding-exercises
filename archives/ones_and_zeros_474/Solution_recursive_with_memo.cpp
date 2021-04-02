/* 474. Ones and Zeroes
 * https://leetcode.com/problems/ones-and-zeroes/
 * --
 * Zhiyong 2021-04-02
 */

class Solution {
    unordered_map<int, int> memo;

    int findMaxForm(vector<pair<int, int>>& items, int start, int m, int n) {
        if (start == items.size())
            return 0;
        
        if (m == 0 && n == 0)
            return 0;
        
        int key = (start << 14) + (m << 7) + n;
        if (memo.count(key))
            return memo[key];
        
        auto [zero, one] = items[start];

        if (m < zero || n < one)
            return findMaxForm(items, start + 1, m, n);
        
        return memo[key] = max(
            1 + findMaxForm(items, start + 1, m - zero, n - one),
            findMaxForm(items, start + 1, m, n)
        );
    }
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<pair<int, int>> items;
        items.reserve(strs.size());
        for (auto& s : strs) {
            int zero = 0, one = 0;
            for (char c : s) {
                if (c == '0')
                    ++zero;
                else
                    ++one;
            }
            items.push_back({zero, one});
        }
        
        return findMaxForm(items, 0, m, n);
    }
};
