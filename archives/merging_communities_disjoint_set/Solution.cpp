/*
Merging Communities
https://www.hackerrank.com/challenges/merging-communities/problem

Topic: disjoint set; parent tree.
*/

#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <numeric>
using namespace std;

#define for(i, begin, end) for (auto i = begin; i < end; i++)

int n, q;
int links[100001];
int sizes[100001];

int root_of(int p) {
    int pp = p;
    while (links[pp] != 0 && links[pp] != p) {
        pp = links[pp];
    }
    if (pp != p)
        links[p] = pp;
    return pp;
}

void query(int p) {
    cout << sizes[root_of(p)] << endl;
}

void merge(int p, int q) {
    int proot = root_of(p);
    int qroot = root_of(q);


    if (proot < qroot) {
        links[qroot] = links[q] = proot;
        sizes[proot] += sizes[qroot];
    } else if (proot > qroot) {
        links[proot] = links[p] = qroot;
        sizes[qroot] += sizes[proot];
    }
}

int main() {
    char cmd;
    int arg1, arg2;

    cin >> n >> q;

    fill(links, links + n + 1, 0);
    fill(sizes, sizes + n + 1, 1);

    for (i, 0, q) {
        cin >> cmd >> arg1;
        if (cmd == 'Q') {
            query(arg1);
        } else {
            cin >> arg2;
            merge(arg1, arg2);
        }
    }

    return 0;
}
