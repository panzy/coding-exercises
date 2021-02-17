/* This program solves HackerRank problem Maximum Element
 * https://www.hackerrank.com/challenges/maximum-element/problem
 *
 * While the problem itself can be solved in a more clever way, using
 * a stack as the only container, this program demostrates how to
 * generally using a hash map to help a priority_queue to remove elements
 * other than the top. Notices that std::priority_queue does not have
 * erase() method.
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
    priority_queue<int, vector<int>> heap;
    stack<int> stk;
    unordered_map<int, int> freq;
    int n, cmd, arg;
    
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> cmd;
        
        if (cmd == 1) {
            // push a value
            cin >> arg;
            stk.push(arg);
            heap.push(arg);
            freq[arg]++;
        } else if (cmd == 2) {
            // remove the previously pushed value
            freq[stk.top()]--;
            stk.pop();
        } else if (cmd == 3) {
            // print the maximal element
            while (freq[heap.top()] <= 0)
                heap.pop();
            cout << heap.top() << endl;
        }
    }
    return 0;
}

