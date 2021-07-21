//
// 384. Shuffle an Array
// https://leetcode.com/problems/shuffle-an-array/
// 
// 10 / 10 test cases passed.	Status: Accepted
// Runtime: 116 ms
// Memory Usage: 91.6 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      20/07/2021, 23:26:46
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/525795332/?from=explore&item_id=3820/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    vector<int> _nums;
    std::random_device rd;  //Will be used to obtain a seed for the random number engine
    std::mt19937 gen; //Standard mersenne_twister_engine seeded with rd()
    std::uniform_int_distribution<> distrib;
public:
    Solution(vector<int>& nums)
        : _nums(begin(nums), end(nums)), gen(rd()), distrib(0, nums.size() - 1) {
        //test();
    }
    
    /** Resets the array to its original configuration and return it. */
    vector<int> reset() {
        return _nums;
    }
    
    /** Returns a random shuffling of the array. */
    vector<int> shuffle() {
        int n = _nums.size();
        vector<int> res(n, 0);
        vector<bool> seen(n, false);
        
        // populate the output array with shuffled indices
        for (int i = 0; i < n; ++i) {
            do {
                //res[i] = (int)(rand() * 1.0 / RAND_MAX * (n - 1)); // rand() is not good
                res[i] = distrib(gen);
            } while (seen[res[i]]);
            seen[res[i]] = true;
        }
        
        // map the array items from indices to values 
        for (int i = 0; i < n; ++i) {
            res[i] = _nums[res[i]];
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