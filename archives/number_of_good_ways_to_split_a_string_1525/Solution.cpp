/*
1525. Number of Good Ways to Split a String
https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
--
Zhiyong
2021-02-26
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
using namespace std;


class Solution {
public:
    int numSplits1(string s) {
        int n = s.length();
        int freqL[26]{};
        int freqR[26]{};
        
        for (char c : s) {
            freqR[c - 'a']++;
        }
        
        int ans = 0;
        
        for (int i = 0; i + 1 < n; ++i) {
            freqL[s[i] - 'a']++;
            freqR[s[i] - 'a']--;
            
            if (count(freqL, freqL + 26, 0) == count(freqR, freqR + 26, 0))
                ++ans;
        }
        
        return ans;
    }

    int numSplits(string s) {
        int n = s.length();
        int freqL[26]{};
        int freqR[26]{};
        int cntL = 0, cntR = 0; // distinct count
        
        for (char c : s) {
            if (++freqR[c - 'a'] == 1) {
                ++cntR;
            }
        }
        
        int ans = 0;
        
        for (int i = 0; i + 1 < n; ++i) {
            if (++freqL[s[i] - 'a'] == 1) {
                ++cntL;
            }
            if (--freqR[s[i] - 'a'] == 0) {
                --cntR;
            }
            
            if (cntL == cntR)
                ++ans;
        }
        
        return ans;
    }
};
