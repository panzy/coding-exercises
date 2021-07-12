//
// 205. Isomorphic Strings
// https://leetcode.com/problems/isomorphic-strings/
// 
// 39 / 39 test cases passed.	Status: Accepted
// Runtime: 4 ms
// Memory Usage: 6.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      12/07/2021, 18:06:48
// LeetCode submit time: 1 hour, 6 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/521522449//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        // char x is mapped to bindings[x],
        // bindingsR[y] is mapped to y.
        char bindings[128];
        char bindingsR[128];
        
        fill(begin(bindings), end(bindings), 0);
        fill(begin(bindingsR), end(bindingsR), 0);
        
        int n = s.length();
        
        for (int i = 0; i < n; ++i) {
            char &b = bindings[s[i]];
            if (b) {
                if (b != t[i]) // s[i] has been bound to another char
                    return false;
            } else {
                if (bindingsR[t[i]]) // another char has been bound to t[i]
                    return false;
                b = t[i];
                bindingsR[t[i]] = b;
            }  
        }

        return true;
    }
};