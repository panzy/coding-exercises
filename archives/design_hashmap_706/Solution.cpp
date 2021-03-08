/*
706. Design HashMap
https://leetcode.com/problems/design-hashmap/
--
Zhiyong
2021-03-07
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
#include <cassert>
#include <bitset>
using namespace std;

class MyHashMap {
public:
    /** Initialize your data structure here. */
    MyHashMap() : data(MOD, vector<pair<int, int>>{}) {
        
    }
    
    /** value will always be non-negative. */
    void put(int key, int value) {
        int idx = key % MOD;

        // If the key exists, update
        for (auto& p : data[idx]) {
            if (p.first == key) {
                p.second = value;
                return;
            }
        }

        // otherwise, push back
        data[idx].push_back({ key, value });
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    int get(int key) {
        for (auto& p : data[key % MOD]) {
            if (p.first == key)
                return p.second;
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    void remove(int key) {
        auto& lst = data[key % MOD];
        for (auto i = lst.begin(); i != lst.end(); ++i) {
            if (i->first == key) {
                lst.erase(i);
                return;
            }
        }
    }
private:
    const static int MOD = 10003;
    vector<vector<pair<int, int>>> data;
};

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap* obj = new MyHashMap();
 * obj->put(key,value);
 * int param_2 = obj->get(key);
 * obj->remove(key);
 */

int main() {
    int ans;
    MyHashMap m;

    m.put(1, 10);
    m.put(2, 20);
    m.put(2 + 10003, 23);
    assert(10 == (ans = m.get(1)));
    assert(20 == (ans = m.get(2)));
    assert(23 == (ans = m.get(2 + 10003)));

    m.put(2, 21); // update
    assert(10 == (ans = m.get(1)));
    assert(21 == (ans = m.get(2)));
    assert(23 == (ans = m.get(2 + 10003)));

    m.remove(2);
    assert(10 == (ans = m.get(1)));
    assert(-1 == (ans = m.get(2)));
    assert(23 == (ans = m.get(2 + 10003)));

    m.remove(2 + 10003);
    assert(10 == (ans = m.get(1)));
    assert(-1 == (ans = m.get(2)));
    assert(-1 == (ans = m.get(2 + 10003)));

    m.put(2 + 10003, 23);
    assert(10 == (ans = m.get(1)));
    assert(-1 == (ans = m.get(2)));
    assert(23 == (ans = m.get(2 + 10003)));
}

