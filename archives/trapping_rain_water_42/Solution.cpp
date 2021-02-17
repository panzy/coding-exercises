/*
42. Trapping Rain Water
https://leetcode.com/problems/trapping-rain-water/

This solution is correct and fast. But it seems to be overcomplicated compared to
LeetCode official solutions (there are at least 3 acceptable approaches).
--
Zhiyong, 2021-02-17
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
using namespace std;

class Solution {
private:
public:
    int trap(vector<int>& height) {
        const int n = height.size();

		// Bowl volumes. Element = { left edge position, volume }
		stack<pair<int, int>> volumes;

        // Left edges of potential bowls. Element = { left edge position, sum of heights not including the edge }
        // Only the top element's sum value is guaranteed correct.
        stack<pair<int, int>> edges;

        edges.push({ 0, 0 });

        for (int i = 1; i < n; ++i) {

            if (height[i - 1] < height[i]) {
                // There is at least one bowl ending at i.
                // Enumerate there bowls.
                bool found = false;
                while (!edges.empty()) {
                    auto [j, v] = edges.top();

					// Found a new bowl, [j, i].
                    if (i - j - 1 > 0) {
						// Drop the nested bowls.
						while (!volumes.empty() && volumes.top().first >= j)
							volumes.pop();
						volumes.push({ j, min(height[j], height[i]) * (i - j - 1) - v });
                        found = true;
                    }

                    if (height[j] >= height[i]) {
                        // A bow ending at i can not go beyond j.
                        break;
                    }
                    else {
                        // This edge, j, has been covered by i.
                        edges.pop();
                        if (!edges.empty())
                            edges.top().second += height[j] + v;
                    }
                }
            }

            if (height[i] > 0)
				edges.push({ i, 0 });
        }

        int sum = 0;
        while (!volumes.empty()) {
            sum += volumes.top().second;
            volumes.pop();
        }
        return sum;
    }
};

int main() {
    { // test case #118
		vector<int> heights = { 6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3 };
        int res = Solution().trap(heights);
        _ASSERT(83 == res);
    }
    {
		vector<int> heights = { 0,1,0,2,1,0,1,3,2,1,2,1 };
        _ASSERT(6 == Solution().trap(heights));
    }
    {
		vector<int> heights = { 5,4,1,2 };
        _ASSERT(1 == Solution().trap(heights));
    }
    {
		vector<int> heights = { 9,2,1,0,1,2,1,0,1,2,9 };
        _ASSERT(7+8+9+8+7+8+9+8+7 == Solution().trap(heights));
    }
    return 0;
}
