//
// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// 
// 2094 / 2094 test cases passed.	Status: Accepted
// Runtime: 75 ms
// Memory Usage: 89.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      21/07/2022, 00:26:13
// LeetCode submit time: 4 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/752571763//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    static const int MAX_VALUE = 10e6;
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int total = nums1.size() + nums2.size();
        auto p = nums1.begin(), q = nums1.end();
        auto u = nums2.begin(), v = nums2.end();
        // a is the (first) median item
        int a = findNthElement(p, q, u, v, (total - 1) / 2);
        if (total % 2 == 1) {
            return a;
        } else {
            // need to find the second media item. it could be:
            // A. the same as |a| if there are repeats,
            // B. the item following the last |a| in array 1, or
            // C. the item following the last |a| in array 2.
            auto p1 = upper_bound(p, q, a);
            auto u1 = upper_bound(u, v, a);
            if ((q - p1) + (v - u1) < total / 2) {
                // there are enough repeats of |a|
                return a;
            } else {
                // average |a| and the next value
                return (a + min(
                    p1 < q ? *p1 : MAX_VALUE + 1,
                    u1 < v ? *u1 : MAX_VALUE + 1)) / 2.0;
            }
        }
    }
private:
    // Find the n-th element of the merged array.
    // The arrays are [p, q) and [u, v).
    static int findNthElement(
        vector<int>::iterator p,  vector<int>::iterator q, 
        vector<int>::iterator u,  vector<int>::iterator v,
        int idx) {
        
        // dump for debug
        if (false) {
            cout << "array 1:";
            for (auto i = p; i < q; ++i) cout << ' ' << *i;
            cout << endl;
            cout << "array 2:";
            for (auto i = u; i < v; ++i) cout << ' ' << *i;
            cout << endl;
            printf("idx %d\n", idx);
        }
        
        // done if idx == 0
        if (idx == 0) {
            if (p == q) {
                return *u;
            } else if (u == v) {
                return *p;
            } else {
                return min(*p, *u);
            }
        }
        
        // done if one array has been exhausted
        if (p == q) {
            return *(u + idx);
        } else if (u == v) {
            return *(p + idx);
        }
        
        if (*p == *u) {
            // skip repeats of |*p|
            auto p1 = upper_bound(p, q, *p); 
            if (p + idx < p1) {
                return *p;
            } else {
                idx -= p1 - p;
                p = p1;
            }
            
            // skip repeats of |*q|
            if (idx > 0) {
                auto u1 = upper_bound(u, v, *u); 
                if (u + idx < u1) {
                    return *u;
                } else {
                    idx -= u1 - u;
                    u = u1;
                }
            }
            
            return findNthElement(p, q, u, v, idx);
        } else if (*p > *u) {
            return findNthElement(u, v, p, q, idx);
        } else {
            // [p, u1) is the smallest items of both arrays, safely skip it
            auto u1 = lower_bound(p, q, *u);
            if (p + idx < u1) {
                return *(p + idx);
            } else {
                return findNthElement(u1, q, u, v, idx - (u1 - p));
            }
        }
    }
};