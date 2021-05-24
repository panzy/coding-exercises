//
// 943. Find the Shortest Superstring
// https://leetcode.com/problems/find-the-shortest-superstring/
// 
// 83 / 83 test cases passed.	Status: Accepted
// Runtime: 44 ms
// Memory Usage: 13.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      24/05/2021, 15:19:07
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/497729804//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// Improvements on Solution_497449447.cpp:
// - Changed cache value from full length path (a vector) to a single node.
// - Changed cache container from unordered_map to array. Because the cache is actually a DP memo.
// - Changed type of `consumed` from std::bitset to int.

// Represent a word in a DFS path.
//
// Note that this structure doesn't explicitly specify which is the next node.
//
// That's because this is a dynamic programming algo, the next node is determined
// by the current state. And the state comprises the set of consumed words and
// the last consumed word, i.e., PathNode.currWord.
struct PathNode {
    // The length of the super string of the path starting from this node.
    int superstrLen;
    // The word index.
    int currWord;
};

class Solution {
    // Constraints.
    //
    // The important things here are:
    // 1. The bit set of words can fit into an int, so we don't need
    // std::bitset or std::vector<bool>.
    // 2. The total number of cache items isn't too big, even though the space
    // complexity is O(2^n * n).
    //
    static const int MAX_WORDS = 20;
    // static const int MAX_WORD_LEN = 12; // irrelevant

    // input
    int n{ 0 };
    vector<string> words;

    // Define connectivity of i and j = overlapLen(words[i], words[j])
    int conn[MAX_WORDS][MAX_WORDS]{};

    // cache[state] = the optimized path of the remainning words,
    // where state is encoded into it two pieces of information:
    //
    // 1. consumed - which words has already been selected?
    // 2. prevWord - which is the last word selected?
    //
    // The method to encode such state is defined in makeCacheKey().
    // 
    // The max cache size = (1 << 20) * 20 = 20971520. It's not that bad.
    //
    // Note that the cache value used to be a vector containing all the word
    // indices in the path. Since the algorithm involves concatnating vectors
    // and requires avoiding copy as much as possible, the implementation is
    // very complicated (see Solution_497449447.cpp).
    vector<PathNode> cache;

    // Build path from the head node.
    vector<int> buildPath(const PathNode& head) {
        vector<int> path;
        int consumed = 0;
        const PathNode* p = &head;
        while (p->currWord != -1) {
            path.push_back(p->currWord);
            consumed |= 1 << p->currWord;
            p = &cache[makeCacheKey(consumed, p->currWord)];
        }
        return path;
    }

    // Build answer from the path.
    string buidAnswer(const vector<int>& path) {
        string res = "";
        int prev = -1;
        for (int i : path) {
            auto& nextWord = words[i];
            if (prev == -1) {
                res += nextWord;
            }
            else {
                int offset = conn[prev][i];
                res += nextWord.substr(offset);
            }
            prev = i;
        }
        return res;
    }

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

    int makeCacheKey(int consumed, int prevWord) {
        return ((prevWord == -1 ? n : prevWord) << n) + consumed;
    }

    // Given a state condition, determine the best arrangement of the remaining words.
    // It returns a path node wrapping the next word, which is supposed to follow the `prevWord`.
    PathNode shortestSuperstring(int consumed, int prevWord) {
        auto key = makeCacheKey(consumed, prevWord);
        auto& currNode = cache[key];

        if (cache[key].superstrLen > 0)
            return cache[key];

        if (consumed == (1 << (n + 1)) - 1) // all 1's means all words have been consumed
            return cache[key];

        for (int i = 0; i < n; ++i) {
            if ((consumed & (1 << i)) == 0) {
                // Try make words[i] as the next word.

                // What's next to words[i]?
                consumed |= (1 << i);
                auto&& rest = shortestSuperstring(consumed, i);
                consumed ^= (1 << i);

                // What's the score of choosing i?
                int len = words[i].length() - (prevWord == -1 ? 0 : conn[prevWord][i])
                    + rest.superstrLen;

                // Update the cache if it's better.
                if (currNode.superstrLen == 0 || currNode.superstrLen > len) {
                    currNode.superstrLen = len;
                    currNode.currWord = i;
                }
            }
        }

        return currNode;
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

        // Init cache.
        cache.resize(1 + (1LL << n) * n, { 0, -1 });

        int consumed = 0;
        auto&& pathHead = shortestSuperstring(consumed, -1);
        auto&& path = buildPath(pathHead);
        return buidAnswer(path);
    }
};

