//
// 363. Max Sum of Rectangle No Larger Than K
// https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
// 
// 39 / 39 test cases passed.	Status: Accepted
// Runtime: 1820 ms
// Memory Usage: 291.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      03/07/2021, 21:38:58
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/516995035//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// This implementation tries hard to reduce the time complexity from
// O(n * n * m * m) to O(n * n * m * log2(m)).
// It even transposed the matrix to make sure n >= m.
// But for m, n <= 100, the effort is not necessarily worth.
class Solution {
public:
    int maxSumSubmatrix(vector<vector<int>>& matrix, int k) {
        int m = matrix.size();
        int n = matrix[0].size();
        int ans = INT_MIN;
        
        // copy matrix to grid, transpose the matrix if necessary to make sure rows >= cols.
        int grid[100][100];
        if (m >= n) {
            for (int r = 0; r < m; ++r) {
                for (int c = 0; c < n; ++c) {
                    grid[r][c] = matrix[r][c];
                }
            }
        } else {
            for (int r = 0; r < m; ++r) {
                for (int c = 0; c < n; ++c) {
                    grid[c][r] = matrix[r][c];
                }
            }
            swap(m, n);
        }
        
        // keep all the row sums of the previous width so we can calculate
        // each row sum later in O(1) instead of O(width).
        int rowSums[100];
        int rowSums0[100];
        
        // init row sums for left == 0 and width = 1
        for (int rowIdx = 0; rowIdx < m; ++rowIdx) {
            rowSums0[rowIdx] = grid[rowIdx][0];
        }
        
        // for each possible width of the rectangles...
        for (int width = 1; width <= n; ++width) { // O(n)
            
            // init row sums for left == 0
            for (int rowIdx = 0; rowIdx < m; ++rowIdx) {
                if (width == 1) {
                    rowSums[rowIdx] = rowSums0[rowIdx];
                } else {
                    rowSums0[rowIdx] += grid[rowIdx][width - 1];
                    rowSums[rowIdx] = rowSums0[rowIdx];
                }
            }
        
            // and for each possible left position of the rectangles...
            for (int left = 0; left + width <= n; ++left) { // O(n)
                set<int> prefixSums;
                int prevPrefixSum = 0;
                prefixSums.insert(0);

                // and for each possible bottom position of the rectangles...
                for (int bottom = 0; bottom < m; ++bottom) { // O(m)
                    if (left > 0) {
                        rowSums[bottom] += -grid[bottom][left - 1] + grid[bottom][left + width - 1];
                    }
                    int currPrefixSum = prevPrefixSum + rowSums[bottom];
                    // ...what is the best rectangle with this bottom?
                    int minPrefixSum = currPrefixSum - k;
                    auto itr = prefixSums.lower_bound(minPrefixSum); // O(log2(m))
                    if (itr != prefixSums.end()) {
                        ans = max(ans, currPrefixSum - *itr);
                    }
                    prefixSums.insert(currPrefixSum);
                    prevPrefixSum = currPrefixSum;
                }
            }
        }
        
        return ans;
    }
};