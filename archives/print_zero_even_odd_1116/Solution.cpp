/*
1116. Print Zero Even Odd
https://leetcode.com/problems/print-zero-even-odd/
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
using namespace std;

class ZeroEvenOdd {
private:
    int n;

    // n = 4
    // i: 12345678
    // x: 01020304
    int i;

    mutex m;
    condition_variable cv;

public:
    ZeroEvenOdd(int n) {
        this->n = n;
        i = 1;
    }

    // printNumber(x) outputs "x", where x is an integer.
    void zero(function<void(int)> printNumber) {
        unique_lock<mutex> lk(m);
        while (i < 2 * n) {
            cv.wait(lk, [this] { return i % 2 == 1; });
            printNumber(0);
            ++i;
            cv.notify_all();
        }
    }

    void even(function<void(int)> printNumber) {
        unique_lock<mutex> lk(m);
        int maxEven = (n % 2 == 0 ? n : n - 1);
        while (i <= 2 * maxEven) {
            cv.wait(lk, [this] { return i % 4 == 0; });
            printNumber(i / 2);
            ++i;
            cv.notify_all();
        }
    }

    void odd(function<void(int)> printNumber) {
        unique_lock<mutex> lk(m);
        int maxOdd = (n % 2 == 0 ? n - 1 : n);
        while (i <= 2 * maxOdd) {
            cv.wait(lk, [this] { return (i + 2) % 4 == 0; });
            printNumber(i / 2);
            ++i;
            cv.notify_all();
        }
    }
};

int main() {
    int n = 9;
    ZeroEvenOdd z(n);

    auto printNumber = [](int x) { cout << x; };

    thread t0([&] { z.zero(printNumber); });
    thread t1([&] { z.odd(printNumber); });
    thread t2([&] { z.even(printNumber); });

    t0.join();
    t1.join();
    t2.join();

    cout << endl;
}
