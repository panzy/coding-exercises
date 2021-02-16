/*
 * 784. Letter Case Permutation 
 * https://leetcode.com/problems/letter-case-permutation/  
 *
 * --
 * Zhiyong, 2021-02-16
 */
class Solution {
	public:
		vector<string> letterCasePermutation(string S) {
			vector<string> res;
			helper(S, 0, "", res);
			return res;
		}

		void helper(string S, int offset, string prefix, vector<string> &res) {
			if (offset == S.length()) {
				res.push_back(prefix);
				return;
			}

			helper(S, offset + 1, prefix + string(1, S[offset]), res);
			if ('a' <= S[offset] && S[offset] <= 'z') {
				helper(S, offset + 1, prefix + string(1, 'A' + S[offset] - 'a'), res);
			} else if ('A' <= S[offset] && S[offset] <= 'Z') {
				helper(S, offset + 1, prefix + string(1, 'a' + S[offset] - 'A'), res);
			}
		}
};
