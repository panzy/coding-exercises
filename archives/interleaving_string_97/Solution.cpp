/*
97. Interleaving String

https://leetcode.com/problems/interleaving-string/submissions/

--
Zhiyong
2021-06-05
*/
#include <array>
#include <chrono>
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

// A recursive approach not very efficient but easy to understand and implement.
class Solution_recursive {
public:
    bool isInterleave(const string& s1, const string& s2, const string& s3) {
        ps1 = &s1;
        return go(s1, 0, s2, 0, s3) || go(s2, 0, s1, 0, s3);  
    }
private:
    unordered_map<int, bool> memo;
    const string* ps1; // help to distinguish if the first arg of go() is s1 or s2.

    // Determine if s3[o1+o2:] is formed by an interleaving of s1[o1:] and s2[o2:]
    // where s1 goes first into s3.
    bool go(const string& s1, int o1, const string& s2, int o2, const string& s3) {
        // Encode 3 pieces of information into the memo key:
        // 1. which input string goes first?
        // 2. offset #1
        // 3. offset #2
        auto key = ((ps1 == &s1 ? 1 : 0) << 31) + (o1 << 16) + o2;
        if (memo.count(key) > 0) {
            return memo[key];
        }

        int res = false;
        int o3 = o1 + o2;

        if (o2 == s2.length()) {
            res = 0 == s1.compare(o1, s1.length() - o1, s3, o3);
        }

        for (int i = 1;
             o1 + i <= (int)s1.length()
             && o3 + i <= (int)s3.length()
             && 0 == s3.compare(o3, i, s1, o1, i);
             ++i) {
            // recursion. note that the roles of s1 and s2 are switched.
            if (go(s2, o2, s1, o1 + i, s3)) {
                res = true;
                break;
            }
        }
        memo[key] = res;
        return res;
    }
};

// Rewrite the recursive algo to an iterative one.
class Solution_dp_v1 {
public:
    bool isInterleave(const string& s1, const string& s2, const string& s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3)
            return false;

        // dp1[o1][o2] = isInterLeave_pickArg1First(s1[o1:], s2[o2:], s3[o3:])
        // dp2[o1][o2] = isInterLeave_pickArg1First(s2[o1:], s1[o2:], s3[o3:])
        // use int instead of bool so that in debugging it's easy to tell if any cell is not computed.
        vector<vector<int>> dp1(len1 + 1, vector<int>(len2 + 1, -1));
        vector<vector<int>> dp2(len1 + 1, vector<int>(len2 + 1, -1));

        for (int o1 = len1; o1 >= 0; --o1) {
            for (int o2 = len2; o2 >= 0; --o2) {
                // determine dp1[o1][o2] and dp2[o1][o2].

                int o3 = o1 + o2;

                // dp1

                if (o2 == len2) {
                    dp1[o1][o2] = 0 == s1.compare(o1, len1 - o1, s3, o3);
                }
                else {
                    dp1[o1][o2] = 0;

                    // try each possible length of s1's first substring
                    for (int i = 1;
                        o1 + i <= len1
                        && o3 + i <= len3
                        && 0 == s1.compare(o1, i, s3, o3, i);
                        ++i) {
                        if (dp2[o1 + i][o2] == 1) {
                            dp1[o1][o2] = 1;
                            break;
                        }
                    }
                }

                // dp2

                if (o1 == len1) {
                    dp2[o1][o2] = 0 == s2.compare(o2, len2 - o2, s3, o3);
                }
                else {
                    dp2[o1][o2] = 0;

                    for (int i = 1;
                        o2 + i <= len2
                        && o3 + i <= len3
                        && 0 == s2.compare(o2, i, s3, o3, i);
                        ++i) {
                        if (dp1[o1][o2 + i] == 1) {
                            dp2[o1][o2] = 1;
                            break;
                        }
                    }
                }
            }
        }

        // dump for debug
        //cout << "dp1" << endl;
        //dump(dp1);
        //cout << "dp2" << endl;
        //dump(dp2);

        // Sample dump for input ("aabcc", "dbbca", "aadbbcbcac"):
        //dp1
        //1 0 0 0 0 0
        //1 0 0 0 0 0
        //0 1 1 0 1 0
        //0 0 1 0 1 0
        //0 0 0 0 0 1
        //0 0 0 0 0 1
        //dp2
        //0 0 0 0 0 0
        //0 0 0 0 0 0
        //1 1 1 1 0 0
        //0 1 0 0 0 0
        //0 0 1 1 1 0
        //0 0 0 0 0 1

        return dp1[0][0] || dp2[0][0];
    }

private:
    void dump(const vector<vector<int>>& dp1) {
        for (int o1 = 0; o1 < dp1.size(); ++o1) {
            for (int o2 = 0; o2 < dp1[0].size(); ++o2) {
                cout << dp1[o1][o2] << " ";
            }
            cout << endl;
        }
    }
};

