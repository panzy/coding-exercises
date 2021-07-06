//
// 1338. Reduce Array Size to The Half
// https://leetcode.com/problems/reduce-array-size-to-the-half/
// 
// 31 / 31 test cases passed.	Status: Accepted
// Runtime: 112 ms
// Memory Usage: 60 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      06/07/2021, 19:34:37
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/518486241/?from=explore&item_id=3804/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int minSetSize(vector<int>& arr) {
        int maxVal = *max_element(arr.begin(), arr.end());
        vector<int> freqs(maxVal + 1, 0);
        
        for (int v : arr)
            freqs[v]++;

        // arr.length <= 10^5, priority_queue might be overkilling
        sort(freqs.begin(), freqs.end(), greater<int>());

        int removedSetSize = 0;
        int removedArrSize = 0;
        int target = arr.size() / 2;

        for (int i = 0; removedArrSize < target; ++i) {
            removedSetSize++;
            removedArrSize += freqs[i];
        }
        
        return removedSetSize;
    }
};