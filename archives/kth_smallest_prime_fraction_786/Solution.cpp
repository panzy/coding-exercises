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

/*
786. K-th Smallest Prime Fraction
https://leetcode.com/problems/k-th-smallest-prime-fraction/

The solution idea came from 
https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/986614/Java-Easiest-and-Readable-O(k-log-k)-Solution
*/
vector<int> kthSmallestPrimeFraction(const vector<int>& arr, int k) {
	const int n = arr.size();

	// A minimum heap.
	// element = pair{numerator index, dominator index}
	auto gt = [&arr](pair<int, int> a, pair<int, int> b) {
		return arr[a.first] * arr[b.second] > arr[b.first] * arr[a.second];
	};
	priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(gt)> fractions(gt);

	fractions.push(make_pair(0, n - 1));

	for (int i = 1; i < k; ++i) {
		auto f = fractions.top();
		fractions.pop();

		// The next greater fraction could be (first + 1)/second.
		// Notice the "second == n - 1" condition here. Without this:
		//		(1) there might be unwanted jump in our traversal, the missed fractions being those
		//			between (first + 1, second) and (first, second - 1).
		//		(2) there might be duplicates in our traversal.
		// Both issues can be solved as in the "less clever version" of solution below.
		if (f.first + 1 < n && f.second == n - 1) {
			fractions.push(make_pair(f.first + 1, f.second));
		}

		// The next greater fraction could also be first/(second - 1).
		if (f.second - 1 >= 0) {
			fractions.push(make_pair(f.first, f.second - 1));
		}
	}

	auto f = fractions.top();
	return { arr[f.first], arr[f.second] };
}

vector<int> kthSmallestPrimeFraction_lessCleverVersion(const vector<int>& arr, int k) {
	const int n = arr.size();

	// A minimum heap.
	// element = pair{numerator index, dominator index}
	auto gt = [&arr](pair<int, int> a, pair<int, int> b) {
		return arr[a.first] * arr[b.second] > arr[b.first] * arr[a.second];
	};
	priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(gt)> fractions(gt);
	unordered_set<int> seen;

	auto check_push = [&](int numerator_idx, int dominator_idx) {
		int key = (numerator_idx << 16) + dominator_idx;
		if (seen.find(key) == seen.end()) {
			seen.insert(key);
			fractions.push(make_pair(numerator_idx, dominator_idx));
		}
	};

	fractions.push(make_pair(0, n - 1));

	for (int i = 1; i < k; ++i) {
		auto f = fractions.top();
		fractions.pop();

		if (f.first + 1 < n) {
			check_push(f.first + 1, f.second);
			for (int dt = f.second - 2; dt >= 0 && !gt(make_pair(f.first, dt), make_pair(f.first + 1, f.second)); --dt) {
				check_push(f.first, dt);
			}
		}

		if (f.second - 1 >= 0) {
			for (int nt = f.first + 2; nt < n && !gt(make_pair(nt, f.second), make_pair(f.first, f.second - 1)); ++nt) {
				check_push(nt, f.second);
			}
			check_push(f.first, f.second - 1);
		}
	}

	auto f = fractions.top();
	return { arr[f.first], arr[f.second] };
}

int main() {
	{
		auto f = kthSmallestPrimeFraction({ 1,7,23,29,47 }, 8);
		_ASSERT(f[0] == 23);
		_ASSERT(f[1] == 47);
	}
	{
		auto f = kthSmallestPrimeFraction({ 1, 2, 3, 5 }, 3);
		_ASSERT(f[0] == 2);
		_ASSERT(f[1] == 5);
	}
	return 0;
}
