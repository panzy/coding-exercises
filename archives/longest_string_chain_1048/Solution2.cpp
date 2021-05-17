/**
 * 1048. Longest String Chain
 * https://leetcode.com/problems/longest-string-chain/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-17, 12:38:08 p.m.
 */

class Solution {
    // Determine whether word1 is a predecessor of word2.
    bool chainable(const string& word1, const string& word2) {
        for (int i = 0, n = word2.length(); i < n; ++i) {
            if (word2.compare(0, i, word1, 0, i) == 0 &&
                word2.compare(i + 1, n - i - 1, word1, i, n - i - 1) == 0)
                return true;
        }
        return false;
    }
public:
    int longestStrChain(const vector<string>& words) {
        // L[i] = the length of the longest chain starting from words[i].
        unordered_map<int, int> L;

        // groups[len] = indices of words of that length.
        const int MAX_WORD_LEN = 16;
        vector<int> groups[MAX_WORD_LEN + 1];

        for (int i = 0, n = words.size(); i < n; ++i) {
            auto& w = words[i];
            groups[w.length()].push_back(i);
            L[i] = 1;
        }

        for (int wordLen = MAX_WORD_LEN; wordLen > 1; --wordLen) {
            for (auto&& w2 : groups[wordLen]) {
                for (auto&& w1 : groups[wordLen - 1]) {
                    if (chainable(words[w1], words[w2])) {
                        L[w1] = max(L[w1], 1 + L[w2]);
                    }
                }
            }
        }

        int ans = 0;
        for (auto&& p : L) {
            ans = max(ans, p.second);
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
