/*
220. Contains Duplicate III
https://leetcode.com/problems/contains-duplicate-iii/
--
Zhiyong
2021-02-25
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

typedef long long LL;

class Solution {
private:
    // Check whether a map key betwen [num - t, num + t] exists.
    bool check1(const map<LL, LL>& win, int num, int t) {
        auto i = win.lower_bound(num);
        if (i != win.end()) {
            if (i->first - num <= t) {
                return true;
            }
            else if (i != win.begin()) {
                i--;
                if (num - i->first <= t)
                    return true;
            }
        } else if (win.size() && num - win.rbegin()->first <= t) {
            return true;
        }
        return false;
    }

    // Simpler than check1().
    bool check2(const map<LL, LL>& win, LL num, int t) {
        auto i = win.lower_bound(num - t);
        if (i != win.end() && abs(i->first - num) <= t) {
            return true;
        }
        return false;
    }
public:
    bool containsNearbyAlmostDuplicate1(vector<int>& nums, int k, int t) {
        if (k == 0) return false;

        const int n = nums.size();

        // num val -> frequency
        map<LL, LL> win;

        for (int i = 0; i < n; i++) {

            if (check2(win, nums[i], t))
                return true;

            win[nums[i]]++;

            if (i >= k) {
                // XXX: no need to check frequency, because if there were duplicates in the map,
                // the algorithm has already returned true.
                if (--win[nums[i - k]] == 0) {
                    win.erase(nums[i - k]);
                }
            }
        }

        return false;
    }

    // A little bit simpler than containsNearbyAlmostDuplicate1().
    bool containsNearbyAlmostDuplicate2(vector<int>& nums, int k, int t) {
        if (k == 0) return false;

        const int n = nums.size();

        // num val -> dummy
        map<LL, LL> win;

        for (int i = 0; i < n; i++) {

            if (win.size() && check2(win, nums[i], t))
                return true;

            win[nums[i]] = 0;

            if (i >= k) {
                win.erase(nums[i - k]);
            }
        }

        return false;
    }

    bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
        return containsNearbyAlmostDuplicate2(nums, k, t);
    }
};

int main() {
    {
        vector<int> nums = { 1, 2, 3, 4, 1 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 3, 0);
        _ASSERT(!ans);
    }
    {
        vector<int> nums = { 7, 1, 3 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 2, 3);
        _ASSERT(ans);
    }
    { // k == 0
        vector<int> nums = { 1, 2 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 0, 1);
        _ASSERT(!ans);
    }
    { // n < k
        vector<int> nums = { 1, 2 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 3, 1);
        _ASSERT(ans);
    }
    { // n == k
        vector<int> nums = { 1, 2 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 2, 1);
        _ASSERT(ans);
    }
    {
        vector<int> nums = { 2147483646, 2147483647 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 3, 3);
        _ASSERT(ans);
    }
    {
        vector<int> nums = { -2147483648, 2147483647 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 1, 1);
        _ASSERT(!ans);
    }
    {
        vector<int> nums = { 1, 2, 3, 4, 1, 3 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 3, 0);
        _ASSERT(ans);
    }
    {
        vector<int> nums = { 1, 2, 3, 1 };
        int ans = Solution().containsNearbyAlmostDuplicate(nums, 3, 0);
        _ASSERT(ans);
    }
    return 0;
}
