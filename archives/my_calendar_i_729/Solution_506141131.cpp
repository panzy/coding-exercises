//
// 729. My Calendar I
// https://leetcode.com/problems/my-calendar-i/
// 
// 108 / 108 test cases passed.	Status: Accepted
// Runtime: 84 ms
// Memory Usage: 38.1 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/06/2021, 20:17:29
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/506141131/?from=explore&item_id=3774/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//

/*
Approache #1: Linear search.

108 / 108 test cases passed.
Status: Accepted
Runtime: 96 ms
Memory Usage: 37.5 MB
*/
class MyCalendar_trivial {
public:
    bool book(int start, int end) {
        if (start == end) return true;
        for (auto&& e : events) {
            if (start < e.second && end > e.first)
                return false;
            if (end > e.first && start < e.second)
                return false;
        }
        
        events.push_back({start, end});
        return true;
    }
    
private:
    vector<pair<int, int>> events;
};

/*
Approach #2: Search in ordered intervals.

108 / 108 test cases passed.
Status: Accepted
Runtime: 128 ms
Memory Usage: 38.8 MB
*/
class MyCalendar_ordered {
public:
    bool book(int start, int end) {
        if (start == end) return true;
        
        // ingore events that end before this start
        auto p = events.upper_bound(start);
        
        if (p != events.end() && p->second < end)
            return false;
        
        events[end] = start;
        return true;
    }
    
private:
    // events[end] = start
    map<int, int> events;
};

/*
Approach #3: represent the linear space with a tree.
*/

struct Span {
    int start, end;
    Span *left, *right;
    Span(int _start, int _end)
        : start(_start), end(_end), left(nullptr), right(nullptr)
        {}
};

class MyCalendar {
public:
    MyCalendar() : space(nullptr) {}
    
    bool insert(Span*& space, int start, int end) {
        if (!space) {
            space = new Span(start, end);
            return true;
        }
        
        if (end <= space->start) {
            return insert(space->left, start, end);
        } else if (start >= space->end) {
            return insert(space->right, start, end);
        } else {
            return false;
        }
    }
    
    bool book(int start, int end) {
        if (start == end) return true;
        return insert(space, start, end);
    }
    
private:
    Span* space;
};

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar* obj = new MyCalendar();
 * bool param_1 = obj->book(start,end);
 */