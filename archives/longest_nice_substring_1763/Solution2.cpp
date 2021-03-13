/*
1763. Longest Nice Substring
https://leetcode.com/problems/longest-nice-substring/

A string s is nice if, for every letter of the alphabet that s contains, it
appears both in uppercase and lowercase. For example, "abABB" is nice because
'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b'
appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are
multiple, return the substring of the earliest occurrence. If there are none,
return an empty string.

Constraints:
1 <= s.length <= 100
s consists of uppercase and lowercase English letters.

--
Zhiyong
2021-03-13
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
#include <tuple>
#include <algorithm>
#include <functional>
#include <regex>
#include <mutex>
#include <barrier>
#include <cassert>
#include <bitset>
using namespace std;

// Divide & conquer
class Solution {
    string longestNiceSubstring(const string& s, int begin, int end) {
        int flag[26]{}; // 1 = lower case is present, 2 = upper case is present

        for (int i = begin; i < end; ++i) {
            char c = s[i];
            if ('a' <= c && c <= 'z')
                flag[c - 'a'] |= 1;
            else if ('A' <= c && c <= 'Z')
                flag[c - 'A'] |= 2;
        }

        for (int i = begin; i < end; ++i) {
            char c = s[i];
            int flagIdx = ('a' <= c && c <= 'z') ? (c - 'a') : (c - 'A');
            if (flagIdx < 0 || flagIdx >= 26 || flag[flagIdx] == 1 || flag[flagIdx] == 2) {
                string ans1 = longestNiceSubstring(s, begin, i);
                string ans2 = longestNiceSubstring(s, i + 1, end);
                return ans1.length() >= ans2.length() ? ans1 : ans2;
            }
        }
        return s.substr(begin, end - begin);
    }
public:
    string longestNiceSubstring(const string& s) {
        return longestNiceSubstring(s, 0, s.length());
    }

};

int main() {
    string ans;

    ans = Solution().longestNiceSubstring("d");
    assert(ans.compare("") == 0);

    ans = Solution().longestNiceSubstring("bB");
    assert(ans.compare("bB") == 0);

    ans = Solution().longestNiceSubstring("dDzeE");
    assert(ans.compare("dD") == 0);

    ans = Solution().longestNiceSubstring("YazaAay");
    assert(ans.compare("aAa") == 0);
}
