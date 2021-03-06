/*
1195. Fizz Buzz Multithreaded
https://leetcode.com/problems/fizz-buzz-multithreaded/
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

class FizzBuzz {
private:
    int n;
    int progress;
    mutex m;
    condition_variable cv;

public:
    FizzBuzz(int n) {
        this->n = n;
        progress = 1;
    }

    // printFizz() outputs "fizz".
    void fizz(function<void()> printFizz) {
        for (int i = 1; i <= n; ++i) {
            if (i % 3 == 0 && i % 5 != 0) {
                // need to print. but wait till the progress has come.
                unique_lock<mutex> lk(m);
                cv.wait(lk, [this, i] { return progress == i; });
                printFizz();
                progress = i + 1;
                cv.notify_all();
            }
        }
    }

    // printBuzz() outputs "buzz".
    void buzz(function<void()> printBuzz) {
        for (int i = 1; i <= n; ++i) {
            if (i % 3 != 0 && i % 5 == 0) {
                // need to print. but wait till the progress has come.
                unique_lock<mutex> lk(m);
                cv.wait(lk, [this, i] { return progress == i; });
                printBuzz();
                progress = i + 1;
                cv.notify_all();
            }
        }
    }

    // printFizzBuzz() outputs "fizzbuzz".
	void fizzbuzz(function<void()> printFizzBuzz) {
        for (int i = 1; i <= n; ++i) {
            if (i % 3 == 0 && i % 5 == 0) {
                // need to print. but wait till the progress has come.
                unique_lock<mutex> lk(m);
                cv.wait(lk, [this, i] { return progress == i; });
                printFizzBuzz();
                progress = i + 1;
                cv.notify_all();
            }
        }
    }

    // printNumber(x) outputs "x", where x is an integer.
    void number(function<void(int)> printNumber) {
        for (int i = 1; i <= n; ++i) {
            if (i % 3 != 0 && i % 5 != 0) {
                // need to print. but wait till the progress has come.
                unique_lock<mutex> lk(m);
                cv.wait(lk, [this, i] { return progress == i; });
                printNumber(i);
                progress = i + 1;
                cv.notify_all();
            }
        }
    }
};

int main() {
    
    int n = 15;

    auto printFizz = [] { cout << "fizz,"; };
    auto printBuzz = [] { cout << "buzz,"; };
    auto printFizzBuzz = [] { cout << "fizzbuzz,"; };
    auto printNumber = [](int x) { cout << x << ","; };

    FizzBuzz obj(n);

    thread t1([&] { obj.fizz(printFizz); });
    thread t2([&] { obj.buzz(printBuzz); });
    thread t3([&] { obj.fizzbuzz(printFizzBuzz); });
    thread t4([&] { obj.number(printNumber); });

    t1.join();
    t2.join();
    t3.join();
    t4.join();
}
