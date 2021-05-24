//
// 943. Find the Shortest Superstring
// https://leetcode.com/problems/find-the-shortest-superstring/
// Submission detail page: https://leetcode.com/submissions/detail/497449447//
// 
// --
// Created by Zhiyong Pan
// 24/05/2021, 01:10:07
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/*
Approach: Recursive DFS + cache.

XXX: The return value of the recursive function is a path, which is a vector.
It makes the problem really slow. How to get rid of that?
*/
class Solution {
    // Constraints.
    static const int MAX_WORDS = 20;
    static const int MAX_WORD_LEN = 12;

    // Path[1:end] = word indices
    // Path[0] = super string length
    using Path = vector<int>;

    using Key = unsigned int;

    // input
    int n;
    vector<string> words;

    // Define connectivity of i and j = overlapLen(words[i], words[j])
    int conn[MAX_WORDS][MAX_WORDS]{};

    // cache[state] = the optimized path of the remainning words,
    // where state is encoded into it two pieces of information:
    // 1. which words has already been selected?
    // 2. which is the last word been selected?
    unordered_map<Key, const Path*> cache;

    const Path EMPTY_PATH{ 0 };

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

    Key makeCacheKey(const bitset<MAX_WORDS>& consumed, int prevWord) {
        if (prevWord == -1)
            prevWord = n;

        Key key = (Key)(prevWord == -1 ? n : prevWord) << MAX_WORDS;

        for (int i = 0; i < n; ++i) {
            if (consumed[i])
                key |= 1LL << i;
        }

        return key;
    }

    const Path* shortestSuperstring(bitset<MAX_WORDS>& consumed, int prevWord) {
        if (consumed.count() == n)
            return &EMPTY_PATH;

        auto key = makeCacheKey(consumed, prevWord);

        if (cache.count(key))
            return cache[key];

        Path* ans = new Path{ 0, -1 };

        if (consumed.count() + 1 < n) {
            // Need recursion.

            const Path* ansTail = nullptr;

            for (int i = 0; i < n; ++i) {
                if (!consumed[i]) {
                    consumed[i] = true;
                    auto ans2 = shortestSuperstring(consumed, i);
                    consumed[i] = false;

                    int newLen = (*ans2)[0] + words[i].length() - (prevWord == -1 ? 0 : conn[prevWord][i]);

                    if ((*ans)[0] == 0 || (*ans)[0] > newLen) {
                        (*ans)[0] = newLen;
                        (*ans)[1] = i;
                        ansTail = ans2;
                    }
                }
            }

            if (ansTail)
                ans->insert(ans->end(), next(ansTail->begin()), ansTail->end());
        }
        else {
            for (int i = 0; i < n; ++i) {
                if (!consumed[i]) {
                    int newLen = words[i].length() - (prevWord == -1 ? 0 : conn[prevWord][i]);

                    if ((*ans)[0] == 0 || (*ans)[0] > newLen) {
                        (*ans)[0] = newLen;
                        (*ans)[1] = i;
                    }
                }
            }
        }

        cache[key] = ans;
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
        auto ppath = shortestSuperstring(consumed, -1);
        auto path = *ppath;

        // Build answer from path
        string res = "";
        for (auto itr = path.cbegin(); next(itr) != path.cend(); ++itr) {
            int prev = *itr;
            int i = *next(itr); // note that the word indices begin from path[1].

            auto& nextWord = words[i];
            if (res == "") {
                res += nextWord;
            }
            else {
                int offset = conn[prev][i];
                res += nextWord.substr(offset);
            }
        }

        // clean up
        for (auto&& p : cache) {
            delete p.second;
        }

        return res;
    }
};

