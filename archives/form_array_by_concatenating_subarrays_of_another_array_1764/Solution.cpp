/*
 * 1764. Form Array by Concatenating Subarrays of Another Array
 * https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array
 * --
 * Zhiyong, 2021-02-20
 */
class Solution {
	public:
		bool canChoose(vector<vector<int>>& groups, vector<int>& nums) {
			int n = nums.size();
			int i = 0;

			for (auto g : groups) {
				bool found = false;
				while (i < n) {
					while (i < n && nums[i] != g[0])
						++i;
					if (i == n)
						return false;

					int j = i;
					for (int gi : g) {
						if (j < n && nums[j] == gi) {
							++j;
						}
						else {
							break;
						}
					}

					if (j - i < g.size()) { // not found
						++i;
					}
					else {
						found = true;
						i = j;
						break;
					}
				}
				if (!found)
					return false;
			}

			return true;
		}
};
