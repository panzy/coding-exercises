/* 1353. Maximum Number of Events That Can Be Attended
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * Approach: 
 *
 * - Keep track of the number of living events.
 * - For each day, if there is at last one living event, ++ans.
 * - Once attened, an event will be removed immediately, to prevent this event from
 *   being attended again.
 *
 * This solution fails because it doesn't keep track of the number correctly.
 *
 * --
 * Zhiyong 2021-03-30
 */

class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        int startEvents[100002]{}; // day index -> number of events starting on that day
        int endEvents[100002]{}; // day index -> number of events ending BEFORE that day
        int minDay = 100001, maxDay = 0;
        
        for (auto&& e : events) {
            ++startEvents[e[0]];
            ++endEvents[e[1] + 1];
            
            if (minDay > e[0]) minDay = e[0];
            if (maxDay < e[1]) maxDay = e[1];
        }
        
        int ans = 0; // number of events attended
        int avail = 0; // number of available events on the current day
        int earlyEnds = 0; // number of events that should end early because we have already attended them

        for (int d = minDay; d <= maxDay; ++d) {
            
            // Before subtracting endEvents[d] from |avail|, we should be aware
            // all attended events have been removed from |avail| immediately so they
            // must not be removed again. 
            //
            // Let's adjust endEvents[d] considering earlyEnds.
            if (endEvents[d] <= earlyEnds) {
                earlyEnds -= endEvents[d];
                endEvents[d] = 0;
            } else {
                // XXX Here's a bug: the actual event counted to |earlyEnds| might not end THIS early. Consider:
                // Input:
                // [[1,10],[2,2],[2,2],[2,2],[2,2]]
                // Output:
                // 3
                // Expected:
                // 2

                endEvents[d] -= earlyEnds;
                earlyEnds = 0;
            }
            
            avail += startEvents[d];
            avail -= endEvents[d];
            
            if (avail > 0) {
                ++ans;

                // Prevent this event from being attended again.
                // Note that we don't know exactly which event is attended.
                --avail;

                // Ideally, we would --endEvents[i] if we knew the current event ends on day |i|.
                ++earlyEnds;
            }
        }
        
        return ans;
    }
};
