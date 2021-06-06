//
// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
// 
// 70 / 70 test cases passed.	Status: Accepted
// Runtime: 256 ms
// Memory Usage: 38.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      06/06/2021, 10:58:35
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/503922458/?from=explore&item_id=3769/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/* Debug info for input nums = [0,3,7,2,5,8,4,6,0,1]
0: standalone
	0 -> 0
3: standalone
	0 -> 0
	3 -> 3
7: standalone
	0 -> 0
	3 -> 3
	7 -> 7
2: prepend to [3,3]
	0 -> 0
	2 -> 3
	3 -> 2
	7 -> 7
5: standalone
	0 -> 0
	2 -> 3
	3 -> 2
	5 -> 5
	7 -> 7
8: append to [7,7]
	0 -> 0
	2 -> 3
	3 -> 2
	5 -> 5
	7 -> 8
	8 -> 7
4: combine [2,3] and [5,5]
	0 -> 0
	2 -> 5
	5 -> 2
	7 -> 8
	8 -> 7
6: combine [2,5] and [7,8]
	0 -> 0
	2 -> 8
	8 -> 2
0: ignore duplicate
	0 -> 0
	2 -> 8
	8 -> 2
1: combine [0,0] and [2,8]
	0 -> 8
	8 -> 0
*/
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        // seqs[i] = j where [i, j] (if i <= j) or [j, i] (if j <= i)
        // is a consecutive elements sequence,
        map<int, int> seqs;
        unordered_set<int> seen; // helps to ignore duplicates
        int ans = 0;
        bool debug = false;

        auto dump = [&]() {
            for (auto&& p : seqs)
                cout << "\t" << p.first << " -> " << p.second << endl;
        };

        for (int i : nums) {
            if (seen.count(i)) {
                if (debug) cout << i << ": ignore duplicate" << endl;
                continue;
            }
            seen.insert(i);
            
            // will append i to an existing seq [j, i-1]
            bool append = seqs.count(i - 1) > 0 && seqs[i - 1] <= i - 1;
            // will prepend i to an existing seq [i+1, j]
            bool prepend = seqs.count(i + 1) > 0 && seqs[i + 1] >= i + 1;
            
            if (append && prepend) {
                assert(seqs.count(i - 1) == 1);
                assert(seqs.count(i + 1) == 1);
                // combine [j, i-1] and [i+1, k]
                int j = seqs[i - 1], k = seqs[i + 1];
                if (debug) cout << i << ": combine [" << j << "," << (i - 1) << "] and [" << (i + 1) << "," << k << "]" << endl;
                if (j != i - 1)
                    seqs.erase(seqs.find(i - 1));
                if (j != i + 1)
                    seqs.erase(seqs.find(i + 1));
                seqs[j] = k;
                seqs[k] = j;
                ans = max(ans, k - j + 1);
            }
            else if (append) {
                assert(seqs.count(i - 1) == 1);
                // extend [j, i-1] to [j, i]
                int j = seqs[i - 1];
                if (debug) cout << i << ": append to [" << j << "," << (i - 1) << "]" << endl;
                if (j != i - 1)
                    seqs.erase(seqs.find(i - 1));
                seqs[i] = j;
                seqs[j] = i;
                ans = max(ans, i - j + 1);
            }
            else if (prepend) {
                assert(seqs.count(i + 1) == 1);
                // extend [i+1, j] to [i, j]
                int j = seqs[i + 1];
                if (debug) cout << i << ": prepend to [" << (i + 1) << "," << j << "]" << endl;
                if (j != i + 1)
                    seqs.erase(seqs.find(i + 1));
                seqs[i] = j;
                seqs[j] = i;
                ans = max(ans, j - i + 1);
            } else if (seqs.count(i) == 0) {
                if (debug) cout << i << ": standalone" << endl;
                seqs[i] = i;
                ans = max(ans, 1);
            } else {
                if (debug) cout << i << ": impossible" << endl;
            }
            if (debug) dump();
        }
        
        return ans;
    }
};
