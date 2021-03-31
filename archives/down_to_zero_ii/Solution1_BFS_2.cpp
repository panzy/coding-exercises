/* Down to Zero II
 * https://www.hackerrank.com/challenges/down-to-zero-ii
 *
 * Approach: BFS (accepted).
 *
 * Simliar to the previous version, but somehow goes without maintaining the "seen" set.
 *
 * --
 * Zhiyong 2021-03-30
 */
#include <bits/stdc++.h>

using namespace std;

void pushNextStops(queue<pair<int, int>>& q, int n, int dist) {
    for (int i = sqrt(n); i >= 2; --i) {
        if (n % i == 0) {
            int j = n / i;
            q.push({j, dist + 1});
        }
    }
    q.push({n - 1, dist + 1});
}

int downToZero(int n) {
    // BFS
    queue<pair<int, int>> q;

    q.push({n, 0});

    while (q.size()) {
        int val = q.front().first;
        int dist = q.front().second;
        q.pop();

        // To BFS without keeping track of visited neighbour numbers,
        // it's important to search from numbers > 4.
        // XXX It should also be okay to include 4, but changing "val <= 4"
        // to "val < 4" below leads to wrong answers in some cases. Don't know why.
        if (val <= 4) {
            switch (val) {
                case 4:
                case 3:
                    return dist + 3;
                default:
                    return dist + val;
            }
        }

        pushNextStops(q, val, dist);
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

