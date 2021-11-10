//
// 1178. Number of Valid Words for Each Puzzle
// https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
// 
// 10 / 10 test cases passed.	Status: Accepted
// Runtime: 1056 ms
// Memory Usage: 30.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      09/11/2021, 22:16:19
// LeetCode submit time: 2 hours, 21 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/584744055//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> findNumOfValidWords(vector<string>& words, vector<string>& puzzles) {
        vector<int> wf;
        wf.reserve(words.size());
        for (auto&& w : words) {
            auto r = letterFlags(w);
            if (r.second <= 7)
                wf.push_back(r.first);
        }
        
        vector<int> res;
        res.reserve(puzzles.size());
        for (auto&& p : puzzles) {
            int pff = letterFlags(p).first;
            int n = 0;
            for (auto&& wff : wf) {
                if ((pff & wff) == wff // puzzle contains word
                    && ((1 << (p[0] - 'a')) & wff)) { // puzzle's first letter is contained in word
                    ++n;
                }
            }
            res.push_back(n);
        }
        
        return res;
    }
    
private:
    static pair<int, int> letterFlags(const string& w) {
        int f = 0;
        int n = 0; // distinct count
        for (char c : w) {
            int mask = (1 << (c - 'a'));
            if (!(f & mask)) {
                f |= mask;
                ++n;
            }
        }
        return {f, n};
    }
};