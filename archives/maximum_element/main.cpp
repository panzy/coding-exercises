/* This program solves HackerRank problem Maximum Element
 * https://www.hackerrank.com/challenges/maximum-element/problem
 */
#include <cmath>
#include <cstdio>
#include <vector>
#include <stack>
#include <queue>
#include <unordered_map>
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    stack<int> stk;
    stack<int> maximums; // maximum element of each snapshot
    int n, cmd, arg;
    
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> cmd;
        
        if (cmd == 1) {
            // push a value
            cin >> arg;
            if (maximums.empty())
                maximums.push(arg);
            else
                maximums.push(max(maximums.top(), arg));
            stk.push(arg);
        } else if (cmd == 2) {
            // remove the previously pushed value
            stk.pop();
            maximums.pop();
        } else if (cmd == 3) {
            // print the maximal element
            cout << maximums.top() << endl;
        }
    }
    return 0;
}

