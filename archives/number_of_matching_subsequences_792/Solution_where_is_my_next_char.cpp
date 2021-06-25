//
// 792. Number of Matching Subsequences
// https://leetcode.com/problems/number-of-matching-subsequences/
// 
// 52 / 52 test cases passed.	Status: Accepted
// Runtime: 296 ms
// Memory Usage: 224.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      25/06/2021, 19:56:04
// LeetCode submit time: 3 days ago
// Submission detail page: https://leetcode.com/submissions/detail/511815513//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        // motivation: need to speed up this operation: indexOf(chr, startPos),
        // where startPos > the last index of this char.
        
        auto N = s.length();

        // key = <char, startPos>
        // value = index
        vector<vector<int>> chars(26, vector<int>(N, -1));
        
        // build maps
        int last[26]; // last position of each char
        fill(begin(last), end(last), -1);
        
        for (int i = 0; i < N; ++i) {
            for (int start = last[s[i] - 'a'] + 1; start <= i; ++start)
                chars[s[i] - 'a'][start] = i;
            last[s[i] - 'a'] = i;
        }
        
        // query
        int ans = 0;
        for (auto&& w : words) {
            int i = 0;
            for (char c : w) {
                if (i >= N || chars[c - 'a'][i] == -1) {
                    i = -1;
                    break;
                }
                i = chars[c - 'a'][i] + 1;
            }
            if (i != -1)
                ++ans;
        }
        return ans;
    }
};