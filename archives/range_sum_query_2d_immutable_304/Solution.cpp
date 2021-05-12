/**
 * 304. Range Sum Query 2D - Immutable
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * --
 * Zhiyong 2021-05-12
 */

class NumMatrix {
    // sum[r][c] = sum of region [0,0,r,c]
    vector<vector<int>> sum;

    public:
    NumMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        sum = matrix;

        for (int r = 0; r < m; ++r) {
            for (int c = 1; c < n; ++c) {
                sum[r][c] += sum[r][c - 1];
            }
        }

        for (int c = 0; c < n; ++c) {
            for (int r = 1; r < m; ++r) {
                sum[r][c] += sum[r - 1][c];
            }
        }
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        int sumTop = 0, sumLeft = 0, sumShared = 0;
        if (row1 > 0)
            sumTop = sum[row1 - 1][col2];
        if (col1 > 0)
            sumLeft = sum[row2][col1 - 1];
        if (row1 > 0 && col1 > 0)
            sumShared = sum[row1 - 1][col1 - 1];
        return sum[row2][col2] - sumTop - sumLeft + sumShared;
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * int param_1 = obj->sumRegion(row1,col1,row2,col2);
 */
