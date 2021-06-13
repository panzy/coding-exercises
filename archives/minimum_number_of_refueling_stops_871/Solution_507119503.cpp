//
// 871. Minimum Number of Refueling Stops
// https://leetcode.com/problems/minimum-number-of-refueling-stops/
// 
// 198 / 198 test cases passed.	Status: Accepted
// Runtime: 20 ms
// Memory Usage: 17.1 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      12/06/2021, 23:41:13
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/507119503//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/* Greedy Approach inspired by the official solution #2 */
class Solution {
public:
    int minRefuelStops(int target, int startFuel, const vector<vector<int>>& stations) {
        // unused refuel chances. item = volume
        // only refuel when we have to, and when we do, refuel at the passed
        // station with maximum volume -- remember that the tank is infinite.
        priority_queue<int> chances;

        int n = stations.size();
        int fuel = startFuel;
        int loc = 0;
        int stops = 0;

        for (int i = 0; i <= n; ++i) {
            auto&& s = i < n ? stations[i] : vector<int>{target, 0};
            fuel -= s[0] - loc;

            while (fuel < 0 && !chances.empty()) { // refuel
                fuel += chances.top();
                chances.pop();
                ++stops;
            }

            if (fuel < 0)
                return -1;

            chances.push(s[1]);
            loc = s[0];
        }

        return stops;
    }
};
