/**
 * 수 찾기
 */

#include <algorithm>
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

set<ll> h;

int main() {
  init();
  ll N, tmp, M;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    h.insert(tmp);
  }
  cin >> M;
  for (int i = 0; i < M; i++) {
    cin >> tmp;
	auto iter = h.find(tmp);
    if (iter != h.end())
      cout << "1\n";
    else
      cout << "0\n";
  }
}
