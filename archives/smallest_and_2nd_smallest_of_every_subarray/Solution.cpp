/* AND xor OR (for every subarray, find the 1st and 2nd smallest elements)
 * https://www.hackerrank.com/challenges/and-xor-or/copy-from/205403598
 * --
 * Zhiyong
 * 2021-03-22
 */

int calc(int a, int b) {
    return ((a & b) ^ (a | b)) & (a ^ b);
}

int andXorOr(vector<int> a) {
    int ans = 0;

    // Core tip: for any sub sequence {a, x, b}, if a > x, then a and b will
    // never be the smallest two of any subarray.

    // A stack holding the ascending sub sequence.
    //
    // An important property:
    // new elements have priorities, if pushing them to the back
    // makes the sub seq non-ascending, then old elements are popped.
    vector<int> seq{a[0]};
    
    for (int i = 1; i < a.size(); ++i) {
        // a subarray of length 2 always contains the 1st and 2nd smallest.
        ans = max(ans, calc(a[i - 1], a[i]));
        
        while (seq.size()) {
            // Assertion: array elements between the seq back and [i],
            // if any, are all greater than the two.
            // So, the seq back and [i] must be the 1st and 2nd smallest
            // elements of the shortest subarray containing them.
            int x = seq.back();
            ans = max(ans, calc(x, a[i]));

            // If the seq back > a[i]:
            // (1) we MUST drop it to ensure the seq is ascending after push [i];
            // (2) we CAN drop it because it will never form a pair with [j]
            // where j > i.
            if (seq.back() > a[i])
                seq.pop_back();
            else
                break;
        }

        seq.push_back(a[i]);
    }
    return ans;
}
