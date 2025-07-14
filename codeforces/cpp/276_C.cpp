/**
 * A little and maximum sum
 * 
 * 1. naive -> sort and sort / matching each other - time exceed
 * 2. priority queue -> sort + pq / matching each other
 */

//#include <bits/stdc++.h>
#include <iostream>
#include <queue>
using namespace std;
typedef long long ll;
#define total 200001

ll N[total], range[total];

void init() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
}

int main() {
	ll n, q, tmp;
	cin >> n >> q;
	priority_queue<ll, vector<ll>, greater<ll> > pq;

	for (int i =0; i < n; i++)
	{
		cin >> tmp;
		pq.push(tmp);
	}
	for (int i = 0; i < q; i++)
	{
		ll tmp1, tmp2;
		cin >> tmp1 >> tmp2;
		for (int j = tmp1 - 1; j <= tmp2 - 1; j++)
		{
			range[j]++;
		}
	}
	sort(range, range + n);
	ll ret = 0;
	for (int i = 0; i < n; i++)
	{
		ret += range[i] * pq.top();
		pq.pop();
	}
	cout << ret << "\n";
}
