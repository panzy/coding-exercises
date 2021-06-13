/**
 * 871. Minimum Number of Refueling Stops
 * https://leetcode.com/problems/minimum-number-of-refueling-stops/
 * 
 * --
 * Created by Zhiyong 
 * 2021-06-12, 2:35:28 p.m.
 */
#include <array>
#include <chrono>
#include <string>
#include <sstream>
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <bitset>
#include <algorithm>
#include <functional>
#include <cassert>
using namespace std;

/*
2D Dynamic Programming.

TODO: 1-D DP is possible; quote from official solution:

    Let's determine dp[i], the farthest location we can get to using i refueling
    stops. This is motivated by the fact that we want the smallest i for which
    dp[i] >= target.

198 / 198 test cases passed.
Status: Accepted
Runtime: 180 ms
Memory Usage: 62.3 MB
*/
class Solution_DP_2D {
public:
    int minRefuelStops(int target, int startFuel, const vector<vector<int>>& stations) {
        const vector<vector<int>>& S = stations;
        int n = S.size();

        if (n == 0)
            return startFuel >= target ? 0 : -1;

        // remaining fuel at station #0
        int fuel0 = startFuel - S[0][0];

        if (fuel0 < 0)
            return -1;

        // dp[i][c] = the minimum start fuel for a car starting at the i-th (0-based) station
        // to be able reach the target with c refuelings
        // the answer is the minium x that dp[0][x] <= startFuel - stations[0][0].
        vector<vector<int>> dp(n + 1, vector<int>(n + 1, 0));

        // let staion #n be the target
        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; --i) {
            for (int c = 0; c <= n; ++c) {
                if (i + c > n) { // have no chance to refuel so many times
                    dp[i][c] = dp[i][c - 1];
                    continue;
                }

                int nextLoc = i + 1 < n ? S[i + 1][0] : target;
                int distToNext = nextLoc - S[i][0];

                // at this point, only one decision has to be made -- stop and refuel at #i or not?
                if (c == 0) {
                    dp[i][c] = distToNext + dp[i + 1][c];
                }
                else {
                    dp[i][c] = min(
                        distToNext + dp[i + 1][c],
                        max(0, distToNext + dp[i + 1][c - 1] - S[i][1])
                    );
                }
            }
        }

        for (int c = 0; c <= n; ++c) {
            if (dp[0][c] <= fuel0)
                return c;
        }
        
        return -1;
    }
};

/* BFS node */
struct State {
    int loci; // index of last refuel station, or -1
    int loc; // curent location
    int fuel; // current fuel level
    int refuels; // refuel times
};

/*
BFS.

120 / 198 test cases passed.
Status: Time Limit Exceeded
*/
class Solution_BFS {
public:
    int minRefuelStops(int target, int startFuel, const vector<vector<int>>& stations) {
        int n = stations.size();
        queue<State> Q;

        Q.push({ -1, 0, startFuel, 0 });

        while (!Q.empty()) {
            auto s = Q.front();
            Q.pop();

            if (s.loc + s.fuel >= target)
                return s.refuels;

            for (int i = s.loci + 1; i < n; ++i) {
                auto&& st = stations[i];
                if (s.loc + s.fuel >= st[0]) {
                    Q.push({ i, st[0], s.fuel - (st[0] - s.loc) + st[1], s.refuels + 1 });
                }
                else {
                    break;
                }
            }
        }

        return -1;
    }
};

/*
Recursion with memo.

XXX: It is bad that fuel level is encoded into memo key and make the cache hard to hit.

109 / 198 test cases passed.
Status: Time Limit Exceeded
*/
class Solution_recursion_with_memo {
public:
    int minRefuelStops(int _target, int _startFuel, const vector<vector<int>>& _stations) {
        n = _stations.size();
        target = _target;
        stations = _stations;
        memo.clear();

        return go(-1, _startFuel);
    }
    
private:
    int n;
    int target;
    vector<vector<int>> stations;
    unordered_map<int64_t, int> memo;
    
    int go(int startStation, int startFuel) {
        int loc = startStation == -1 ? 0 : stations[startStation][0];

        if (loc + startFuel >= target)
            return 0;

        // max station idx = 500, so shifting 9 bits are enough
        int64_t key = ((int64_t)startFuel << 10) + startStation;
        if (memo.count(key) > 0)
            return memo[key];

        int ans = -1;

        for (int i = startStation + 1; i < n; ++i) {
            if (loc + startFuel >= stations[i][0]) {
                int newAns = go(i, startFuel - (stations[i][0] - loc) + stations[i][1]);
                if (newAns != -1 && (ans == -1 || ans > newAns))
                    ans = newAns;
            }
            else {
                break;
            }
        }

        memo[key] = ans == -1 ? -1 : 1 + ans;
        return memo[key];
    }
};


using Solution = Solution_DP_2D;

