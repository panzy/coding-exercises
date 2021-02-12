/*
Find the Running Median
https://www.hackerrank.com/challenges/find-the-running-median/problem
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

////////////////////////////////////////////////////////////////////////////////
// Types

/* A heap class implemented using std heap functions.
   For max heap, pass less<T> as the Compare type, for min heap, greater<T>.  */
template <class T, class Compare>
class heap
{
private:
	Compare comp;
	vector<T> nums;
	unordered_set<T> removedNums;
public:
	void add(T v) {
		nums.push_back(v);
		push_heap(nums.begin(), nums.end(), comp);

		auto itr = find(removedNums.begin(), removedNums.end(), v);
		if (itr != removedNums.end())
			removedNums.erase(itr);
	}

	void remove(T v) {
		removedNums.insert(v);
	}

	T peek() {
		while (removedNums.find(nums.front()) != removedNums.end()) {
			pop_heap(nums.begin(), nums.end(), comp);
			nums.pop_back();
		}
		return nums.front();
	}

	T poll() {
		T v = peek();
		pop_heap(nums.begin(), nums.end(), comp);
		nums.pop_back();
		return v;
	}

	int size() {
		return nums.size() - removedNums.size();
	}
};

template <class T> using min_heap = heap<T, greater<T>>;
template <class T> using max_heap = heap<T, less<T>>;

////////////////////////////////////////////////////////////////////////////////
// Util functions

void set_precision(int n) {
	cout.precision(1);
	cout.setf(std::ios::fixed, std::ios::floatfield);
}

////////////////////////////////////////////////////////////////////////////////
// The solution

int main()
{
	int N;
	max_heap<int> h1;
	min_heap<int> h2;
	float m = 0;
	
	cin >> N;

	for (int i = 0; i < N; ++i) {
		int a;
		cin >> a;

		if (a >= m) {
			if (h2.size() > h1.size()) h1.add(h2.poll());
			h2.add(a);
		}
		else {
			if (h1.size() > h2.size()) h2.add(h1.poll());
			h1.add(a);
		}

		if (h1.size() < h2.size()) {
			m = h2.peek();
		}
		else if (h1.size() > h2.size()) {
			m = h1.peek();
		}
		else {
			m = (h1.peek() + h2.peek()) / 2.0;
		}

		set_precision(1);
		cout << m << endl;
	}

	return 0;
}
