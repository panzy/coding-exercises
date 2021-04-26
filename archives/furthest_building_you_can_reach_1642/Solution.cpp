/*
1642. Furthest Building You Can Reach
https://leetcode.com/problems/furthest-building-you-can-reach/

--
Zhiyong
2021-04-26
*/
#include <string>
#include <sstream>
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <bitset>
#include <algorithm>
#include <functional>
#include <cassert>
using namespace std;

class Solution {
public:
    int furthestBuilding(const vector<int>& heights, int bricks, int ladders) {
        // for large gaps, use ladders
        priority_queue<int, vector<int>, greater<int>> largeGaps;
        size_t n = heights.size();
        int ans = 0;

        for (size_t i = 1; i < n; ++i) {
            int d = heights[i] - heights[i - 1];
            if (d > 0) {
                largeGaps.push(d);
                if (largeGaps.size() > ladders) {
                    // change the method for the smallest gap on the heap from ladder to brick
                    bricks -= largeGaps.top();
                    largeGaps.pop();
                    if (bricks < 0) {
                        break;
                    }
                }
            }
            ans = i;
        }

        return ans;
    }
};

int main() {
    int ans;
    assert(4 == (ans = Solution().furthestBuilding({ 4, 2, 7, 6, 9, 14, 12 }, 5, 1)));
    assert(7 == (ans = Solution().furthestBuilding({ 4,12,2,7,3,18,20,3,19 }, 10, 2)));
    assert(3 == (ans = Solution().furthestBuilding({ 14, 3, 19, 3 }, 17, 0)));
    assert(0 == (ans = Solution().furthestBuilding({ 14 }, 17, 0)));
}