// Combine matrix dp1 and dp2 from previous implementation.
class Solution_dp_v2 {
public:
    bool isInterleave(const string& s1, const string& s2, const string& s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3)
            return false;

        // dp[o1][o2] = isInterLeave(s1[o1:], s2[o2:], s3[o3:])
        vector<vector<int>> dp(len1 + 1, vector<int>(len2 + 1, -1));

        for (int o1 = len1; o1 >= 0; --o1) {
            for (int o2 = len2; o2 >= 0; --o2) {
                // determine dp[o1][o2].

                int o3 = o1 + o2;

                if (o1 == len1 && o2 == len2) {
                    dp[o1][o2] = true;
                }
                else if (o1 == len1) {
                    dp[o1][o2] = dp[o1][o2 + 1] && s2[o2] == s3[o3];
                }
                else if (o2 == len2) {
                    dp[o1][o2] = dp[o1 + 1][o2] && s1[o1] == s3[o3];
                }
                else {
                    // try first putting char s1[o1] to the destination string
                    if (dp[o1 + 1][o2] && s1[o1] == s3[o3])
                        dp[o1][o2] = 1;
                    // try first putting char s2[o2] to the destination string
                    else if (dp[o1][o2 + 1] && s2[o2] == s3[o3])
                        dp[o1][o2] = 1;
                    else
                        dp[o1][o2] = 0;
                }
            }
        }

        // dump for debug
        //cout << "dp" << endl;
        //dump(dp);

        // Sample dump for input ("aabcc", "dbbca", "aadbbcbcac"):
        //dp
        //1 0 0 0 0 0
        //1 0 0 0 0 0
        //1 1 1 1 1 0
        //0 1 1 0 1 0
        //0 0 1 1 1 1
        //0 0 0 0 0 1

        return dp[0][0];
    }

private:
    void dump(const vector<vector<int>>& dp) {
        for (int o1 = 0; o1 < dp.size(); ++o1) {
            for (int o2 = 0; o2 < dp[0].size(); ++o2) {
                cout << dp[o1][o2] << " ";
            }
            cout << endl;
        }
    }
};

// Revise previous implementation.
class Solution_dp_v2_r1 {
public:
    bool isInterleave(const string& s1, const string& s2, const string& s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3)
            return false;

        // dp[o1][o2] = isInterLeave(s1[o1:], s2[o2:], s3[o3:])
        vector<vector<bool>> dp(len1 + 1, vector<bool>(len2 + 1, false));

        for (int o1 = len1; o1 >= 0; --o1) {
            for (int o2 = len2; o2 >= 0; --o2) {
                // determine dp[o1][o2].

                int o3 = o1 + o2;

                if (o1 == len1 && o2 == len2) {
                    dp[o1][o2] = true;
                }
                // try first putting char s1[o1] to the destination string
                else if (o1 < len1 && dp[o1 + 1][o2] && s1[o1] == s3[o3]) {
                    dp[o1][o2] = true;
                }
                // try first putting char s2[o2] to the destination string
                else if (o2 < len2 && dp[o1][o2 + 1] && s2[o2] == s3[o3]) {
                    dp[o1][o2] = true;
                }
            }
        }

        return dp[0][0];
    }
};

// Reduce the space complexity from O(len1 * len2) to O(len2).
class Solution_dp_v3 {
public:
    bool isInterleave(const string& s1, const string& s2, const string& s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3)
            return false;

        // dp[o1][o2] = isInterLeave(s1[o1:], s2[o2:], s3[o3:])
        // Because for any dp[o1][o2], only the two cells that are to its right or bottom are needed,
        // we can reduce in-memory grid to a single row:
        // - row[0:o2] corresponds to dp[o1+1][0:o2],
        // - row[o2:] corresponds to dp[o1][o2:].
       vector<bool> row(len2 + 1, false);

        for (int o1 = len1; o1 >= 0; --o1) {
            for (int o2 = len2; o2 >= 0; --o2) {
                // determine dp[o1][o2].

                int o3 = o1 + o2;

                if (o1 == len1 && o2 == len2) {
                    row[o2] = true;
                }
                // try first putting char s1[o1] to the destination string
                else if (o1 < len1 && row[o2] && s1[o1] == s3[o3]) {
                    row[o2] = true;
                }
                // try first putting char s2[o2] to the destination string
                else if (o2 < len2 && row[o2 + 1] && s2[o2] == s3[o3]) {
                    row[o2] = true;
                }
                else {
                    row[o2] = false; // overwrite the legacy value
                }
            }
        }

        return row[0];
    }
};

using Solution = Solution_dp_v3;

int main() {

    std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();

    int ans = 0;

    assert(!Solution().isInterleave("a", "b", "a"));
    assert(Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    assert(!Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    assert(Solution().isInterleave("", "", ""));

    std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

    cout << "Elapse: " << (end - begin) / 1ms << " ms" << endl;
    return 0;
}

