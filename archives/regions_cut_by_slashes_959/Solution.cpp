/*
959. Regions Cut By Slashes
https://leetcode.com/problems/regions-cut-by-slashes/
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

int regionsBySlashes(const vector<string>& grid) {
    // Define each cell contains 4 graph nodes, which are triangles.
	// Define the top triangle inside a cell has index 0.

	int n = grid.size();

	// visited indexes = {row index, col index, triangle index }
	vector<vector<vector<bool>>> visited(n, vector<vector<bool>>(n, vector<bool>(4, false)));

	int comps = 0; // number of connected components
	
	for (int row = 0; row < n; ++row) {
		for (int col = 0; col < n; ++col) {
			for (int tri = 0; tri < 4; ++tri) {
				if (visited[row][col][tri]) continue;

				// Found a new connected component.
				++comps;

				// Mark all triangles of this component visited.

				// layer[i] = {row index, col index, triangle index }
				queue<vector<int>> layer;
				layer.push({ row, col, tri });
				visited[row][col][tri] = true;

				while (!layer.empty()) {
					auto node = layer.front(); layer.pop();
					int r = node[0], c = node[1], t = node[2];
					//visited[r][c][t] = true;

					if (t == 0) {
						if (r > 0 && !visited[r - 1][c][2]) {
							// it connects to the bottom triangle from the above cell
							layer.push({ r - 1, c, 2 });
							visited[r - 1][c][2] = true;
						}
						if (grid[r][c] != '/' && !visited[r][c][3]) {
							// it connects to the right triangle from this cell
							layer.push({ r, c, 3 });
							visited[r][c][3] = true;
						}
						if (grid[r][c] != '\\' && !visited[r][c][1]) {
							// it connects to the left triangle from this cell
							layer.push({ r, c, 1 });
							visited[r][c][1] = true;
						}
					}
					else if (t == 1) {
						if (c > 0 && !visited[r][c - 1][3]) {
							// it connects to the right triangle from the left cell
							layer.push({ r, c - 1, 3 });
							visited[r][c - 1][3] = true;
						}
						if (grid[r][c] != '/' && !visited[r][c][2]) {
							// it connects to the bottom triangle from this cell
							layer.push({ r, c, 2 });
							visited[r][c][2] = true;
						}
						if (grid[r][c] != '\\' && !visited[r][c][0]) {
							// it connects to the top triangle from this cell
							layer.push({ r, c, 0 });
							visited[r][c][0] = true;
						}
					}
					else if (t == 2) {
						if (r + 1 < n && !visited[r + 1][c][0]) {
							// it connects to the top triangle from the below cell
							layer.push({ r + 1, c, 0 });
							visited[r + 1][c][0] = true;
						}
						if (grid[r][c] != '/' && !visited[r][c][1]) {
							// it connects to the left triangle from this cell
							layer.push({ r, c, 1 });
							visited[r][c][1] = true;
						}
						if (grid[r][c] != '\\' && !visited[r][c][3]) {
							// it connects to the right triangle from this cell
							layer.push({ r, c, 3 });
							visited[r][c][3] = true;
						}
					}
					else if (t == 3) {
						if (c + 1 < n && !visited[r][c + 1][1]) {
							// it connects to the left triangle from the right cell
							layer.push({ r, c + 1, 1 });
							visited[r][c + 1][1] = true;
						}
						if (grid[r][c] != '/' && !visited[r][c][0]) {
							// it connects to the top triangle from this cell
							layer.push({ r, c, 0 });
							visited[r][c][0] = true;
						}
						if (grid[r][c] != '\\' && !visited[r][c][2]) {
							// it connects to the bottom triangle from this cell
							layer.push({ r, c, 2 });
							visited[r][c][2] = true;
						}
					}
				}
			}
		}
	}

	return comps;
}

int main()
{
	_ASSERT(regionsBySlashes({ " /", "/ " }) == 2);
	_ASSERT(regionsBySlashes({ "/" }) == 2);
	cout << "All assertions are valid." << endl;
	return 0;
}
