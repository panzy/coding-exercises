/**
 * 816. Ambiguous Coordinates
 * https://leetcode.com/problems/ambiguous-coordinates
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-13, 4:38:50 p.m.
 */

class Solution {
    // Can this substr be a coordinate before or after inserting a dot somewhere?
    bool valid(const string& s, int begin, int end) {
        return end - begin > 0 && (begin + 1 == end || s[begin] != '0' || s[end - 1] != '0');
    }
    
    // Given a string of digits, enumerate possible numbers. A dot may or may not be inserted.
    vector<string> coords(const string& s, int begin, int end) {
        vector<string> res;
        
        if (s[begin] == '0' && end - begin > 1) {
            // a dot is needed immediately
            res.push_back("0." + s.substr(begin + 1, end - begin - 1));
        } else {
            // could have a dot?
            if (s[end - 1] != '0') {
                // i = where to insert dot before
                for (int i = begin + 1; i < end; ++i) {
                    res.push_back(s.substr(begin, i - begin) + "." + s.substr(i, end - i));
                }
            }
            
            // could be a whole number
            res.push_back(s.substr(begin, end - begin));
        }
        
        return res;
    }
    
public:
    vector<string> ambiguousCoordinates(string s) {
        int n = s.length();
        vector<string> res;
        
        // i = where to insert comma before
        for (int i = 2; i < n - 1; ++i) {
            if (valid(s, 1, i) && valid(s, i, n - 1)) {
                auto&& xs = coords(s, 1, i);
                auto&& ys = coords(s, i, n - 1);
                
                for (auto&& x : xs) {
                    for (auto&& y : ys) {
                        res.push_back("(" + x + ", " + y + ")");
                    }
                }
            }
        }
        
        return res;
    }
};