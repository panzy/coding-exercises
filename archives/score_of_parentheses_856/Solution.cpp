/*
856. Score of Parentheses
https://leetcode.com/problems/score-of-parentheses/
--
Zhiyong
2021-02-24
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
    // Approach 1, recursive.
    int scoreOfParentheses(const string& S, int begin, int end) {
        if (begin == end) return 0;

        // The stack top is the position of the most recent '('.
        stack<int> st;
        int score = 0;

        for (int i = begin; i < end; ++i) {
            if (S[i] == '(') {
                st.push(i);
            }
            else if (S[i] == ')') {
                if (st.size() == 1) {
                    if (st.top() + 1 == i) {
                        score++;
                    }
                    else {
                        score += 2 * scoreOfParentheses(S, st.top() + 1, i);
                    }
                    score += scoreOfParentheses(S, i + 1, end);
                    break;
                }
                st.pop();
            }
        }

        return score;
    }

public:
    // Approach 2, iterative.
    int scoreOfParentheses(const string& S) {

        // The stack top is the nested score of the most recent '('.
        stack<int> st;
        int score = 0;

        for (int i = 0; i < S.length(); ++i) {
            if (S[i] == '(') {
                st.push(0);
            }
            else if (S[i] == ')') {
                int nestedScore = st.top();
                st.pop();

                int currScore = 0;
                if (nestedScore == 0) {
                    currScore = 1;
                }
                else {
                    currScore = 2 * nestedScore;
                }

                if (st.empty()) {
                    score += currScore;
                }
                else {
                    st.top() += currScore; // accumulate outter's nested score.
                }
            }
        }

        return score;
    }
};

int main() {
    _ASSERT(1 == Solution().scoreOfParentheses("()"));
    _ASSERT(2 == Solution().scoreOfParentheses("(())"));
    _ASSERT(2 == Solution().scoreOfParentheses("()()"));
    _ASSERT(6 == Solution().scoreOfParentheses("(()(()))"));
    _ASSERT(968 == Solution().scoreOfParentheses("()()()()((()(((()))((())(((((()))()()())))))))"));
    return 0;
}
