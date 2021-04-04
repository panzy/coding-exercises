/* 1813. Sentence Similarity III
 * https://leetcode.com/problems/sentence-similarity-iii/
 *
 * XXX: This solution operates on chars, too complicated. 
 *
 * It would be much easier if I had split the sentences into words first.
 *
 * Or at least consider adding sentinel whitespaces at both ends of each sentence
 * to reduce some edge cases?
 * 
 * --
 * Zhiyong 2021-04-03
 */

class Solution {
public:
    bool areSentencesSimilar(string s1, string s2) {
        int m = s1.length();
        int n = s2.length();
        int i = 0, // length of head (not including the OPTIONAL ending whitespace)
        j = 0; // length of tail (not including the OPTIONAL ending whitespace)

        int k = 0;
        while (k < m && k < n && s1[k] == s2[k]) {
            if (s1[k] == ' ')
                i = k; // the latest whitespace
            ++k;
        }
        // exhausted?
        if ((k == m || s1[k] == ' ') && (k == n || s2[k] == ' ')) i = k;

        k = 0;
        while (k < m - i && k < n - i && s1[m - 1 - k] == s2[n - 1 - k]) {
            if (s1[m - 1 - k] == ' ')
                j = k; // the latest whitespace
            ++k;
        }
        // exhausted?
        if (k == m || k == n) j = k;

        int len = i + j + (i > 0 && j > 0 ? 1 : 0);
        return len == m || len == n;
    }
};
