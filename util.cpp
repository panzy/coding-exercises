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
