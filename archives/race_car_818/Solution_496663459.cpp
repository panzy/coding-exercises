//
// 818. Race Car
// https://leetcode.com/problems/race-car/
// 
// --
// Created by Zhiyong Pan
// 22/05/2021, 10:13:07
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int racecar(int target) {
        // BFS
        // { position, speed, instruction_count }
        queue<array<int64_t, 3>> Q;
        Q.push({ 0, 1, 0 });
        while (Q.front()[0] != target) {
            auto [p, s, i] = Q.front();
            Q.pop();
            // 'A'
            Q.push({ p + s, s * 2, i + 1 });
            // 'R'
            // Important: don't turn around unless it makes sense.
            if ((p + s > target && s > 0) || (p + s < target && s < 0)) {
                Q.push({ p, s > 0 ? -1 : 1, i + 1 });
            }
        }
        return Q.front()[2];
    }
};