//
// 150. Evaluate Reverse Polish Notation
// https://leetcode.com/problems/evaluate-reverse-polish-notation/
// 
// 20 / 20 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 11.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      25/05/2021, 12:04:35
// LeetCode submit time: 1 minute ago
// Submission detail page: https://leetcode.com/submissions/detail/498127044//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int evalRPN(const vector<string>& tokens) {
        stack<int> operands;

        for (auto&& t : tokens) {
            if (t == "+" || t == "-" || t == "*" || t == "/") {
                int b = operands.top();
                operands.pop();
                int a = operands.top();
                operands.pop();

                if (t == "+") {
                    operands.push(a + b);
                }
                else if (t == "-") {
                    operands.push(a - b);
                }
                else if (t == "*") {
                    operands.push(a * b);
                }
                else if (t == "/") {
                    operands.push(a / b);
                }
            }
            else {
                operands.push(stoi(t));
            }
        }

        return operands.top();
    }
};
