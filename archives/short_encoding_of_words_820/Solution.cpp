/*
820. Short Encoding of Words
https://leetcode.com/problems/short-encoding-of-words/

--
Zhiyong
2021-03-06
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
using namespace std;

class Solution {
public:
    int minimumLengthEncoding(vector<string>& words) {
        size_t n = words.size();

        // reverse and sort
        for (auto& s : words) {
            reverse(s.begin(), s.end());
        }
        sort(words.begin(), words.end());

        int ans = 0;

        for (size_t i = 0; i < n; ++i) {
            if (i > 0 &&
                words[i - 1].length() <= words[i].length() &&
                words[i].compare(0, words[i - 1].length(), words[i - 1]) == 0) {
                ans += words[i].length() - words[i - 1].length(); // append (words[i] - words[i-1])
            }
            else {
                ans += 1 + words[i].length(); // append #words[i]
            }
        }

        return ans;
    }
};


int main() {
    int ans;
    vector<string> words;

    words = { "time", "me", "bell" };
    _ASSERT(10 == (ans = Solution().minimumLengthEncoding(words)));

    words = { "t" };
    _ASSERT(2 == (ans = Solution().minimumLengthEncoding(words)));

    words = { "t", "t" };
    _ASSERT(2 == (ans = Solution().minimumLengthEncoding(words)));

    words = { "t", "ttt", "tttt" };
    _ASSERT(5 == (ans = Solution().minimumLengthEncoding(words)));

    words = { "" };
    _ASSERT(1 == (ans = Solution().minimumLengthEncoding(words)));
}
