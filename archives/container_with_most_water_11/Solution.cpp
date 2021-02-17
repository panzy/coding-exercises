/*
11. Container With Most Water
https://leetcode.com/problems/container-with-most-water/
--
Zhiyong, 2021-02-17
*/
#include <cmath>
#include <cstdio>
#include <vector>
#include <set>
#include <queue>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <numeric>
#include <functional>
using namespace std;

int maxArea(vector<int>& height) {
    int i = 0, j = height.size() - 1;
    int area = 0;

    while (i < j) {
        area = max(area, min(height[i], height[j]) * (j - i));
        if (height[i] >= height[j])
            --j;
        else
            ++i;
    }

    return area;
}

int main() {
    {
		vector<int> heights = { 1,8,6,2,5,4,8,3,7 };
        _ASSERT(49 == maxArea(heights));
    }
    {
		vector<int> heights = { 1,8,6,2,5,4,8,3,7,2 };
        _ASSERT(49 == maxArea(heights));
    }
    {
		vector<int> heights = { 1,7,6,2,5,4,8,3,8,2 };
        _ASSERT(49 == maxArea(heights));
    }
    {
		vector<int> heights = { 1,1 };
        _ASSERT(1 == maxArea(heights));
    }
    {
		vector<int> heights = { 4,3,2,1,4 };
        _ASSERT(16 == maxArea(heights));
    }
    {
		vector<int> heights = { 1,2,1 };
        _ASSERT(2 == maxArea(heights));
    }
    return 0;
}
