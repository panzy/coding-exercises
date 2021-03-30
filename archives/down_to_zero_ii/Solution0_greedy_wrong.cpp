/* Down to Zero II
 * https://www.hackerrank.com/challenges/down-to-zero-ii
 *
 * Approach: greedy (wrong).
 * --
 * Zhiyong 2021-02
 */
#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the downToZero function below.
 */
int downToZero(int n) {
    int ans = 0;
    while (n > 0) {
        int i = sqrt(n);
        bool done = false;
        for (; i > 1; --i) {
            if (n % i == 0) {
                n /= i;
                done = true;
                break;
            }
        }
        if (!done)
            --n;
        ++ans;
    }
    return ans;

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

