typedef long long LL;
typedef unsigned long long ULL;

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
