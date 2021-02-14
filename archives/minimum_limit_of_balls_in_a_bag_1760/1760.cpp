#include <vector>
#include <algorithm>
using namespace std;

/*
1760. Minimum Limit of Balls in a Bag
https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/ 
*/
int minimumSize(vector<int>& nums, int maxOperations) {
	sort(nums.begin(), nums.end(), greater<int>{});

	int lo = 1, hi = nums[0];
	while (lo < hi) {
		int mi = (lo + hi) / 2;
		if (check(nums, maxOperations, mi)) {
			hi = mi;
		}
		else {
			if (lo < mi)
				lo = mi;
			else
				break;
		}
	}

	return hi;
}

bool check(vector<int>& nums, int maxOperations, int minimumSize) {
	int operations = maxOperations;
	for (int num : nums) {
		if (num <= minimumSize) break;
		operations -= (num - 1) / minimumSize;
		if (operations < 0) break;
	}
	return operations >= 0;
}