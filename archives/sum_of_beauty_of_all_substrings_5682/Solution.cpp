/*
 * 5682. Sum of Beauty of All Substrings
 * https://leetcode.com/contest/biweekly-contest-47/problems/sum-of-beauty-of-all-substrings/
 * --
 * Zhiyong
 * 2021-03-06
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
#include <tuple>
#include <algorithm>
#include <functional>
#include <regex>
using namespace std;


class Solution {
public:
    int beautySum(const string& s) {
        int freq[26]{};
        int n = s.length();
        int sum = 0;

        for (int i = 0; i < n; ++i) {
            fill(freq, freq + 26, 0);
            for (int j = i; j < n; ++j) {
                ++freq[s[j] - 'a'];
                auto b = *max_element(freq, freq + 26);
                int a = b;
                for (int f : freq) {
                    if (f > 0 && f < a)
                        a = f;
                }
                sum += b - a;
            }
        }

        return sum;
    }
};

