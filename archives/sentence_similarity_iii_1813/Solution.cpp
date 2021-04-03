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
        cout << s1 << endl << s2 << endl;
        int m = s1.length();
        int n = s2.length();
        int i = 0, // length of head
        j = 0; // length of tail
        
        while (i < m && i < n && s1[i] == s2[i]) ++i;
        
        cout << "i=" << i << endl;
        if (i > 0) {
            // back to the nearest space
            if (i < m)
                while (i > 0 && s1[i] != ' ') --i;
            if (i < n)
                while (i > 0 && s2[i] != ' ') --i;
        }
        cout << "i=" << i << endl;
        
        while (j < m - i && j < n - i && s1[m - j - 1] == s2[n - j - 1]) ++j;
        
        cout << "j=" << j << endl;
        if (j > 0 && m - j > 0 && (n - j != 0 && s1[m - j] != ' ')) {
            cout << "break word 1" << endl;
            return false;
        }
        if (j > 0 && n - j > 0 && (m - j != 0 && s2[n - j] != ' ')) {
            cout << "break word 2" << endl;
            return false;
        }
        if (s1[m - j] == ' ') {
            --j;
        }
        cout << "j=" << j << endl;
        
        int len = i + j + (i > 0 && j > 0 ? 1 : 0);
        bool ans = len == m || len == n;
        cout << (ans ? "true" : "false") << endl;
        return ans;
    }
};
