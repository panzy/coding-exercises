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

// Approach: DFS.
class NestedIterator {
    vector<int> data; // flatten
    int pos;
    
    void dfs(const vector<NestedInteger>& lst) {
        for (auto&& e : lst) {
            if (e.isInteger()) {
                data.push_back(e.getInteger());
            } else {
                dfs(e.getList());
            }
        }
    }
public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        dfs(nestedList); 
        pos = 0;
    }
    
    int next() {
        return data[pos++];
    }
    
    bool hasNext() {
        return pos != data.size();
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
