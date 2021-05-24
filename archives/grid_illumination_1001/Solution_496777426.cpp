//
// 1001. Grid Illumination
// https://leetcode.com/problems/grid-illumination/
// Submission detail page: https://leetcode.com/submissions/detail/496777426//
// 
// --
// Created by Zhiyong Pan
// 22/05/2021, 15:11:12
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> gridIllumination(int n, vector<vector<int>>& lamps, vector<vector<int>>& queries) {
        //
        // Core idea: keep track of illuminated rows, cols and diagonals with hash maps.
        //
        
        // rows[i] = how many lamps are illuminating the i-th row (0-based).
        unordered_map<int, int> rows;
        unordered_map<int, int> cols;
        
        // diasA[r] = how many lamps are illuminating the diagonal that intersects the left border at (r, 0),
        // where 0 <= r < 2*n.
        unordered_map<int, int> diasA;
        
        // diasB[c] = how many lamps are illuminating the diagonal that intersects the top border at (0, c),
        // where -n < c < n.
        unordered_map<int, int> diasB;

        // Lamps by position
        // key = (((uint64_t)r << 32) + c)
        unordered_set<uint64_t> lampSet;
        
        for (auto&& pos : lamps) {
            int r = pos[0];
            int c = pos[1];
            auto key = ((uint64_t)r << 32) + c;
            
            if (lampSet.count(key))
                continue;
            
            lampSet.insert(key);

            ++rows[r];
            ++cols[c];
            
            ++diasA[c + r];
            ++diasB[c - r];
        }
        
        //
        // query
        //
        
        vector<int> res;
        res.reserve(queries.size());
        
        for (auto&& pos : queries) {
            int r0 = pos[0];
            int c0 = pos[1];
            
            bool light = (rows.count(r0) && rows[r0] > 0)
                || (cols.count(c0) && cols[c0] > 0);
            
            if (!light) {
                light = (diasA.count(c0 + r0) && diasA[c0 + r0] > 0)
                    || (diasB.count(c0 - r0) && diasB[c0 - r0] > 0);
                    
            }
            
            res.push_back(light ? 1 : 0);
            
            // turn off the neighbours
            for (int dr = -1; dr <= 1; ++dr) {
                for (int dc = -1; dc <= 1; ++dc) {
                    int r = dr + r0;
                    int c = dc + c0;
                    if (0 <= r && r < n && 0 <= c && c < n) {
                        auto key = ((uint64_t)r << 32) + c;
                        if (lampSet.count(key)) {
                            lampSet.erase(key);

                            --rows[r];
                            --cols[c];

                            --diasA[c + r];
                            --diasB[c - r];
                        }
                    }
                }
            }
        }
        
        return res;
    }
};
