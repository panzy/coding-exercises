typedef long long LL;
typedef unsigned long long ULL;

#define FOR(i, n) for (int i = 0; i < n; ++i)
#define FOR_RANGE(i, begin, end) for (int i = begin; i < end; ++i)

// 4 neighbour cells in a grid.
const int NEIBS4[4][2] = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };

template <class T>
pair<int, int> grid_size(const vector<vector<T>>& grid) {
    return { grid.size(), grid[0].size() };
}

template <class T>
T min_element_2d(const vector<vector<T>>& grid) {
    T ans = numeric_limits<T>::max();
    for (auto&& row : grid)
        ans = min(ans, *min_element(row.begin(), row.end()));
    return ans;
}

/*
https://stackoverflow.com/a/9331125/1350949 
Tips: Excel COMBIN() function can be used to verify this.
*/
ULL combination(ULL n, ULL k)
{
	if (k > n) return 0;
	if (k * 2 > n) k = n - k;
	if (k == 0) return 1;

	ULL result = n;
	for (int i = 2; i <= k; ++i) {
		result *= (n - i + 1);
		result /= i;
	}
	return result;
}

ULL permutation(int n, int r)
{
	ULL ans = 1;
	while (n > r) {
		ans *= n;
		--n;
	}
	return ans;
}

bool between(int x, int a, int b) {
    return a <= x && x <= b;
}

int dist2(int x1, int y1, int x2, int y2) {
    return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
}
