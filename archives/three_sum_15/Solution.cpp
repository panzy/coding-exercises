/* 15. 3 Sum
 * https://leetcode.com/problems/3sum/ 
 * --
 * Zhiyong
 * 2021-03-05
 */
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        int n = nums.size();
        map<int, int> M; // num -> freq
        vector<vector<int>> res;
        
        for (int i : nums) {
            ++M[i];
        }
        
        for (auto&& i = M.begin(); i != M.end(); ++i) {
            auto j = i;
            if (i->second < 2) ++j;
            for (; j != M.end(); ++j) {
                // desired k value                
                int kVal = 0 - i->first - j->first;
                
                // for each triplet (i,j,k), require i<=j<=k
                // so there's no duplicates.
                // Since the map keys are ordered, i<=j is guarranteed.
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
