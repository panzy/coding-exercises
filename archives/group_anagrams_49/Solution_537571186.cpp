//
// 49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/
// 
// 114 / 114 test cases passed.	Status: Accepted
// Runtime: 28 ms
// Memory Usage: 19.6 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      12/08/2021, 17:24:47
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/537571186/?from=explore&item_id=3887/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> grps;
        for (auto&& s : strs) {
            string key = s;
            sort(begin(key), end(key));
            grps[key].push_back(s);
        }
        
        vector<vector<string>> res;
        for (auto&& p : grps) {
            res.push_back(p.second);
        }
        
        return res;
    }
};