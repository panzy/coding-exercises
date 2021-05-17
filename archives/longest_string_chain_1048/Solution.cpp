/**
 * 1048. Longest String Chain
 * https://leetcode.com/problems/longest-string-chain/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-17, 12:38:08 p.m.
 */

class Solution {
    // W[word] = the longest chain length ending with this word.
    unordered_map<string, int> W;

    // Find the value of W[endWord].
    int go(const string& endWord) {
        if (endWord.length() <= 1)
            return 1;

        int& len = W[endWord];

        if (len > 0)
            return len;

        len = 1; // at least

        for (int i = 0, n = endWord.length(); i < n; ++i) {
            // a predecessor
            string pre = endWord.substr(0, i) + endWord.substr(i + 1, n - i - 1);
            if (W.count(pre)) {
                len = max(len, 1 + go(pre));
            }
        }

        return len;
    }
public:
    int longestStrChain(const vector<string>& words) {
        for (auto&& w : words) {
            W[w] = w.length() == 1 ? 1 : 0;
        }

        int ans = 0;
        for (auto&& p : W) {
            ans = max(ans, go(p.first));
        }

        return ans;
    }
};

int main() {
    {
        int ans = Solution().longestStrChain({ "a", "b", "ba", "bca", "bda", "bdca" });
        assert(4 == ans);
    }
    {
        int ans = Solution().longestStrChain({ "xbc","pcxbcf","xb","cxbc","pcxbc" });
        assert(5 == ans);
    }
    {
        int ans = Solution().longestStrChain({ "a", "a", "aa", "aaa", "aaaa", "aaaaa" });
        assert(5 == ans);
    }
}
