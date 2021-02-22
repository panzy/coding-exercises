/*
524. Longest Word in Dictionary through Deleting
https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
--
Zhiyong
2021-02-22
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
private:
    bool is_subseq(const string& s, const string& ss) {
        int i = 0, j = 0;
        for (; i < s.length() && j < ss.length(); ++i) {
            if (s[i] == ss[j]) {
                ++j;
            }
        }
        return j == ss.length();
    }

public:
    string findLongestWord(const string& s, const vector<string>& d) {
        string ans;
        for (auto&& di : d) {
            if ((di.length() > ans.length() || di.length() == ans.length() && di < ans) && is_subseq(s, di))
                ans = di;
        }
        return ans;
    }
};

int main() {
    {
        string ans = Solution().findLongestWord("abpcplea", { "ale","apple","monkey","plea" });
        _ASSERT("apple" == ans);
    }
    {
        string ans = Solution().findLongestWord("abpcplea", { "a","b","c" });
        _ASSERT("a" == ans);
    }
    {
        string ans = Solution().findLongestWord("abpcplea", { "b","a","c" });
        _ASSERT("a" == ans);
    }
    return 0;
}
