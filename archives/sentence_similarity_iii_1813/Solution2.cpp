/* 1813. Sentence Similarity III
 * https://leetcode.com/problems/sentence-similarity-iii/
 * --
 * Zhiyong 2021-04-04
 */

class Solution {
    void split(string& s, vector<string>& words) {
        int m = s.length();
        for (int i = 1, p = 0; i <= m; ++i) {
            if (i == m || s[i] == ' ') {
                words.push_back(s.substr(p, i - p));
                p = i + 1;
            }
        }
    }
    
    bool similar(vector<string>& L, vector<string>& S) {
        int m = L.size();
        int n = S.size();
        int i = 0, j = 0;
        
        while (i < S.size() && L[i] == S[i]) ++i;
        while (j < S.size() && L[m - 1 - j] == S[n - 1 - j]) ++j;
        return i + j >= n;
    }
public:
    bool areSentencesSimilar(string s1, string s2) {
        vector<string> w1, w2;
        
        split(s1, w1);
        split(s2, w2);
        
        return w1.size() >= w2.size() ? similar(w1, w2) : similar(w2, w1);
    }
};
