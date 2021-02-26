/*
1401. Circle and Rectangle Overlapping
https://leetcode.com/problems/circle-and-rectangle-overlapping/
--
Zhiyong
2021-02-26
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
#include <algorithm>
#include <functional>
using namespace std;


class Solution {
public:
    bool between(int x, int a, int b) {
        return a <= x && x <= b;
    }

    int dist2(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    // XXX this approach is complicated, see here for a clever & concise solution:
    // https://leetcode.com/problems/circle-and-rectangle-overlapping/discuss/861060/Python-math-solution-O(1)
    bool checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        if (between(x_center - radius, x1, x2) &&
            between(x_center + radius, x1, x2) &&
            between(y_center - radius, y1, y2) &&
            between(y_center + radius, y1, y2)) {
            // rect contains circle
            return true;
        }
        if (between(x1, x_center - radius, x_center + radius) &&
            between(x2, x_center - radius, x_center + radius) &&
            between(y1, y_center - radius, y_center + radius) &&
            between(y2, y_center - radius, y_center + radius) &&
            dist2(x1, y1, x_center, y_center) <= radius * radius) {
            // circle roughly contains rect
            return true;
        }

        if (x_center - radius <= x2 && x_center + radius >= x2) {
            // check intersections of right edge and circle
            int tmp = sqrt(radius * radius - (x2 - x_center) * (x2 - x_center));
            if (between(y_center + tmp, y1, y2) || between(y_center - tmp, y1, y2))
                return true;
        }
        if (x_center - radius <= x1 && x_center + radius >= x1) {
            // check intersections of left edge and circle
            int tmp = sqrt(radius * radius - (x1 - x_center) * (x1 - x_center));
            if (between(y_center + tmp, y1, y2) || between(y_center - tmp, y1, y2))
                return true;
        }
        if (y_center - radius <= y2 && y_center + radius >= y2) {
            // check intersections of top edge and circle
            int tmp = sqrt(radius * radius - (y2 - y_center) * (y2 - y_center));
            if (between(x_center + tmp, x1, x2) || between(x_center - tmp, x1, x2))
                return true;
        }
        if (y_center - radius <= y1 && y_center + radius >= y1) {
            // check intersections of bottom edge and circle
            int tmp = sqrt(radius * radius - (y1 - y_center) * (y1 - y_center));
            if (between(x_center + tmp, x1, x2) || between(x_center - tmp, x1, x2))
                return true;
        }
        return false;
    }
};

int main() {
    // Input: radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
    // Output: true
    _ASSERT(Solution().checkOverlap(1, 0, 0, 1, -1, 3, 1));

    // Input: radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
    // Output: true
    _ASSERT(Solution().checkOverlap(1, 0, 0, -1, 0, 0, 1));

    // Input: radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3
    // Output: true
    _ASSERT(Solution().checkOverlap(1, 1, 1, -3, -3, 3, 3));

    // Input: radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
    // Output: false
    _ASSERT(!Solution().checkOverlap(1, 1, 1, 1, -3, 2, -1));

    _ASSERT(Solution().checkOverlap(100, 0, 0, 70, 70, 200, 200));
    _ASSERT(!Solution().checkOverlap(100, 0, 0, 71, 71, 200, 200));
    _ASSERT(!Solution().checkOverlap(100, 0, 0, 71, 71, 80, 80));

    _ASSERT(Solution().checkOverlap(100, 0, 0, 70, -200, 200, -70));
    _ASSERT(!Solution().checkOverlap(100, 0, 0, 71, -200, 200, -71));

    _ASSERT(Solution().checkOverlap(100, 0, 0, -200, -200, -70, -70));
    _ASSERT(!Solution().checkOverlap(100, 0, 0, -200, -200, -71, -71));
    _ASSERT(!Solution().checkOverlap(100, 0, 0, -80, -80, -71, -71));

    _ASSERT(Solution().checkOverlap(100, 0, 0, -200, 70, -70, 200));
    _ASSERT(!Solution().checkOverlap(100, 0, 0, -200, 71, -71, 200));

    return 0;
}
