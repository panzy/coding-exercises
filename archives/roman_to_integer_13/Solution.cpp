/*
13. Roman to Integer
https://leetcode.com/problems/roman-to-integer/
--
Zhiyong
2021-02-20
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
using namespace std;

class Solution {
public:
    int romanToInt(string s) {
        int ans = 0;
        const int n = s.size();
        map<char, int> table = { {'I', 1}, {'V', 5}, {'X', 10}, {'L', 50}, {'C', 100}, {'D', 500}, {'M', 1000} };
        for (int i = 0; i < n; ++i) {
            if (
                (s[i] == 'I' && i + 1 < n && (s[i + 1] == 'V' || s[i + 1] == 'X')) ||
				(s[i] == 'X' && i + 1 < n && (s[i + 1] == 'L' || s[i + 1] == 'C')) ||
				(s[i] == 'C' && i + 1 < n && (s[i + 1] == 'D' || s[i + 1] == 'M')))
				ans -= table[s[i]];
            else
				ans += table[s[i]];
        }
        return ans;
    }
};
