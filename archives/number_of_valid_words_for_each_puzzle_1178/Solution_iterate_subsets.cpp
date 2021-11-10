//
// 1178. Number of Valid Words for Each Puzzle
// https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
// 
// 10 / 10 test cases passed.	Status: Accepted
// Runtime: 164 ms
// Memory Usage: 47.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      09/11/2021, 22:17:09
// LeetCode submit time: 5 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/584796521//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> findNumOfValidWords(vector<string>& words, vector<string>& puzzles) {
        // (1) We will group the words by their letter sets, which are represented by a bitmap.
        // (2) For each puzzle, instead of iterating over the word groups, we iterate over the
        // subsets of the puzzle bitmap and query the word count of each sub bitmap. The number
        // of subsets is at most 2^7 since the whole set contains at most 7 distinct letters.

        // Group words by their bitmaps.
        // key = bitmap
        // value = number of words with the same bitmap
        unordered_map<int, int> wordCount;

        for (auto&& w : words) {
            ++wordCount[letterBitmap(w)];
        }
        
        vector<int> res;
        res.reserve(puzzles.size());
        
        for (auto&& p : puzzles) {
            int first = 1 << (p[0] - 'a'); // the first letter must be present
            int n = wordCount[first]; // count those words consiting of only the first letter
            int mask = letterBitmap(p.substr(1)); // the rest letters
            
            // How to iterate over subsets
            for (int submask = mask; submask; submask = (submask - 1) & mask) {
                n += wordCount[submask | first];
            }
            
            res.push_back(n);
        }
        
        
        return res;
    }
    
private:
    static int letterBitmap(const string& w) {
        int f = 0;
        for (char c : w) {
            f |= (1 << (c - 'a'));
        }
        return f;
    }
};