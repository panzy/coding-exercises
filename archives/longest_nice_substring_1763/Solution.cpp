/* 
 * 1763. Longest Nice Substring
 * https://leetcode.com/problems/longest-nice-substring
 * --
 * Zhiyong, 2021-02-20
 */
class Solution {
public:
	bool check(const string & s) {
		for (char c : s) {
			if (
				('a' <= c && c <= 'z' && s.find(c - 'a' + 'A') == -1) ||
				('A' <= c && c <= 'Z' && s.find(c - 'A' + 'a') == -1))
			{
                return false;
			}
		}
        return true;
	}

    string longestNiceSubstring(const string& s) {
        const int n = s.length();
        string ans = "";
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                auto s2 = s.substr(i, j - i);
                if (check(s2) && ans.length() < s2.length())
                    ans = s2;
            }
        }
        return ans;
    }
};

