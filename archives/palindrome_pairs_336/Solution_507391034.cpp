//
// 336. Palindrome Pairs
// https://leetcode.com/problems/palindrome-pairs/
// 
// 134 / 134 test cases passed.	Status: Accepted
// Runtime: 224 ms
// Memory Usage: 37.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      13/06/2021, 12:45:53
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/507391034/?from=explore&item_id=3777/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/*
Approach: for each word, generate all the words that could make a palindrome with this one,
and if the other word exists in hash map, we get a pair.

A key component of this algo is function getRight(word, center1, center2),
which returns word2 that s = (word + word2) is a palindrome
with s[center1] and s[center2] beging the center.

Note that center2 could:
- equals to center1
- equals word.length()

*/
class Solution {
public:
    vector<vector<int>> palindromePairs(vector<string>& words) {

        // +++++++++++ begin unit tests +++++++++++++
        if (false) { 
            string w = "sssll";
            
            assert(getRight(w, 4, 4) == "lsss");
            assert(getRight(w, 3, 4) == "sss");
            assert(getRight(w, 3, 3) == NOT_FOUND);
            assert(getRight(w, 2, 2) == NOT_FOUND);
            
            assert(getLeft(w, 0, 0) == "llss"); // implemented as getRight("llsss", 4, 4)
            assert(getLeft(w, 1, 1) == "ll");
            assert(getLeft(w, 2, 2) == NOT_FOUND);
            
            w = "lls";
            assert(getRight(w, 1, 1) == NOT_FOUND);
            assert(getRight(w, 1, 2) == NOT_FOUND);
            assert(getLeft(w, 0, 0) == "sl");
            assert(getLeft(w, 0, 1) == "s");
            assert(getLeft(w, 1, 1) == NOT_FOUND);
        }
        // +++++++++++ end unit tests +++++++++++++

        // dict[word] = index
        unordered_map<string, int> dict;
        int n = words.size();
        
        for (int i = 0; i < n; ++i) {
            dict[words[i]] = i;
        }
        
        vector<vector<int>> res;
        
        for (int i = 0; i < n; ++i) {
            string& w = words[i];
            int m = w.length();
            
            if (m == 0) continue; // an optional short cut
            
            // is there a palindrome with w[0:j) being the left half and w[j] being the center?
            // or with w[0:j+1) being the left half, w[j] and w[j+1] (if exists) being the fat center?
            for (int j = m / 2 - 1; j < m; ++j) {
                string right = getRight(w, j, j);
                if (right != NOT_FOUND && dict.count(right) > 0 && dict[right] != i) {
                    res.push_back({ i, dict[right] });
                }

                right = getRight(w, j, j + 1);
                if (right != NOT_FOUND && dict.count(right) > 0 && dict[right] != i) {
                    res.push_back({ i, dict[right] });
                }
            }
            
            // is there a palindrome with w[j:m) or w[j+1:m) being the right part?
            for (int j = 0; j <= m / 2; ++j) {
                string left = getLeft(w, j, j);
                if (left != NOT_FOUND && dict.count(left) > 0 && dict[left] != i) {
                    res.push_back({ dict[left], i });
                }

                left = getLeft(w, j, j + 1);
                if (left != NOT_FOUND && dict.count(left) > 0 && dict[left] != i) {
                    res.push_back({ dict[left], i });
                }
            }
        }    
        
        return res;
    }
    
private:
    const string NOT_FOUND = "!"; // a non-lower-case Eng letter indicates there is no such a word
    
    string getRight(const string& w, int center1, int center2) {
        if (center1 < 0
            || (center1 + 1 < w.length() - center2)
            || (center2 < w.length() && w[center1] != w[center2]))
            return NOT_FOUND;

        string res = "";

        if (center2 == w.length())
            res += w[center1];

        for (int i = center1 - 1, j = center2 + 1; i >= 0; --i, ++j) {
            if (j < w.length()) {
                if (w[i] != w[j])
                    return NOT_FOUND;
            } else {
                res += w[i];
            }
        }
        return res;
    }
    
    string getLeft(string& w, int center1, int center2) {
        reverse(begin(w), end(w));
        string res = getRight(w, w.length() - center2 - 1, w.length() - center1 - 1);
        reverse(begin(res), end(res));
        reverse(begin(w), end(w));
        return res;
    }
};
