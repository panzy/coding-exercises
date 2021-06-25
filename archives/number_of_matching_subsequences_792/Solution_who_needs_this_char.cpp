//
// 792. Number of Matching Subsequences
// https://leetcode.com/problems/number-of-matching-subsequences/
// 
// 52 / 52 test cases passed.	Status: Accepted
// Runtime: 116 ms
// Memory Usage: 36.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      25/06/2021, 19:56:32
// LeetCode submit time: 2 hours, 40 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/513169367//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// Group words by their current wanted char.
// Each char in s makes all words that wait for it wait to their next char (re-grouping is involved).
class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        const int n = s.length();
        const int m = words.size();
        vector<pair<int, int>> waitlists[26];

        // each word is waiting for its first char to appear in s
        for (int i =0; i < m; ++i) {
            waitlists[words[i][0] - 'a'].push_back({i, 0});
        }

        int cnt = 0;

        for (char c : s) {
            // which words are waiting for this char?
            // fullfill them and move them to other waitlists accroding to its next char.

            vector<pair<int, int>> tmpList;

            for (auto&& p : waitlists[c - 'a']) {
                auto&& w = words[p.first];
                ++p.second;
                if (p.second == w.length()) { // word exhausted
                    ++cnt;
                } else if (w[p.second] == c) {
                    tmpList.push_back(p);
                } else {
                    waitlists[w[p.second] - 'a'].push_back(p);
                }
            }

            waitlists[c - 'a'] = tmpList;
        }

        return cnt; 
    }
};