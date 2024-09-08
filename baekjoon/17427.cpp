/**
 * 약수의 합 2
 * 제곱근을 바탕으로 나눠서 구하면 된다.
 */

#include <algorithm>
#include <iostream>
#include <cmath>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

ll func_N[1000001];

int main() {
	init();
	ll N;
	cin >> N;
	for (ll i = 1; i <= N; i++) {
		ll sqrt_i = sqrt(i);
		func_N[i] = func_N[i - 1];
		for (ll j = 1; j <= sqrt_i; j++) {
			if (i % j == 0) {
				ll mod_j = i / j;
				if (mod_j != j) func_N[i] += j + mod_j;
				else func_N[i] += j;
			}
		}
	}
	cout << func_N[N] << "\n";
}
