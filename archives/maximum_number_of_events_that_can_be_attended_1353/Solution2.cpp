/* 1353. Maximum Number of Events That Can Be Attended
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * Approach: 
 *
 * - Greedy. For each day, attend the event that will end first.
 * - Use a priority queue to pick the event with the earliest end date.
 * - Do not add all the events to the queue at once.
 *
 * --
 * Zhiyong 2021-03-30
 */

class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        // Greedy: for each day, attend the event that will end first.
        //
        // So use a priority queue and sort by end date.
        //
        // (Instead of using a pair to save end day and start day, in the heap we save end days only.
        // See the purpose of |eventGroups| below for why.)
        priority_queue<int, vector<int>, greater<int>> pq; // element = event { end day }

        // ... but we don't want to see events that are yet to start sit on the heap.
        // For example, [[1,10], [2,2]]
        // [2,2] ends earlier but on day 1 we have to attend [1,10].
        //
        // To archive that, we add events to the heap incrementally -- a event is added only if it has
        // already started.
        //
        // To quickly retrieve those events for each day, we group the events by start day in advance,
        // keeping the results in a map.
        unordered_map<int, vector<int>> eventGroups; // start day -> end day(s)

        int minDay = 100001, maxDay = 0;

        for (auto&& e : events) {
            if (minDay > e[0]) minDay = e[0];
            if (maxDay < e[1]) maxDay = e[1];
            eventGroups[e[0]].push_back(e[1]);
        }

        int ans = 0; // number of events attended

        for (int d = minDay; d <= maxDay; ++d) {
            // add available events to the heap
            for (int e : eventGroups[d]) {
                pq.push(e);
            }

            // pop the heap until an attendable event has been found
            while (pq.size()) {
                int e = pq.top();
                pq.pop();
                if (d <= e) {
                    ++ans;
                    break; // can only attend one event at any day.
                }
            }
        }

        return ans;
    }
};
