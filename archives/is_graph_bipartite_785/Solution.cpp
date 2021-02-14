/*
785. Is Graph Bipartite?
https://leetcode.com/problems/is-graph-bipartite/
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

////////////////////////////////////////////////////////////////////////////////
// Utils 

template <class T> using min_heap = priority_queue<T, vector<T>, greater<T>>;
template <class T> using max_heap = priority_queue<T, vector<T>, less<T>>;

void set_precision(int n) {
	cout.precision(1);
	cout.setf(std::ios::fixed, std::ios::floatfield);
}

////////////////////////////////////////////////////////////////////////////////
// The solution

bool isBipartite(const vector<vector<int>>& graph) {
	int n = (int)graph.size();
	char color[100]; // {?, 0, 1}
	bool res = true;

	fill(color, color + n, '?');

	for (int i = 0; i < n; ++i) {
		if (color[i] == '?') {

			queue<pair<int, char>> layer;
			layer.push({ i, 0 });

			while (!layer.empty()) {
				auto [u, c] = layer.front();
				layer.pop();
				color[u] = c;
				for (int v : graph[u]) {
					if (color[v] == c) {
						res = false;
						break;
					}
					else if (color[v] == '?') {
						layer.push(make_pair(v, 1 - c));
					}
				}
			}

			if (!res) break;
		}
	}

	return res;
}

int main()
{
	_ASSERT(isBipartite({ {1, 3}, {0, 2}, {1, 3}, {0, 2} }));
	_ASSERT(!isBipartite({ {1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2} }));
	cout << "All assertions are valid." << endl;
	return 0;
}
