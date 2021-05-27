//
// 318. Maximum Product of Word Lengths
// https://leetcode.com/problems/maximum-product-of-word-lengths/
// 
// 167 / 167 test cases passed.	Status: Accepted
// Runtime: 24 ms
// Memory Usage: 16.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      27/05/2021, 11:27:16
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/499042248/?from=explore&item_id=3757/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int maxProduct(const vector<string>& words) {
        auto n = words.size();
        
        // Define mask as a word's char bit set.
        // Then for each word, we only care about its mask and length.
        // Then for each mask, we only care about the max word length.
        // For every two masks, if the have no common bits, the product
        // of their word lengths is a potential answer.
        
        unordered_map<int, int> masks;
        int ans = 0;

        for (auto&& w : words) {
            int mask = 0;
            for (char c : w) {
                mask |= 1 << (c - 'a');
            }
            
            // check this word against previous ones
            for (auto&& p : masks) {
                if ((mask & p.first) == 0) {
                    ans = max(ans, (int)w.length() * p.second);
                }
            }

            // record this word for next ones
            masks[mask] = max(masks[mask], (int)w.length());
        }
        

        return ans;
    }
};