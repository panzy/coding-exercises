/*
1114. Print in Order
https://leetcode.com/problems/print-in-order/
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

class Foo {
    mutex m;
    condition_variable cv;
    int progress = 0;
public:
    Foo() {
        
    }

    void first(function<void()> printFirst) {
        unique_lock<mutex> lk(m);
        ++progress;
        
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        
        cv.notify_all();
    }

    void second(function<void()> printSecond) {
        unique_lock<mutex> lk(m);
        while (progress != 1)
            cv.wait(lk);
        ++progress;

        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        
        cv.notify_all();
    }

    void third(function<void()> printThird) {
        unique_lock<mutex> lk(m);
        while (progress != 2)
            cv.wait(lk);
        ++progress;

        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};
