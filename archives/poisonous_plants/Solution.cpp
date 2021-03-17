// Poisonous Plants
// https://www.hackerrank.com/challenges/poisonous-plants/problem
//
// There are a number of plants in a garden. Each of the plants has been
// treated with some amount of pesticide. After each day, if any plant has more
// pesticide than the plant on its left, being weaker than the left one, it
// dies.
//
// You are given the initial values of the pesticide in each of the plants.
// Determine the number of days after which no plant dies, i.e. the time after
// which there is no plant with more pesticide content than the plant to its
// left.
//
// --
// Zhiyong
// 2021-03-17


// Simple but TLE; good for demonstrate the idea.
int poisonousPlants(vector<int> p) {
    // d[i] = the day the i-th plant die. zero means never die.
    vector<int> d(p.size(), 0);

    for (int i = 1; i < p.size(); ++i) {
        if (p[i] > p[i - 1]) {
            d[i] = 1; // dies on the first day
        } else {
            int k = i - 1;
            for (; k >= 0 && p[k] >= p[i]; --k) { // WARNING: O(n^2)
                // will not die earlier than [k], if die at all
                d[i] = max(d[i], d[k] + 1);
            }
            if (k == -1) { // no number is less than [i], so [i] will ever die
                d[i] = 0;
            }
        }
    }

    return *max_element(d.begin(), d.end());
}

// Optimized using a stack; accepted.
int poisonousPlants(vector<int> p) {
    // stack element = {p[i], die day}
    // A die day of zero means never.
    vector<pair<int, int>> d;

    // the 1st plant never dies.
    d.push_back({p[0], 0});

    int ans = 0;

    // example:
    // day 0: 20 5 6 15 2 2 17 2 11 5 14 5 10 9 19 12 5
    // day 1: 20 5      2 2    2    5    5    9    12 5
    // day 2: 20 5      2 2    2         5            5
    // day 3: 20 5      2 2    2                      5
    // day 4: 20 5      2 2    2

    for (int i = 1; i < p.size(); ++i) {
        if (p[i] > p[i - 1]) {
            // dies on the first day
            d.push_back({p[i], 1});
            ans = max(ans, 1);
        } else {
            int day = 1; // assume it will eventually die
            while (d.size() && d.back().first >= p[i]) {
                // won't die before the plant at the stack top, if die at all
                day = max(day, d.back().second + 1);
                d.pop_back();
            }
            if (d.empty()) {
                // won't die
                d.push_back({p[i], 0});
            } else {
                // will die
                d.push_back({p[i], day});
                ans = max(ans, day);
            }
        }

        // dump the stack (that's why I prefer vector to stack):
        // cout << "stack: ";
        // for (auto t : d)
        //     cout << t.first << " " << t.second << ", ";
        // cout << endl;
    }

    return ans;
}
