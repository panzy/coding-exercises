/* Down to Zero II
 * https://www.hackerrank.com/challenges/down-to-zero-ii
 *
 * Approach: DP (accepted).
 *
 * --
 * Zhiyong 2021-03-30
 */
#include <bits/stdc++.h>

using namespace std;
using ULL = unsigned long long;

const int MAXN = 1000000;
int dp[MAXN + 1]{-1};

/*
 * Complete the downToZero function below.
 */
int downToZero(int N) {
    if (dp[0] == -1) {
        // init dp table
        
        dp[1] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        
        fill(dp + 4, dp + MAXN + 1, -1);
        
        for (int a = 2; a < MAXN; ++a) {
            // Answers for previous numbers are guaranteed.
            assert(dp[a - 1] != -1);

            // dp[a] might have already been updated before when
            // we found p * q = a, but that's not necessarily the final answer.
            if (dp[a] == -1 || dp[a] > 1 + dp[a - 1])
                dp[a] = 1 + dp[a - 1];
            // Now dp[a] is final.

            for (int b = 2; b <= a; ++b) {
                ULL n = (ULL)a * b;
                if (n > MAXN) break;
                if (dp[n] == -1 || dp[n] > 1 + dp[a])
                    dp[n] = 1 + dp[a]; // found a better answer.
            }
        }
    }
    
    return dp[N];
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

