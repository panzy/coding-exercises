/*
684. Redundant Connection
https://leetcode.com/problems/redundant-connection/
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

// Disjoint Set Union
class DSU {
private:
	vector<int> parent;
public:
	DSU(int n) {
		parent.resize(n + 1);
		fill(parent.begin(), parent.end(), -1);
	}

	// Finds a node's parent.
	// Returns the node iteself if it has no parent.
	// If it does have parent other than itself, its parent pointer will be updated to the root.
	int find(int x) {
		int p = parent[x];
		while (p != -1 && parent[p] != -1) {
			parent[x] = p = parent[p];
		}
		return p == -1 ? x : p;
	}

	bool connect(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px == py)
			return false; // already connected
		parent[py] = px;
        return true;
	}
};

////////////////////////////////////////////////////////////////////////////////
// The solution

vector<int> findRedundantConnection(const vector<vector<int>>& edges) {
	DSU dsu(edges.size());

	for (auto edge : edges) {
		int x = edge[0], y = edge[1];
		if (!dsu.connect(x, y))
			return edge;
	}
	throw "should have found an answer";
}

int main()
{
	{
		auto edge = findRedundantConnection({ {1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5} });
		_ASSERT(edge[0] == 1);
		_ASSERT(edge[1] == 3);
	}

	{
		auto edge = findRedundantConnection({ {1, 2}, {1, 3}, {2, 3} });
		_ASSERT(edge[0] == 2);
		_ASSERT(edge[1] == 3);
	}

	cout << "All assertions are valid." << endl;
	return 0;
}
