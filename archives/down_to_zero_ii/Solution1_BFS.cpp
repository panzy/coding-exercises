/* Down to Zero II
 * https://www.hackerrank.com/challenges/down-to-zero-ii
 *
 * Approach: BFS (accepted).
 *
 * --
 * Zhiyong 2021-03-30
 */
#include <bits/stdc++.h>

using namespace std;

void pushNextStops(queue<pair<int, int>>& q, unordered_set<int>& seen, int n, int dist) {
    for (int i = sqrt(n); i > 1; --i) {
        if (n % i == 0) {
            int j = n / i;
            if (!seen.count(j))
                q.push({j, dist + 1});
        }
    }
    if (!seen.count(n - 1))
        q.push({n - 1, dist + 1});  
}

int downToZero(int n) {
    // BFS
    queue<pair<int, int>> q;
    unordered_set<int> seen;
    
    q.push({n, 0});
    
    while (q.size()) {
        int val = q.front().first;
        int dist = q.front().second;
        q.pop();
        
        if (val == 0)
            return dist;
        
        if (seen.count(val))
            continue;
        seen.insert(val);
        
        pushNextStops(q, seen, val, dist);
    }
    
    return n;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int q;
    cin >> q;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int q_itr = 0; q_itr < q; q_itr++) {
        int n;
        cin >> n;
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

        int result = downToZero(n);

        fout << result << "\n";
    }

    fout.close();

    return 0;
}

