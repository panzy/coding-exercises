/*
1249. Minimum Remove to Make Valid Parentheses
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses

--
Zhiyong, 2021-02-19
*/
#include <iostream>
#include <string>
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
#include <iterator>
using namespace std;

typedef long long LL;
typedef unsigned long long ULL;

class Solution {
public:
    string minRemoveToMakeValid(const string& s) {
        stack<int> lp; // indexes of unmatched left parentheses
        set<int> rem; // indexes of removal
        const int n = s.length();

        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                lp.push(i);
            }
            else if (s[i] == ')') {
                if (lp.empty()) {
                    rem.insert(i);
                }
                else {
                    lp.pop();
                }
            }
        }

        while (!lp.empty()) {
            rem.insert(lp.top());
            lp.pop();
        }

        string res;
        res.reserve(n);
        for (int i = 0; i < n; ++i) {
            if (rem.find(i) == rem.end()) {
                res.append(1, s[i]);
            }
        }

        return res;
    }
};

int main() {
    {
        string res = Solution().minRemoveToMakeValid("lee(t(c)o)de)");
		_ASSERT("lee(t(c)o)de" == res);
    }
    {
		string res = Solution().minRemoveToMakeValid("a)b(c)d");
		_ASSERT("ab(c)d" == res);
    }
    {
		string res = Solution().minRemoveToMakeValid("))((");
		_ASSERT("" == res);
    }
    {
		string res = Solution().minRemoveToMakeValid("(a(b(c)d)");
		_ASSERT("a(b(c)d)" == res);
    }
    return 0;
}
