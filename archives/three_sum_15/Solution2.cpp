/* 15. 3 Sum
 * https://leetcode.com/problems/3sum/ 
 *
 * Slightly differ from the previous one in that this time the map keys are not ordered.
 * --
 * Zhiyong
 * 2021-03-05
 */
class Solution {
	public:
		vector<vector<int>> threeSum(vector<int>& nums) {
			int n = nums.size();
			unordered_map<int, int> M; // num -> freq
			vector<vector<int>> res;

			for (int i : nums) {
				++M[i];
			}

			for (auto&& i = M.begin(); i != M.end(); ++i) {
				for (auto j = M.begin(); j != M.end(); ++j) {
					// for each triplet (i,j,k), require i<=j<=k
					// so there's no duplicates.

					if (j->first < i->first) continue;
					if (j->first == i->first && M[i->first] < 2) continue;
					// desired k value                
					int kVal = 0 - i->first - j->first;
					if (kVal < j->first) continue;
					if (M.find(kVal) == M.end()) continue;

					int kFreq = M[kVal];

					int requiredFreq = 1;
					if (kVal == j->first) ++requiredFreq;
					if (kVal == i->first) ++requiredFreq;

					if (kFreq >= requiredFreq) {
						res.push_back({i->first, j->first, kVal});
					}
				}
			}

			return res;
		}
};
