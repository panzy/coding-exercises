/* 341. Flatten Nested List Iterator
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * --
 * Zhiyong 2021-04-13
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */

// Approach: backtracking.
class NestedIterator {
    // path to the current nested list
    // path[i] = { the nested list, its position among its siblings }
    stack<pair<vector<NestedInteger>*, int>> path;
    int pos; // index of the next integer
public:
    NestedIterator(vector<NestedInteger> &nestedList)
        : pos(0) {
        path.push({&nestedList, 0});
    }
    
    int next() {
        // The pos-th element of the current list is guaranteed to hold an integer,
        // otherwise hasNext() would have returned false.
        // After fetch the next integer, just increase the pos by 1. The hasNext()
        // will take care of the rest.
        return (*path.top().first)[pos++].getInteger();
    }
    
    bool hasNext() {
        if (path.empty()) return false;

        auto&& [lst, idx] = path.top();
        
        if (pos < lst->size()) {
            if ((*lst)[pos].isInteger()) {
                // the happy case
                return true;
            } else {
                // dive deeper to the nested list
                path.push({&(*lst)[pos].getList(), pos});
                pos = 0;
                return hasNext();
            }
        } else {
            // backtrack to the next sibling
            while (path.size()) {
                auto&& [lst, idx] = path.top();
                path.pop();
                
                if (path.empty()) return false; // exhausted
                
                if (idx + 1 < path.top().first->size()) {
                    NestedInteger& sibling = (*path.top().first)[idx + 1];
                    if (sibling.isInteger()) {
                        pos = idx + 1;
                    } else {
                        path.push({&sibling.getList(), idx + 1});
                        pos = 0;
                    }
                    break;
                }
            }

            return hasNext();
        }
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
