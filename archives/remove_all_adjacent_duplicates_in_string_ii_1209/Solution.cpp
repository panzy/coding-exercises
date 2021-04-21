/* 1209. Remove All Adjacent Duplicates in String II
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 * --
 * Zhiyong 2021-04-20
 */
class Solution {
	public:
		string removeDuplicates(string s, int k) {
			vector<pair<char, int>> st;

			for (char c : s) {
				if (!st.empty() && st.back().first == c) {
					if (++st.back().second == k) {
						st.pop_back();
					}
				} else {
					st.push_back({c, 1});
				}    
			}

			string res;
			for (auto&& [ch, cnt] : st) {
				for (int i = 0; i < cnt; ++i)
					res += ch;
			}
			return res;
		}
};
