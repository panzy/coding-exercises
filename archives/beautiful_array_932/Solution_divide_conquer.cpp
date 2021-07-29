//
// 932. Beautiful Array
// https://leetcode.com/problems/beautiful-array/
// 
// 38 / 38 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 8.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      29/07/2021, 08:53:38
// LeetCode submit time: 21 hours, 6 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/529690145//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/*
Divide & Conquer approach:

1. split the numbers into two groups: odds and evens;
2. arrange the odds following this process:
    a) let A = beautifulArray(odds.size());
        e.g. if odds = {1 3 5 7}, then A = beautifulArray(4) = {1 3 2 4}
    b) treat elements of A as indices, mapping them to the original odds
        e.g. A.map(i => 2 * i - 1) = {1 5 3 7}
3. arrange the evens following this process:
    a) let B = beautifulArray(evens.size());
    b) treat elements of B as indices, mapping them to the original evens
        e.g. B.map(i => 2 * i) = {1 3 2 4}x2 = {2 6 4 8}
4. concatenate the two results   
*/
class Solution {
    vector<vector<int>> cache;
public:
    vector<int> beautifulArray(int n) {
        if (n == 1) return {1};
        if (n == 2) return {1, 2};
        
        // init cache
        if (cache.empty()) {
            cache.resize(n + 1);
        }
        
        if (cache[n].size())
            return cache[n];
        
        auto leftHalf = beautifulArray((n + 1) / 2);
        for (int& i : leftHalf)
            i = 2 * i - 1;
        
        auto rightHalf = beautifulArray(n / 2);
        for (int& i : rightHalf)
            i = 2 * i;
        
        cache[n].reserve(n);
        move(begin(leftHalf), end(leftHalf), back_inserter(cache[n]));
        move(begin(rightHalf), end(rightHalf), back_inserter(cache[n]));
        
        return cache[n];
    }
};