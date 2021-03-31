/* 435. Non-overlapping Intervals
 * https://leetcode.com/problems/non-overlapping-intervals/
 *
 * This solution is wrong.
 *
 * Consider input
 *   [[0,2],[1,3],[1,3],[2,4],[3,5],[3,5],[4,6]]
 *
 * It's corresponding graph is
 *
 *       1   4
 *      /|\ /|\
 *     0 | 3 | 6
 *      \|/ \|/
 *       2   5  
 *
 * Initially, Node #3 has the maximum degree of (4) but it's
 * actually not the node to be removed.
 *
 * --
 * Zhiyong 2021-03-31
 */

class Solution {
    bool overlap(vector<int>& a, vector<int>& b) {
        return b[1] > a[0] && b[0] < a[1] ||
            a[1] > b[0] && a[0] < b[1];
    }
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();
        unordered_map<int, list<int>> graph; // interval id -> overlapped interval ids
        int numEdges = 0;
        
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (overlap(intervals[i], intervals[j])) {
                    cout << "overlap: " << i << ' ' << j << endl;
                    graph[i].push_back(j);
                    graph[j].push_back(i);
                    ++numEdges;
                }
            }
        }
        
        int ans = 0;
        
        while (numEdges > 0) {
            int maxIdx = -1;
            for (auto&& p : graph) {
                if (maxIdx == -1 || graph[maxIdx].size() < p.second.size())
                    maxIdx = p.first;
            }
            
            cout << "node with max degree: " << maxIdx << ' ' << graph[maxIdx].size() << endl;
            
            numEdges -= graph[maxIdx].size();
            ++ans;
            
            for (int neib : graph[maxIdx]) {
                auto itr = find(graph[neib].begin(), graph[neib].end(), maxIdx);
                graph[neib].erase(itr);
            }
            graph.erase(maxIdx);
        }
        
        return ans;
    }
};
