/*
1115. Print FooBar Alternately
https://leetcode.com/problems/print-foobar-alternately/
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

class FooBar {
private:
    int n;
    int idx;
    mutex m;
    condition_variable cv;

public:
    FooBar(int n) {
        this->n = n;
        idx = 0;
    }

    void foo(function<void()> printFoo) {                
        
        for (int i = 0; i < n; i++) {
            unique_lock<mutex> lk(m);
            cv.wait(lk, [this]{ return idx % 2 == 0; });
            ++idx;
            
            // printFoo() outputs "foo". Do not change or remove this line.
            printFoo();
            
            cv.notify_one();
        }
    }

    void bar(function<void()> printBar) {
        
        for (int i = 0; i < n; i++) {
            unique_lock<mutex> lk(m);
            cv.wait(lk, [this]{ return idx % 2 == 1; });
            ++idx;
            
        	// printBar() outputs "bar". Do not change or remove this line.
        	printBar();
            
            cv.notify_one();
        }
    }
};
