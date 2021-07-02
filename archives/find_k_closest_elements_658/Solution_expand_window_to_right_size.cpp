//
// 658. Find K Closest Elements
// https://leetcode.com/problems/find-k-closest-elements/
// 
// 63 / 63 test cases passed.	Status: Accepted
// Runtime: 36 ms
// Memory Usage: 31.1 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      02/07/2021, 19:50:19
// LeetCode submit time: 4 hours, 48 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/516451699//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        auto n = arr.size();
        // will expand the inclusive selection range [i+1:j-1] until its size reaches k
        auto j = upper_bound(begin(arr), end(arr), x) - begin(arr), i = j - 1;
        
        while (j - (i + 1) < k) {
            // pick either arr[i] or arr[j]
            if (i >= 0 && (j >= n || arr[j] - x >= x - arr[i])) {
                --i;
            } else {
                ++j;
            }
        }
        
        return vector<int>(arr.begin() + i + 1, arr.begin() + j);
    }
};