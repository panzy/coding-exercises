/*
1461. Check If a String Contains All Binary Codes of Size K
https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
--
Zhiyong
2021-03-12
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
#include <mutex>
#include <barrier>
#include <cassert>
#include <bitset>
using namespace std;

class Solution {
public:
    bool hasAllCodes(string s, int k) {
        bitset<1048576> nums; // 2^20 = 1048576

        int num = 0;
        int n = s.length();

        // For each substring of length k, record its binary value in the bitset.
        for (int i = 0; i < n; ++i) {
            num <<= 1;
            num += s[i] - '0';

            if (i >= k) {
                num &= ~(1 << k); // drop the leftmost bit
            }

            if (i + 1 >= k) {
                nums.set(num);
            }
        }

        // cannot use nums.all() because we didn't use all the bits unless when k==20.
        for (int i = 0, maxNum = (int)pow(2, k) - 1; i <= maxNum; ++i) {
            if (!nums[i])
                return false;
        }

        return true;
    }
};

int main() {
    int ans;
    vector<int> coins;
    //using Solution = Solution4;

    assert(Solution().hasAllCodes("01", 1));
    assert(Solution().hasAllCodes("10", 1));
    assert(Solution().hasAllCodes("00110", 2));
    assert(Solution().hasAllCodes("11001", 2));
    assert(Solution().hasAllCodes("0111000101", 3));
    assert(!Solution().hasAllCodes("011100010", 3));
}
