/*
42. Trapping Rain Water
https://leetcode.com/problems/trapping-rain-water/

Simplified the previous solution.

--
Zhiyong, 2021-02-19
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

		int ans = 0;

        // Left edges of potential bowls. Element = { left edge position, unusable volume }
        //
        // Unusable volume is composed of:
        //   - volumes occupied by shorter pillars.
        //   - volumes of water that have already been added to the answers.
        //
        // Notice that:
        //   - Unusable volume doesn't include the left edge itself.
        //   - Only the top element's unusable volume is guaranteed to be correct.
        //     That means, each time pop an edge, its unusable volume should be added to the next edge.
        stack<pair<int, int>> edges;

        edges.push({ 0, 0 });

        for (int i = 1; i < n; ++i) {

            if (height[i - 1] < height[i]) {
                // There is at least one bowl ending at i.
                // Enumerate there bowls.

                while (!edges.empty()) {
                    auto [j, v] = edges.top();

					int newVol = min(height[j], height[i]) * (i - j - 1) - v;
					ans += newVol;

                    if (height[j] >= height[i]) {
						edges.top().second += newVol;
                        // A bow ending at i cannot go beyond j.
                        break;
                    }
                    else {
                        // This edge, j, has been covered by i.
                        edges.pop();
                        if (!edges.empty()) {
                            // The next edge's unusable volume increases.
                            edges.top().second += height[j] + v + newVol;
                        }
                    }
                }
            }

            if (height[i] > 0)
				edges.push({ i, 0 });
        }

        return ans;
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
