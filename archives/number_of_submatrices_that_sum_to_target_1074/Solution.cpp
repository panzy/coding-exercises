/**
 * 1074. Number of Submatrices That Sum to Target
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/submissions/
 * 
 * This is an O(n^4) solution, where n = matrix size <= 100.
 *
 * There are two implementations with the same time complexity but
 * differentiate a lot in runtime. The faster one eliminated the ternary
 * expressions in the innermost loop by defining the leftmost col and topmost
 * row as sentinels. 
 *
 * --
 * Created by Zhiyong 
 * 2021-05-15, 12:37:45 p.m.
 */

/** Implementation #1, runtime 1840ms, faster than 7% of C++ submissions. */
class Solution {
	public:
		int numSubmatrixSumTarget(vector<vector<int>>& matrix, int target) {
			int m = matrix.size();
			int n = matrix[0].size();

			// sum[r][c] = sum of submatrix [0,0,r,c]
			auto sum = matrix;

			for (int y = 1; y < m; ++y) {
				for (int x = 0; x < n; ++x) {
					sum[y][x] += sum[y - 1][x];
				}
			}
			for (int x = 1; x < n; ++x) {
				for (int y = 0; y < m; ++y) {
					sum[y][x] += sum[y][x - 1];
				}
			}

			int ans = 0;

			for (int y2 = 0; y2 < m; ++y2) {
				for (int x2 = 0; x2 < n; ++x2) {
					for (int y1 = 0; y1 <= y2; ++y1) {
						for (int x1 = 0; x1 <= x2; ++x1) {
							if (sum[y2][x2]
									- (y1 > 0 ? sum[y1 - 1][x2] : 0) // top submatrix
									- (x1 > 0 ? sum[y2][x1 - 1] : 0) // left submatrix
									+ (y1 > 0 && x1 > 0 ?sum[y1 - 1][x1 - 1] : 0) // top-left submatrix
									== target)
								++ans;
						}
					}        
				}
			}        

			return ans;
		}
};

/** Implementation #2, runtime 330ms, faster than 98.7% of C++ submissions. */
class Solution2 {
	public:
		int numSubmatrixSumTarget(vector<vector<int>>& matrix, int target) {
			int m = matrix.size();
			int n = matrix[0].size();

			// let sum[r+1][c+1] = sum of submatrix [0,0,r,c]
			int sum[101][101] = {0};
			for (int y = 0; y < m; ++y) {
				for (int x = 0; x < n; ++x) {
					sum[y + 1][x + 1] = matrix[y][x] + sum[y][x + 1] + sum[y + 1][x] - sum[y][x];
				}
			}

			int ans = 0;

			for (int y2 = 0; y2 < m; ++y2) {
				for (int x2 = 0; x2 < n; ++x2) {
					for (int y1 = 0; y1 <= y2; ++y1) {
						for (int x1 = 0; x1 <= x2; ++x1) {
							if (sum[y2 + 1][x2 + 1]
									- sum[y1][x2 + 1] // top subsum
									- sum[y2 + 1][x1] // left subsum
									+ sum[y1][x1] // top-left subsum
									== target)
								++ans;
						}
					}        
				}
			}        

			return ans;
		}
};
