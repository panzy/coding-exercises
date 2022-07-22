//
// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// 
// 2094 / 2094 test cases passed.	Status: Accepted
// Runtime: 32 ms
// Memory Usage: 89.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      22/07/2022, 17:35:21
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/754051574//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        const int MAX_VAL = 10e6 + 1;

        // We are going to do binary search in nums1 so we want it to be the shorter one.
        // (If it's empty, no need to search at all.)
        if (nums1.size() > nums2.size()) {
            swap(nums1, nums2);
        }

        int m = nums1.size(), n = nums2.size();
        int odd = (m + n) % 2;
        // Count of items on one side the median item(s). So we have
        // m + n == halfSize + (odd ? 1 : 2) + halfSize
        int halfSize = (m + n - 1 + odd) / 2;

        // Binary search an index q in nums1, where all nums1 items before it should go to the left side ofthe median items(s).

        int lo = 0, hi = m;
        int p = (lo + hi) / 2;

        while (lo < hi) {
            // All items in nums2 before L is less than nums1[p - 1], so they MUST go to left.
            auto L = p > 0 ? lower_bound(nums2.begin(), nums2.end(), nums1[p - 1]) : nums2.begin();
            // All items in nums2 before U is not greater than nums1[p], so they MAY go to left.
            auto U = upper_bound(nums2.begin(), nums2.end(), nums1[p]);
            // Minimum possible count of items in the merged array that belong to the left part.
            int minLeftSize = p + (L - nums2.begin());
            // Maximum possible count of items in the merged array that belong to the left part.
            int maxLeftSize = p + (U - nums2.begin());

            // If the possible left size is not acceptable, we have to adjust p.
            if (maxLeftSize < halfSize) {
                lo = p + 1;
            }
            else if (minLeftSize > halfSize) {
                hi = p;
            }
            else {
                break;
            }

            p = (lo + hi) / 2;
        }

        // For a given p, q can be calculated. All nums2 items before q go to left.
        int q = halfSize - p;

        // x1 and x2 are the first two nums1 items starting from p.
        // y1 and y2 are the first two nums2 items starting from q.
        int x1, x2, y1, y2;

        x1 = x2 = y1 = y2 = MAX_VAL;

        if (p < m) {
            x1 = nums1[p];
        }
        if (p + 1 < m) {
            x2 = nums1[p + 1];
        }
        if (q < n) {
            y1 = nums2[q];
        }
        if (q + 1 < n) {
            y2 = nums2[q + 1];
        }

        // Answer is either the less one between x1 and y1
        // or the average of the least two of x1, x2, y1 and y2.
        if (odd) {
            return min(x1, y1);
        }
        else {
            int a = min(x1, y1);
            int b = min(max(x1, y1), min(x2, y2));
            return (a + b) / 2.0;
        }
    }
};
