//
// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
// 
// 70 / 70 test cases passed.	Status: Accepted
// Runtime: 136 ms
// Memory Usage: 35.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      06/06/2021, 11:00:42
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/503927807/?from=explore&item_id=3769/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//

// Same algorithm as the previous solution, only slightly different in implementaion.
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        // seqs[i] = j where [i, j] (if i <= j) or [j, i] (if j <= i)
        // is a consecutive elements sequence,
        unordered_map<int, int> seqs;
        unordered_set<int> seen; // helps to ignore duplicates
        int ans = nums.empty() ? 0 : 1;

        for (int i : nums) {
            if (seen.count(i)) {
                continue;
            }
            seen.insert(i);
            
            auto prev = seqs.find(i - 1), next = seqs.find(i + 1);

            // will append i to an existing seq [j, i-1]
            bool append = prev != seqs.end() && prev->second < i;
            // will prepend i to an existing seq [i+1, j]
            bool prepend = next != seqs.end() && next->second > i;
            
            if (append && prepend) {
                // combine [j, i-1] and [i+1, k]
                int j = prev->second, k = next->second;
                if (j != i - 1)
                    seqs.erase(prev);
                if (j != i + 1)
                    seqs.erase(next);
                seqs[j] = k;
                seqs[k] = j;
                ans = max(ans, k - j + 1);
            }
            else if (append) {
                // extend [j, i-1] to [j, i]
                int j = prev->second;
                if (j != i - 1)
                    seqs.erase(prev);
                seqs[i] = j;
                seqs[j] = i;
                ans = max(ans, i - j + 1);
            }
            else if (prepend) {
                // extend [i+1, j] to [i, j]
                int j = next->second;
                if (j != i + 1)
                    seqs.erase(next);
                seqs[i] = j;
                seqs[j] = i;
                ans = max(ans, j - i + 1);
            } else {
                seqs[i] = i;
            }
        }
        
        return ans;
    }
};
