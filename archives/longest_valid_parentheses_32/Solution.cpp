/* 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/
 * --
 * Zhiyong 2021-04-03
 */

class Solution {
public:
    // sample input: )()(()
    // sample input: )()(()()
    // sample input: ")()())"
    
    // sample input: "()()(()()()"
    // stack[i]:      2 2 62 2 2
    int longestValidParentheses(string s) {
        stack<int> opens; // [i] = length of nested valid substring of this '('
        int ans = 0;
        int prevLen = 0; // length of previous valid substring that is followed immediately by the current one

        for (int i = 0; i < s.length(); ++i) {
            char c = s[i];

            if (c == '(') {
                opens.push(0);
            } else {
                if (opens.empty()) {
                    // unexpected ')'
                    prevLen = 0; // the previous valid substring is irrelevant from now on
                } else {
                    int currLen = opens.top() + 2; // the length closed by this ')'
                    opens.pop();
                    
                    if (opens.empty()) {
                        ans = max(ans, prevLen + currLen);
                        prevLen += currLen;
                    } else {
                        // concate continuous, nested parentheses
                        opens.top() += currLen;
                        ans = max(ans, opens.top());
                    }
                }
            }
        }
        
        return ans;
    }
};
