/**
 * Fly me to the Alpha Centauri
 * 먼저, 풀로 이동할 때의 경우를 계산한다.
 * 이 경우의 수를 바탕으로 f(n-1) + f(n) / f(n) + f(n) 등등을 계산한다.
 */

#include <algorithm>
#include <cmath>
#include <iostream>
#include <set>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

int main() {
  init();
  ll T, x, y, ceil_dis, floor_dis;
  double sqrt_dis;
  cin >> T;
  while (T--) {
    cin >> x >> y;
    sqrt_dis = sqrt(y - x);
	ceil_dis = ceil(sqrt_dis);
	floor_dis = floor(sqrt_dis);
	if (ceil_dis * floor_dis < y - x) cout << ceil_dis + ceil_dis - 1 << "\n";
	else cout << floor_dis + ceil_dis - 1 << "\n";
  }
}
