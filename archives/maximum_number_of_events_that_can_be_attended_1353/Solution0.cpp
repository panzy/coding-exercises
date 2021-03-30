/* 1353. Maximum Number of Events That Can Be Attended
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * Approach: For each event (ordered by end day asc), try to attend it ASAP.
 *
 * --
 * Zhiyong 2021-03-30
 */

// This solution is inefficient. I didn't even try to submit.
// However it demonstrates the idea before adopting DSU to speed up findAvailableDay().
class Solution {
    bitset<100001> days; // days[i] = is the i-th day available?
    
    int findAvailableDay(int start, int end) {
        for (int d = start; d <= end; ++d) {
            if (!days[d])
                return d;
        }
        return -1;
    }
    
    void markUnavailable(int day) {
        days[day] = true;
    }
public:
    int maxEvents(vector<vector<int>>& events) {
        // sort by end day
        sort(events.begin(), events.end(), [](vector<int>& a, vector<int>& b){
            if (a[1] != b[1])
                return a[1] < b[1];
            else
                return a[0] < b[0];
        });
        
        int ans = 0;

        // For each event (ordered by end day asc), try to attend it ASAP.
        for (auto&& e : events) {
            int d = findAvailableDay(e[0], e[1]);
            if (d != -1) {
                ++ans;
                markUnavailable(d);
            }
        }
        return ans;
    }
};
