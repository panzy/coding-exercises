/*
630. Course Schedule III
https://leetcode.com/problems/course-schedule-iii/
--
Zhiyong
2021-05-02
*/
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
During iteration, say I want to add the current course, currentTotalTime being
total time of all courses taken till now, but adding the current course might
exceed my deadline or it doesn’t.

1. If it doesn’t, then I have added one new course. Increment the
currentTotalTime with duration of current course.

2. If it exceeds deadline, I can swap current course with current courses that
has biggest duration.
- No harm done and I might have just reduced the currentTotalTime, right?
- What preprocessing do I need to do on my course processing order so that this
swap is always legal?
*/

class Solution {
public:
    int scheduleCourse(vector<vector<int>>& courses) {
        // sort by last day
        sort(courses.begin(), courses.end(), [](const vector<int>& c1, const vector<int>& c2) { return c1[1] < c2[1]; });

        priority_queue<int> Q;
        int totalTime = 0;
        for (auto&& c : courses) {
            if (totalTime + c[0] <= c[1]) {
                Q.push(c[0]);
                totalTime += c[0];
            }
            else if (!Q.empty() && Q.top() > c[0] && totalTime - Q.top() + c[0] <= c[1]) {
                totalTime += c[0] - Q.top();
                Q.pop();
                Q.push(c[0]);
            }
        }
        return Q.size();
    }
};

int main() {
    int ans = 0;

    {
        vector<vector<int>> courses = { {100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200} };
        assert(3 == (ans = Solution().scheduleCourse(courses)));
    }

    {
        vector<vector<int>> courses = { {200, 1300}, {2000, 3200}, {1000, 1250}, {100, 200} };
        assert(3 == (ans = Solution().scheduleCourse(courses)));
    }

    {
        vector<vector<int>> courses = { {1, 2} };
        assert(1 == (ans = Solution().scheduleCourse(courses)));
    }

    {
        vector<vector<int>> courses = { {3, 2}, {4, 3} };
        assert(0 == (ans = Solution().scheduleCourse(courses)));
    }
}
