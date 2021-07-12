//
// 290. Word Pattern
// https://leetcode.com/problems/word-pattern/
// 
// 36 / 36 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 6.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      12/07/2021, 18:07:26
// LeetCode submit time: 12 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/521540046//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    bool wordPattern(string pattern, string s) {
        int n = s.length();
        // letter x is bound to bindings[x - 'a'],
        vector<const char*> bindings(26, nullptr);
        // if a word has been bound to a letter, it is in this set.
        unordered_set<string> bindingsR;
        
        int wi = 0; // word idx
        
        // p is the head of a word, q the tail.
        for (int p = 0, q = p + 1; p < n && q <= n; ++q) {
            if (q == n || s[q] == ' ') { // found a word
                if (wi >= pattern.length())
                    return false;
                int key = pattern[wi] - 'a';
                if (bindings[key]) {
                    if (s.compare(p, q - p, bindings[key], q - p)) // binding doesn't match
                        return false;
                } else {
                    string target = s.substr(p, q - p);
                    if (bindingsR.count(target)) // reversed binding doesn't match
                        return false;
                    bindings[key] = s.c_str() + p;
                    bindingsR.insert(target);
                }
                
                p = q + 1;
                ++q;
                ++wi;
            }
        }
        
        return wi == pattern.size();
    }
};