/* 1354. Construct Target Array With Multiple Sums
 * https://leetcode.com/problems/construct-target-array-with-multiple-sums/
 * --
 * Zhiyong 2021-05-09
 */

class Solution {
public:
    bool isPossible(vector<int>& target) {
        if (target.size() < 2)
            return target[0] == 1;
        
        priority_queue<int> Q;
        uint64_t sum = 0; 
        
        for (int i : target) {
            Q.push(i);
            sum += i;
        }
        
        // Reverse the process until we get all 1's.
        while (Q.top() > 1) {
            int t = Q.top();
            Q.pop();
            
            // Imagine t2 was what t changed from.
            sum -= t;
            // Conceptually, t2 = t - sum,
            // but consider t = 10^9 and sum = 1 ... we don't want to loop for 10^9-1 times.
            int repeats = (t - Q.top() + sum - 1) / sum;
            int t2 = t - repeats * sum;
            sum += t2;
            
            if (t2 < 1 || t2 >= t)
                return false;
            
            Q.push(t2);
        }
        
        return true;
    }
};