int main() {

    std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();

    int ans = 0;

    if (true) {
        assert(-1 == (ans = Solution().minRefuelStops(
            1000,
            83,
            { {25, 27}, {36, 187}, {140, 186}, {378, 6}, {492, 202}, {517, 89}, {579, 234}, {673, 86}, {808, 53}, {954, 49} }
        )));
    }

    if (true) {
        assert(32 == (ans = Solution().minRefuelStops(
            1000, 36,
            { {7, 13}, {10, 11}, {12, 31}, {22, 14}, {32, 26}, {38, 16}, {50, 8}, {54, 13}, {75, 4}, {85, 2}, {88, 35}, {90, 9}, {96, 35}, {103, 16}, {115, 33}, {121, 6}, {123, 1}, {138, 2}, {139, 34}, {145, 30}, {149, 14}, {160, 21}, {167, 14}, {188, 7}, {196, 27}, {248, 4}, {256, 35}, {262, 16}, {264, 12}, {283, 23}, {297, 15}, {307, 25}, {311, 35}, {316, 6}, {345, 30}, {348, 2}, {354, 21}, {360, 10}, {362, 28}, {363, 29}, {367, 7}, {370, 13}, {402, 6}, {410, 32}, {447, 20}, {453, 13}, {454, 27}, {468, 1}, {470, 8}, {471, 11}, {474, 34}, {486, 13}, {490, 16}, {495, 10}, {527, 9}, {533, 14}, {553, 36}, {554, 23}, {605, 5}, {630, 17}, {635, 30}, {640, 31}, {646, 9}, {647, 12}, {659, 5}, {664, 34}, {667, 35}, {676, 6}, {690, 19}, {709, 10}, {721, 28}, {734, 2}, {742, 6}, {772, 22}, {777, 32}, {778, 36}, {794, 7}, {812, 24}, {813, 33}, {815, 14}, {816, 21}, {824, 17}, {826, 3}, {838, 14}, {840, 8}, {853, 29}, {863, 18}, {867, 1}, {881, 27}, {886, 27}, {894, 26}, {917, 3}, {953, 6}, {956, 3}, {957, 28}, {962, 33}, {967, 35}, {972, 34}, {984, 8}, {987, 12} }
        )));
    }

    if (true) { // this test case defeats the recursion + memo approach with startStation and startFuel being the memo key.
        assert(16 == (ans = Solution().minRefuelStops(
            1000000000,
            96590732,
            { {25986132, 35042654}, {73010513, 42446503}, {89554554, 71260014}, {144931234, 41477700}, {174637755, 9245521}, {237634584, 84422728}, {260452156, 12287833}, {294142935, 90978823}, {332466798, 76939654}, {364139626, 45639235}, {435155117, 94859038}, {499476199, 14940036}, {515199109, 29165737}, {550477434, 8924797}, {611366308, 16169076}, {626966312, 42002901}, {644883359, 6410389}, {671788734, 68556639}, {731324901, 42929741}, {759715433, 39162570}, {797990745, 40383473}, {871012827, 29418685}, {933076323, 80084684}, {961242739, 16495102}, {986834945, 68736799} }
        )));
    }

    if (true) {
        assert(7 == (ans = Solution().minRefuelStops(
            1000000000,
            145267354,
            { {5510987, 84329695}, {10682248, 76273791}, {56227783, 136858069}, {91710087, 18854476}, {111148380, 127134059}, {165982642, 122930004}, {184216180, 124802819}, {217578071, 7123113}, {233719001, 95862544}, {339631786, 7676497}, {349762649, 136128214}, {403119403, 21487501}, {423890164, 61095325}, {424072629, 50415446}, {572994480, 13561367}, {609623597, 69207102}, {662818314, 84432133}, {678679727, 20403175}, {682325369, 14288077}, {702137485, 6426204}, {716318901, 47662322}, {738137702, 129579140}, {761962118, 23765733}, {820353983, 70497719}, {895811889, 75533360} }
        )));
    }

    {
        assert(4 == (ans = Solution().minRefuelStops(1000, 299,
            { {13, 21}, {26, 115}, {100, 47}, {225, 99}, {299, 141}, {444, 198}, {608, 190}, {636, 157}, {647, 255}, {841, 123} }
        )));
    }

    {
        assert(0 == (ans = Solution().minRefuelStops(1, 1, {})));
    }

    {
        assert(-1 == (ans = Solution().minRefuelStops(100, -1, { {10, 100} })));
    }

    {
        assert(2 == (ans = Solution().minRefuelStops(100, 10, { {10, 60}, {20, 30}, {30, 30}, {60, 40} })));
    }

    {
        assert(0 == (ans = Solution().minRefuelStops(999, 1000, { {5, 100}, {997, 100}, {998, 100} })));
    }

    {
        vector<vector<int>> stations {
            {47, 220}, {65, 1}, {98, 113}, {126, 196}, {186, 218}, {320, 205},
            {686, 317}, {707, 325}, {754, 104}, {781, 105}
        };
        assert(4 == (ans = Solution().minRefuelStops(1000, 83, stations)));
    }

    std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

    cout << "Elapse: " << (end - begin) / 1ms << " ms" << endl;
    return 0;
}

