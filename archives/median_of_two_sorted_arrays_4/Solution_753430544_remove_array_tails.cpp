//
// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// 
// 2094 / 2094 test cases passed.	Status: Accepted
// Runtime: 52 ms
// Memory Usage: 89.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      22/07/2022, 00:23:07
// LeetCode submit time: 1 minute ago
// Submission detail page: https://leetcode.com/submissions/detail/753430544//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    static const int MAX_VALUE = 10e6;
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int total = nums1.size() + nums2.size();
        auto p = nums1.begin(), q = nums1.end();
        auto u = nums2.begin(), v = nums2.end();
        // The median is item [idx] or the average of [idx-1] and [idx].
        int idx = total / 2;
        
        // Repeatedly cut the arrays' tails as long as item idx is still in the remained. 
        while (false) {
            // dump for debug
            if (true) {
                cout << "array A: [";
                for (auto i = p; i < q; ++i) cout << ' ' << *i;
                cout << " ]" << endl;
                cout << "array B: [";
                for (auto i = u; i < v; ++i) cout << ' ' << *i;
                cout << " ]" << endl;
                printf("idx %d\n", idx);
            }

            // split array 2 with the median of array 1
            int m = *(p + (q - p) / 2); // median of array 1
            auto t = upper_bound(u, v, m); // corresponding item of array 2 
            
            if (((q - p) / 2 + 1) + (t - u) > idx) {
                // the answer sits in the merge of [p, *m] and [u, t)
                if (p + (q - p) / 2 + 1 < q || t < v) {
                    // shrink the arrays
                    q = p + (q - p) / 2 + 1;
                    v = t;
                } else {
                    break;
                }
            } else {
                // the answer sits beyond the merge of [p, *m) and [u, t).
                // split array 1 with the median of array 2.
                m = *(u + (v - u) / 2); // median of array 2
                t = upper_bound(p, q, m); // corresponding item of array 1
                if (t < q || u + (v - u) / 2 + 1 < v) {
                    // shrink the arrays
                    q = t;
                    v = u + (v - u) / 2 + 1;
                } else {
                    break;
                }
            }
        }
        
        // At this point, both arrays should be short enough for us to do an O(n) search
        int a, b;
        for (int i = 0; i <= idx; ++i) {
            a = b;
            if (p == q) {
                b = *u;
                ++u;
            } else if (u == v) {
                b = *p;
                ++p;
            } else {
                if (*p <= *u) {
                    b = *p;
                    ++p;
                } else {
                    b = *u;
                    ++u;
                }
            }
        }
        
        if (total % 2 == 1) {
            return b;
        } else {
            return (a + b) / 2.0;
        }
    }
};