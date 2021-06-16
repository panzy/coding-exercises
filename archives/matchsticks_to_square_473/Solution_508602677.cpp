//
// 473. Matchsticks to Square
// https://leetcode.com/problems/matchsticks-to-square/
// 
// 173 / 173 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 10 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      16/06/2021, 00:46:58
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/508602677/?from=explore&item_id=3780/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    bool makesquare(vector<int>& matchsticks) {
        squareSize = 0;
        for (int l : matchsticks) {
            squareSize += l;
        }
        if (squareSize % 4 != 0)
            return false;
        squareSize /= 4;
        
        fill(begin(memo), end(memo), -1);
        return go(matchsticks, 0, 0);
    }
    
private:
    int squareSize{0};
    // memo key = bit set of consumed sticks
    // max key = 2^n = 2^16
    int memo[65536]{ };

    // mask = bit set of consumed match sticks
    // accuLen = accumulated length of the consumed sticks
    bool go(const vector<int>& matchsticks, int mask, int accuLen) {
        if (memo[mask] != -1)
            return memo[mask];

        // since the total len is squareSize * 4, we are already done when 3 sides are completed
        if (accuLen == squareSize * 3)
            return memo[mask] = 1;
        
        // remaining length of the current side
        int remainLen = squareSize - (accuLen % squareSize);
        int n = matchsticks.size();
        
        for (int i = 0; i < n; ++i) {
            if ((mask & (1 << i)) == 0 && matchsticks[i] <= remainLen
               && go(matchsticks, mask | (1 << i), accuLen + matchsticks[i])) {
                return memo[mask] = 1;
            }
        }
        
        return memo[mask] = 0;
    }
};
