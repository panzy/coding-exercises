/* 1353. Maximum Number of Events That Can Be Attended
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * Approach: 
 *
 * - For each event (ordered by end day asc), try to attend it ASAP.
 * - Use DSU to quickly find an available date between given start and end.
 *
 * --
 * Zhiyong 2021-03-30
 */

// Disjoint Set Union
class DSU {
private:
	vector<int> parent;
public:
	DSU(int n) {
		parent.resize(n + 1);
		fill(parent.begin(), parent.end(), -1);
	}

	// Finds a node's parent.
	// Returns the node iteself if it has no parent.
	// If it does have parent other than itself, its parent pointer will be updated to the root.
	int find(int x) {
		int p = parent[x];
		while (p != -1 && parent[p] != -1) {
			parent[x] = p = parent[p];
		}
		return p == -1 ? x : p;
	}

	bool connect(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px == py)
			return false; // already connected

        // Important: parent index is never less than child indices.
        // This property is not required by DSU but by this application.
        if (px < py)
            parent[px] = py;
        else
            parent[py] = px;
            
        return true;
	}
};

class Solution {
    int findAvailableDay(DSU& dsu, int start, int end) {
        // The next available day is |start|'s parent.
        int d = dsu.find(start);
        return d <= end ? d : -1;
    }
    
    void markUnavailable(DSU& dsu, int day) {
        // Mark a day unavailable by letting it point to the next available day.
        dsu.connect(day, dsu.find(day + 1));
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
        
        int maxDay = events.back()[1];

        // Init a DSU such that each day points to the next available day (could be itself).
        DSU dsu(maxDay + 1);
        
        int ans = 0;

        // For each event (ordered by end day asc), try to attend it ASAP.
        for (auto&& e : events) {
            int d = findAvailableDay(dsu, e[0], e[1]);
            if (d != -1) {
                ++ans;
                markUnavailable(dsu, d);
            }
        }
        return ans;
    }
};
