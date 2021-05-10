/* 204. Count Primes
 * https://leetcode.com/problems/count-primes/
 * --
 * Zhiyong 2021-05-10
 */

// Optimized: all even numbers are excluded from the begining. 40ms.
class Solution {
	public:
		int countPrimes(int n) {
			if (n <= 2) return 0;

			// nums[i] = whether (2*i + 1) is a prime, where i>=1. Note that 2*i is never a prime.
			vector<bool> nums(n / 2, true);

			// All evens are not primes, so remove the count of them from the answer.
			// Although 1 and 2 are special, the correctness of the following expression maintains.
			int ans = n / 2;

			// For each k, all its multiples are not primes.
			// Since evens have already excluded, we need to make sure no even numbers are generated and checked again. So k is never even, neither is np.
			for (int k = 3, maxK = sqrt(n); k <= maxK; k += 2) {
				int np = k * 3; // Not a Prime
				while (np < n) {
					int i = (np - 1) / 2;
					if (nums[i]) {
						nums[i] = false;
						--ans;
					}
					np += k * 2;
				}
			}

			return ans;
		}
};

// Not optimized. ~200ms
class Solution0 {
	public:
		int countPrimes(int n) {
			if (n < 2) return 0;

			// nums[i] = whether number i is a prime
			vector<bool> nums(n, true);
			nums[0] = nums[1] = false;

			// for each k, all its multiples are not primes
			for (int k = 2; 2 * k < n; ++k) {
				int i = k * 2;
				while (i < n) {
					nums[i] = false;
					i += k;
				}
			}

			return count(nums.begin(), nums.end(), true);
		}
};
