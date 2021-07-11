//
// 295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/
// 
// 18 / 18 test cases passed.	Status: Accepted
// Runtime: 88 ms
// Memory Usage: 46.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      11/07/2021, 16:42:27
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/520974196/?from=explore&item_id=3810/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class MedianFinder {
    // the middle value is left.top() or right.top() or both.
    priority_queue<int> left;
    priority_queue<int, vector<int>, greater<int>> right; 
public:
    /** initialize your data structure here. */
    MedianFinder() {
    }
    
    void addNum(int num) {
        if (right.empty() || right.top() <= num) {
            right.push(num);
        } else {
            left.push(num);
        }
        
        // warn: there is overflow in (left.size() - right.size() > 1) if left.size() < right.size().
        if (left.size() > right.size() + 1) {
            right.push(left.top());
            left.pop();
        } else if (right.size() > left.size() + 1) {
            left.push(right.top());
            right.pop();
        }
    }
    
    double findMedian() {
        if (left.size() == right.size()) {
            return (left.top() + right.top()) / 2.0;
        } else if (left.size() > right.size()) {
            return left.top();
        } else {
            return right.top();
        }
    }
};
