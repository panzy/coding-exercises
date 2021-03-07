/*
1117. Building H2O
https://leetcode.com/problems/building-h2o/
--
Zhiyong
2021-03-06
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
using namespace std;

class H2O {
    // Numbers of hydrogen and oxygen atoms been held.
    int cntH, cntO;

    // For each H2O molecule, releaseProgress is initialized to 3 and is reduced to 0.
    // After it reaches 0, cntH and cntO will be reset.
    int releaseProgress;

    mutex m;
    condition_variable cv;

    // Release an atom.
    // For each H2O molecule, there are three threads waiting.
    // Note that we can't reduce cntH or cntO one by one, because that would prevent some threads from seeing the whole molecule.
    // So the releaseProgress variable was introduced, and only the last awaken thread is responsible for reseting the counters.
    void releaseAtom() {
        if (--releaseProgress == 0) {
            cntH = cntO = 0;
            releaseProgress = 3;
            cv.notify_all();
        }
    }
public:
    H2O() : cntH{ 0 }, cntO{ 0 }, releaseProgress{ 3 } {
        
    }

    void hydrogen(function<void()> releaseHydrogen) {
        unique_lock<mutex> lk(m);

        // wait for a free atom slot
        cv.wait(lk, [this] { return cntH < 2; });
        ++cntH;
        cv.notify_all();

        // wait for the molecule
        cv.wait(lk, [this] { return cntH == 2 && cntO == 1; });

        // releaseHydrogen() outputs "H". Do not change or remove this line.
        releaseHydrogen();

        releaseAtom();
    }

    void oxygen(function<void()> releaseOxygen) {
        unique_lock<mutex> lk(m);

        // wait for a free atom slot
        cv.wait(lk, [this] { return cntO < 1; });
        ++cntO;
        cv.notify_all();

        // wait for the molecule
        cv.wait(lk, [this] { return cntH == 2 && cntO == 1; });

        // releaseOxygen() outputs "O". Do not change or remove this line.
        releaseOxygen();

        releaseAtom();
    }
};

int main() {
    
    const int n = 5;

    auto releaseH = [] { cout << "H"; };
    auto releaseO = [] { cout << "O"; };

    H2O obj;

    thread t[n * 3];
    for (int i = 0; i < n * 3; ++i) {
        if (i < n * 2)
            t[i] = thread([&] { obj.hydrogen(releaseH); });
        else
            t[i] = thread([&] { obj.oxygen(releaseO); });
    }

    for (int i = 0; i < n * 3; ++i) {
        t[i].join();
    }
}
