/**
 * 376. Wiggle Subsequence
 * https://leetcode.com/problems/wiggle-subsequence/ 
 * 
 * Given an integer array nums, return the length of the longest wiggle sequence.
 *
 * --
 * Created by Zhiyong Pan on 2021-03-18.
 */
class Solution {
	public:
		int wiggleMaxLength(vector<int>& nums) {
			int n = nums.size();
			if (n <= 1)
				return n;
			int ans = 1; // nums[0]
			int tail = nums[0]; // the tail number of the sequence
			int delta = 0; // the tailing direction of the sequence
			for (int i = 1; i < n; ++i) {
				if (nums[i] == nums[i - 1])
					continue; // ignore [i]

				if ((delta == 0 && nums[i] != tail) || (i > 1 && (nums[i] - tail) * delta < 0)) { // nice
					++ans;
				}
				tail = nums[i]; // even if length doesn't increase, the tail should be updated
				delta = nums[i] - nums[i - 1]; // change or not
			}

			return ans;
		}
};
