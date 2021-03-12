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

// time: beats 94.61%
// memory: beats 96.49%
//
// If track the count of distinct numbers, even though in the end we don't have to do
// a O(2^k) traveral on the bitset, the overall time is slightly longer. I think that's
// because to maintain the distinct count, we have to query the bitset each time to see
// whether a number is appear for the first time.
class Solution1 {
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

// Same idea, slightly different implementation:
// 1. replaced bitset with bool array on heap;
// 2. count distinct numbers on the fly.
//
// Runtime: 52 ms, faster than 94.61% of C++ online submissions for Check If a String Contains All Binary Codes of Size K.
// Memory Usage : 27.1 MB, less than 80.33 % of C++ online submissions for Check If a String Contains All Binary Codes of Size K.
class Solution2 {
public:
    bool hasAllCodes(string s, int k) {
        size_t endNum = (size_t)pow(2, k);
        unique_ptr<bool[]> nums(new bool[endNum]);
        fill(nums.get(), nums.get() + endNum, false);

        int num = 0;
        int n = s.length();
        int cnt = 0;

        // For each substring of length k, record its binary value in the bitset.
        for (int i = 0; i < n; ++i) {
            num <<= 1;
            num += s[i] - '0';

            if (i >= k) {
                num &= ~(1 << k); // drop the leftmost bit
            }

            if (i + 1 >= k) {
                if (!nums[num])
                    ++cnt;
                nums[num] = true;
            }
        }

        return cnt == endNum;
    }
};

int main() {
    int ans;
    vector<int> coins;
    using Solution = Solution2;

    assert(Solution().hasAllCodes("01", 1));
    assert(Solution().hasAllCodes("10", 1));
    assert(Solution().hasAllCodes("00110", 2));
    assert(Solution().hasAllCodes("11001", 2));
    assert(Solution().hasAllCodes("0111000101", 3));
    assert(!Solution().hasAllCodes("011100010", 3));
}
