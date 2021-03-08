/*
Shortest substring containg all the distinct chars.
--
Zhiyong
2021-03-07
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

int shortestSubstring(string givenString) {
    int freq[26]{0};
    int distinctCnt = 0;
    for (char c : givenString) {
        if (++freq[c - 'a'] == 1) {
            ++distinctCnt;
        }
    }

    int n = givenString.length();
    int begin = 0, end = 1; // defines a substring
    int cnt = 1; // distinct numbers in the sub string
    int ans = n; // the answer

    // put in the first char
    fill(freq, freq + 26, 0);
    freq[givenString[0] - 'a'] = 1;

    while (end <= n) {
        if (cnt < distinctCnt) {
            if (end >= n)
                break;

            // extend the right
            if (++freq[givenString[end++] - 'a'] == 1) {
                ++cnt;
            }
        }
        else {
            // shrink the left
            while (freq[givenString[begin] - 'a'] > 1) {
                --freq[givenString[begin] - 'a'];
                ++begin;
            }

            ans = min(ans, end - begin);

            // move on
            if (--freq[givenString[begin] - 'a'] == 0)
                --cnt;
            ++begin;
        }
    }

    return ans;
}

int main() {
    int ans;
    assert(3 == (ans = shortestSubstring("aabc")));
    assert(3 == (ans = shortestSubstring("abc")));
    assert(2 == (ans = shortestSubstring("aba")));
}

