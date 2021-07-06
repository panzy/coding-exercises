//
// 1338. Reduce Array Size to The Half
// https://leetcode.com/problems/reduce-array-size-to-the-half/
// 
// 31 / 31 test cases passed.	Status: Accepted
// Runtime: 172 ms
// Memory Usage: 78.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      06/07/2021, 19:27:07
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/518483740/?from=explore&item_id=3804/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int minSetSize(vector<int>& arr) {
        unordered_map<int, int> valFreqs;
        for (int v : arr)
            valFreqs[v]++;

        priority_queue<int> freqs;
        for (auto&& p : valFreqs)
            freqs.push(p.second);
        
        int removedSetSize = 0;
        int removedArrSize = 0;
        int target = arr.size() / 2;

        while (removedArrSize < target) {
            removedSetSize++;
            removedArrSize += freqs.top();
            freqs.pop();
        }
        
        return removedSetSize;
    }
};