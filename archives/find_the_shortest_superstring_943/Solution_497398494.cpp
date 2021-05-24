//
// 943. Find the Shortest Superstring
// https://leetcode.com/problems/find-the-shortest-superstring/
// 
// 83 / 83 test cases passed, but took too long.	Status: Time Limit Exceeded
// 
// --
// Created by Zhiyong Pan
// Git commit time:      24/05/2021, 10:31:46
// LeetCode submit time: 12 hours, 5 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/497398494//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    static const int MAX_WORDS = 20;
    static const int MAX_WORD_LEN = 12;

    int n;
    vector<string> words;

    // Define connectivity of i and j = overlapLen(words[i], words[j])
    int conn[MAX_WORDS][MAX_WORDS]{};

    unordered_map<uint64_t, string> cache;

    // Return the overlap length when appending b to a.
    int overlapLen(const string& a, const string& b) {
        // Init to the maximum
        int len = min(a.length(), b.length());
        // Adjust until it's correct.
        while (len > 0 && a.compare(a.length() - len, len, b, 0, len) != 0) {
            --len;
        }
        return len;
    }

    uint64_t makeCacheKey(const bitset<MAX_WORDS>& consumed, int prevWord) {
        if (prevWord == -1)
            prevWord = n;

        uint64_t key = (uint64_t)(prevWord == -1 ? n : prevWord) << MAX_WORDS;

        for (int i = 0; i < n; ++i) {
            if (consumed[i])
                key |= 1L << i;
        }

        return key;
    }

    string shortestSuperstring(bitset<MAX_WORDS>& consumed, int prevWord, const string& prevSuperstr) {
        if (consumed.count() == n)
            return prevSuperstr;

        string ans;

        auto key = makeCacheKey(consumed, prevWord);

        if (cache.count(key))
            return prevSuperstr + cache[key];

        for (int i = 0; i < n; ++i) {
            if (!consumed[i]) {
                consumed[i] = true;
                string ans2 = prevWord == -1 ?
                    shortestSuperstring(consumed, i, words[i]) :
                    shortestSuperstring(consumed, i, prevSuperstr + words[i].substr(conn[prevWord][i]));
                consumed[i] = false;

                if (ans.empty() || ans.length() > ans2.length())
                    ans = ans2;
            }
        }

        cache[key] = ans.substr(prevSuperstr.length());
        return ans;
    }
public:
    string shortestSuperstring(vector<string>& words) {
        int n = words.size();

        this->n = n;
        this->words = words;

        // Compute connectivity.
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                conn[i][j] = overlapLen(words[i], words[j]);
                conn[j][i] = overlapLen(words[j], words[i]);
            }
        }

        bitset<MAX_WORDS> consumed;
        return shortestSuperstring(consumed, -1, "");
    }
};

