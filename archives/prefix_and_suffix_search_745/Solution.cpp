/*
745. Prefix and Suffix Search
https://leetcode.com/problems/prefix-and-suffix-search/
--
Zhiyong
2021-05-01
*/
#include <string>
#include <sstream>
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <bitset>
#include <algorithm>
#include <functional>
#include <cassert>
using namespace std;

class WordFilter {
    // For a word like "test", consider "#test", "t#test", "st#test",
    // "est#test", "test#test". Then if we have a query like prefix = "te",
    // suffix = "t", we can find it by searching for something we've inserted
    // starting with "t#te".
    //
    // key = possible suffix + '#' + possible prefix
    // value = the largest index of the valid words
    unordered_map<string, int> table;
public:
    WordFilter(const vector<string>& words) {
        for (int idx = 0, n = words.size(); idx < n; ++idx) {
            auto& w = words[idx];
            string ww = w + '#' + w;
            size_t m = w.length();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < m; ++j) {
                    string key = ww.substr(i, m - i + 1 + j + 1);
                    table[key] = idx;
                }
            }
        }
    }
    
    int f(string prefix, string suffix) {
        string key = suffix + '#' + prefix;
        return table.count(key) == 0 ? -1 : table[key];
    }
};

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter* obj = new WordFilter(words);
 * int param_1 = obj->f(prefix,suffix);
 */

int main() {
    {
        WordFilter obj({ "xyz", "apple" });
        int ans;
        assert(1 == (ans = obj.f("a", "e")));
        assert(-1 == (ans = obj.f("ao", "e")));
    }
    {
        WordFilter obj({ "apple", "test", "teast", "teist", "z" });
        int ans;
        assert(0 == (ans = obj.f("a", "e")));
        assert(1 == (ans = obj.f("te", "est")));
        assert(3 == (ans = obj.f("te", "st")));
        assert(3 == (ans = obj.f("t", "t")));
        assert(3 == (ans = obj.f("tei", "t")));
        assert(2 == (ans = obj.f("tea", "t")));
        assert(2 == (ans = obj.f("tea", "ast")));
        assert(2 == (ans = obj.f("teast", "teast")));
        assert(4 == (ans = obj.f("z", "z")));
    }
    {
        WordFilter obj({ "a", "a", "a", "b", "b", "b" });
        int ans;
        assert(2 == (ans = obj.f("a", "a")));
    }
}
