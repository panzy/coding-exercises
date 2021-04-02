/* 474. Ones and Zeroes
 * https://leetcode.com/problems/ones-and-zeroes/
 * --
 * Zhiyong 2021-04-02
 */

class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        // dp[i][j] = findMaxForm(strs[0:progress], i, j)
        // when progress = strs.size(), dp[m][n] is the final answer.
        int dp[101][101]{};
        
        for (auto&& s : strs) {
            int zero = count(begin(s), end(s), '0'),
            one = s.length() - zero;
            
            for (int i = m; i >= zero; --i) {
                for (int j = n; j >= one; --j) {
                     if (dp[i][j] < 1 + dp[i - zero][j - one])
                         dp[i][j] = 1 + dp[i - zero][j - one];
                }
            }
        }
        
        return dp[m][n];
    }
};
