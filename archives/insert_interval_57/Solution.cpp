/*
57. Insert Interval
https://leetcode.com/problems/insert-interval/
--
Zhiyong
2021-02-22
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
    vector<vector<int>> insert(const vector<vector<int>>& intervals, const vector<int>& newInterval) {
        vector<vector<int>> res;
        res.reserve(intervals.size() + 1);

        int a = -1; // Could be a bool. Indicates whether the new interval has been added.

        for (int ii = 0, n = intervals.size(); ii < n; ) {
            auto& i = intervals[ii];

            if (res.size() && res.back()[1] >= i[0]) {
                // Extend the prev interval to cover the curr one
                res.back()[1] = max(res.back()[1], i[1]);
                ++ii;
            }
            else if (a == -1 && newInterval[0] <= i[0]) {
                // Insert the new interval
                res.push_back(newInterval);
                a = newInterval[0];
                // and leave i for next loop
            }
            else if (a == -1 && i[1] >= newInterval[0]) {
                // Insert the combination of i and new
                res.push_back({ i[0], max(i[1], newInterval[1]) });
                a = i[0];
                ++ii;
            }
            else {
                res.push_back(i);
                ++ii;
            }
        }

        if (a == -1) {
            res.push_back(newInterval);
        }
        
        return res;
    }
};
