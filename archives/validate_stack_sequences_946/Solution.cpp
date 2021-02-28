/* 946. Validate Stack Sequences
 * https://leetcode.com/problems/validate-stack-sequences/
 * 2021-02-27
 */
class Solution {
public:
    bool validateStackSequences(const vector<int>& pushed, const vector<int>& popped) {
        //simulate
        
        stack<int> st;
        
        int popPos = 0;
        
        for (int i = 0; i < pushed.size(); ++i) {
            st.push(pushed[i]);
            while (st.size() && st.top() == popped[popPos]) {
                st.pop();
                ++popPos;
            }
        }
        
        return st.empty();
    }
};
