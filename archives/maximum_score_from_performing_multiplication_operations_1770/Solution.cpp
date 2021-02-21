/*
 * 1770. Maximum Score from Performing Multiplication Operations
 * https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
 * --
 * Zhiyong
 * 2021-02-20
 */
class Solution {
	private:
		const int INV = 1000 * 1000 * 9;
		vector<vector<int>> memo;
	public:
		int helper(const vector<int>& nums, const vector<int>& multipliers,
				int i, int j, int m) {

			if (m >= multipliers.size() || i > j) return 0;

			if (memo[i][m] != INV)
				return memo[i][m];

			return memo[i][m] = max(
					nums[i] * multipliers[m] + helper(nums, multipliers, i + 1, j, m + 1),
					nums[j] * multipliers[m] + helper(nums, multipliers, i, j - 1, m + 1)
					);
		};

		int maximumScore(const vector<int>& nums, const vector<int>& multipliers) {
			int m = multipliers.size();
			fill_2d(memo, m + 2, m + 2, INV);
			return helper(nums, multipliers, 0, nums.size() - 1, 0);
		}
};

