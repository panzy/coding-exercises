#include <cmath>
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

typedef long long LL;

void segment(LL L, LL R, vector<vector<LL>> &output) {
	for (int k = 4; k >= 1; --k) {
		LL size = pow(10, pow(2, k - 1));
		LL L2 = 1 + L / size * size;
		if (L2 < L) L2 += size;
		if (L2 <= R) {
			LL c = (R - L2 + 1) / size;
			if (c > 0) {
				int R2 = L2 + c * size - 1;
				if (L < L2) segment(L, L2 - 1, output);
				output.push_back({ k, c, L2, R2 });
				segment(R2 + 1, R, output);
				return;
			}
		}
	}

	// 0-th level
	if (L <= R) output.push_back({0, R - L + 1, L, R});
}

int main() {
	LL L, R;
	cin >> L >> R;

	vector<vector<LL>> segs;
	segment(L, R, segs);

	// output
	bool debug = true;
	cout << segs.size() << endl;
	for (auto s : segs) {
		cout << s[0] << ' ' << s[1];
		if (debug) cout << " [" << s[2] << ' ' << s[3] << ']';
		cout << endl;
	}
}
