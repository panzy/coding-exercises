/* 435. Non-overlapping Intervals
 * https://leetcode.com/problems/non-overlapping-intervals/
 *
 * A greedy approach.
 *
 * --
 * Zhiyong 2021-03-31
 */

class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();
        
        if (n == 0) return 0;
        
        // Sort spans by end position.
        sort(intervals.begin(), intervals.end(), [](vector<int>& a, vector<int>& b){
            return a[1] < b[1];
        });
        
        // Intuition: pick as many non-overlapping spans as possible,
        // then the remaining spans are the least to remove.
        //
        // To maximize the number of non-overplapping spans, at any x position,
        // we prefer the one ending the earliest. The sooner a span ends, the less likely
        // it will overlap with others.

        int x = intervals[0][0]; // start from the begining of the first span
        int m = 0; // number of maximum non-overlapping spans
        for (int i = 0; i < n; ++i) {
            if (x <= intervals[i][0]) {
                ++m;
                x = intervals[i][1];
            }
        }
        
        return n - m;
    }
};
