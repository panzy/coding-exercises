//
// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
// 
// 70 / 70 test cases passed.	Status: Accepted
// Runtime: 56 ms
// Memory Usage: 30.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      06/06/2021, 11:34:10
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/503945259//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/* this is the official solution Approach 3: HashSet and Intelligent Sequence Building */
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> distincts;
        
        for (int i : nums) {
            distincts.insert(i);
        }
        
        int ans = 0;
        
        for (int i : distincts) {
            if (distincts.find(i - 1) == distincts.end()) {
                // i is at the beginning of a consecutive seq, find the length
                int j = i;
                while (distincts.find(j) != distincts.end()) {
                    ++j; // this statement executes only N times throughout the algo
                }
                ans = max(ans, j - i);
            }
        }
        
        return ans;
    }
};
