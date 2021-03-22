/*
Simple Text Editor
https://www.hackerrank.com/challenges/simple-text-editor/problem

TODO: This program has bugs. It only passes 4/16 of the test cases.
--
Zhiyong
2021-02-25
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
#include <optional>
#include <cassert>
using namespace std;

#ifndef _ASSERT // No _ASSERT on Linux
#define _ASSERT assert
#endif // !_ASSERT


struct Cursor {
    int bufPos;
    int nextAppendingPos; // index of the appending in the action array.
    int nextAppendingOffset; // not start from the first char of that appending word?
};

struct Action {
    int type; // 1=append, 2=delete
    Cursor before;
    string w; // the appended string if this is an appending
    int k; // the number of deleted chars if this is a deletion
};

class Solution {
    char* buf = new char[(int)1e6 + 1]{};
    Cursor tail{ 0, 0, 0 };

    // tuple = { action type, before buf pos, before actions count, appended string, deleted count }
    vector<Action> actions;

    bool verbose = true;

    void dump() {
        cout << '#' << actions.size() << ' ' << string(buf, buf + tail.bufPos) << ' ' 
            << tail.bufPos << ',' << tail.nextAppendingPos << ',' << tail.nextAppendingOffset;

        auto a = actions.back();
        if (a.type == 1) {
            cout << "    // +" << a.w;
        }
        else if (a.type == 2) {
            cout << "    // -" << a.k;
        }

        cout << endl;
    }
public:
    ~Solution() {
        delete[] buf;
    }

    void append(const string& w) {
        actions.push_back({ 1, tail, w, 0 });
        copy(w.begin(), w.end(), buf + tail.bufPos);
        tail.bufPos += w.length();
        tail.nextAppendingPos = actions.size();
        tail.nextAppendingOffset = 0;

        if (verbose) dump();
    }

    void del(int k) {
        Action a{ 2, tail, "", k };

        int r = k;
        while (r > 0) {
            if (tail.nextAppendingOffset >= r) {
                tail.nextAppendingOffset -= r;
                r = 0;
            }
            else if (tail.nextAppendingOffset > 0) {
                r -= tail.nextAppendingOffset;
                --tail.nextAppendingPos;
                tail.nextAppendingOffset = actions[tail.nextAppendingPos].w.length();
            }
            else {
                --tail.nextAppendingPos;
                tail.nextAppendingOffset = actions[tail.nextAppendingPos].w.length();
            }
        }

        actions.push_back(a);
        tail.bufPos -= k;
        if (verbose) dump();
    }

    void undo() {
        if (actions.empty()) return;

        auto a = actions.back();
        actions.pop_back();

        if (a.type == 1) {
            // undo an appending
            tail = a.before;
        }
        else if (a.type == 2) {
            // undo a deletion
            int r = a.k;

            while (r > 0) {
                if (tail.nextAppendingOffset < actions[tail.nextAppendingPos].w.length()) {
                    buf[tail.bufPos++] = actions[tail.nextAppendingPos].w[tail.nextAppendingOffset++];
                    --r;
                }
                if (tail.nextAppendingOffset == actions[tail.nextAppendingPos].w.length()) {
                    do {
                        ++tail.nextAppendingPos;
                    } while (tail.nextAppendingPos < actions.size() && actions[tail.nextAppendingPos].type == 2);
                    tail.nextAppendingOffset = 0;
                }
            }

            _ASSERT(tail.bufPos == a.before.bufPos);
            _ASSERT(tail.nextAppendingPos == a.before.nextAppendingPos);
            _ASSERT(tail.nextAppendingOffset == a.before.nextAppendingOffset);
        }
        if (verbose) dump();
    }

    char get(int kth) {
        return buf[kth - 1];
    }
};

int main() {
    int n, a, k;
    string w;
    Solution s;

    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> a;
        if (a == 1) {
            cin >> w;
            s.append(w);
        }
        else if (a == 2) {
            cin >> k;
            s.del(k);
        }
        else if (a == 3) {
            cin >> k;
            cout << s.get(k) << endl;
        }
        else if (a == 4) {
            s.undo();
        }
    }

    return 0;
}
