/* 583. Delete Operation for Two Strings
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * --
 * Zhiyong 2021-05-07
 */

// Recursive approach. Didn't submit. 
class Solution0 {
    int minDistance(string& word1, int offset1, string& word2, int offset2) {
        if (offset1 == word1.length())
            return word2.length() - offset2;
        if (offset2 == word2.length())
            return word1.length() - offset1;
        if (word1[offset1] == word2[offset2])
            return minDistance(word1, offset1 + 1, word2, offset2 + 1);
        else
            return 1 + min(minDistance(word1, offset1 + 1, word2, offset2),
                       minDistance(word1, offset1, word2, offset2 + 1));
    }
public:
    int minDistance(string word1, string word2) {
        return minDistance(word1, 0, word2, 0);
    }
};

// The identical DP approach. Accepted.
class Solution {
public:
    int minDistance(string word1, string word2) {
        int dp[501][501]{}; // dp[offset1][offset2] = minDistance(word1, offset1, word2, offset2) as in the recursive approach.
        int m = word1.length();
        int n = word2.length();
        
        for (int i = m - 1; i >= 0; --i)
            dp[i][n] = m - i;
        for (int j = n - 1; j >= 0; --j)
            dp[m][j] = n - j;
        
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (word1[i] == word2[j])
                    dp[i][j] = dp[i + 1][j + 1];
                else
                    dp[i][j] = 1 + min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        
        return dp[0][0];
    }
};
