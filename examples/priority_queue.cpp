/* Demonstrates how to use std::priority_queue with a lambda comparator.

Notice that before C++ 20, as well as passing the comparator TYPE by using decltype specifier,
one also has to pass the comparator OBJECT to the priority_queue's constructor.
Otherwise the compiler says this error: attempting to reference a deleted function.

Example:

auto stronger = [](...)...

	// C++14, 17:
	priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(stronger)> soliders(stronger);

	// C++20:
	priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(stronger)> soliders;
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

/*
1337. The K Weakest Rows in a Matrix
https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
*/
vector<int> kWeakestRows(const vector<vector<int>>& mat, int k) {
	const int m = mat.size();
	const int n = mat[0].size();

	// A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j,
	// or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row,
	// that is, always ones may appear first and then zeros.
	auto stronger = [](pair<int, int> a, pair<int, int> b) {
		if (a.second != b.second) return a.second > b.second;
		else return a.first > b.first;
	};

	// A minimum heap
	priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(stronger)> soliders(stronger);

	for (int r = 0; r < m; ++r) {
		for (int c = 0; c <= n; ++c) {
			if (c == n || mat[r][c] == 0) {
				soliders.push(make_pair(r, c));
				break;
			}
		}
	}

	vector<int> ans;
	for (int i = 0; i < k; ++i) {
		ans.push_back(soliders.top().first);
		soliders.pop();
	}

	return ans;
}

int main() {
	for (int r : kWeakestRows({ {1,1,0,0,0}, {1,1,1,1,0}, {1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1} }, 3))
		cout << r << endl;
	return 0;
}
