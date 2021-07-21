//
// 384. Shuffle an Array
// https://leetcode.com/problems/shuffle-an-array/
// 
// 10 / 10 test cases passed.	Status: Accepted
// Runtime: 92 ms
// Memory Usage: 90.4 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      20/07/2021, 23:27:50
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/525800543/?from=explore&item_id=3820/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    vector<int> _nums;
    std::random_device rd;  //Will be used to obtain a seed for the random number engine
    std::mt19937 gen; //Standard mersenne_twister_engine seeded with rd()
public:
    Solution(vector<int>& nums)
        : _nums(begin(nums), end(nums)), gen(rd()) {
        //test();
    }
    
    /** Resets the array to its original configuration and return it. */
    vector<int> reset() {
        return _nums;
    }
    
    /** Returns a random shuffling of the array. */
    vector<int> shuffle() {
        vector<int> res(begin(_nums), end(_nums));
        int n = res.size();
        // The Fisher-Yates algorithm: for each i, pick one from [i:n-1] and save it to [i]
        for (int i = 0; i < n; ++i) {
            std::uniform_int_distribution<> distrib(i, n - 1);
            int j = distrib(gen);
            swap(res[i], res[j]);
        }
        return res;
    }
    
    // run shuffle for many times, for each position, each value should appear for roughly the same times.
    void test() {
        unordered_map<int, int> cnt;
        for (int j = 0; j < 10000; ++j) {
            vector<int> res = shuffle();
            ++cnt[res[2]];
        }
        for (auto&& c : cnt)
            cout << c.first << " appears at the end for " << c.second << " times;" << endl;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * vector<int> param_1 = obj->reset();
 * vector<int> param_2 = obj->shuffle();
 */