/*
Minimum Average Waiting Time
https://www.hackerrank.com/challenges/minimum-average-waiting-time/problem
*/
#include <cmath>
#include <cstdio>
#include <vector>
#include <set>
#include <queue>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <numeric>
#include <functional>
using namespace std;

////////////////////////////////////////////////////////////////////////////////
// Utils 

template <class T> using min_heap = priority_queue<T, vector<T>, greater<T>>;
template <class T> using max_heap = priority_queue<T, vector<T>, less<T>>;

void set_precision(int n) {
	cout.precision(1);
	cout.setf(std::ios::fixed, std::ios::floatfield);
}

////////////////////////////////////////////////////////////////////////////////
// The solution

struct Person {
	long long when; // when does he arrive?
	long long duration; // how long will his cooking take?

	Person() : when{ 0 }, duration{ 0 } {}
	Person(long w, long d) : when{ w }, duration{ d } {}

	bool operator>(Person b) {
		return duration > b.duration;
	}

	bool operator==(Person b) {
		return when == b.when && duration == b.duration;
	}
};

int main()
{
	int N;
	auto longer = [](Person a, Person b) { return a.duration > b.duration; }; // order people by duration
	priority_queue <Person, vector<Person>, decltype(longer)> waiting(longer); // people who are waiting
	vector<Person> unavailable; // people who haven't arrive
	
	
	cin >> N;

	long long elapsed = 0; // elapsed time
	long long waited = 0;  // total waited time

	for (int i = 0; i < N; ++i) {
		int a, b;
		cin >> a >> b;
		if (a == 0)
			waiting.push(Person{ a, b });
		else
			unavailable.push_back(Person{ a, b });
	}

	// order people by their arriving time, descending.
	sort(unavailable.begin(), unavailable.end(), [](Person p1, Person p2) { return p1.when > p2.when; });

	while (!waiting.empty() || !unavailable.empty()) {
		// Move available people from unavailable queue to waiting queue.
		while (!unavailable.empty() && unavailable.back().when <= elapsed) {
			waiting.push(unavailable.back());
			unavailable.pop_back();
		}

		if (waiting.empty()) {
			// forward the clock to meet the next person.
			elapsed = unavailable.back().when;
		}
		else {
			// cook for the person with minimal duration.
			auto p = waiting.top();
			waiting.pop();
			long long elapsed2 = max(elapsed, p.when) + p.duration;
			waited += elapsed2 - p.when;
			elapsed = elapsed2;
		}
	}

	cout << waited / N << endl;

	return 0;
}
