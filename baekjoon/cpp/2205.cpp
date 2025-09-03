/**
 * 저울 추 만들기
 * 최선의 결과를 낸다 -> 그리디
 * 큰 수는 조합이 적다. 그러니 큰수부터 처리하자.
 * 시간복잡도는  N**2logN까지 가능
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

ll pb[10001], tin[10001];
set<ll> n2;

int main() {
  init();
  ll N, n = 1;
  cin >> N;
  for (ll i = 2; i <= 20000; i *= 2) {
    n2.insert(i);
  }
  for (ll i = N; i >= 1; i--) {
    for (ll j = N; j >= 1; j--) {
      if (tin[j]) continue;
      if (n2.find(i + j) != n2.end()) {
        pb[i] = j;
        tin[j] = i;
        break;
      }
    }
  }
  for (ll i = 1; i <= N; i++) {
    cout << pb[i] << "\n";
  }
}
