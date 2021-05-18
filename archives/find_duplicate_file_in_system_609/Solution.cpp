/**
 * 609. Find Duplicate File in System
 * https://leetcode.com/problems/find-duplicate-file-in-system/solution/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-18, 12:48:14 p.m.
 */

class Solution {
    public:
        vector<vector<string>> findDuplicate(vector<string>& paths) {
            // Group files by their content digest.
            // groups[content_hash] = paths
            unordered_map<size_t, vector<string>> groups;

            // to generate content digest
            hash<string> hasher;

            for (string& s : paths) {
                // split string
                istringstream iss(s);
                vector<string> tokens{
                    istream_iterator<string>{iss},
                    istream_iterator<string>{}};

                string& dir = tokens[0];

                // put each file into a group
                for (int i = 1, n = tokens.size(); i < n; ++i) {
                    auto pos = tokens[i].find('(');
                    auto filename = tokens[i].substr(0, pos);
                    auto content = tokens[i].substr(pos + 1, tokens[i].length() - 1 - (pos + 1));
                    auto digest = hasher(content);
                    groups[digest].push_back(dir + '/' + filename);
                }
            }

            vector<vector<string>> res;

            for (auto&& p : groups) {
                if (p.second.size() > 1)
                    res.push_back(p.second);
                // Follow up: How to make sure the duplicated files you find are not false positive?
                // For each pair of files with the same digest, compare them byte by byte? Or compare
                // them by another digest algorithm?
            }

            return res;
        }
};
