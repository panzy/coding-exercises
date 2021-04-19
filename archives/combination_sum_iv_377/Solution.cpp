/*
377. Combination Sum IV
https://leetcode.com/problems/combination-sum-iv/

--
Zhiyong
2021-04-19
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
#include <algorithm>
#include <functional>
#include <cassert>
using namespace std;

class Solution {
public:
    int combinationSum4(const vector<int>& nums, int target) {
        vector<unsigned int> dp(target + 1, 0);
        dp[0] = 1;

        for (int t = 1; t <= target; ++t) {
            for (int a : nums) {
                if (t >= a) {
                    dp[t] += dp[t - a];
                }
            }
        }

        return dp[target];
    }
};

int main() {
    int ans;

    assert(1 == (ans = Solution().combinationSum4({ 1, 2, 3 }, 1)));
    assert(2 == (ans = Solution().combinationSum4({ 1, 2, 3 }, 2)));
    assert(4 == (ans = Solution().combinationSum4({ 1, 2, 3 }, 3)));
    assert(7 == (ans = Solution().combinationSum4({ 1, 2, 3 }, 4)));
    assert(0 == (ans = Solution().combinationSum4({ 9 }, 3)));

    assert(1 == (ans = Solution().combinationSum4({
        10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,210,220,230,240,250,260,270,
        280,290,300,310,320,330,340,350,360,370,380,390,400,410,420,430,440,450,460,470,480,490,500,510,520,
        530,540,550,560,570,580,590,600,610,620,630,640,650,660,670,680,690,700,710,720,730,740,750,760,770,
        780,790,800,810,820,830,840,850,860,870,880,890,900,910,920,930,940,950,960,970,980,990,111
    }, 999)));
}
