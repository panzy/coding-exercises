class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        vector<int> *a, *b;
        
        if (nums1.size() <= nums2.size()) {
            a = &nums1;
            b = &nums2;
        } else {
            a = &nums2;
            b = &nums1;
        }
        
        sort(a->begin(), a->end());
        sort(b->begin(), b->end());
        
        vector<int> ans;
        
        int freqb = 0; // frequency in b of the last found intersection element 
        for (int i : *a) {
            if (!ans.empty() && ans[ans.size() - 1] == i) {
                if (--freqb > 0)
                    ans.push_back(i);
                continue;
            }
            
            auto lo = lower_bound(b->begin(), b->end(), i);
            if (lo == b->end() || *lo != i) continue;
            auto up = upper_bound(lo, b->end(), i);
            
            freqb = up - lo;
            ans.push_back(i);
        }
        return ans;        
    }
};
