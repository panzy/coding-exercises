/**
 * 890. Find and Replace Pattern
 * https://leetcode.com/problems/find-and-replace-pattern/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-21, 11:49:15 a.m.
 */

class Solution {
    bool match(const string& word, const string& pattern) {
        // p(x) = y, q(y) = x, where x is a char from word and y is the corresponding char from pattern.
        char p[26], q[26];

        fill(begin(p), end(p), 0);
        fill(begin(q), end(q), 0);

        for (int i = 0, n = word.length(); i < n; ++i) {
            auto x = word[i];
            auto y = pattern[i];
            if (p[x - 'a'] == y && q[y - 'a'] == x) {
                // the connection exists
            } else if (p[x - 'a'] == 0 && q[y - 'a'] == 0) {
                // establish a connection between x and y
                p[x - 'a'] = y;
                q[y - 'a'] = x;
            } else {
                // conflict
                return false;
            }
        }

        return true;
    }

public:
    vector<string> findAndReplacePattern(vector<string>& words, string& pattern) {
        vector<string> res;

        for (auto& w : words) {

